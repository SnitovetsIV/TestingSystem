package by.epam.testingsystem.command.admin.test;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.CommandHelper;
import by.epam.testingsystem.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * This class implements a pattern command
 * This class cancel create test and clear session
 *
 * @author Илья
 */
public class CancelCreateTestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        CommandHelper.clearSession(request.getSession());
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
    }

}
