package by.epam.testingsystem.command.admin.test;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class implements a pattern command
 * This class start create test
 *
 * @author Илья
 */
public class StartCreateTestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
        List<String> subjects = dao.readAllSubjects();
        request.getSession().setAttribute(Constants.ATR_SUBJECTS, subjects);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHOOSE_TEST_SUBJECT_PAGE_PATH);
    }
}
