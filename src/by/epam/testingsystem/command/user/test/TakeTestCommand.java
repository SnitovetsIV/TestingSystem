package by.epam.testingsystem.command.user.test;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.command.user.personal.ChangePasswordCommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.Question;
import by.epam.testingsystem.entity.Test;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This class implements a pattern command
 * This class start take test
 *
 * @author Илья
 */
public class TakeTestCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(ChangePasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        int testId = Integer.parseInt(request.getParameter(Constants.PARAM_NAME_TEST_ID));
        List<Test> tests = (List<Test>) session.getAttribute(Constants.ATR_TESTS);
        Test test = null;
        for (Test t : tests) {
            if (t.getId() == testId) {
                test = t;
                break;
            }
        }
        if (test != null) {
            ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
            List<Question> questions = dao.readQuestionsByTestId(testId);
            test.setQuestions(questions);
            session.setAttribute(Constants.ATR_TEST, test);
            session.setAttribute(Constants.ATR_ALL_COUNT_QUESTIONS, questions.size());
            session.setAttribute(Constants.ATR_CURRENT_QUESTION_NUMBER, 1);
            session.setAttribute(Constants.ATR_CURRENT_QUESTION, questions.get(0));
            session.setAttribute(Constants.ATR_CORRECT_QUESTIONS, 0);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TAKE_TEST_PAGE_PATH);
        } else {
            LOG.error("Can't take test, because can't find it");
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.SHOW_TESTS_PAGE_PATH);
        }
        return page;
    }
}
