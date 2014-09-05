package by.epam.testingsystem.entity;

public class User extends Entity {

    private String login;
    private String password;
    private String name;
    private String surname;
    private int countCompletedTests;
    private double statistic;
    private UserType type;

    public User() {
    }

    public int getCountCompletedTests() {
        return countCompletedTests;
    }

    public void setCountCompletedTests(int countCompletedTests) {
        this.countCompletedTests = countCompletedTests;
    }

    public double getStatistic() {
        return statistic;
    }

    public void setStatistic(double statistic) {
        this.statistic = statistic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
