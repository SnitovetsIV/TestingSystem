package by.epam.testingsystem.command.user.personal;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.IUserDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * This class implements a pattern command
 * This class save personal data
 *
 * @author Илья
 */
public class SavePersonalDataCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(SavePersonalDataCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.PERSONAL_ACCOUNT_PAGE_PATH);
        String name = request.getParameter(Constants.PARAM_NAME_NAME);
        String surname = request.getParameter(Constants.PARAM_NAME_SURNAME);
        User user = (User) request.getSession().getAttribute(Constants.ATR_USER);
        if (!name.equals(user.getName()) || !surname.equals(user.getSurname())) {
            IUserDao userDao = MysqlDaoFactory.getInstance().getUserDAO();
            if (userDao.changeNameSurname(name, surname, user.getId())) {
                user.setName(name);
                user.setSurname(surname);
                request.setAttribute(Constants.ATR_GOOD_MESSAGE, Constants.PERS_ACC_DATA_SUCCESS_MESS);
            } else {
                LOG.error("Can't change name and surname of user (" + user.getLogin() + ")");
                request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.PERS_ACC_DATA_ERROR_MESS);
            }
        } else {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.PERS_ACC_SAME_DATA_MESS);
        }
        return page;
    }
}
