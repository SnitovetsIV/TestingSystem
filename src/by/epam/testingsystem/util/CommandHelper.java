package by.epam.testingsystem.util;

import javax.servlet.http.HttpSession;

/**
 * @author Илья
 */
public class CommandHelper {

    public static String replaceAllHtmlSpecialCharacters(String s) {
        s = s.replaceAll(">", "&gt;");
        s = s.replaceAll("<", "&lt;");
        return s;
    }

    public static void clearSession(HttpSession session) {
        session.removeAttribute(Constants.ATR_SUBJECTS);
        session.removeAttribute(Constants.ATR_TOPIC_NAME);
        session.removeAttribute(Constants.ATR_SUBJECT_NAME);
        session.removeAttribute(Constants.PARAM_NAME_COUNT_ANSWERS);
        session.removeAttribute(Constants.ATR_QUESTION_DESCRIPTION);
        session.removeAttribute(Constants.ATR_TOPICS);
        session.removeAttribute(Constants.ATR_QUESTIONS);
        session.removeAttribute(Constants.ATR_TEST_DESCRIPTION);
        session.removeAttribute(Constants.ATR_START_LIST);
        session.removeAttribute(Constants.ATR_USERS);
        session.removeAttribute(Constants.ATR_TEST);
        session.removeAttribute(Constants.ATR_TESTS);
        session.removeAttribute(Constants.ATR_TEST_DESCRIPTION);
        session.removeAttribute(Constants.ATR_TEST_NAME);
        session.removeAttribute(Constants.ATR_CURRENT_QUESTION);
        session.removeAttribute(Constants.ATR_ALL_COUNT_QUESTIONS);
        session.removeAttribute(Constants.ATR_CURRENT_QUESTION_NUMBER);
    }

}
