package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StartAddQuestionsCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String testName = request.getParameter(Constants.PARAM_NAME_TEST_NAME);
        String page;
        if ((testName != null) && (!testName.isEmpty())) {
            session.setAttribute(Constants.PARAM_NAME_TEST_NAME, testName);
            session.setAttribute(Constants.PARAM_NAME_TEST_DESCRIPTION, request.getParameter(Constants.PARAM_NAME_TEST_DESCRIPTION));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADD_QUESTIONS_PAGE_PATH);
        } else {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CREATE_TEST_PAGE_PATH);
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.CREATE_TEST_NULL_NAME_MESS);
        }
        return page;
    }
}
