package by.epam.testingsystem.command.user;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.Question;
import by.epam.testingsystem.entity.Test;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AnswerQuestionCommand implements ICommand {

    public static final int CONST_TRANSFER_PERCENT = 100;
    public static final String PERCENT_STRING = "%";

    @Override
    public String execute(HttpServletRequest request) {
        String[] answersString = request.getParameterValues(Constants.PARAM_NAME_ANSWERS);
        String page;
        if (answersString != null) {
            HttpSession session = request.getSession();
            int currentQuestionNumber = (int) session.getAttribute(Constants.PARAM_NAME_CURRENT_QUESTION_NUMBER);
            int allCountQuestions = (int) session.getAttribute(Constants.PARAM_NAME_ALL_COUNT_QUESTIONS);
            Question currentQuestion = (Question) session.getAttribute(Constants.PARAM_NAME_CURRENT_QUESTION);
            int correctQuestions = (int) session.getAttribute(Constants.PARAM_NAME_CORRECT_QUESTIONS);
            int[] answers = new int[answersString.length];
            Test test = (Test) session.getAttribute(Constants.PARAM_NAME_TEST);
            for (int i = 0; i < answersString.length; i++) {
                answers[i] = Integer.parseInt(answersString[i]);
            }
            if (currentQuestion.isCorrectAnswers(answers)) {
                correctQuestions++;
            }
            if (currentQuestionNumber == allCountQuestions) {
                int result = correctQuestions * CONST_TRANSFER_PERCENT / allCountQuestions;
                ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
                User user = (User) session.getAttribute(Constants.PARAM_NAME_USER);
                if (dao.saveUserCompleteTest(user.getId(), test.getId(), result)) {
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_PAGE_PATH);
                    request.setAttribute(Constants.PARAM_NAME_RESULT_TEST, result + PERCENT_STRING);
                } else {
                    request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.USER_ERROR_SAVE_RESULT_MESS);
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_PAGE_PATH);
                }
            } else {
                session.setAttribute(Constants.PARAM_NAME_CORRECT_QUESTIONS, correctQuestions);
                session.setAttribute(Constants.PARAM_NAME_CURRENT_QUESTION_NUMBER, ++currentQuestionNumber);
                Question nextQuestion = test.getQuestions().get(currentQuestionNumber - 1);
                session.setAttribute(Constants.PARAM_NAME_CURRENT_QUESTION, nextQuestion);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TAKE_TEST_PAGE_PATH);
            }
        } else {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TAKE_TEST_PAGE_PATH);
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.USER_NULL_ANSWERS_MESS);
        }
        return page;
    }
}
