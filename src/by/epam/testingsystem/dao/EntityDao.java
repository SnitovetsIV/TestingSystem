package by.epam.testingsystem.dao;

import by.epam.testingsystem.dao.pool.ConnectionPool;
import by.epam.testingsystem.dao.pool.ProxyConnection;
import by.epam.testingsystem.entity.Answer;
import by.epam.testingsystem.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EntityDao implements IEntityDao {

    public static final String SQL_CREATE_NEW_TEST = "INSERT INTO test VALUES (NULL, ?, ?)";
    public static final String SQL_ASSOCIATE_QUESTIONS_TO_TEST = "INSERT INTO test_question VALUES";
    public static final String SQL_NEW_QUESTION_ADD = "(?, ?)";
    public static final String SQL_CREATE_QUESTION = "INSERT INTO question VALUES " +
            "(NULL,?,(SELECT topic_id FROM topic WHERE topic.name=? LIMIT 1))";
    public static final String SQL_ADD_QUESTION_ANSWER_DEPENDENCE = "INSERT INTO question_answer VALUES (?,?,?)";
    public static final String SQL_ANSWER_EXIST = "SELECT answer_id FROM answer WHERE description=?";
    public static final String SQL_NEW_ANSWER = "INSERT INTO answer VALUES (NULL, ?)";
    private static final Logger LOG = Logger.getLogger(EntityDao.class);

    @Override
    public boolean createNewTest(String testName, String testDescription, int[] questionsId) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        PreparedStatement statement = null;
        ProxyConnection connection = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_CREATE_NEW_TEST, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, testName);
            statement.setString(2, testDescription);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int testId = rs.getInt(1);
                pool.closeStatement(statement);
                StringBuilder builder = new StringBuilder(SQL_ASSOCIATE_QUESTIONS_TO_TEST);
                for (int i = 1; i < questionsId.length; i++) {
                    builder.append(SQL_NEW_QUESTION_ADD).append(",");
                }
                builder.append(SQL_NEW_QUESTION_ADD);
                statement = connection.prepareStatement(builder.toString());
                for (int i = 1; i <= questionsId.length; i++) {
                    statement.setInt(i * 2 - 1, testId);
                    statement.setInt(i * 2, questionsId[i - 1]);
                }
                statement.executeUpdate();
                if (statement.getUpdateCount() > 0) {
                    result = true;
                    connection.commit();
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } catch (DAOException | SQLException e) {
            LOG.error(e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    LOG.error(e);
                }
            }
            pool.closeStatement(statement);
            pool.putConnection(connection);
        }
        return result;
    }

    @Override
    public boolean createNewQuestion(String description, String topicName, List<Answer> answers) {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        PreparedStatement statement = null;
        ProxyConnection connection = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_CREATE_QUESTION, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, description);
            statement.setString(2, topicName);
            statement.executeUpdate();
            if (statement.getUpdateCount() > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                int questionId;
                if (rs.next()) {
                    questionId = rs.getInt(1);
                    pool.closeStatement(statement);
                    for (Answer answer : answers) {
                        statement = connection.prepareStatement(SQL_ANSWER_EXIST);
                        statement.setString(1, answer.getDescription());
                        ResultSet set = statement.executeQuery();
                        if (set.next()) {
                            int answerID = set.getInt(1);
                            pool.closeStatement(statement);
                            statement = connection.prepareStatement(SQL_ADD_QUESTION_ANSWER_DEPENDENCE);
                            statement.setInt(1, questionId);
                            statement.setInt(2, answerID);
                            statement.setBoolean(3, answer.isCorrect());
                            statement.executeUpdate();
                            if (statement.getUpdateCount() > 0) {
                                connection.commit();
                                result = true;
                            } else {
                                connection.rollback();
                            }
                        } else {
                            pool.closeStatement(statement);
                            statement = connection.prepareStatement(SQL_NEW_ANSWER, Statement.RETURN_GENERATED_KEYS);
                            statement.setString(1, answer.getDescription());
                            statement.executeUpdate();
                            if (statement.getUpdateCount() > 0) {
                                ResultSet resultSet = statement.getGeneratedKeys();
                                if (resultSet.next()) {
                                    int answerID = resultSet.getInt(1);
                                    pool.closeStatement(statement);
                                    statement = connection.prepareStatement(SQL_ADD_QUESTION_ANSWER_DEPENDENCE);
                                    statement.setInt(1, questionId);
                                    statement.setInt(2, answerID);
                                    statement.setBoolean(3, answer.isCorrect());
                                    statement.executeUpdate();
                                    if (statement.getUpdateCount() > 0) {
                                        connection.commit();
                                        result = true;
                                    } else {
                                        connection.rollback();
                                    }
                                } else {
                                    connection.rollback();
                                }
                            } else {
                                connection.rollback();
                            }
                        }
                    }
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } catch (DAOException | SQLException e) {
            LOG.error(e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    LOG.error(e);
                }
            }
            pool.closeStatement(statement);
            pool.putConnection(connection);
        }
        return result;
    }


}
