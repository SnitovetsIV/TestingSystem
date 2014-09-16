package test.by.epam.testingsystem;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Илья
 */
public class LogTest {

    @Test
    public void logInitializeTest() {
        try {
            new DOMConfigurator().doConfigure("web\\log4j.xml", LogManager.getLoggerRepository());
        } catch (Exception ex) {
            Assert.fail("Can't initialize logger: " + ex.getMessage());
        }
    }

}
