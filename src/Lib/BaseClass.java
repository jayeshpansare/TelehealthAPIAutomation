package Lib;

import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    static String projectPath = System.getProperty("user.dir");

    public static Properties readProperty() {
        Properties prop = null;
        try {
            File file = new File(projectPath + "/src/config/config.properties");
            FileInputStream fin = new FileInputStream(file);
            prop = new Properties();
            prop.load(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
