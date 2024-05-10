package org.udemy.globant.tests.flights;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.udemy.globant.pages.flights.*;
import org.udemy.globant.tests.AbstractTest;
import org.udemy.globant.tests.flights.model.FlightReservationTestData;
import org.udemy.globant.util.Config;
import org.udemy.globant.util.Constants;
import org.udemy.globant.util.JsonUtil;

import java.io.IOException;

public class FlightReservationJSONTest extends AbstractTest {
    private FlightReservationTestData data;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParams(String testDataPath) throws IOException {
        this.data = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest() {
        RegisterPage register = new RegisterPage(driver);
        register.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(register.isAt());

        register.enterUserName(data.getFirstName(), data.getLastName());
        register.enterUserCredentials(data.getEmail(), data.getPassword());
        register.enterUserAddress(data.getStreet(), data.getCity(), data.getZip());
        register.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        ConfirmationPage confirm = new ConfirmationPage(driver);
        Assert.assertTrue(confirm.isAt());
        Assert.assertEquals(confirm.getUserRegistered(), data.getFirstName());

        confirm.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest() {
        FlightSearchPage flightSearch = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearch.isAt());

        flightSearch.selectPassengers(data.getPassengersCount());
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
        Assert.assertEquals(confirmFlight.getPrice(), data.getExpectedPrice());
    }
}
