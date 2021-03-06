package by.epam.testingsystem.command.user;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.CommandHelper;
import by.epam.testingsystem.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * This class implements a pattern command
 *
 * @author Илья
 */
public class MainPageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        CommandHelper.clearSession(request.getSession());
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_PAGE_PATH);
    }
}
