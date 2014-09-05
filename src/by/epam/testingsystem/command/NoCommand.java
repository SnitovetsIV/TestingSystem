package by.epam.testingsystem.command;

import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        if (user != null) {
            switch (user.getType()) {
                case ADMIN:
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
                    break;
                case USER:
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_PAGE_PATH);
                    break;
                default:
                    session.removeAttribute(Constants.ATR_USER);
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
                    request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.LOGIN_UNKNOWN_USER_TYPE_MESS);
            }
        } else {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
        return page;
    }
}
