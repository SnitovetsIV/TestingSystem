package by.epam.testingsystem.dao;

import by.epam.testingsystem.entity.User;

import java.util.List;

/**
 * Interface of IUserDao
 *
 * @author Илья
 */
public interface IUserDao extends IEntityDao {

    /**
     * Create user
     *
     * @param login    unique login of new user
     * @param password hashed password of new user
     * @return true is user was successfully created
     */
    boolean createUser(String login, String password);

    /**
     * Checks login in database
     *
     * @param login login, which you want to check for exist
     * @return true if login exist
     */
    boolean isLoginExist(String login);

    /**
     * Find user in database by login
     *
     * @param login login of user
     * @return entity User, by login
     * @see by.epam.testingsystem.entity.User
     */
    User findUserByLogin(String login);

    /**
     * Change user password
     *
     * @param newPassword new hashed password of user
     * @param id          id of user in database
     * @return true is password was successfully changed
     */
    boolean changePassword(String newPassword, int id);

    /**
     * Read all users
     *
     * @return list of users
     * @see by.epam.testingsystem.entity.User
     */
    List<User> findAllUsers();

    /**
     * Clear user statistic
     *
     * @param id id of user in database
     * @return true if statistic was successfully cleared
     */
    boolean clearUserTestStat(int id);

    /**
     * Change user name and surname
     *
     * @param name    name of user
     * @param surname surname of user
     * @param id      id of user in database
     * @return true if name and surname was successfully changed
     */
    boolean changeNameSurname(String name, String surname, int id);


}
