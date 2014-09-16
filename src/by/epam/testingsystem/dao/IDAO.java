package by.epam.testingsystem.dao;

/**
 * Interface of DAO
 *
 * @author Илья
 */
public interface IDAO {

    /**
     * Method get DAO class for work with users
     *
     * @return IUserDao for work with users
     * @see by.epam.testingsystem.dao.IUserDao
     */
    public IUserDao getUserDAO();

    /**
     * Method get DAO class for work with tests
     *
     * @return ITestDao for work with tests and questions
     * @see by.epam.testingsystem.dao.ITestDao
     */
    public ITestDao getTestDAO();

}
