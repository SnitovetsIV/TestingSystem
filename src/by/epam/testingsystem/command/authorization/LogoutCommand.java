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
        User user = (User) session.getAttribute(Constants.ATR_USER);
        if (user != null) {
            LOG.info(user.getType() + " " + user.getLogin() + " was logout");
            request.setAttribute(Constants.PARAM_NAME_LOGIN, user.getLogin());
        }
        Locale lang = (Locale) session.getAttribute(Constants.ATR_LOCALE);
        session.invalidate();
        if (lang == null) {
            lang = Locale.getDefault();
        }
        session = request.getSession();
        session.setAttribute(Constants.ATR_LOCALE, lang);
        return page;
    }
}
