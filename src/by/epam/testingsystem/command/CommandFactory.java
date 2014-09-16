package by.epam.testingsystem.command;

import by.epam.testingsystem.command.admin.MainAdminPageCommand;
import by.epam.testingsystem.command.admin.management.ClearUserStatCommand;
import by.epam.testingsystem.command.admin.management.UserManagementCommand;
import by.epam.testingsystem.command.admin.question.*;
import by.epam.testingsystem.command.admin.test.*;
import by.epam.testingsystem.command.authorization.*;
import by.epam.testingsystem.command.user.MainPageCommand;
import by.epam.testingsystem.command.user.personal.ChangePasswordCommand;
import by.epam.testingsystem.command.user.personal.PersonalAccountCommand;
import by.epam.testingsystem.command.user.personal.SavePersonalDataCommand;
import by.epam.testingsystem.command.user.test.*;
import by.epam.testingsystem.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * This is class which implements a pattern factory
 *
 * @author Илья
 * @see by.epam.testingsystem.command.ICommand
 * @see by.epam.testingsystem.command.CommandType
 */
public class CommandFactory {

    private final HashMap<CommandType, ICommand> allCommands;

    public CommandFactory() {
        allCommands = new HashMap<>();

        //Commands for all
        allCommands.put(CommandType.RETURN_FROM_ERROR, new NoCommand());
        allCommands.put(CommandType.TO_ERROR_PAGE, new ToErrorPageCommand());
        allCommands.put(CommandType.NO_COMMAND, new NoCommand());
        allCommands.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        allCommands.put(CommandType.LOGOUT, new LogoutCommand());
        allCommands.put(CommandType.SWITCH_PAGE, new SwitchPageCommand());

        //Commands for registration
        allCommands.put(CommandType.LOGIN, new LoginCommand());
        allCommands.put(CommandType.START_REGISTRATION, new StartRegistrationCommand());
        allCommands.put(CommandType.REGISTRATION, new RegistrationCommand());
        allCommands.put(CommandType.BACK_REGISTRATION, new BackRegistration());

        //Commands for user
        allCommands.put(CommandType.PERSONAL_ACCOUNT, new PersonalAccountCommand());
        allCommands.put(CommandType.CHANGE_PASSWORD, new ChangePasswordCommand());
        allCommands.put(CommandType.SAVE_PERSONAL_DATA, new SavePersonalDataCommand());
        allCommands.put(CommandType.ALL_TESTS, new AllTestsCommand());
        allCommands.put(CommandType.MAIN_PAGE, new MainPageCommand());
        allCommands.put(CommandType.TAKE_TEST, new TakeTestCommand());
        allCommands.put(CommandType.COMPLETED_TESTS, new CompletedTestsCommand());
        allCommands.put(CommandType.SELECT_SUBJECT, new SelectSubjectCommand());
        allCommands.put(CommandType.SUBJECT_TESTS, new SubjectTestsCommand());
        allCommands.put(CommandType.ANSWER_QUESTION, new AnswerQuestionCommand());
        allCommands.put(CommandType.CANCEL_TAKE_TEST, new CancelTakeTestCommand());

        //Commands for admin
        allCommands.put(CommandType.MAIN_ADMIN_PAGE, new MainAdminPageCommand());
        allCommands.put(CommandType.START_CREATE_TEST, new StartCreateTestCommand());
        allCommands.put(CommandType.CHOOSE_SUBJECT, new ChooseTestSubjectCommand());
        allCommands.put(CommandType.CHOOSE_TOPICS, new ChooseTestTopicsCommand());
        allCommands.put(CommandType.CANCEL_CREATE_TEST, new CancelCreateTestCommand());
        allCommands.put(CommandType.START_ADD_QUESTIONS, new StartAddQuestionsCommand());
        allCommands.put(CommandType.SAVE_NEW_TEST, new SaveNewTestCommand());
        allCommands.put(CommandType.CHOOSE_QUESTION_SUBJECT, new ChooseQuestionSubjectCommand());
        allCommands.put(CommandType.CHOOSE_QUESTION_TOPIC, new ChooseQuestionTopicCommand());
        allCommands.put(CommandType.START_CREATE_QUESTION, new StartCreateQuestionCommand());
        allCommands.put(CommandType.CANCEL_CREATE_QUESTION, new CancelCreateQuestionCommand());
        allCommands.put(CommandType.START_ADD_ANSWERS, new StartAddAnswersCommand());
        allCommands.put(CommandType.SAVE_NEW_QUESTION, new SaveNewQuestionCommand());
        allCommands.put(CommandType.USER_MANAGEMENT, new UserManagementCommand());
        allCommands.put(CommandType.CLEAR_STAT, new ClearUserStatCommand());

    }

    public static CommandFactory getInstance() {
        return LazyHolder.singletonInstance;
    }

    /**
     * @param request
     * @return Command by name of command in request
     */
    public ICommand getCommand(HttpServletRequest request) {
        String action = (String) request.getAttribute(Constants.PARAM_NAME_COMMAND);
        CommandType type = CommandType.valueOf(action);
        ICommand command = allCommands.get(type);
        if (null == command) {
            command = allCommands.get(CommandType.NO_COMMAND);
        }
        return command;
    }

    private static class LazyHolder {
        public static CommandFactory singletonInstance = new CommandFactory();
    }

}
