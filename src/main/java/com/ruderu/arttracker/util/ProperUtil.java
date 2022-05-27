package com.ruderu.arttracker.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProperUtil {

    private Properties properties = null;

    public ProperUtil() {
        Properties prop = new Properties();
        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("WEB-INF/config/api.properties");
            prop.load(stream);
            properties = prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProp(String name) {
        return properties.getProperty(name);
    }
}
