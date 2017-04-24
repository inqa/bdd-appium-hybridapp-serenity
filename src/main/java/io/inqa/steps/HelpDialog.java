package io.inqa.steps;

import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Andrew on 18/04/2017.
 */
public class HelpDialog extends CoreWhereAmIAgainAppSteps  {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpDialog.class);

    //ELEMENTS
    protected static final String closeDialogButtonLocator = "#myModal > div.modal-dialog > div > div.modal-footer > button";
    @FindBy(css = closeDialogButtonLocator)
    public WebElementFacade closeDialogButton;


    protected static final String helpHeaderText = "#myModalLabel";
    @FindBy(css = helpHeaderText)
    public WebElementFacade helpHeader;

    protected static final String helpBodyContentLocator = "#myModal > div.modal-dialog > div > div.modal-body";
    @FindBy(css = helpBodyContentLocator)
    public WebElementFacade helpBodyContent;



    //ACTIONS
    @Step
    public void closeHelp() {
        LOGGER.info("In closeHelp");
        //Get the AndroidDriver object and switch the context - THESE 2 LINES ARE NEEDED IN EVERY METHOD!!
        AndroidDriver androidDriver = Serenity.sessionVariableCalled("myDriver");
        androidDriver.context("WEBVIEW_com.inquisitum.waia");

        clickOn(closeDialogButton);

        //Switch back to native app context - THIS IS GOING TO BE PAINFUL
        androidDriver.context("NATIVE_APP");

    }

    @Step
    public Boolean isHelpDisplayed() {
        //Get the AndroidDriver object and switch the context - THESE 2 LINES ARE NEEDED IN EVERY METHOD!!
        AndroidDriver androidDriver = Serenity.sessionVariableCalled("myDriver");
        androidDriver.context("WEBVIEW_com.inquisitum.waia");

        //androidDriver.findElements(By.className("someclass")).size() > 0;
        Boolean isHelpHeaderVisible = helpHeader.isCurrentlyVisible();

        //Switch back to native app context - THIS IS GOING TO BE PAINFUL
        androidDriver.context("NATIVE_APP");

        return isHelpHeaderVisible;
    }

    @Step
    public String getDialogContent() {
        //Get the AndroidDriver object and switch the context - THESE 2 LINES ARE NEEDED IN EVERY METHOD!!
        AndroidDriver androidDriver = Serenity.sessionVariableCalled("myDriver");
        androidDriver.context("WEBVIEW_com.inquisitum.waia");

        String helpBodyContentText = helpBodyContent.getText();

        //Switch back to native app context - THIS IS GOING TO BE PAINFUL
        androidDriver.context("NATIVE_APP");

        return helpBodyContentText;
    }
}
