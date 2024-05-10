package org.udemy.globant.pages.vendorportal;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.globant.utils.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="monthly-earning")
    private WebElement earningMonthText;

    @FindBy(id="annual-earning")
    private WebElement earningAnnualText;

    @FindBy(id="profit-margin")
    private WebElement earningProfitText;

    @FindBy(id="available-inventory")
    private WebElement availableInventoryText;

    @FindBy(css="input[type='search']")
    public WebElement searchInput;

    @FindBy(id="dataTable_info")
    private WebElement dataTableResultsText;

    @FindBy(css="#userDropdown > img")
    private WebElement profileImgButton;

    @FindBy(xpath="//a[@class='dropdown-item'][4]")
    private WebElement logoutButton;

    @FindBy(xpath="//div[@class='modal-footer'] /a")
    private WebElement modalLogoutButton;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.earningMonthText));

        return this.earningMonthText.isDisplayed();
    }

    public String getMonthlyEarning() {
        return this.earningMonthText.getText();
    }

    public String getAnnualEarning() {
        return this.earningAnnualText.getText();
    }

    public String getProfitMargin() {
        return this.earningProfitText.getText();
    }

    public String getAvailableInventory() {
        return this.availableInventoryText.getText();
    }

    public int getSearchResultCount() {
        //String result = this.dataTableResultsText.getText().split(" ")[5];
        String resultTxt = this.dataTableResultsText.getText();
        String[] arr = resultTxt.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Results count: {}", count);

        return count;
    }

    public void logout() {
        this.profileImgButton.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutButton));
        this.logoutButton.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
        this.modalLogoutButton.click();
    }
}
