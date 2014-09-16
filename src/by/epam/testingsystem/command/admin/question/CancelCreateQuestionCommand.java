package by.epam.testingsystem.command.admin.question;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class implements a pattern command
 * This class cancel create question and clear session
 *
 * @author Илья
 */
public class CancelCreateQuestionCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.ATR_SUBJECTS);
        session.removeAttribute(Constants.ATR_TOPIC_NAME);
        session.removeAttribute(Constants.ATR_SUBJECT_NAME);
        session.removeAttribute(Constants.PARAM_NAME_COUNT_ANSWERS);
        session.removeAttribute(Constants.ATR_QUESTION_DESCRIPTION);
        session.removeAttribute(Constants.ATR_TOPICS);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
    }
}
