package br.com.cep.domain.distancia;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ConsultaDistanciaBrowser {
    private WebDriver driver;

    public ConsultaDistanciaBrowser(WebDriver driver) {
        this.driver = driver;
    }

    public String getDistancia(String url) throws IOException, InterruptedException {
        driver.get(url);
        Thread.sleep(500);
        driver.findElement(By.xpath("//img[@aria-label='A pé']")).click();
        Thread.sleep(1800);
        String distancia = driver.findElement(By.xpath("/html/body/div[3]/div[8]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div[1]/div[1]/div/div[1]/div[2]")).getText();
        System.out.println(distancia);
        return distancia;
    }
}

