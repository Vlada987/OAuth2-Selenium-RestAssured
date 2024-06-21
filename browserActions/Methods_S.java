package browserActions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Methods_S {

//class for opening browser login take Url and parse a code.	

	public WebDriver driver;
	public WebDriverWait wait;
	String buttonXp = "//button[@class='email-submit-button dwg-button2 dwg-button2--button-style-primary dwg-button2--button-size-standard dwg-box dwg-display--inline-block dwg-width--full dwg-pt--2 dwg-pr--3 dwg-pb--2 dwg-pl--3 dwg-bg-color--core-accent disabled:dwg-bg-color--disabled dwg-border-color--core-accent disabled:dwg-border-color--disabled dwg-border-style--solid dwg-color--inverse-standard disabled:dwg-color--inverse-faint']";
	String button1Xp = "//button[@class='_login-button_1fel4_21 dwg-button2 dwg-button2--button-style-primary dwg-button2--button-size-standard dwg-box dwg-display--inline-block dwg-pt--2 dwg-pr--3 dwg-pb--2 dwg-pl--3 dwg-bg-color--core-accent disabled:dwg-bg-color--disabled dwg-border-color--core-accent disabled:dwg-border-color--disabled dwg-border-style--solid dwg-color--inverse-standard disabled:dwg-color--inverse-faint']";
	Robot robot;

	public Methods_S() {
		driver = Firefox_Driver.getFirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(70));
	}

	public void browserSetup() {

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
	}

	public String reformatUrl(String client_id, String red_url) {

		String url = "https://www.dropbox.com/oauth2/authorize?client_id=" + client_id
				+ "&response_type=code&redirect_uri=" + red_url;

		return url;
	}

	public void login(String username, String password) throws AWTException {

		robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);

		driver.findElement(By.name("susi_email")).sendKeys(username);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
//driver.findElement(By.id("remember_me04402489009680599")).click();
		driver.findElement(By.name("login_password")).sendKeys(password);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		wait.until(ExpectedConditions.urlContains("?code="));

	}

	public String parseCode(String urlcode) {

		String code = "";
		if (urlcode.contains("=")) {
			code = urlcode.split("=")[1];
		}

		return code;
	}

	public String get_Code(String client_id, String redirect_url, String username, String password) {

		String code = "";
		try {
			String url = reformatUrl(client_id, redirect_url);
			browserSetup();
			driver.navigate().to(url);
			login(username, password);
			String urlWithCode = driver.getCurrentUrl();
			code = parseCode(urlWithCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();

		return code;
	}

}
