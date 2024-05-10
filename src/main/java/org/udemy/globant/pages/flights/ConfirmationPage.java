package org.udemy.globant.pages.flights;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.udemy.globant.utils.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends AbstractPage{

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="#registration-confirmation-section p > b")
    private WebElement confirmRegisterLbl;

    @FindBy(id="go-to-flights-search")
    private WebElement flightSearchBtn;

    public void goToFlightsSearch() {
        this.flightSearchBtn.click();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightSearchBtn));

        return this.flightSearchBtn.isDisplayed();
    }

    public String getUserRegistered() {
        return this.confirmRegisterLbl.getText();
    }

}
