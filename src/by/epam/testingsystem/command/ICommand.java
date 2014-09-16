package by.epam.testingsystem.command;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {

    /**
     * @param request
     * @return relative path to the next page
     */
    public String execute(HttpServletRequest request);


}
