package by.epam.testingsystem.command;

import by.epam.testingsystem.entity.UserType;

public enum CommandType {

    //Commands for all
    RETURN_FROM_ERROR(UserType.ALL),
    TO_ERROR_PAGE(UserType.ALL),
    CHANGE_LANGUAGE(UserType.ALL),
    NO_COMMAND(UserType.ALL),
    LOGOUT(UserType.ALL),

    //Commands for registration
    LOGIN(UserType.ALL),
    START_REGISTRATION(UserType.ALL),
    REGISTRATION(UserType.ALL),
    BACK_REGISTRATION(UserType.ALL),

    //Commands for user
    MAIN_PAGE(UserType.USER),
    PERSONAL_ACCOUNT(UserType.USER),
    SAVE_PERSONAL_DATA(UserType.USER),
    CHANGE_PASSWORD(UserType.USER),
    ALL_TESTS(UserType.USER),
    TAKE_TEST(UserType.USER),
    SWITCH_PAGE(UserType.USER),
    SELECT_SUBJECT(UserType.USER),
    SUBJECT_TESTS(UserType.USER),
    COMPLETED_TESTS(UserType.USER),
    ANSWER_QUESTION(UserType.USER),
    CANCEL_TAKE_TEST(UserType.USER),

    //Commands for admin
    MAIN_ADMIN_PAGE(UserType.ADMIN),
    START_CREATE_TEST(UserType.ADMIN),
    CHOOSE_SUBJECT(UserType.ADMIN),
    CHOOSE_TOPICS(UserType.ADMIN),
    CANCEL_CREATE_TEST(UserType.ADMIN),
    START_ADD_QUESTIONS(UserType.ADMIN),
    SAVE_NEW_TEST(UserType.ADMIN),
    START_CREATE_QUESTION(UserType.ADMIN),
    CHOOSE_QUESTION_SUBJECT(UserType.ADMIN),
    CHOOSE_QUESTION_TOPIC(UserType.ADMIN),
    CANCEL_CREATE_QUESTION(UserType.ADMIN),
    START_ADD_ANSWERS(UserType.ADMIN),
    USER_MANAGEMENT(UserType.ADMIN),
    CLEAR_STAT(UserType.ADMIN),
    SAVE_NEW_QUESTION(UserType.ADMIN);


    private UserType userType;

    private CommandType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

}
