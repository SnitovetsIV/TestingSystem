package by.epam.testingsystem.command.user;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SubjectTestsCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
        List<String> subjects = dao.readAllSubjects();
        request.getSession().setAttribute(Constants.PARAM_NAME_SUBJECTS, subjects);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHOOSE_SUBJECT_PAGE_PATH);
    }
}
