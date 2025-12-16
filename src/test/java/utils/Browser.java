package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Browser {
    private static RemoteWebDriver driverInstance = null;

    public static WebDriver getInstance() {
        if (driverInstance == null) {
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("download.prompt_for_download", false);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");

            String remoteUrl = System.getenv()
                    .getOrDefault("SELENIUM_REMOTE_URL", "http://selenium-chrome:4444/wd/hub");

            try {
                driverInstance = new RemoteWebDriver(new URL(remoteUrl), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

        }
        return driverInstance;
    }

    public static void quitDriver(){
        driverInstance.quit();
        driverInstance = null;
    }
}