package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

    public static String getDataConfig(String propertyName) throws IOException {
        FileInputStream fis  = new FileInputStream(new File("./src/main/java/data/dataConfig.properties"));
        Properties prop = new Properties();

        prop.load(fis);
        return prop.getProperty(propertyName);
    }
}
