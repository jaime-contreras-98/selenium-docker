package org.udemy.globant.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.udemy.globant.listener.TestListener;
import org.udemy.globant.util.Config;
import org.udemy.globant.util.Constants;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners({TestListener.class})
public class AbstractTest {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void setupConfig() throws IOException {
        Config.initialize();
    }

    @BeforeTest
    //@Parameters({"browser"}) String browser
    public void setDriver(ITestContext context) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        context.setAttribute(Constants.DRIVER, this.driver);
    }

    // String browser
    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities caps = new ChromeOptions();

        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER)))
            caps= new FirefoxOptions();
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url=String.format(urlFormat, hubHost);
        log.info("grid url: {}", url);

        return new RemoteWebDriver(new URL(url), caps);
    }

    private WebDriver getLocalDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }

    @AfterTest
    public void closeDriver() {
        this.driver.quit();
    }
}
