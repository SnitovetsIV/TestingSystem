package by.epam.testingsystem.dao.mysql;

import by.epam.testingsystem.dao.IEntityDao;
import by.epam.testingsystem.dao.pool.ConnectionPool;
import by.epam.testingsystem.dao.pool.ProxyConnection;
import by.epam.testingsystem.util.SQLQueryHelper;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.PreparedStatement;

class MysqlDaoProxy implements InvocationHandler {

    private static final Logger LOG = Logger.getLogger(MysqlDaoProxy.class);
    private ConnectionPool pool = ConnectionPool.getInstance();
    private IEntityDao dao;


    private MysqlDaoProxy(IEntityDao dao) {
        this.dao = dao;
    }

    public static IEntityDao newInstance(IEntityDao dao, Class<?>... interf) {
        return (IEntityDao) Proxy.newProxyInstance(IEntityDao.class.getClassLoader(), interf,
                new MysqlDaoProxy(dao));
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        for (Class<?> interf : dao.getClass().getInterfaces()) {
            if (declaringClass.isAssignableFrom(interf)) {
                try {
                    return method.invoke(dao, objects);
                } catch (InvocationTargetException e) {
                    throw e.getTargetException();
                }
            }
        }
        Object result = null;
        ProxyConnection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLQueryHelper.getQueryByNameMethod(method.getName()));
        try {
            for (int i = 1; i <= statement.getParameterMetaData().getParameterCount(); i++) {
                statement.setObject(i, objects[i - 1]);
            }
            statement.execute();
            result = SQLQueryHelper.constructResult(method.getName(), statement);
        } finally {
            pool.closeStatement(statement);
            pool.putConnection(connection);
        }
        return result;
    }
}
