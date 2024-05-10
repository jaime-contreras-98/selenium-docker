package org.udemy.globant.pages.flights;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.udemy.globant.utils.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends AbstractPage{

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id= "firstName")
    private WebElement firstNameInp;

    @FindBy(id = "lastName")
    private WebElement lastNameInp;

    @FindBy(id = "email")
    private WebElement emailInp;

    @FindBy(id = "password")
    private WebElement passwordInp;

    @FindBy(name = "street")
    private WebElement streetInp;

    @FindBy(name = "city")
    private WebElement cityInp;

    @FindBy(id = "inputState")
    private WebElement stateInp;

    @FindBy(name = "zip")
    private WebElement zipInp;

    @FindBy(id = "register-btn")
    private WebElement registerBtn;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstNameInp));

        return this.firstNameInp.isDisplayed();
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void enterUserName(String fname, String lname) {
        this.firstNameInp.sendKeys(fname);
        this.lastNameInp.sendKeys(lname);
    }

    public void enterUserCredentials(String email, String password) {
        this.emailInp.sendKeys(email);
        this.passwordInp.sendKeys(password);
    }

    public void enterUserAddress(String street, String city, String zipcode) {
        this.streetInp.sendKeys(street);
        this.cityInp.sendKeys(city);
        this.zipInp.sendKeys(zipcode);
    }

    public void register() {
        this.registerBtn.click();
    }
}
