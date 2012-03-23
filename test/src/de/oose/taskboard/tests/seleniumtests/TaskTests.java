package de.oose.taskboard.tests.seleniumtests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		//Search the new task button and click it
		WebElement newTaskButton = driver.findElement(By.cssSelector("button.gwt-Button"));
		assertThat(newTaskButton,notNullValue());
		newTaskButton.click();
		//wait for the pop up to open - we simply wait until we see a gwt text area
		(new WebDriverWait(driver, SeleniumTest.GWT_JS_TIMEOUT)).until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d) {
				return d.findElement(By.className("gwt-TextArea"))!=null;
			}
		});
		
		WebElement titleInput = driver.findElement(By.cssSelector("input.gwt-TextBox"));
		assertThat(titleInput,notNullValue());
		LOGGER.debug("Setting the title");
		titleInput.sendKeys("This is the official test taks");
		
		WebElement descriptionInput = driver.findElement(By.cssSelector("textarea.gwt-TextArea"));
		assertThat(descriptionInput,notNullValue());
		LOGGER.debug("Setting the description");
		descriptionInput.sendKeys("let's see how selenium handles this!");
		
		WebElement saveButton = driver.findElement(By.cssSelector("button.gwt-Button"));
		assertThat(saveButton,notNullValue());
		LOGGER.debug("Saving the new Task");
		
		
	}

}
