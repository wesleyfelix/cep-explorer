package br.com.cep.domain.distancia;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ConsultaDistanciaBrowser {
    private WebDriver driver;
    private WebDriverWait wait;
    public ConsultaDistanciaBrowser(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 segundos de timeout
    }

    public String getDistancia(String url) throws IOException, InterruptedException {
        driver.get(url);

        String distancia = "";
        for (int tentativa = 1; tentativa <= 3; tentativa++) {
            try {
                WebElement aPeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@aria-label='A pé']")));
                aPeElement.click();
                WebElement distanciaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[8]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div[1]/div[1]/div/div[1]/div[2]")));
                distancia = distanciaElement.getText();
                break;
            } catch (Exception e) {
                System.out.println("Tentativa " + tentativa + ": Elemento não encontrado. Atualizando a página.");
                distancia = "Erro no momento de capturar Distância";
                driver.navigate().refresh();
            }
        }

        return distancia;
    }
}

