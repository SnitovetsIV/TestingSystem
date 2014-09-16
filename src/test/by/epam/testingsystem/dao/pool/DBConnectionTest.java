package test.by.epam.testingsystem.dao.pool;

import by.epam.testingsystem.util.ConfigurationManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;

/**
 * @author Илья
 */
public class DBConnectionTest {

    @Test
    public void driverRegisterTest() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            Assert.fail("Can't register com.mysql.jdbc.Driver");
        }
    }

    @Test(timeout = 15000)
    public void getConnectionTest() {
        try {
            String url = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL);
            String login = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN);
            String password = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASSWORD);
            final int WAIT_TIME = Integer.parseInt(ConfigurationManager
                    .getInstance().getProperty(ConfigurationManager.DB_POOL_WAIT_TIME));
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            Assert.fail("Can't get connection from database: " + e.getMessage());
        } catch (MissingResourceException ex) {
            Assert.fail("Can't initialize ConfigurationManager: " + ex.getMessage());
        }
    }

}
