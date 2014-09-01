package by.epam.testingsystem.command.user;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.IUserDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.BCrypt;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.PERSONAL_ACCOUNT_PAGE_PATH);
        String oldPassword = request.getParameter(Constants.PARAM_NAME_OLD_PASSWORD);
        String password1 = request.getParameter(Constants.PARAM_NAME_PASSWORD);
        String password2 = request.getParameter(Constants.PARAM_NAME_REPEAT_PASSWORD);
        User user = (User) request.getSession().getAttribute(Constants.PARAM_NAME_USER);
        if (oldPassword == null || oldPassword.isEmpty()) {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.PERS_ACC_PASS_EMPTY_MESS);
        } else if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.PERS_ACC_WRONG_PASS_MESS);
        } else if (password1 == null || password1.isEmpty()) {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.PERS_ACC_PASS_EMPTY_MESS);
        } else if (!password1.equals(password2)) {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.PERS_ACC_PASS_NOT_MATCH_MESS);
        } else if (password1.equals(oldPassword)) {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.PERS_ACC_SAME_PASSWORD_MESS);
        } else {
            String hashPassword = BCrypt.hashpw(password1, BCrypt.gensalt());
            IUserDao userDao = MysqlDaoFactory.getInstance().getUserDAO();
            if (userDao.changePassword(hashPassword, user.getId())) {
                user.setPassword(hashPassword);
                request.setAttribute(Constants.ATR_GOOD_MESSAGE, Constants.PERS_ACC_PASS_SUCCESS_MESS);
            } else {
                request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.PERS_ACC_PASS_ERROR_MESS);
            }
        }
        return page;
    }
}
