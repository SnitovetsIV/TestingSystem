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
        session.removeAttribute(Constants.ATR_SUBJECTS);
        session.removeAttribute(Constants.ATR_QUESTIONS);
        session.removeAttribute(Constants.ATR_TEST_NAME);
        session.removeAttribute(Constants.ATR_TEST_DESCRIPTION);
        session.removeAttribute(Constants.ATR_TOPICS);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
    }

}
