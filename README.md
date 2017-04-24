Example project with Serenity, Cucumber and Appium (of a hybrid app)
------------------------------------------------------------------
This project represents my attempts to produce an example of Serenity, Cucumber and Appium all working together to test a hybrid mobile app.

This readme page may become a bit long, but it's probably going to turn into content for me to remember what I did to make this work evetually.

Application under test
--------------------
In this project, the AUT is one of my own Android apps called "Where Am I Again". The app has a splashscreen which is a native Android activity, and after a few seconds, it opens a browser window in full-screen that runs the website www.inquisitum.co.uk/whereamiagain. For this, the app uses phonegap.

Therefore, in my code I have the challenge of how to switch context to the WEBVIEW

NB: my app has no controls that are native Android elements
NB: I've included a waia3-debug.apk in this project. The differences between this and the released apk are documented below.
NB: I think I've left google ads on in the attached apk. When I remember to tidy this project up, I will remove that. The source code for this project isn't shared.

How to run
---------
* Either attach an Android device or an emulator
* Run an Appium server (in the Android settings in Appium, set "Platform Version" to "5.0 Lollipop (API level 21)", and "Platform Name" to "Android", and "Automation Name" to "Appium" )
* mvn clean install


How to add further tests
-----------------------
For a new story
* Design your feature files based on your acceptance criteria and what your user wants to achieve
* Create your step definitions for how to do what the user wants to achieve


Reporting
--------
This project does nothing special by itself, as it uses the Serenity reports (which are fantastic already).

After running the "mvn clean install", the report is output to target/site/serenity/index.html

If you don't know Serenity reports then check them out. They provide great traceability through from feature/acceptance criteria through to screenshots for each step.



What I've had to do to make this work
-----------------------------------
1. Given up working with emulators. I find them so slow and frustrating - that may be my laptops ability to run them. I was finding the actions were working but with about a 20-30 second delay.
2. Find a spare Android device. Mine is a Samsung Galaxy SM-N9005 with Android 5.0 (Lollipop)
3. Enable Developer mode on the device (click 7 times on the version number, and then enable USB debugging and allow remote connection/debug)
4. Rebuild my app as a debug apk (I have no idea if this was really required, but I was getting desperate)
5. Changed my activity that extends DroidGap to add in:
    ```
    import android.webkit.WebView;
    ```
    * And, in the OnCreate method, I added:
    ```
     // Enable web debugging
            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
      ```
      (Got that from here: http://jbavari.github.io/blog/2014/04/18/enabling-android-remote-debugging-in-phonegap-2-dot-9/)
     NB: my app uses Phonegap 2.9, so I guess for other cordova/phonegap versions, some other solutions may be required. 
6. In Appium, changed the chromedriver.exe version to 2.25 (I tried some other versions and had various pains, other versions may work)
NB: my chromedriver.exe was in: 
    ```
    C:\Program Files (x86)\Appium\node_modules\appium\node_modules\appium-chromedriver\chromedriver\win
    ```
I just downloaded a load of different versions and renamed them repeatedly. The one that works is:
    ```
    ChromeDriver 2.25.426923 (0390b88869384d6eb0d5d09729679f934aab9eed)
    ```
I believe there is a flag for specifying the location/filename of the driver, but I didn't have the patience to find that too yet.

Now, with a real device, the actions run instantly and reliably

Useful debugging thing I found for a WebView with Chrome remotely
----------------------------
This was new to me, but is something that probably a lot of people knew already...

With the device in developer/debug mode and connected to a desktop/laptop, open the app and go where your Webview appears.

Open Chrome in your laptop/desktop, and press F12 to open dev tools. Click on the three vertical dots button and select More Tools > Remote Devices

You should then get a "Remote devices" tab, and your device will appear down the left. Click on it

In the middle pane, you should get your app name and "WebView" with an Inspect button. Press Inspect.
 
Finally, you'll be able to see the WebView in action in the Chrome dev tool, and be able to use the usual Chrome dev tools.
 
Debugging in Intellij
-------------------
One useful thing I learnt was how to debug this in Intellij. Create a new Run configuration of the Maven project

And in the "Command line" field, enter: 
```
verify -DforkMode=never -DforkCount=0
```
Then right click on your Run Configuration and select Debug


Tests
----
The res/WhereAmIAgain-HighLevelTestMindMap.pdf is a high level overview of what the tests should cover.
The red boxes indicate areas of functionalty and their children some of the high level tests. This isn't intended on being an exhaustive list, but if anyone wants to add some tests then feel free :)


