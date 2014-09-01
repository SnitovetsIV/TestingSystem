package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.mysql.MysqlDaoFactory;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SaveNewTestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute(Constants.PARAM_NAME_TEST_NAME);
        String description = (String) session.getAttribute(Constants.PARAM_NAME_TEST_DESCRIPTION);
        String[] questionsTd = request.getParameterValues(Constants.PARAM_NAME_QUESTIONS_TEST);
        if (questionsTd != null && questionsTd.length > 0) {
            int[] qestId = new int[questionsTd.length];
            for (int i = 0; i < questionsTd.length; i++) {
                qestId[i] = Integer.parseInt(questionsTd[i]);
            }
            ITestDao dao = MysqlDaoFactory.getInstance().getTestDAO();
            if (dao.createNewTest(name, description, qestId)) {
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
            } else {
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
