package io.inqa.steps;

import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andrew on 13/04/2017.
 */
public class CoreWhereAmIAgainAppSteps extends PageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreWhereAmIAgainAppSteps.class);

    public AndroidDriver androidDriver;


    //ELEMENTS

    protected static final String helpIconLocator = "aef";
    @FindBy(id = helpIconLocator)
    private WebElementFacade helpIcon;


    //ACTIONS

    @Step
    public void openHelp() {
        LOGGER.info("In openHelp");
        //Get the AndroidDriver object and switch the context - THESE 2 LINES ARE NEEDED IN EVERY METHOD!!
        AndroidDriver androidDriver = Serenity.sessionVariableCalled("myDriver");
        androidDriver.context("WEBVIEW_com.inquisitum.waia");

        clickOn(helpIcon);

        //Switch back to native app context - THIS IS GOING TO BE PAINFUL
        androidDriver.context("NATIVE_APP");

    }

    @Step
    public void startApp() {
        LOGGER.info("Starting WhereAmIAgain app");

        //SETUP driver stuff - COULD THIS BE A @Before method???
        androidDriver = ((AndroidDriver)((WebDriverFacade) getDriver()).getProxiedDriver());
        Serenity.setSessionVariable("myDriver").to(androidDriver);

        //Wait for splashscreen to have disappeared
        //TODO - something a bit more elegant here
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Set<String> contextNames = androidDriver.getContextHandles();
        for (String contextName : contextNames) {
            LOGGER.info("Context name is: " + contextName);
        }

        //Switch back to native app context - without this it appears to just hang
        //DISABLE THIS AS IN THIS STEP IT'S NOT NEEDED??
        androidDriver.context("NATIVE_APP");
    }


}
