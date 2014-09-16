package by.epam.testingsystem.entity;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class of question in test
 *
 * @author Илья
 */
public class Question extends Entity {

    private static final Logger LOG = Logger.getLogger(Question.class);
    private static final long serialVersionUID = 7627691692148380963L;
    private String description;
    private String topic;
    private List<Answer> answers;

    public Question() {
    }

    /**
     * @return list of answers
     * @see by.epam.testingsystem.entity.Answer
     */
    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    /**
     * @param answers list of answers
     * @see by.epam.testingsystem.entity.Answer
     */
    public void setAnswers(List<Answer> answers) {
        if (answers != null) {
            this.answers = answers;
        } else {
            LOG.warn("List of answers can not be null. Value has not been assigned.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        } else {
            LOG.warn("Description can not be null or empty. Value has not been assigned.");
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        if (topic != null && !topic.isEmpty()) {
            this.topic = topic;
        } else {
            LOG.warn("Topic can not be null or empty. Value has not been assigned.");
        }
    }

    /**
     * @param answers numbers marked answers
     * @return true is marked answers are correct
     */
    public boolean isCorrectAnswers(int... answers) {
        boolean result = false;
        if (answers != null && answers.length > 0) {
            ArrayList<Integer> userAnswers = new ArrayList<>();
            for (int answerId : answers) {
                userAnswers.add(answerId);
            }
            ArrayList<Integer> correctAnswers = new ArrayList<>();
            for (Answer answer : this.answers) {
                if (answer.isCorrect()) {
                    correctAnswers.add(answer.getId());
                }
            }
            if (userAnswers.size() == correctAnswers.size() &&
                    correctAnswers.containsAll(userAnswers)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Question question = (Question) o;

        if (answers != null ? !answers.equals(question.answers) : question.answers != null) {
            return false;
        }
        if (description != null ? !description.equals(question.description) : question.description != null) {
            return false;
        }
        if (topic != null ? !topic.equals(question.topic) : question.topic != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Question{" + super.toString() +
                "description='" + description + '\'' +
                ", topic='" + topic + '\'' +
                ", answers=" + answers +
                "} ";
    }
}
