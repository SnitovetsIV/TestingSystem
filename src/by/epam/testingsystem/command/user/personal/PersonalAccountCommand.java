package by.epam.testingsystem.command.user.personal;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * This class implements a pattern command
 *
 * @author Илья
 */
public class PersonalAccountCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PERSONAL_ACCOUNT_PAGE_PATH);
    }
}
