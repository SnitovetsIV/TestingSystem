package by.epam.testingsystem.dao;

import by.epam.testingsystem.entity.Question;
import by.epam.testingsystem.entity.Test;

import java.util.List;

public interface ITestDao extends IEntityDao {

    /**
     * @param userId - id of user in database
     * @return list of tests. All tests.
     * @see by.epam.testingsystem.entity.Test
     */
    List<Test> readAllTests(int userId);

    /**
     * @param subjectId - id of subject in database
     * @param subject   - subject tests which you want to get
     * @return list of tests. Subject tests.
     * @see by.epam.testingsystem.entity.Test
     */
    List<Test> readTestsBySubject(int subjectId, String subject);

    /**
     * @param userId - id of user in database
     * @return list of tests which user completed
     * @see by.epam.testingsystem.entity.Test
     */
    List<Test> readTestsByUser(int userId);

    /**
     * @return list of subjects.All subjects.
     */
    List<String> readAllSubjects();

    /**
     * @param subjectName - subject topics which you want to get
     * @return list of topics. Subject topics.
     */
    List<String> readTopicsBySubject(String subjectName);

    /**
     * @param topics - string containing all topics separated by commas
     * @return list of questions.
     * @see by.epam.testingsystem.entity.Question
     */
    List<Question> readQuestionsByTopics(String topics);

    /**
     * @param testId - id of test in database
     * @return list of questions
     * @see by.epam.testingsystem.entity.Question
     */
    List<Question> readQuestionsByTestId(int testId);

    /**
     * @param userId - id of user in database
     * @param testId - id of test in database
     * @param result - result of completed test in percent
     * @return true is result was successfully saved
     */
    boolean saveUserCompleteTest(int userId, int testId, int result);
}
