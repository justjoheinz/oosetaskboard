package de.oose.taskboard.tests.seleniumtests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oose.taskboard.tests.SeleniumTest;

public class TaskTests implements SeleniumTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskTests.class);

	@Override
	public void perform(WebDriver driver) {
		createTask(driver);
	}

	private void createTask(WebDriver driver) {
		LOGGER.info("Creating a new Task");
		WebElement newTaskButton = driver.findElement(By.cssSelector("button.gwt-Button"));
		assertThat(newTaskButton,not(is((WebElement)null)));
		newTaskButton.click();
	}

}
