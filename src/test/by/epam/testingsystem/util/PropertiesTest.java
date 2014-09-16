package test.by.epam.testingsystem.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Илья
 */
public class PropertiesTest {

    @Test
    public void configurationPropertiesExist() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("resources.config");
        } catch (MissingResourceException ex) {
            Assert.fail(" Can't find bundle: " + ex.getMessage());
        }
    }

}
