package io.inqa.steps;

import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andrew on 15/04/2017.
 */
public class LauncherPageSteps extends CoreWhereAmIAgainAppSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(LauncherPageSteps.class);

    //ELEMENTS
    protected static final String playButtonLocator = "#startGamePlayButton";
    @FindBy(css = playButtonLocator)
    public WebElementFacade playButton;

    protected static final String welcomeMessageLocator = "#introText";
    @FindBy(css = welcomeMessageLocator)
    private WebElementFacade welcomeMessage;

    //ACTIONS

    @Step
    public void checkWelcomeMessage(String expectedWelcomeMessage) {
        LOGGER.info("In checkWelcomeMessage");
        //Get the AndroidDriver object and switch the context - THESE 2 LINES ARE NEEDED IN EVERY METHOD!!
        AndroidDriver androidDriver = Serenity.sessionVariableCalled("myDriver");
        androidDriver.context("WEBVIEW_com.inquisitum.waia");

        String actualWelcomeMessage = welcomeMessage.getText();
        LOGGER.info("Actual Welcome Message is: " + actualWelcomeMessage);
        LOGGER.info("Expected Welcome Message is: " + expectedWelcomeMessage);
        assertEquals(expectedWelcomeMessage, actualWelcomeMessage);

        //Switch back to native app context - THIS IS GOING TO BE PAINFUL
        androidDriver.context("NATIVE_APP");
    }




}
