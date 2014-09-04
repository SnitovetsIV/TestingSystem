package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MainAdminPageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.PARAM_NAME_TOPICS);
        session.removeAttribute(Constants.PARAM_NAME_SUBJECTS);
        session.removeAttribute(Constants.PARAM_NAME_START_LIST);
        session.removeAttribute(Constants.PARAM_NAME_USERS);
        session.removeAttribute(Constants.PARAM_NAME_TOPIC_NAME);
        session.removeAttribute(Constants.PARAM_NAME_SUBJECT_NAME);
        session.removeAttribute(Constants.PARAM_NAME_COUNT_ANSWERS);
        session.removeAttribute(Constants.PARAM_NAME_QUESTION_DESCRIPTION);
        session.removeAttribute(Constants.PARAM_NAME_QUESTIONS_TOPICS);
        session.removeAttribute(Constants.PARAM_NAME_TEST_NAME);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
    }
}
