package com.pms.steps;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class InserirContasSteps {

	private WebDriver driver;
	

	@Dado("que desejo adicionar uma conta")
	public void queDesejoAdicionarUmaConta() {
		System.setProperty("webdriver.chrome.driver", "E:\\Programming\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me/");
		driver.findElement(By.id("email")).sendKeys("phillipmarq@hotmail.com");
		driver.findElement(By.id("senha")).sendKeys("Alceu1228!");
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Adicionar")).click();
	}
	
	
	@Quando("adiciono a conta {string}")
	public void adicionoAConta(String string) {
		driver.findElement(By.id("nome")).sendKeys(string);
		driver.findElement(By.tagName("button")).click();
	}
	
	@Então("recebo a mensagem {string}")
	public void receboAMensagem(String string) {
		String texto = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
		assertEquals(string, texto);
	}

	
	@Before
	public void iniciandoTestes() {
		System.out.println("Começando");
	}
	
	@After(order = 1, value = "@funcionais")
	public void screenshot(Scenario cenario) {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("target/screenshots/"+cenario.getId()+".jpg"));
			System.out.println("Print realizado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After(order = 0, value = "@funcionais")
	public void finalizarBrowser() {
		driver.quit();
		System.out.println("Finalizando testes");
	}

}
