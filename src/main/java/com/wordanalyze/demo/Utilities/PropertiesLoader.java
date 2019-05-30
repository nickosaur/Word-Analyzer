package com.wordanalyze.demo.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private Properties prop;
    private static PropertiesLoader ourInstance = new PropertiesLoader();

    public static PropertiesLoader getInstance() {
        return ourInstance;
    }

    private PropertiesLoader() {
        prop = new Properties();
        InputStream inputStream;
        try{
           inputStream = getClass().
                         getClassLoader().
                         getResourceAsStream("dev-app.properties");
           prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace(); // let it fail
        }
    }

    public String getDataLocation(){
        return getProjectRootDirectory() + this.prop.getProperty("data_location");
    }

    public String getResultsFile(){
        return getProjectRootDirectory() + this.prop.getProperty("results");
    }

    private String getProjectRootDirectory(){
        return this.prop.getProperty("root");
    }
}
