package by.epam.testingsystem.command.admin.question;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * This class implements a pattern command
 * This class choose question topic
 *
 * @author Илья
 */
public class ChooseQuestionTopicCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String topic = request.getParameter(Constants.ATR_TOPIC_NAME);
        request.getSession().setAttribute(Constants.ATR_TOPIC_NAME, topic);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CREATE_QUESTION_PAGE_PATH);
    }
}
