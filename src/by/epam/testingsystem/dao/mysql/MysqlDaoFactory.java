package by.epam.testingsystem.dao.mysql;

import by.epam.testingsystem.dao.*;

/**
 * Class which get different DAO
 *
 * @author Илья
 */
public class MysqlDaoFactory implements IDAO {

    private IEntityDao dao = MysqlDaoProxy.newInstance(new EntityDao(), IUserDao.class, ITestDao.class);

    private MysqlDaoFactory() {
    }

    /**
     * @return singleton instance of MysqlDaoFactory
     */
    public static MysqlDaoFactory getInstance() {
        return LazyMySQLDAOFactoryHolder.singletonInstance;
    }

    /**
     * @return IUserDao for work with users
     * @see by.epam.testingsystem.dao.IUserDao
     */
    @Override
    public IUserDao getUserDAO() {
        return (IUserDao) dao;
    }

    /**
     * @return ITestDao for work with tests and questions
     * @see by.epam.testingsystem.dao.ITestDao
     */
    @Override
    public ITestDao getTestDAO() {
        return (ITestDao) dao;
    }

    private static class LazyMySQLDAOFactoryHolder {
        public static MysqlDaoFactory singletonInstance = new MysqlDaoFactory();
    }
}
