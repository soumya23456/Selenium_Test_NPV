package testwithselinuim;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import testwithselinuim.ExcelFile;

class ContactusTest {

//	WebDriver driver;
//	JavascriptExecutor jsExecutor;
//	WebDriverWait wait, imageWait, elementWait;
//	Actions action;
//	ChromeOptions chromeoptions;
//	String excelFilePath = "./testFiles/contactFormTestData.xls";
//	Object[][] contacttestData;
	
//	static WebDriver driver;
	static RemoteWebDriver driver;
	static DesiredCapabilities capabilities_chrome, capabilities_firefox;
	
	static JavascriptExecutor jsExecutor;
	static WebDriverWait wait, imageWait, elementWait;
	static Actions action;
	static ChromeOptions chromeoptions;
	static String excelFilePath = "./testFiles/contactFormTestData.xls";
	static Object[][] contacttestData;
//	static UtilTypes hoverJs;

	@BeforeAll
	static void closeBeforeAll() throws Exception{
		
		capabilities_chrome = new DesiredCapabilities();
		capabilities_chrome.setBrowserName("chrome");
		capabilities_chrome.setPlatform(Platform.WIN11);
		driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities_chrome);
		
//		hoverJs = new UtilTypes();
		
		
		//Chrome Browser
//		chromeoptions = new ChromeOptions();
//		chromeoptions.addArguments("start-maximized");
//		driver = new ChromeDriver(chromeoptions);

		//Edge Browser
//		driver = new EdgeDriver();
		
		//Firefox Browser
//		driver = new FirefoxDriver();
		
		//actions
		action = new Actions(driver);
		
		//JavaScriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		
	}
	
	@AfterAll
	static void closeAfterAll()throws Exception{
		driver.quit();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		// Chrome Browser
//		chromeoptions = new ChromeOptions();
//		chromeoptions.addArguments("start-maximized");
//		driver = new ChromeDriver(chromeoptions);

		// Edge Browser
//		driver = new EdgeDriver();

		// Firefox Browser
//		driver = new FirefoxDriver();

		// actions
//		action = new Actions(driver);

		// JavaScriptExecutor
//		jsExecutor = (JavascriptExecutor) driver;

	}

	@AfterEach
	void tearDown() throws Exception {
//		driver.quit();
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript(
				"arguments[0].setAttribute('style', 'background: yellow;border: 2px solid red;')", element);
	}
	
	public void elementWait(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

	public void mouseHoverJScript(WebElement HoverElement, WebDriver driver) {
		try {
			if (isElementPresent(HoverElement)) {
				
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			
				((JavascriptExecutor) driver).executeScript(mouseOverScript,
						HoverElement);

			} else {
				System.out.println("Element was not visible to hover " + "\n");

			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + HoverElement
					+ "is not attached to the page document"
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found in DOM"
					+ e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace());
		}
	}

	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed()
					|| element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}
	
	
	
//	@Disabled
	@Test
	void ContactFormManualTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoprovigilance.com");

		wait = new WebDriverWait(driver, Duration.ofMillis(20000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));

		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
//		Thread.sleep(200);

		// wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
		Thread.sleep(200);

		// go to contact us link and click
		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		highlightElement(driver, contactLink);
		mouseHoverJScript(contactLink, driver);
//		action.moveToElement(contactLink).perform();
		contactLink.click();
//		Thread.sleep(200);
		
		// From contact us section
		WebElement contactUsHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2"));

		// wait until contact us section is visible
		wait.until(d -> driver.findElement(By.xpath("//section[contains(@id, 'contact')]")).isDisplayed());
		wait.until(d -> contactUsHeading.isDisplayed());

		// close cookies click
		WebElement closeUp = driver.findElement(By.xpath("//button[contains(@class,'cookie-btn')]"));
		closeUp.click();
		action.moveToElement(contactUsHeading).perform();

		// Form Handling
		WebElement nameInput = driver.findElement(By.xpath("//input[contains(@name,'name')]"));
		WebElement emailInput = driver.findElement(By.xpath("//input[contains(@name,'email')]"));
		WebElement subjectElement = driver.findElement(By.xpath("//input[contains(@name,'subject')]"));
		WebElement messageElement = driver.findElement(By.xpath("//textarea[contains(@name,'message')]"));
