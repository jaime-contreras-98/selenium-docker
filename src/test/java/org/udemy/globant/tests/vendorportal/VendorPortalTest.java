package org.udemy.globant.tests.vendorportal;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.udemy.globant.tests.AbstractTest;
import org.udemy.globant.pages.vendorportal.DashboardPage;
import org.udemy.globant.pages.vendorportal.LoginPage;
import org.udemy.globant.tests.vendorportal.model.VendorPortalTestData;
import org.udemy.globant.util.Config;
import org.udemy.globant.util.Constants;
import org.udemy.globant.util.JsonUtil;

import java.io.IOException;

public class VendorPortalTest extends AbstractTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData data;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPages(String testDataPath) throws IOException {
        this.data = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void loginTest() {
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(data.getUsername(), data.getPassword());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() {
        Assert.assertTrue(dashboardPage.isAt());

        Assert.assertEquals(dashboardPage.getMonthlyEarning(), data.getMonthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), data.getAnnualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), data.getProfitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), data.getAvailableInventory());

        dashboardPage.searchInput.sendKeys(data.getSearchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultCount(), data.getSearchResultsCount());

        dashboardPage.logout();
    }
}
