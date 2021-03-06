package by.epam.testingsystem.controller;

import by.epam.testingsystem.command.CommandFactory;
import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class implements the pattern MVC. This is Servlet which handles requests
 *
 * @author Илья
 */
public class TestingSystemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param req  servlet request
     * @param resp servlet response
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page;
        ICommand command = CommandFactory.getInstance().getCommand(req);
        page = command.execute(req);
        if (page == null) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            req.setAttribute(Constants.ATR_GOOD_MESSAGE, Constants.LOGIN_NULL_PAGE_MESS);
        }
        req.getSession().setAttribute(Constants.ATR_PAGE, page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
}
