import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaskBoardSeleniumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//generate a simple driver
		WebDriver driver = new FirefoxDriver();
		System.out.println("Getting home page");
		//get the main page of the task board
		driver.get("http://localhost:8080/taskboard/");
		System.out.println("verifying that we got the home page");
		assertThat(driver.getTitle(),is("Taskboard"));

	}

}
