package by.epam.testingsystem.command.user;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CancelTakeTestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.PARAM_NAME_CORRECT_QUESTIONS);
        session.removeAttribute(Constants.PARAM_NAME_CURRENT_QUESTION_NUMBER);
        session.removeAttribute(Constants.PARAM_NAME_ALL_COUNT_QUESTIONS);
        session.removeAttribute(Constants.PARAM_NAME_CURRENT_QUESTION);
        session.removeAttribute(Constants.PARAM_NAME_CORRECT_QUESTIONS);
        session.removeAttribute(Constants.PARAM_NAME_TEST);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_PAGE_PATH);
    }
}