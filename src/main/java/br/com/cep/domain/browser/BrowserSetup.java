package br.com.cep.domain.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserSetup {
    private WebDriver driver;

    public BrowserSetup() {
        String driverPath = System.getProperty("user.dir") + "/geckodriver.exe";
//        String driverPath = System.getProperty("user.dir") + "/src/main/java/br/com/setup/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", driverPath);
        this.driver = new FirefoxDriver();
        this.driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown() {
        driver.quit();
    }
}
