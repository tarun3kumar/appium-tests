package com.saucelabs.appium;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidDeviceWebTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME", "Android");

        // Use app chrome when running tests on real device
        capabilities.setCapability("app", "Chrome");

        // Set up version and device name else appium server would pick the only available emulator/device
        capabilities.setCapability("VERSION", "XXX");
        capabilities.setCapability("deviceName", "XXX");
        capabilities.setCapability("platformName", "Android");

        // 4723 is the port where appium server is running, you can see it when starting the appium server
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testMobileWeb() {
        driver.get("http://www.seleniumtests.com/");
        driver.findElement(By.cssSelector("a[href $= 'selenium-2-forum.html']")).click();
        assert driver.findElement(By.cssSelector(".post-title")).getText().equals("WebDriver Forum") : "unable to launch Webdriver forum";
    }
}
