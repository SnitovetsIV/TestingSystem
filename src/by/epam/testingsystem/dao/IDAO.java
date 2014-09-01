package by.epam.testingsystem.dao;

public interface IDAO {

    /**
     * @return IUserDao for work with users
     */
    public IUserDao getUserDAO();

    /**
     * @return ITestDao for work with tests and questions
     */
    public ITestDao getTestDAO();

}
