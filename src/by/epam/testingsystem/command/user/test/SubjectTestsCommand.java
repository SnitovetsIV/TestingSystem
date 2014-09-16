package by.epam.testingsystem.command.user.test;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class implements a pattern command
 * This class show subject tests
 *
 * @author Илья
 */
public class SubjectTestsCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
        List<String> subjects = dao.readAllSubjects();
        request.getSession().setAttribute(Constants.ATR_SUBJECTS, subjects);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHOOSE_SUBJECT_PAGE_PATH);
    }
}
