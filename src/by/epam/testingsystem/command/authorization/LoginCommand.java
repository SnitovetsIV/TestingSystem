package by.epam.testingsystem.command.authorization;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.IUserDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.BCrypt;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        String login = request.getParameter(Constants.PARAM_NAME_LOGIN);
        String password = request.getParameter(Constants.PARAM_NAME_PASSWORD);
        if ((login != null) && (!login.isEmpty()) && (password != null) && (!password.isEmpty())) {
            login = login.trim();
            IUserDao userDao = MysqlDaoFactory.getInstance().getUserDAO();
            User user = userDao.findUserByLogin(login);
            if (user != null) {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    switch (user.getType()) {
                        case USER:
                            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_PAGE_PATH);
                            request.getSession().setAttribute(Constants.ATR_USER, user);
                            LOG.info("USER " + user.getLogin() + " was login");
                            break;
                        case ADMIN:
                            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
                            request.getSession().setAttribute(Constants.ATR_USER, user);
                            LOG.info("ADMIN " + user.getLogin() + " was login");
                            break;
                        default:
                            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.LOGIN_UNKNOWN_USER_TYPE_MESS);
                    }
                } else {
                    request.setAttribute(Constants.PARAM_NAME_LOGIN, login);
                    request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.LOGIN_BAD_LOGIN_PASS_MESS);
                }
            } else {
                request.setAttribute(Constants.PARAM_NAME_LOGIN, login);
                request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.LOGIN_BAD_LOGIN_PASS_MESS);
            }
        } else {
            request.setAttribute(Constants.PARAM_NAME_LOGIN, login);
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.LOGIN_BAD_LOGIN_PASS_MESS);
        }
        return page;
    }
}
