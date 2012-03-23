package de.oose.taskboard.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaskBoardSeleniumTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskBoardSeleniumTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//generate a simple driver
		LOGGER.info("Creating Firefox Driver");
		WebDriver driver = new FirefoxDriver();
		LOGGER.info("Getting home page");
		//get the main page of the task board
		driver.get("http://localhost:8080/taskboard/");
		LOGGER.debug("verifying that we got the home page");
		assertThat(driver.getTitle(),is("Taskboard"));

	}

}
