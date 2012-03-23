package de.oose.taskboard.tests;

import org.openqa.selenium.WebDriver;

public interface SeleniumTest {
	
	long GWT_JS_TIMEOUT = 10*1000;// we give the GWT JS 10 seconds of time to prepare itself

	void perform(WebDriver driver);

}
