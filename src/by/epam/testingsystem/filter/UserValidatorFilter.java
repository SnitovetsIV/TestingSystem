package by.epam.testingsystem.filter;

import by.epam.testingsystem.command.CommandType;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.entity.UserType;
import by.epam.testingsystem.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserValidatorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String commandName = servletRequest.getParameter(Constants.PARAM_NAME_COMMAND);
        if (commandName != null) {
            CommandType commandType = CommandType.valueOf(commandName);
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            User user = (User) request.getSession().getAttribute(Constants.PARAM_NAME_USER);
            boolean result = true;
            switch (commandType.getUserType()) {
                case ADMIN:
                    if (user == null || !UserType.ADMIN.equals(user.getType())) {
                        result = false;
                    }
                    break;
                case USER:
                    if (user == null || !UserType.USER.equals(user.getType())) {
                        result = false;
                    }
                    break;
                case ALL:
                    break;
            }
            if (result) {
                request.setAttribute(Constants.PARAM_NAME_COMMAND, commandName);
            } else {
                request.setAttribute(Constants.PARAM_NAME_COMMAND, Constants.COMMAND_TO_ERROR_PAGE);
                request.setAttribute(Constants.ATR_ERROR_MESSAGE, "Access denied");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}