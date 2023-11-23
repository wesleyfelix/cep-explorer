package br.com.cep.domain.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserSetup {
    private WebDriver driver;

    public BrowserSetup() {
        String driverPath = System.getProperty("user.dir") + "/geckodriver.exe";
//        String driverPath = System.getProperty("user.dir") + "/src/main/java/br/com/setup/geckodriver.exe";

        System.setProperty("webdriver.gecko.driver", driverPath);

        // Configurar as opções do Firefox para o modo headless
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        this.driver = new FirefoxDriver(options);
        this.driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown() {
        driver.quit();
    }
}
