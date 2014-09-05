package by.epam.testingsystem.entity;

import java.util.Collections;
import java.util.List;

public class Question extends Entity {

    private String description;
    private String topic;
    private List<Answer> answers;

    public Question() {
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isCorrectAnswers(int... answers) {
        boolean result = true;
        if (answers != null && answers.length > 0) {
            int countCorrectAnswers = 0;
            falseBreak:
            for (Answer answer : this.answers) {
                for (int answerId : answers) {
                    if (answer.getId() == answerId && !answer.isCorrect()) {
                        result = false;
                        break falseBreak;
                    }
                }
                if (answer.isCorrect()) {
                    countCorrectAnswers++;
                }
            }
            if (countCorrectAnswers != answers.length) {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }


}
