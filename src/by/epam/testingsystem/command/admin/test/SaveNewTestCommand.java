package by.epam.testingsystem.command.admin.test;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.util.CommandHelper;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class implements a pattern command
 * This class save new test
 *
 * @author Илья
 */
public class SaveNewTestCommand implements ICommand {

    private static final Logger LOG = Logger.getLogger(SaveNewTestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute(Constants.ATR_TEST_NAME);
        String description = (String) session.getAttribute(Constants.ATR_TEST_DESCRIPTION);
        description = CommandHelper.replaceAllHtmlSpecialCharacters(description);
        String[] questionsTd = request.getParameterValues(Constants.PARAM_NAME_TEST_QUESTIONS);
        if (questionsTd != null && questionsTd.length > 0) {
            int[] qestId = new int[questionsTd.length];
            for (int i = 0; i < questionsTd.length; i++) {
                qestId[i] = Integer.parseInt(questionsTd[i]);
            }
            ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
            if (dao.createNewTest(name, description, qestId)) {
                LOG.info("Test (" + name + ") was created.");
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
                CommandHelper.clearSession(session);
                request.setAttribute(Constants.ATR_GOOD_MESSAGE, Constants.CREATE_TEST_SUCCESS_MESS);
            } else {
                LOG.error("Can't create new test.");
                request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.CREATE_TEST_ERROR_MESS);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADD_QUESTIONS_PAGE_PATH);
            }
        } else {
            request.setAttribute(Constants.ATR_BAD_MESSAGE, Constants.CREATE_TEST_NULL_QUESTIONS_MESS);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADD_QUESTIONS_PAGE_PATH);
        }
        return page;
    }
}
