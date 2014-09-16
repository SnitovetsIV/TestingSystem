package test.by.epam.testingsystem.dao.pool;

import by.epam.testingsystem.util.ConfigurationManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Илья
 */
public class DBConnectionTest {

    @Test
    public void getConnectionTest() throws SQLException {
        Connection connection = null;
        try {
            String url = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL);
            String login = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN);
            String password = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASSWORD);
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(url, login, password);
        } finally {
            Assert.assertNotNull("Can't get connection from DriverManager", connection);
        }
    }

}