//		WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class,'btn-submit')]"));
		WebElement submitButton = driver.findElement(By.className("btn-submit"));

//		jsExecutor.executeScript("arguments[0].value='test name'", nameInput);
//		jsExecutor.executeScript("arguments[0].value='test@example.com'", emailInput);
//		jsExecutor.executeScript("arguments[0].value='test subject'", subjectElement);
//		jsExecutor.executeScript("arguments[0].value='test message'", messageElement);

		nameInput.sendKeys("test name");
		emailInput.sendKeys("test@example.com");
		subjectElement.sendKeys("test subject");
		messageElement.sendKeys("test message");

		jsExecutor.executeScript("arguments[0].scrollIntoView();", submitButton);
		wait.until(d -> submitButton.isDisplayed());

		action.moveToElement(submitButton).perform();
//		Thread.sleep(500);
		System.out.println(submitButton.getCssValue("background-color"));
//		assertTrue(submitButton.getCssValue("background-color").equals("rgba(66, 148, 79, 1)"));
		submitButton.click();
		highlightElement(driver, submitButton);

		WebElement successMessage = driver.findElement(By.className("form_status"));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", successMessage);
		wait.until(d -> successMessage.isDisplayed());

//		assertTrue(successMessage.isDisplayed());
//		System.out.println(driver.findElement(By.className("text-success")).getText());
	}
	
	// sample test not necessary
		@Disabled
		@Test
		void test() {

			driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			driver.get("https://novoproso.com");
	//
//			wait = new WebDriverWait(driver, Duration.ofMillis(20000));
//			elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));
//			
//			wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
//			WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
//			elementWait.until(d -> startnow.isDisplayed());
//			highlightElement(driver, startnow);
//			startnow.click();
	//
//			//wait until about us link in the header appears
//			elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
	//
//			//go to contact us link and click
//			WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
//			highlightElement(driver, contactLink);
//			action.moveToElement(contactLink).perform();
//			contactLink.click();
	//
//			//From contact us section
//			WebElement contactUsHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2"));
	//
//			//wait until contact us section is visible
//			wait.until(d-> driver.findElement(By.xpath("//section[contains(@id, 'contact')]")).isDisplayed());
//			wait.until(d -> contactUsHeading.isDisplayed());
	//
////			action.moveToElement(contactUsHeading).perform();
	//
//			//Form Handling
//			WebElement nameInput = driver.findElement(By.xpath("//input[contains(@name,'name')]"));
//			WebElement emailInput = driver.findElement(By.xpath("//input[contains(@name,'email')]"));
//			WebElement subjectElement = driver.findElement(By.xpath("//input[contains(@name,'subject')]"));
//			WebElement messageElement = driver.findElement(By.xpath("//textarea[contains(@name,'message')]"));
////			WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class,'btn-submit')]"));
//			WebElement submitButton = driver.findElement(By.className("btn-submit"));
	//
////			jsExecutor.executeScript("arguments[0].value='test name'", nameInput);
////			jsExecutor.executeScript("arguments[0].value='test@example.com'", emailInput);
////			jsExecutor.executeScript("arguments[0].value='test subject'", subjectElement);
////			jsExecutor.executeScript("arguments[0].value='test message'", messageElement);
	//
//			nameInput.sendKeys("test name");
//			emailInput.sendKeys("test@example.com");
//			subjectElement.sendKeys("test subject");
//			messageElement.sendKeys("test message");
	//
//			jsExecutor.executeScript("arguments[0].scrollIntoView();", submitButton);
//			wait.until(d -> submitButton.isDisplayed());
//			
//			action.moveToElement(submitButton).perform();
//			assertTrue(submitButton.getCssValue("background-color").equals("rgb(66, 148, 79)"));
//			submitButton.click();
//			highlightElement(driver, submitButton);
	//
//			WebElement successMessage = driver.findElement(By.className("form_status"));
//			jsExecutor.executeScript("arguments[0].scrollIntoView();", successMessage);
//			wait.until(d -> successMessage.isDisplayed());
//			
//			assertTrue(successMessage.isDisplayed());
//			System.out.println(driver.findElement(By.className("text-success")).getText());

		}

}
