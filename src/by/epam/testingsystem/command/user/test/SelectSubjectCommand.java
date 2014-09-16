package by.epam.testingsystem.command.user.test;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.Test;
import by.epam.testingsystem.entity.User;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This class implements a pattern command
 * This class select subject
 *
 * @author Илья
 */
public class SelectSubjectCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        ITestDao testDao = MysqlDaoFactory.getInstance().getTestDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.ATR_USER);
        List<Test> tests = testDao.readTestsBySubject(user.getId(), request.getParameter(Constants.ATR_SUBJECT_NAME));
        session.setAttribute(Constants.ATR_TESTS, tests);
        session.setAttribute(Constants.ATR_START_LIST, 0);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.SHOW_TESTS_PAGE_PATH);
    }
}
