package by.epam.testingsystem.command.user;

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

public class SelectSubjectCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        ITestDao testDao = MysqlDaoFactory.getInstance().getTestDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.PARAM_NAME_USER);
        List<Test> tests = testDao.readTestsBySubject(user.getId(), request.getParameter(Constants.PARAM_NAME_SUBJECT_NAME));
        session.setAttribute(Constants.PARAM_NAME_TESTS, tests);
        session.setAttribute(Constants.PARAM_NAME_START_LIST, 0);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.SHOW_TESTS_PAGE_PATH);
    }
}
