package StepDefs;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.inqa.steps.CoreWhereAmIAgainAppSteps;
import io.inqa.steps.HelpDialog;
import io.inqa.steps.LauncherPageSteps;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;


/**
 * Created by Andrew on 13/04/2017.
 */
public class MyStepdefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyStepdefs.class);

    @Steps
    CoreWhereAmIAgainAppSteps coreApp;
    LauncherPageSteps launcherPage;
    HelpDialog helpDialog;
  //  WhereAmIAgainHomePage whereAmIAgainHomePage;

    @Then("^a street view displays$")
    public void aStreetViewDisplays() {
        LOGGER.info("Test THEN");
    }

    @Given("^WhereAmIAgain is running$")
    public void whereamiagainIsRunning() throws InterruptedException {
        LOGGER.info("Test GIVEN");
    }

    @When("^I start the game$")
    public void iStartTheGame() {
        LOGGER.info("Test WHEN");
    }




    @Given("^the app is running for the first time$|^the app is running$")
    public void iStartTheGameForTheFirstTime() {
        LOGGER.info("In the old GIVEN - we moved everything out of here and into a @Before");
        //TODO: define how to ensure we're running for hte first time
    }

    @Before
    public void appiumSetup(){
        LOGGER.info("Starting Before");
        coreApp.startApp();
        LOGGER.info("Completing Before");
    }

    
    @Then("^the initial page shows \"([^\"]*)\"$")
    public void theInitialPageShows(String expectedWelcomeMessage) {
        LOGGER.info("Checking the welcome message");
        //WAS: coreApp.checkWelcomeMessage(expectedWelcomeMessage);
        launcherPage.checkWelcomeMessage(expectedWelcomeMessage);
        LOGGER.info("Completing the check of the initial page");
    }


    @When("^the user opens the help$")
    public void theUserOpensTheHelp() {
        coreApp.openHelp();
    }

    @When("^the user closes the help$")
    public void closesTheHelp() {
        helpDialog.closeHelp();
    }

    @Then("^the help displays$")
    public void theHelpDisplays() {
        assertTrue(helpDialog.isHelpDisplayed());
    }

    @Then("^the help does not display$")
    public void theHelpDoesNotDisplay() {
        assertFalse(helpDialog.isHelpDisplayed());
    }

    @Then("^the help shows \"([^\"]*)\"$")
    public void theHelpShows(String expectedHelpContent) {
        assertThat(helpDialog.getDialogContent(), containsString(expectedHelpContent));
    }
}
