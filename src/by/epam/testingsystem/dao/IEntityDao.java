package by.epam.testingsystem.dao;

import by.epam.testingsystem.entity.Answer;

import java.util.List;

public interface IEntityDao {

    /**
     * @param testName        - name of new test
     * @param testDescription - description of new test
     * @param questionsId     - ids questions that you want to add to the test
     * @return true if test was successfully created
     */
    boolean createNewTest(String testName, String testDescription, int[] questionsId);

    /**
     * @param description - a way of answering the question created
     * @param topicName   - name of topic
     * @param answers     - list of answers
     * @return true if question was successfully created
     */
    boolean createNewQuestion(String description, String topicName, List<Answer> answers);

}
