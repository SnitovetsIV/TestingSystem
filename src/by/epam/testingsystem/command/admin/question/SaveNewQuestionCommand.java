package by.epam.testingsystem.command.admin.question;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.Answer;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a pattern command
 * This class save new question
 *
 * @author Илья
 */
public class SaveNewQuestionCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(SaveNewQuestionCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String[] correctAnswersStr = request.getParameterValues(Constants.PARAM_NAME_CORRECT_ANSWERS);
        if (correctAnswersStr != null) {
            String description = (String) session.getAttribute(Constants.ATR_QUESTION_DESCRIPTION);
            String topic = (String) session.getAttribute(Constants.ATR_TOPIC_NAME);
            String[] answers = request.getParameterValues(Constants.PARAM_NAME_ANSWERS);
            int[] correctAnswers = new int[correctAnswersStr.length];
            for (int i = 0; i < correctAnswersStr.length; i++) {
                correctAnswers[i] = Integer.parseInt(correctAnswersStr[i]);
            }
            List<Answer> answerList = new ArrayList<>();
            for (int i = 0; i < answers.length; i++) {
                Answer answer = new Answer();
                String descriptionAnswer = answers[i].trim();
                descriptionAnswer = descriptionAnswer.replaceAll("<", "&lt;");
                descriptionAnswer = descriptionAnswer.replaceAll(">", "&gt;");
                answer.setDescription(descriptionAnswer);
                boolean correct = false;
                for (int numCorrect : correctAnswers) {
                    if (numCorrect == i + 1) {
                        correct = true;
                        break;
                    }
                }
                answer.setCorrect(correct);
                answerList.add(answer);
            }
            ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
            if (dao.createNewQuestion(description, topic, answerList)) {
                LOG.info("Question by the topic (" + topic + ") was created.");
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
                request.setAttribute(Constants.ATR_GOOD_MESSAGE, Constants.CREATE_QUESTION_SUCCESS_MESS);
            } else {
                LOG.error("Can't create new question");
                request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.CREATE_QUESTION_ERROR_MESS);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADD_ANSWERS_PAGE_PATH);
            }
        } else {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.CREATE_QUESTION_NULL_ANSWER_MESS);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADD_ANSWERS_PAGE_PATH);
        }
        return page;
    }
}
