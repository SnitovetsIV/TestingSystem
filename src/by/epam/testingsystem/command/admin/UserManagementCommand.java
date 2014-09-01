package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.IUserDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserManagementCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        IUserDao dao = MysqlDaoFactory.getInstance().getUserDAO();
        List<User> users = dao.findAllUsers();
        HttpSession session = request.getSession();
        session.setAttribute(Constants.PARAM_NAME_USERS, users);
        session.setAttribute(Constants.PARAM_NAME_START_LIST, 0);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_MANAGEMENT_PAGE_PATH);
    }
}
