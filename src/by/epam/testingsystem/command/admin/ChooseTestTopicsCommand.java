package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.Question;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChooseTestTopicsCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] topics = request.getParameterValues(Constants.PARAM_NAME_TEST_TOPICS);
        if (topics != null && topics.length > 0) {
            ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
            StringBuilder findTopics = new StringBuilder();
            for (String s : topics) {
                findTopics.append(s).append(",");
            }
            List<Question> questions = dao.readQuestionsByTopics(findTopics.toString());
            request.getSession().setAttribute(Constants.ATR_QUESTIONS, questions);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CREATE_TEST_PAGE_PATH);
        } else {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.CREATE_TEST_NULL_TOPICS_MESS);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHOOSE_TEST_TOPICS_PAGE_PATH);
        }
        return page;
    }
}
