package de.oose.taskboard.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oose.taskboard.tests.SeleniumTest;
import de.oose.taskboard.tests.seleniumtests.HomePageTest;
import de.oose.taskboard.tests.seleniumtests.TaskTests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaskBoardSeleniumTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskBoardSeleniumTest.class);
	
	private static final SeleniumTest[] tests = { 
		new HomePageTest(),
		new TaskTests()
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//generate a simple driver
		LOGGER.info("Creating Firefox Driver");
		WebDriver driver = new FirefoxDriver();
		
		LOGGER.info("executing the tests");
		//execute the tests
		for (SeleniumTest test: tests) {
			LOGGER.debug("Executing {}", test.getClass().getName());
			test.perform(driver);
		}

		driver.quit();
	}
}
