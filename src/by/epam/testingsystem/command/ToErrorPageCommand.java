package by.epam.testingsystem.command;

import by.epam.testingsystem.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToErrorPageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
    }
}
