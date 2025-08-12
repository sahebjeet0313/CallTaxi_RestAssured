package com.qa.api.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();
    private static Properties tempData = new Properties(); // For storing runtime data like tokens

    static {
        try {
            prop.load(ConfigReader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Could not load config.properties from classpath", e);
        }
    }


    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static void setTempProperty(String key, String value) {
        tempData.setProperty(key, value);
    }

    public static String getTempProperty(String key) {
        return tempData.getProperty(key);
    }
}
