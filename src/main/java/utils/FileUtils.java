package utils;

import aquality.selenium.core.logging.Logger;
import constants.CommonConstant;

import java.io.*;
import java.util.Properties;

public class FileUtils {
    public static Properties loadProperties(String path) {
        Properties properties = null;
        try(FileInputStream fis = new FileInputStream(path)) {
            Logger.getInstance().debug("Loading configuration file: "+path);
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            Logger.getInstance().debug("Properties can't be loaded",e);
        }
        return properties;
    }

    public static void savePngScreenshot(byte[] file){
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            Logger.getInstance().debug("Saving screenshot to .png file");
            fos = new FileOutputStream(CommonConstant.TEST_SCREENSHOT_PATH);
            bos = new BufferedOutputStream(fos);
            bos.write(file);
        } catch (IOException e) {
            Logger.getInstance().debug("Screenshot cannot be save",e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
