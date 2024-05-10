package org.udemy.globant.pages.flights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.udemy.globant.utils.AbstractPage;

public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='flights-confirmation-section'] /div /div /div /p[1]")
    private WebElement confirmationText;

    @FindBy(xpath="//div[@class='card-body'] /div[1] /div /p")
    private WebElement reservationText;

    @FindBy(css="div.card-body > div.row:nth-child(3) p")
    private WebElement priceText;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.priceText));

        return this.priceText.isDisplayed();
    }

    public String getPrice() {
        String confirmation = this.reservationText.getText();
        String price = this.priceText.getText();
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Total price: {}", price);
        
        return this.priceText.getText();
    }
}
