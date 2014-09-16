package test.by.epam.testingsystem.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ResourceBundle;

/**
 * @author Илья
 */
public class PropertiesTest {

    @Test
    public void configurationPropertiesExist() {
        ResourceBundle bundle = null;
        String path = "resources.config";
        try {
            bundle = ResourceBundle.getBundle(path);
        } finally {
            Assert.assertNotNull(" Can't find bundle: " + path, bundle);
        }
    }

}
