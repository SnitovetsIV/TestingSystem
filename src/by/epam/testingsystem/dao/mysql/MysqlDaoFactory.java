package by.epam.testingsystem.dao.mysql;

import by.epam.testingsystem.dao.EntityDao;
import by.epam.testingsystem.dao.IDAO;
import by.epam.testingsystem.dao.ITestDao;
import by.epam.testingsystem.dao.IUserDao;

public class MysqlDaoFactory implements IDAO {

    private Object dao = MysqlDaoProxy.newInstance(new EntityDao(), IUserDao.class, ITestDao.class);

    public static MysqlDaoFactory getInstance() {
        return LazyMySQLDAOFactoryHolder.singletonInstance;
    }

    @Override
    public IUserDao getUserDAO() {
        return (IUserDao) dao;
    }

    @Override
    public ITestDao getTestDAO() {
        return (ITestDao) dao;
    }

    private static class LazyMySQLDAOFactoryHolder {
        public static MysqlDaoFactory singletonInstance = new MysqlDaoFactory();
    }
}
