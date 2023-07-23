package com.pms.runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		glue = "com.pms.steps",
		plugin = {"pretty", "html:target/report.html"},
		tags = "@funcionais",
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false
		//strict = false
		)
public class RunnerFuncionais {

	
	@BeforeClass
	public static void reset() {
		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "E:\\Programming\\chromedriver\\chromedriver.exe");
		driver.get("https://seubarriga.wcaquino.me/");
		driver.findElement(By.id("email")).sendKeys("phillipmarq@hotmail.com");
		driver.findElement(By.id("senha")).sendKeys("Alceu1228!");
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.linkText("reset")).click();
		driver.quit();
		
	}
}
