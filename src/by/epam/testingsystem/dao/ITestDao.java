package by.epam.testingsystem.dao;

import by.epam.testingsystem.entity.Question;
import by.epam.testingsystem.entity.Test;

import java.util.List;

public interface ITestDao extends IEntityDao {

    /**
     * @return list of tests. All tests.
     */
    List<Test> readAllTests(int id);

    /**
     * @param subject - subject tests which you want to get
     * @return list of tests. Subject tests.
     */
    List<Test> readTestsBySubject(int id, String subject);

    /**
     * @param id - id of user in database
     * @return list of tests which user completed
     */
    List<Test> readTestsByUser(int id);

    /**
     * @return list of subjects.All subjects.
     */
    List<String> readAllSubjects();

    /**
     * @param subjectName - subject topics which you want to get
     * @return list of topics. Subject topics.
     */
    List<String> readTopicsBySubject(String subjectName);

    List<Question> readQuestionsByTopics(String topics);

    List<Question> readQuestionsByTestId(int testId);

    /**
     * @param userId - id of user in database
     * @param testId - id of test in database
     * @param result - result of completed test in percent
     * @return true is result was successfully saved
     */
    boolean saveUserCompleteTest(int userId, int testId, int result);
}
