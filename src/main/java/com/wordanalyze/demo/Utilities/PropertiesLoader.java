package com.wordanalyze.demo.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private Properties prop;
    private boolean error = false;
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
            System.err.print("error loading properties file");
            error = true;
        }
    }

    public String getDataLocation(){
        if (!error){
            return this.prop.getProperty("data_location");
        }
        return "";
    }

    public String getResultsFile(){
        if (!error){
            return this.prop.getProperty("results");
        }
        return "";
    }
}
