package by.epam.testingsystem.command.admin;

import by.epam.testingsystem.command.ICommand;
import by.epam.testingsystem.util.ConfigurationManager;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChooseQuestionTopicCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
        String topic = request.getParameter(Constants.PARAM_NAME_TOPIC_NAME);
        HttpSession session = request.getSession();
        session.setAttribute(Constants.PARAM_NAME_TOPIC_NAME, topic);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CREATE_QUESTION_PAGE_PATH);
    }
}
