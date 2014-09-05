package by.epam.testingsystem.util;

import by.epam.testingsystem.entity.*;
import by.epam.testingsystem.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class SQLQueryHelper {

    //user

    public static final String SQL_UPDATE_USER_NAME_SURNAME = "UPDATE user SET name=?, surname=? WHERE user_id=? LIMIT 1";
    public static final String SQL_UPDATE_USER_PASSWORD = "UPDATE user SET password=? WHERE user_id=? LIMIT 1";
    public static final String SQL_READ_ALL_SUBJECTS = "SELECT name FROM subject";
    public static final String SQL_READ_TESTS_BASIS = "SELECT test.test_id, test.name," +
            " test.description, subject.name AS 'subject'," +
            " GROUP_CONCAT(DISTINCT topic.name SEPARATOR ', ') AS 'topics'," +
            " (SELECT GROUP_CONCAT(CONCAT(user_test.completed,'%') SEPARATOR ', ') FROM user_test" +
            " WHERE user_test.test_id=test.test_id AND user_test.user_id=?) AS 'stat' FROM test" +
            " LEFT JOIN test_question ON test.test_id=test_question.test_id" +
            " LEFT JOIN question ON test_question.question_id=question.question_id" +
            " LEFT JOIN topic ON question.topic_id=topic.topic_id" +
            " LEFT JOIN subject ON subject.subject_id=topic.subject_id";
    public static final String SQL_READ_ALL_TESTS = SQL_READ_TESTS_BASIS + " GROUP BY test.test_id";
    public static final String SQL_READ_TESTS_BY_SUBJECT = SQL_READ_TESTS_BASIS + " WHERE subject.name=? GROUP BY test.test_id";
    public static final String SQL_READ_TESTS_BY_USER = SQL_READ_TESTS_BASIS + " GROUP BY test.test_id HAVING stat IS NOT NULL";
    public static final String SQL_SAVE_TEST_RESULT = "INSERT INTO user_test(id, user_id, test_id, completed)" +
            " VALUES (null,?,?,?)";
    public static final String SQL_READ_QUESTION_BASIS = "SELECT question.question_id, question.description," +
            " topic.name, answer.answer_id, answer.description, question_answer.correct FROM question" +
            " JOIN question_answer ON question.question_id=question_answer.question_id" +
            " JOIN answer ON question_answer.answer_id=answer.answer_id" +
            " JOIN topic ON topic.topic_id=question.topic_id";
    public static final String SQL_READ_QUESTIONS_BY_TEST_ID = SQL_READ_QUESTION_BASIS +
            " WHERE question.question_id IN (SELECT test_question.question_id FROM test_question WHERE test_id=?)" +
            " ORDER BY question.question_id";

    //admin

    public static final String SQL_READ_QUESTIONS_BY_TOPICS = SQL_READ_QUESTION_BASIS +
            " WHERE topic.topic_id IN (SELECT topic.topic_id FROM topic WHERE LOCATE(topic.name,?)>0)" +
            " ORDER BY question.question_id";
    public static final String SQL_READ_ALL_USERS = "SELECT user.user_id, user.login, user.name, user.surname," +
            " COUNT(DISTINCT user_test.id) AS 'count test', " +
            " ROUND(SUM(user_test.completed)/COUNT(question.question_id),1) AS 'stat'" +
            " FROM user" +
            " LEFT JOIN user_test ON user_test.user_id=user.user_id " +
            " LEFT JOIN test_question ON test_question.test_id=user_test.test_id" +
            " LEFT JOIN question ON question.question_id=test_question.question_id" +
            " WHERE user.admin=0 GROUP BY user.user_id";
    public static final String SQL_CLEAR_USER_TEST_STAT = "DELETE FROM user_test WHERE user_id=?";

    //all

    public static final String SQL_READ_TOPICS_BY_SUBJECT = "SELECT name FROM topic " +
            "WHERE subject_id=(SELECT subject_id FROM subject WHERE subject.name=? LIMIT 1)";
    public static final String SQL_CREATE_USER = "INSERT INTO user(user_id, name, surname, login, password, admin)" +
            " VALUES (null,null,null,?,?,false)";
    public static final String SQL_IS_LOGIN_EXIST = "SELECT user_id FROM user WHERE login=? LIMIT 1";
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT user.user_id, user.name, user.surname, " +
            " user.login, user.password,  user.admin  FROM user WHERE login=? LIMIT 1";

    private static final Logger LOG = Logger.getLogger(SQLQueryHelper.class);

    private SQLQueryHelper() {
    }

    /**
     * @param nameMethod - name of method
     * @return string of query
     * @throws DAOException - if name of method is incorrect
     */
    public static String getQueryByNameMethod(String nameMethod) throws DAOException {
        switch (nameMethod) {
            case "findUserByLogin":
                return SQL_FIND_USER_BY_LOGIN;
            case "readAllTests":
                return SQL_READ_ALL_TESTS;
            case "readAllSubjects":
                return SQL_READ_ALL_SUBJECTS;
            case "readTestsBySubject":
                return SQL_READ_TESTS_BY_SUBJECT;
            case "readTopicsBySubject":
                return SQL_READ_TOPICS_BY_SUBJECT;
            case "readTestsByUser":
                return SQL_READ_TESTS_BY_USER;
            case "saveUserCompleteTest":
                return SQL_SAVE_TEST_RESULT;
            case "readQuestionsByTopics":
                return SQL_READ_QUESTIONS_BY_TOPICS;
            case "readQuestionsByTestId":
                return SQL_READ_QUESTIONS_BY_TEST_ID;
            case "findAllUsers":
                return SQL_READ_ALL_USERS;
            case "clearUserTestStat":
                return SQL_CLEAR_USER_TEST_STAT;
            case "isLoginExist":
                return SQL_IS_LOGIN_EXIST;
            case "createUser":
                return SQL_CREATE_USER;
            case "changePassword":
                return SQL_UPDATE_USER_PASSWORD;
            case "changeNameSurname":
                return SQL_UPDATE_USER_NAME_SURNAME;
            default:
                throw new DAOException("Bad name of method " + nameMethod + " (getQueryByNameMethod)");
        }
    }

    /**
     * @param nameMethod - name of method
     * @param statement  - statement which contained a result
     * @return result of query
     * @throws DAOException - if name of method is incorrect
     */
    public static Object constructResult(String nameMethod, Statement statement) throws DAOException {
        Object result;
        switch (nameMethod) {
            case "changePassword":
            case "changeNameSurname":
            case "saveUserCompleteTest":
            case "createUser":
                result = false;
                try {
                    if (statement.getUpdateCount() > 0) {
                        result = true;
                    }
                } catch (SQLException e) {
                    LOG.error("SQLException in statement", e);
                }
                break;
            case "clearUserTestStat":
                result = false;
                try {
                    if (statement.getUpdateCount() > -1) {
                        result = true;
                    }
                } catch (SQLException e) {
                    LOG.error("SQLException in statement", e);
                }
                break;
            case "isLoginExist":
                result = false;
                try {
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        result = true;
                    }
                } catch (SQLException e) {
                    LOG.error("Bad in result set", e);
                }
                break;
            case "findUserByLogin":
                User user = null;
                try {
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        user = new User();
                        user.setId(resultSet.getInt(1));
                        user.setName((resultSet.getString(2) != null) ? resultSet.getString(2) : "");
                        user.setSurname((resultSet.getString(3) != null) ? resultSet.getString(3) : "");
                        user.setLogin(resultSet.getString(4));
                        user.setPassword(resultSet.getString(5));
                        user.setType(resultSet.getBoolean(6) ? UserType.ADMIN : UserType.USER);
                    }
                } catch (SQLException e) {
                    LOG.error("Bad in result set", e);
                }
                result = user;
                break;
            case "readTestsByUser":
            case "readTestsBySubject":
            case "readAllTests":
                ArrayList<Test> tests = new ArrayList<>();
                try {
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        Test test = new Test();
                        test.setId(resultSet.getInt(1));
                        test.setName(resultSet.getString(2));
                        test.setDescription((resultSet.getString(3) != null) ? resultSet.getString(3) : "");
                        test.setSubject(resultSet.getString(4));
                        test.setTopics(resultSet.getString(5));
                        test.setStat((resultSet.getString(6) != null) ? (resultSet.getString(6)) : "");
                        tests.add(test);
                    }
                } catch (SQLException e) {
                    LOG.error("Bad in result set", e);
                }
                result = tests;
                break;
            case "readTopicsBySubject":
            case "readAllSubjects":
                ArrayList<String> subjects = new ArrayList<>();
                try {
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        subjects.add(resultSet.getString(1));
                    }
                } catch (SQLException e) {
                    LOG.error("Bad in result set", e);
                }
                result = subjects;
                break;
            case "readQuestionsByTestId":
            case "readQuestionsByTopics":
                ArrayList<Question> questions = new ArrayList<>();
                try {
                    ResultSet resultSet = statement.getResultSet();
                    Question question = null;
                    List<Answer> answers = null;
                    while (resultSet.next()) {
                        int questionId = resultSet.getInt(1);
                        if (question == null || questionId != question.getId()) {
                            question = new Question();
                            question.setId(questionId);
                            question.setDescription(resultSet.getString(2));
                            question.setTopic(resultSet.getString(3));
                            answers = new ArrayList<>();
                            question.setAnswers(answers);
                            questions.add(question);
                        }
                        Answer answer = new Answer();
                        answer.setId(resultSet.getInt(4));
                        answer.setDescription(resultSet.getString(5));
                        answer.setCorrect(resultSet.getBoolean(6));
                        answers.add(answer);
                    }
                } catch (SQLException e) {
                    LOG.error("Bad in result set", e);
                }
                result = questions;
                break;
            case "findAllUsers":
                List<User> users = new ArrayList<>();
                try {
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        User concreteUser = new User();
                        concreteUser.setId(resultSet.getInt(1));
                        concreteUser.setLogin(resultSet.getString(2));
                        concreteUser.setName((resultSet.getString(3) != null) ? resultSet.getString(3) : "");
                        concreteUser.setSurname((resultSet.getString(4) != null) ? resultSet.getString(4) : "");
                        concreteUser.setType(UserType.USER);
                        concreteUser.setCountCompletedTests(resultSet.getInt(5));
                        concreteUser.setStatistic(resultSet.getDouble(6));
                        users.add(concreteUser);
                    }
                } catch (SQLException e) {
                    LOG.error("Bad in result set", e);
                }
                result = users;
                break;
            default:
                throw new DAOException("Bad name of method");
        }
        return result;
    }
}
