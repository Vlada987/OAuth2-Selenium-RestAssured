package browserActions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Firefox_Driver {

//class for creating firefox driver and set opitons.

	static String geckoPath = "C:\\Users\\zikaz\\OneDrive\\Desktop\\projects\\OAuth2_App_Selenium_and_Rest_assured\\geckodriver.exe";

	public static FirefoxOptions setOptions() {

		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--headless");
		return options;
	}

	public static WebDriver getFirefoxDriver() {

		FirefoxOptions option = Firefox_Driver.setOptions();
		System.setProperty("webdriver.gecko.driver", geckoPath);
		WebDriver driver = new FirefoxDriver(option);

		return driver;
	}

}
