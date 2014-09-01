package by.epam.testingsystem.command.authorization;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LogoutCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.PARAM_NAME_USER);
        if (user != null) {
            LOG.info(user.getType() + " " + user.getLogin() + " was logout");
        }
        Locale lang = (Locale) session.getAttribute(Constants.PARAM_NAME_LOCALE);
        session.invalidate();
        if (lang == null) {
            lang = Locale.getDefault();
        }
        request.getSession().setAttribute(Constants.PARAM_NAME_LOCALE, lang);
        return page;
    }
}
