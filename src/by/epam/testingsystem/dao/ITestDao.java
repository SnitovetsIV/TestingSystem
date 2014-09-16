package by.epam.testingsystem.dao;

import by.epam.testingsystem.entity.Question;
import by.epam.testingsystem.entity.Test;

import java.util.List;

/**
 * Interface of ITestDao
 *
 * @author Илья
 */
public interface ITestDao extends IEntityDao {

    /**
     * Read all tests
     *
     * @param userId id of user in database
     * @return list of tests. All tests.
     * @see by.epam.testingsystem.entity.Test
     */
    List<Test> readAllTests(int userId);

    /**
     * Read test by current subject
     *
     * @param subjectId id of subject in database
     * @param subject   subject tests which you want to get
     * @return list of tests. Subject tests.
     * @see by.epam.testingsystem.entity.Test
     */
    List<Test> readTestsBySubject(int subjectId, String subject);

    /**
     * Read completed test of user
     *
     * @param userId id of user in database
     * @return list of tests which user completed
     * @see by.epam.testingsystem.entity.Test
     */
    List<Test> readTestsByUser(int userId);

    /**
     * Read all subjects
     *
     * @return list of subjects.All subjects.
     */
    List<String> readAllSubjects();

    /**
     * Read topics by current subject
     *
     * @param subjectName subject topics which you want to get
     * @return list of topics. Subject topics.
     */
    List<String> readTopicsBySubject(String subjectName);

    /**
     * Read question by topics
     *
     * @param topics string containing all topics separated by commas
     * @return list of questions.
     * @see by.epam.testingsystem.entity.Question
     */
    List<Question> readQuestionsByTopics(String topics);

    /**
     * Read questions of current test
     *
     * @param testId id of test in database
     * @return list of questions
     * @see by.epam.testingsystem.entity.Question
     */
    List<Question> readQuestionsByTestId(int testId);

    /**
     * Save user result of completed test
     *
     * @param userId id of user in database
     * @param testId id of test in database
     * @param result result of completed test in percent
     * @return true is result was successfully saved
     */
    boolean saveUserCompleteTest(int userId, int testId, int result);
}
