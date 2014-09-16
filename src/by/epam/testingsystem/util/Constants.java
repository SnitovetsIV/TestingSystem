package by.epam.testingsystem.util;

/**
 * Class with constants in application
 *
 * @author Илья
 */
public final class Constants {

    public static final String CONTEXT_PARAM_NAME_LOG_CONFIG_PATH = "logConfigPath";

    /**
     * Message attributes
     */
    public static final String ATR_ERROR_MESSAGE = "errorMessage";
    public static final String ATR_GOOD_MESSAGE = "goodMessage";
    public static final String ATR_BAD_MESSAGE = "badMessage";

    /**
     * General attributes
     */
    public static final String ATR_LOCALE = "locale";
    public static final String ATR_PAGE = "page";
    public static final String ATR_START_LIST = "startList";

    /**
     * User attributes
     */
    public static final String ATR_USER = "user";
    public static final String ATR_USERS = "users";

    /**
     * Test attributes
     */
    public static final String ATR_TEST = "test";
    public static final String ATR_TESTS = "tests";
    public static final String ATR_TEST_NAME = "testName";
    public static final String ATR_TEST_DESCRIPTION = "testDescription";

    /**
     * Subject attributes
     */
    public static final String ATR_SUBJECTS = "subjects";
    public static final String ATR_SUBJECT_NAME = "subjectName";

    /**
     * Topic attributes
     */
    public static final String ATR_TOPICS = "topics";
    public static final String ATR_TOPIC_NAME = "topicName";

    /**
     * Question attributes
     */
    public static final String ATR_CURRENT_QUESTION = "question";
    public static final String ATR_QUESTIONS = "questions";
    public static final String ATR_QUESTION_DESCRIPTION = "questionDescription";
    public static final String ATR_CORRECT_QUESTIONS = "correctQuestions";
    public static final String ATR_ALL_COUNT_QUESTIONS = "allCountQuestions";
    public static final String ATR_CURRENT_QUESTION_NUMBER = "currentQuestionNumber";

    /**
     * General parameters
     */
    public static final String PARAM_NAME_COMMAND = "command";
    public static final String PARAM_NAME_LANG = "lang";

    /**
     * User parameters
     */
    public static final String PARAM_NAME_LOGIN = "login";
    public static final String PARAM_NAME_PASSWORD = "password";
    public static final String PARAM_NAME_OLD_PASSWORD = "oldPassword";
    public static final String PARAM_NAME_REPEAT_PASSWORD = "repeatPassword";
    public static final String PARAM_NAME_NAME = "name";
    public static final String PARAM_NAME_SURNAME = "surname";

    /**
     * Other parameters
     */
    public static final String PARAM_NAME_TEST_ID = "testId";
    public static final String PARAM_NAME_USER_ID = "userId";
    public static final String PARAM_NAME_RESULT_TEST = "resultTest";
    public static final String PARAM_NAME_ANSWERS = "answers";
    public static final String PARAM_NAME_CORRECT_ANSWERS = "correctAnswers";
    public static final String PARAM_NAME_TEST_TOPICS = "testTopics";
    public static final String PARAM_NAME_COUNT_ANSWERS = "countAnswers";
    public static final String PARAM_NAME_TEST_QUESTIONS = "testQuestions";

    /**
     * Names of commands
     */
    public static final String COMMAND_TO_ERROR_PAGE = "TO_ERROR_PAGE";

    /**
     * Init parameters
     */
    public static final String INIT_PARAM_ENCODING = "encoding";


    /**
     * Login messages
     */
    public static final String LOGIN_BAD_LOGIN_PASS_MESS = "messages.jsp.login.badloginpassword";
    public static final String LOGIN_REG_SUCCESS_MESS = "messages.jsp.login.regsuccess";
    public static final String LOGIN_NULL_PAGE_MESS = "messages.jsp.login.null.page";
    public static final String LOGIN_UNKNOWN_USER_TYPE_MESS = "messages.jsp.login.unknown.usertype";

    /**
     * Registration messages
     */
    public static final String REG_LOGIN_EMPTY_MESS = "messages.jsp.registration.login.empty";
    public static final String REG_PASS_EMPTY_MESS = "messages.jsp.registration.password.empty";
    public static final String REG_LOGIN_EXIST_MESS = "messages.jsp.registration.login.exist";
    public static final String REG_PASS_NOT_MATCH_MESS = "messages.jsp.registration.password.notmatch";
    public static final String REG_ERROR_MESS = "messages.jsp.registration.error";

    /**
     * User messages
     */
    public static final String USER_ERROR_SAVE_RESULT_MESS = "messages.jsp.user.result.error";
    public static final String USER_NULL_ANSWERS_MESS = "messages.jsp.user.test.null.answers";

    /**
     * Personal account messages
     */
    public static final String PERS_ACC_SAME_PASSWORD_MESS = "messages.jsp.persacc.password.same";
    public static final String PERS_ACC_PASS_NOT_MATCH_MESS = "messages.jsp.persacc.password.notmatch";
    public static final String PERS_ACC_PASS_SUCCESS_MESS = "messages.jsp.persacc.password.success";
    public static final String PERS_ACC_PASS_EMPTY_MESS = "messages.jsp.persacc.password.empty";
    public static final String PERS_ACC_WRONG_PASS_MESS = "messages.jsp.persacc.password.wrong";
    public static final String PERS_ACC_PASS_ERROR_MESS = "messages.jsp.persacc.password.error";
    public static final String PERS_ACC_SAME_DATA_MESS = "messages.jsp.persacc.data.same";
    public static final String PERS_ACC_DATA_SUCCESS_MESS = "messages.jsp.persacc.data.success";
    public static final String PERS_ACC_DATA_ERROR_MESS = "messages.jsp.persacc.data.error";

    /**
     * Admin messages
     */
    public static final String ADMIN_ERROR_CLEAR_STAT = "messages.jsp.management.user.error";
    public static final String ADMIN_SUCCESS_CLEAR_STAT = "messages.jsp.management.user.success";

    /**
     * Create test messages
     */
    public static final String CREATE_TEST_NULL_TOPICS_MESS = "messages.jsp.create.test.null.topics";
    public static final String CREATE_TEST_NULL_QUESTIONS_MESS = "messages.jsp.create.test.null.questions";
    public static final String CREATE_TEST_ERROR_MESS = "messages.jsp.create.test.error";
    public static final String CREATE_TEST_NULL_NAME_MESS = "messages.jsp.create.test.null.name";
    public static final String CREATE_TEST_SUCCESS_MESS = "messages.jsp.create.test.success";

    /**
     * Create question messages
     */
    public static final String CREATE_QUESTION_ERROR_MESS = "messages.jsp.create.question.error";
    public static final String CREATE_QUESTION_NULL_ANSWER_MESS = "messages.jsp.create.question.null.answers";
    public static final String CREATE_QUESTION_NULL_DESCRIPTION_MESS = "messages.jsp.create.question.null.description";
    public static final String CREATE_QUESTION_WRONG_COUNT_ANSWERS_MESS = "messages.jsp.create.question.wrong.countanswers";
    public static final String CREATE_QUESTION_SUCCESS_MESS = "messages.jsp.create.question.success";

    private Constants() {
    }

}
