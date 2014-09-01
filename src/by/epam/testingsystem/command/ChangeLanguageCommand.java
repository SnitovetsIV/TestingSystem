package by.epam.testingsystem.command;

import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ChangeLanguageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String lang = request.getParameter(Constants.PARAM_NAME_LANG);
        Locale locale = new Locale(lang);
        request.getSession().setAttribute(Constants.PARAM_NAME_LOCALE, locale);
        return request.getParameter(Constants.PARAM_NAME_PAGE);
    }
}
