package org.udemy.globant.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BasicTest {

    WebDriver driver;

    @BeforeTest
    public void beforeTests() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://google.com");
    }

    @Test
    public void basicTest1(){

    }

    @AfterTest
    public void afterTests() {
        driver.quit();
    }
}
