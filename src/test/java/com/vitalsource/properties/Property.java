package com.vitalsource.properties;
/**
 * Created by Alexis Espinoza on 11/13/15.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Property {
    String result = "";
    InputStream inputStream;
    public String getPropValues() throws IOException {
        try {
            java.util.Properties prop = new java.util.Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty("loginPage").trim() + ','  + prop.getProperty("validUsername").trim() + ','  + prop.getProperty("validPassword").trim()
                    + ','  + prop.getProperty("invalidUsername").trim() + ','  + prop.getProperty("invalidPassword").trim()
                    + ','  + prop.getProperty("invalidEmailPass").trim() + ','  + prop.getProperty("cantFoolCaptchaYet").trim()
                    + ','  + prop.getProperty("wrongRedeemCode") + ',' + prop.getProperty("bookLink")
                    + ',' + prop.getProperty("vsLoginPage");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
