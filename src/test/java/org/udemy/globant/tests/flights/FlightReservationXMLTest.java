package org.udemy.globant.tests.flights;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.udemy.globant.tests.AbstractTest;
import org.udemy.globant.pages.flights.*;

public class FlightReservationXMLTest extends AbstractTest {
    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setParams(String noOfPassengers, String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void userRegistrationTest() {
        RegisterPage register = new RegisterPage(driver);
        register.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(register.isAt());

        register.enterUserName("Selenium", "Docker");
        register.enterUserCredentials("selenium-udemy@hello.com", "docker123");
        register.enterUserAddress("123 John Doe", "Los Angeles", "83000");
        register.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        ConfirmationPage confirm = new ConfirmationPage(driver);
        Assert.assertTrue(confirm.isAt());

        confirm.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest() {
        FlightSearchPage flightSearch = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearch.isAt());

        flightSearch.selectPassengers(noOfPassengers);
        flightSearch.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest() {
        SelectFlightPage selectFlight = new SelectFlightPage(driver);
        Assert.assertTrue(selectFlight.isAt());

        selectFlight.selectFlights();
        selectFlight.confirmFlight();
    }

    @Test(dependsOnMethods =  "flightSelectionTest")
    public void flightConfirmationTest() {
        FlightConfirmationPage confirmFlight = new FlightConfirmationPage(driver);
        Assert.assertTrue(confirmFlight.isAt());
        Assert.assertEquals(confirmFlight.getPrice(), expectedPrice);
    }
}
