package by.epam.testingsystem.entity;

import org.apache.log4j.Logger;

/**
 * Class of user
 *
 * @author Илья
 */
public class User extends Entity {

    private static final Logger LOG = Logger.getLogger(User.class);
    private static final long serialVersionUID = 439885203886951080L;
    private String login;
    private String password;
    private String name;
    private String surname;
    private int countCompletedTests;
    private double statistic;
    private UserType type;

    public User() {
    }

    /**
     * @return count of completed test
     */
    public int getCountCompletedTests() {
        return countCompletedTests;
    }

    /**
     * @param countCompletedTests count of completed test
     */
    public void setCountCompletedTests(int countCompletedTests) {
        if (countCompletedTests >= 0) {
            this.countCompletedTests = countCompletedTests;
        } else {
            LOG.warn("Count of completed test can not be less than zero. Value has not been assigned.");
        }
    }

    /**
     * @return statistic of user
     */
    public double getStatistic() {
        return statistic;
    }

    /**
     * @param statistic statistic of user
     */
    public void setStatistic(double statistic) {
        if (statistic >= 0) {
            this.statistic = statistic;
        } else {
            LOG.warn("Statistic can not be less tha zero. Value has not been assigned.");
        }
    }

    /**
     * @return login of user
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login login of user
     */
    public void setLogin(String login) {
        if (login != null && !login.isEmpty()) {
            this.login = login;
        } else {
            LOG.warn("Login can not be null or empty. Value has not been assigned.");
        }
    }

    /**
     * @return hashed password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password hashed password of user
     */
    public void setPassword(String password) {
        if (password != null && !password.isEmpty()) {
            this.password = password;
        } else {
            LOG.warn("Password can not be null or empty. Value has not been assigned.");
        }
    }

    /**
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name of user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return surname of user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname surname of user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return type of user
     * @see by.epam.testingsystem.entity.UserType
     */
    public UserType getType() {
        return type;
    }

    /**
     * @return type of user
     * @see by.epam.testingsystem.entity.UserType
     */
    public void setType(UserType type) {
        if (type != null) {
            this.type = type;
        } else {
            LOG.warn("Type of user can not be null. Value has not been assigned.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (countCompletedTests != user.countCompletedTests) {
            return false;
        }
        if (Double.compare(user.statistic, statistic) != 0) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) {
            return false;
        }
        if (type != user.type) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + countCompletedTests;
        temp = Double.doubleToLongBits(statistic);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "User{" + super.toString() +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", countCompletedTests=" + countCompletedTests +
                ", statistic=" + statistic +
                ", type=" + type +
                "} ";
    }
}
