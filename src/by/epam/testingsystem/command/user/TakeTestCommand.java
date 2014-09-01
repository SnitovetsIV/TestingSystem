package by.epam.testingsystem.command.user;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.entity.Question;
import by.epam.testingsystem.entity.Test;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TakeTestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        int testId = Integer.parseInt(request.getParameter(Constants.PARAM_NAME_TEST_ID));
        List<Test> tests = (List<Test>) session.getAttribute(Constants.PARAM_NAME_TESTS);
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
            session.setAttribute(Constants.PARAM_NAME_TEST, test);
            session.setAttribute(Constants.PARAM_NAME_ALL_COUNT_QUESTIONS, questions.size());
            session.setAttribute(Constants.PARAM_NAME_CURRENT_QUESTION_NUMBER, 1);
            session.setAttribute(Constants.PARAM_NAME_CURRENT_QUESTION, questions.get(0));
            session.setAttribute(Constants.PARAM_NAME_CORRECT_QUESTIONS, 0);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TAKE_TEST_PAGE_PATH);
        } else {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.SHOW_TESTS_PAGE_PATH);
        }
        return page;
    }
}
