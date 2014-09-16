package by.epam.testingsystem.command;

import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * This class implements a pattern command
 * This class change language of pages
 *
 * @author Илья
 */
public class ChangeLanguageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String lang = request.getParameter(Constants.PARAM_NAME_LANG);
        Locale locale = new Locale(lang);
        session.setAttribute(Constants.ATR_LOCALE, locale);
        String page = (String) session.getAttribute(Constants.ATR_PAGE);
        if (page == null) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
        return page;
    }
}
