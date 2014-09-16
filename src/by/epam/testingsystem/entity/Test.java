package by.epam.testingsystem.entity;

import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * Class of test
 *
 * @author Илья
 */
public class Test extends Entity {

    private static final Logger LOG = Logger.getLogger(Test.class);
    private static final long serialVersionUID = -2743688013179514986L;
    private List<Question> questions;
    private String name;
    private String subject;
    private String description;
    private String topics;
    private String stat;

    public Test() {
    }

    /**
     * @return statistic of user
     */
    public String getStat() {
        return stat;
    }

    /**
     * @param stat statistic of user
     */
    public void setStat(String stat) {
        if (stat != null) {
            this.stat = stat;
        } else {
            LOG.warn("Statistic can not be null. Value has not been assigned.");
        }
    }

    /**
     * @return list of question
     * @see by.epam.testingsystem.entity.Question
     */
    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    /**
     * @param questions list of question
     * @see by.epam.testingsystem.entity.Question
     */
    public void setQuestions(List<Question> questions) {
        if (questions != null && !questions.isEmpty()) {
            this.questions = questions;
        } else {
            LOG.warn("List of questions can not be null or empty. Value has not been assigned.");
        }
    }

    /**
     * @return name of test
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name of test
     */
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            LOG.warn("Name of test can not be null or empty. Value has not been assigned.");
        }
    }

    /**
     * @return subject of test
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject subject of test
     */
    public void setSubject(String subject) {
        if (subject != null && !subject.isEmpty()) {
            this.subject = subject;
        } else {
            LOG.warn("Subject can not be null or empty. Value has not been assigned.");
        }
    }

    /**
     * @return topics of test in one string
     */
    public String getTopics() {
        return topics;
    }

    /**
     * @param topics topics of test in one string
     */
    public void setTopics(String topics) {
        if (topics != null && !topics.isEmpty()) {
            this.topics = topics;
        } else {
            LOG.warn("Topics can not be null or empty. Value has not been assigned.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        } else {
            LOG.warn("Description can not be null. Value has not been assigned.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Test)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Test test = (Test) o;

        if (description != null ? !description.equals(test.description) : test.description != null) {
            return false;
        }
        if (name != null ? !name.equals(test.name) : test.name != null) {
            return false;
        }
        if (questions != null ? !questions.equals(test.questions) : test.questions != null) {
            return false;
        }
        if (stat != null ? !stat.equals(test.stat) : test.stat != null) {
            return false;
        }
        if (subject != null ? !subject.equals(test.subject) : test.subject != null) {
            return false;
        }
        if (topics != null ? !topics.equals(test.topics) : test.topics != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (topics != null ? topics.hashCode() : 0);
        result = 31 * result + (stat != null ? stat.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Test{" + super.toString() +
                "questions=" + questions +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", topics='" + topics + '\'' +
                ", stat='" + stat + '\'' +
                "} ";
    }
}
