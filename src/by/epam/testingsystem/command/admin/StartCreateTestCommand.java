package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class StartCreateTestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
        List<String> subjects = dao.readAllSubjects();
        HttpSession session = request.getSession();
        session.setAttribute(Constants.PARAM_NAME_SUBJECTS, subjects);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHOOSE_TEST_SUBJECT_PAGE_PATH);
    }
}
