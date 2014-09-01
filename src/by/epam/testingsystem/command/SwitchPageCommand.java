package by.epam.testingsystem.command;

import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SwitchPageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter(Constants.PARAM_NAME_PAGE);
        HttpSession session = request.getSession();
        int numberPage = Integer.parseInt(request.getParameter(Constants.PARAM_NAME_START_LIST));
        session.setAttribute(Constants.PARAM_NAME_START_LIST, numberPage);
        return page;
    }
}
