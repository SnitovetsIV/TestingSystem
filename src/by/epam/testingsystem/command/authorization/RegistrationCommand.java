package by.epam.testingsystem.command.authorization;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.IUserDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.util.BCrypt;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTRATION_PAGE_PATH);
        String login = request.getParameter(Constants.PARAM_NAME_LOGIN);
        String password1 = request.getParameter(Constants.PARAM_NAME_PASSWORD);
        String password2 = request.getParameter(Constants.PARAM_NAME_REPEAT_PASSWORD);
        IUserDao userDao = MysqlDaoFactory.getInstance().getUserDAO();
        if (login == null || login.isEmpty()) {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.REG_LOGIN_EMPTY_MESS);
        } else if (userDao.isLoginExist(login)) {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.REG_LOGIN_EXIST_MESS);
        } else if (password1 == null || password1.isEmpty()) {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.REG_PASS_EMPTY_MESS);
        } else if (!password1.equals(password2)) {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.REG_PASS_NOT_MATCH_MESS);
        } else {
            String hashPassword = BCrypt.hashpw(password1, BCrypt.gensalt());
            if (userDao.createUser(login, hashPassword)) {
                request.setAttribute(Constants.ATR_GOOD_MESSAGE, Constants.LOGIN_REG_SUCCESS_MESS);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            } else {
                LOG.error("Can't create new user.");
                request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.REG_ERROR_MESS);
            }
        }
        return page;
    }
}
