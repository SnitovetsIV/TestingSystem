package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChooseTestSubjectCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String subjectName = request.getParameter(Constants.ATR_SUBJECT_NAME);
        ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
        List<String> topics = dao.readTopicsBySubject(subjectName);
        request.getSession().setAttribute(Constants.ATR_TOPICS, topics);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHOOSE_TEST_TOPICS_PAGE_PATH);
    }

}
