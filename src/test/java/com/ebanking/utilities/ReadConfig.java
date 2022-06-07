package com.ebanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties properties;

    public ReadConfig(){
        File src = new File("./Configuration/config.properties");

        try{
            FileInputStream fileInputStream = new FileInputStream(src);
            properties = new Properties();
            properties.load(fileInputStream);
        }catch (Exception e){
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public String getApplicationURL(){
        return properties.getProperty("baseURL");
    }

    public String getHomePath(){
        return properties.getProperty("home");
    }
    public String getUserName(){
        return properties.getProperty("username");
    }

    public String getPassword(){
        return properties.getProperty("password");
    }

    public String getChromeDriver(){
        return properties.getProperty("chromepath");
    }

    public String getFirefoxDriver(){
        return properties.getProperty("firefoxpath");
    }

    public String getMsedgeDriver(){
        return properties.getProperty("msedgepath");
    }
}
