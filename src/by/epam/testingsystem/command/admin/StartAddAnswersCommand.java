package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StartAddAnswersCommand implements ICommand {

    public static final int MIN_COUNT_ANSWERS = 2;
    public static final int MAX_COUNT_ANSWERS = 10;

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADD_ANSWERS_PAGE_PATH);
        String description = request.getParameter(Constants.PARAM_NAME_QUESTION_DESCRIPTION);
        int countAnswers = Integer.parseInt(request.getParameter(Constants.PARAM_NAME_COUNT_ANSWERS));
        if ((description != null) && (!description.isEmpty())) {
            if ((MIN_COUNT_ANSWERS <= countAnswers) && (countAnswers <= MAX_COUNT_ANSWERS)) {
                HttpSession session = request.getSession();
                session.setAttribute(Constants.PARAM_NAME_COUNT_ANSWERS, countAnswers);
                session.setAttribute(Constants.PARAM_NAME_QUESTION_DESCRIPTION, description);
            } else {
                request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.CREATE_QUESTION_WRONG_COUNT_ANSWERS_MESS);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CREATE_QUESTION_PAGE_PATH);
            }
        } else {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CREATE_QUESTION_PAGE_PATH);
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.CREATE_QUESTION_NULL_DESCRIPTION_MESS);
        }
        return page;
    }
}
