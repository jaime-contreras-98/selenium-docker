package org.udemy.globant.pages.flights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.udemy.globant.utils.AbstractPage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SelectFlightPage extends AbstractPage {

    public SelectFlightPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name="departure-flight")
    private List<WebElement> departureFlightsOptions;

    @FindBy(name="arrival-flight")
    private List<WebElement> arrivalFlightsOptions;

    @FindBy(id="confirm-flights")
    private WebElement confirmFlightBtn;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightBtn));

        return this.confirmFlightBtn.isDisplayed();
    }

    public void selectFlights() {
        int random = ThreadLocalRandom.current().nextInt(0, departureFlightsOptions.size());

        this.departureFlightsOptions.get(random).click();
        this.arrivalFlightsOptions.get(random).click();
    }

    public void confirmFlight() {
        this.confirmFlightBtn.click();
    }
}
