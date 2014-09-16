package by.epam.testingsystem.command.user.test;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class implements a pattern command
 * This class clear session and cancel take test
 *
 * @author Илья
 */
public class CancelTakeTestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.ATR_CORRECT_QUESTIONS);
        session.removeAttribute(Constants.ATR_CURRENT_QUESTION_NUMBER);
        session.removeAttribute(Constants.ATR_ALL_COUNT_QUESTIONS);
        session.removeAttribute(Constants.ATR_CURRENT_QUESTION);
        session.removeAttribute(Constants.ATR_TEST);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_PAGE_PATH);
    }
}
