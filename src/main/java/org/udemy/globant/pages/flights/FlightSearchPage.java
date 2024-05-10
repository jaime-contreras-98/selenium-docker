package org.udemy.globant.pages.flights;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.udemy.globant.utils.AbstractPage;
import org.openqa.selenium.WebDriver;

public class FlightSearchPage extends AbstractPage{

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="oneway")
    private WebElement oneWayCheck;

    @FindBy(id="twoway")
    private WebElement twoWayCheck;

    @FindBy(id="passengers")
    private WebElement passengersDrop;

    @FindBy(id="depart-from")
    private WebElement departDrop;

    @FindBy(id="arrive-in")
    private WebElement arriveDrop;

    @FindBy(id="service-class1")
    private WebElement economyCheck;

    @FindBy(id="service-class2")
    private WebElement firstCheck;

    @FindBy(id="service-class3")
    private WebElement businessCheck;

    @FindBy(id="search-flights")
    private WebElement searchBtn;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.searchBtn));

        return this.searchBtn.isDisplayed();
    }

    public void selectPassengers(String numPassengers) {
        Select passengers = new Select(this.passengersDrop);

        passengers.selectByValue(numPassengers);
    }

    public void searchFlights() {
        this.searchBtn.click();
    }
}
