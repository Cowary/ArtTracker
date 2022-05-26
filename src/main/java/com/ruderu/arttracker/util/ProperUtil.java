package com.ruderu.arttracker.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProperUtil {

    private final Properties properties;

    public static ProperUtil setPath(String name) {
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream("src/main/resources/config/" + name);
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ProperUtil(prop);
    }

    ProperUtil(Properties properties) {
        this.properties = properties;
    }

    public String getProp(String name) {
        return properties.getProperty(name);
    }
}
