package by.epam.testingsystem.command.admin.question;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This class implements a pattern command
 * This class cancel create question and clear session
 *
 * @author Илья
 */
public class ChooseQuestionSubjectCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String subjectName = request.getParameter(Constants.ATR_SUBJECT_NAME);
        ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
        List<String> topics = dao.readTopicsBySubject(subjectName);
        HttpSession session = request.getSession();
        session.setAttribute(Constants.ATR_TOPICS, topics);
        session.setAttribute(Constants.ATR_SUBJECT_NAME, subjectName);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHOOSE_QUESTION_TOPIC_PAGE_PATH);
    }
}
