package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.IUserDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ClearUserStatCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(ClearUserStatCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        IUserDao dao = MysqlDaoFactory.getInstance().getUserDAO();
        int userId = Integer.parseInt(request.getParameter(Constants.PARAM_NAME_USER_ID));
        if (dao.clearUserTestStat(userId)) {
            HttpSession session = request.getSession();
            List<User> users = (List<User>) session.getAttribute(Constants.ATR_USERS);
            for (User user : users) {
                if (user.getId() == userId) {
                    user.setCountCompletedTests(0);
                    user.setStatistic(0);
                    break;
                }
            }
        } else {
            LOG.error("Can't clearing a user statistic.");
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.ADMIN_ERROR_CLEAR_STAT);
        }
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_MANAGEMENT_PAGE_PATH);
    }
}
