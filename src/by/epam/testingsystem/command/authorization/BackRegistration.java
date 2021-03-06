package by.epam.testingsystem.command.authorization;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * This class implements a pattern command
 * This class cancel registration
 *
 * @author Илья
 */
public class BackRegistration implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
    }
}
