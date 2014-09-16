package by.epam.testingsystem.entity;

import org.apache.log4j.Logger;

/**
 * Class of answer in question
 *
 * @author Илья
 */
public class Answer extends Entity {

    private static final Logger LOG = Logger.getLogger(Answer.class);
    private static final long serialVersionUID = -8780405112524169194L;
    private String description;
    private boolean correct;

    public Answer() {
    }

    /**
     * @return true if this answer is correct for the question in which it is included
     * @see by.epam.testingsystem.entity.Question
     */
    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Answer)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Answer answer = (Answer) o;

        if (correct != answer.correct) {
            return false;
        }
        if (description != null ? !description.equals(answer.description) : answer.description != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (correct ? 1 : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Answer{" + super.toString() +
                "description='" + description + '\'' +
                ", correct=" + correct +
                "} ";
    }
}
