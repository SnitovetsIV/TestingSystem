package by.epam.testingsystem.command;

import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class implements a pattern command
 * This class switch page of big list
 *
 * @author Илья
 */
public class SwitchPageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(Constants.ATR_PAGE);
        int numberPage = Integer.parseInt(request.getParameter(Constants.ATR_START_LIST));
        session.setAttribute(Constants.ATR_START_LIST, numberPage);
        return page;
    }
}
