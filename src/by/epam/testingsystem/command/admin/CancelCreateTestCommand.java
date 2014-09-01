package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CancelCreateTestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.PARAM_NAME_SUBJECTS);
        session.removeAttribute(Constants.PARAM_NAME_QUESTIONS_TOPICS);
        session.removeAttribute(Constants.PARAM_NAME_QUESTIONS_TEST);
        session.removeAttribute(Constants.PARAM_NAME_TEST_NAME);
        session.removeAttribute(Constants.PARAM_NAME_TEST_DESCRIPTION);
        session.removeAttribute(Constants.PARAM_NAME_TOPICS);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
    }

}
