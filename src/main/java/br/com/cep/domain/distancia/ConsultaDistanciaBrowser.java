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
        wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // 10 segundos de timeout
    }

    public DistanciasTipo getDistancia(String url) throws IOException, InterruptedException {
        driver.get(url);

        String distanciaPe = "";
        String distanciaCarro = "";

        for (int tentativa = 1; tentativa <= 4; tentativa++) {
            try {
                WebElement aPeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@aria-label='A pé']")));
                aPeElement.click();
                WebElement distanciaElementPe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[8]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div[1]/div[1]/div/div[1]/div[2]")));
                distanciaPe = distanciaElementPe.getText();

//                WebElement carroElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@aria-label='Carro']")));
                WebElement carroElement = this.driver.findElement(By.xpath("//img[@aria-label='Carro']"));
                carroElement.click();
                WebElement distanciaElementCarro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[8]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div[1]/div[1]/div/div[1]/div[2]")));
//                WebElement distanciaElementCarro = this.driver.findElement(By.xpath("/html/body/div[3]/div[8]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div[1]/div[1]/div/div[1]/div[2]"));
                distanciaCarro = distanciaElementCarro.getText();

                break;
            } catch (Exception e) {
                System.out.println("Tentativa " + tentativa + ": Elemento não encontrado. Atualizando a página.");
                distanciaPe = "Erro no momento de capturar Distância";
                driver.navigate().refresh();
            }
        }


        return new DistanciasTipo(distanciaPe, distanciaCarro);
    }
}

