package by.epam.testingsystem.command.user.test;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.Question;
import by.epam.testingsystem.entity.Test;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.CommandHelper;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class implements a pattern command
 * This class answer question
 *
 * @author Илья
 */
public class AnswerQuestionCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(AnswerQuestionCommand.class);
    private static final int CONST_TRANSFER_PERCENT = 100;
    private static final String PERCENT_STRING = "%";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] answersString = request.getParameterValues(Constants.PARAM_NAME_ANSWERS);
        if (answersString != null) {
            HttpSession session = request.getSession();
            int currentQuestionNumber = (int) session.getAttribute(Constants.ATR_CURRENT_QUESTION_NUMBER);
            int allCountQuestions = (int) session.getAttribute(Constants.ATR_ALL_COUNT_QUESTIONS);
            Question currentQuestion = (Question) session.getAttribute(Constants.ATR_CURRENT_QUESTION);
            int correctQuestions = (int) session.getAttribute(Constants.ATR_CORRECT_QUESTIONS);
            int[] answers = new int[answersString.length];
            Test test = (Test) session.getAttribute(Constants.ATR_TEST);
            for (int i = 0; i < answersString.length; i++) {
                answers[i] = Integer.parseInt(answersString[i]);
            }
            if (currentQuestion.isCorrectAnswers(answers)) {
                correctQuestions++;
                session.setAttribute(Constants.ATR_CORRECT_QUESTIONS, correctQuestions);
            }
            if (currentQuestionNumber == allCountQuestions) {
                int result = correctQuestions * CONST_TRANSFER_PERCENT / allCountQuestions;
                ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
                User user = (User) session.getAttribute(Constants.ATR_USER);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_PAGE_PATH);
                CommandHelper.clearSession(session);
                if (dao.saveUserCompleteTest(user.getId(), test.getId(), result)) {
                    request.setAttribute(Constants.PARAM_NAME_RESULT_TEST, result + PERCENT_STRING);
                    LOG.info("Test (" + test.getName() + ") was completed by user (" + user.getLogin() + ") on " + result + "%.");
                } else {
                    LOG.error("Can't save user test result.");
                    request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.USER_ERROR_SAVE_RESULT_MESS);
                }
            } else {
                session.setAttribute(Constants.ATR_CURRENT_QUESTION_NUMBER, ++currentQuestionNumber);
                Question nextQuestion = test.getQuestions().get(currentQuestionNumber - 1);
                session.setAttribute(Constants.ATR_CURRENT_QUESTION, nextQuestion);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TAKE_TEST_PAGE_PATH);
            }
        } else {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TAKE_TEST_PAGE_PATH);
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.USER_NULL_ANSWERS_MESS);
        }
        return page;
    }
}
