package by.epam.testingsystem.dao.pool;

import by.epam.testingsystem.exception.DAOException;
import by.epam.testingsystem.util.ConfigurationManager;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();
    private final int WAIT_TIME;
    private ArrayBlockingQueue<ProxyConnection> connectionQueue;

    private ConnectionPool() {
        try {
            int count = Integer.parseInt(ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.DB_POOL_COUNT_CONNECTION));
            String url = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL);
            String login = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN);
            String password = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASSWORD);
            WAIT_TIME = Integer.parseInt(ConfigurationManager
                    .getInstance().getProperty(ConfigurationManager.DB_POOL_WAIT_TIME));
            connectionQueue = new ArrayBlockingQueue<>(count);
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            for (int i = 0; i < count; i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(url, login, password));
                connectionQueue.put(connection);
            }
        } catch (SQLException e) {
            LOG.error("SQL exception creating pool of connection", e);
            throw new ExceptionInInitializerError(e);
        } catch (InterruptedException e) {
            LOG.error("Interrupt creating pool of connection", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Close all connections
     */
    public static void releasePool() {
        if (instance != null) {
            lock.lock();
            try {
                if (instance != null) {
                    for (ProxyConnection connection : instance.connectionQueue) {
                        if (connection != null) {
                            try {
                                connection.close();
                            } catch (SQLException e) {
                                LOG.error("Can't close connection (" + connection + "): ", e);
                            }
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * @return ProxyConnection
     * @throws DAOException if timeout expired or InterruptedException was found
     */
    public ProxyConnection getConnection() throws DAOException {
        ProxyConnection connection;
        try {
            connection = connectionQueue.poll(WAIT_TIME, TimeUnit.MILLISECONDS);
            if (connection == null) {
                throw new DAOException("There are no available connections to the database.");
            }
        } catch (InterruptedException e) {
            throw new DAOException("Exception in connection poll", e);
        }
        return connection;
    }

    /**
     * @param connection ProxyConnection that you received from this pool
     */
    public void putConnection(ProxyConnection connection) {
        try {
            if (connection != null) {
                connectionQueue.put(connection);
            }
        } catch (InterruptedException e) {
            LOG.error("Can't put connection (" + connection + ")", e);
        }
    }

    /**
     * Close statement
     *
     * @param st statement
     */
    public void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOG.error("Error in close statement", e);
        }
    }


}
