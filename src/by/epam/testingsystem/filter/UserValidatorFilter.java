package by.epam.testingsystem.filter;

import by.epam.testingsystem.command.CommandType;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.entity.UserType;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter is assigned for controlling access rights
 *
 * @author Илья
 */
public class UserValidatorFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(UserValidatorFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String commandName = servletRequest.getParameter(Constants.PARAM_NAME_COMMAND);
        if (commandName != null) {
            CommandType commandType = CommandType.valueOf(commandName);
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            User user = (User) request.getSession().getAttribute(Constants.ATR_USER);
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
                LOG.error("Access denied to command " + commandName);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
