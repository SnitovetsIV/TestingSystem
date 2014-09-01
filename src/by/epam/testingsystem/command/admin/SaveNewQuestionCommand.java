package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.Answer;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SaveNewQuestionCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String[] correctAnswersStr = request.getParameterValues(Constants.PARAM_NAME_CORRECT_ANSWERS);
        if (correctAnswersStr != null) {
            String description = (String) session.getAttribute(Constants.PARAM_NAME_QUESTION_DESCRIPTION);
            String topic = (String) session.getAttribute(Constants.PARAM_NAME_TOPIC_NAME);
            String[] answers = request.getParameterValues(Constants.PARAM_NAME_ANSWERS);
            int[] correctAnswers = new int[correctAnswersStr.length];
            for (int i = 0; i < correctAnswersStr.length; i++) {
                correctAnswers[i] = Integer.parseInt(correctAnswersStr[i]);
            }
            List<Answer> answerList = new ArrayList<>();
            for (int i = 0; i < answers.length; i++) {
                Answer answer = new Answer();
                answer.setDescription(answers[i].trim());
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
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
            } else {
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
