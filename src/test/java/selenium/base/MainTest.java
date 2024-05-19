package selenium.base;

import io.qameta.allure.Allure;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import dev.selenium.driver.DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class MainTest {
    //public WebDriver driver;
    private String url;
    private int implicitWait;
    private String browser;

    private void setUpBrowserDriver() {
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties")) {
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
            browser = config.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (browser) {
            case "chrome":
                DriverFactory.setChromeDriver(implicitWait);
                break;
            case "firefox":
                DriverFactory.setFirefoxDriver(implicitWait);
                break;
            case "edge":
                DriverFactory.setEdgeDriver(implicitWait);
                break;
            default:
                throw new IllegalStateException("Unsupported browser type");

        }
    }

    private void loadUrl() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get(url);
        //System.out.println("Cookies before deletion:");
        //for (Cookie cookies : driver.manage().getCookies()) {
            //String cookieName = cookies.getName();
            //System.out.println(cookieName + "-" + cookies.getValue());
            //driver.manage().deleteCookieNamed(cookieName);
        //}
        //System.out.println("All the Cookie after deletion:");
        //for (Cookie cookies : driver.manage().getCookies()){
            //System.out.println(cookies.getName() + "-" + cookies.getValue());
        //}
    }

        @BeforeMethod
        public void beforeSetup () {
            setUpBrowserDriver();
            loadUrl();
        }

        @AfterMethod
        public void tearDown (ITestResult result){
            WebDriver driver = DriverFactory.getDriver();
            if (ITestResult.FAILURE == result.getStatus()) {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File source = screenshot.getScreenshotAs(OutputType.FILE);
                String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
                String fileName = result.getName() + "_" + timestamp + ".png";
                Path path = Paths.get("./Screenshots", fileName);
                try {
                    Files.copy(source.toPath(), path);
                    Allure.addAttachment("Screenshot on Failure", "image/pgn", Files.newInputStream(path), ".pgn");
                    //FileUtils.copyFile(source, new File("./Screenshots/" + fileName));
                    System.out.println("Screenshot taken: " + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //DriverFactory.quitDriver();
        }
    }

