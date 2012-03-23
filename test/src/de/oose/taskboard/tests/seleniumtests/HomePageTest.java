package de.oose.taskboard.tests.seleniumtests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.oose.taskboard.tests.SeleniumTest;

public class HomePageTest implements SeleniumTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomePageTest.class);

	@Override
	public void perform(WebDriver driver) {
		LOGGER.info("Getting home page");
		//get the main page of the task board
		driver.get("http://localhost:8080/taskboard/");
		LOGGER.debug("Waiting for the JS to be executed");
		(new WebDriverWait(driver, SeleniumTest.GWT_JS_TIMEOUT)).until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d) {
				return d.findElement(By.className("bigFont"))!=null;
			}
		});
		LOGGER.debug("verifying that we got the home page");
		assertThat(driver.getTitle(),is("Taskboard"));
	}

}
