package com.pms.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		glue = "com.pms.steps",
		plugin = {"pretty", "html:target/report.html"},
		tags = "@unitários",
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false
		//strict = false
		)
public class Runner {

	
}
