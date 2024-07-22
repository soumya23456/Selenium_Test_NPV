package test_selenium;



import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import test_selinuim_Utilities.BrowserSetup;
import test_selinuim_Utilities.FooterHighlight;
import test_selinuim_Utilities.Highlight;
import test_selinuim_Utilities.MouseHover;

@TestInstance(Lifecycle.PER_CLASS)
class ContactusTest {

//	 //chrome, edge, firefox
	 String browser = "chrome";
	 RemoteWebDriver driver;
	 
//	 WebDriver driver;
	 JavascriptExecutor jsExecutor;
	 WebDriverWait wait;
	 Actions actions;
	 MouseHover hoverJS;
	 Highlight highlight;
	 FooterHighlight footerHighlight;
	 BrowserSetup browserSetupConfig = new BrowserSetup();


	@BeforeAll
	 void closeBeforeAll() throws Exception{
		
//		driver = browserSetupConfig.getBrowserSetUp(browser);
		driver = browserSetupConfig.getBrowserGridSetUp(browser);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//actions
		actions = new Actions(driver);
		
		//JavaScriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		
		hoverJS = new MouseHover();
		highlight = new Highlight();
		
	}
	
	@AfterAll
	 void closeAfterAll()throws Exception{
		driver.navigate().refresh();
		driver.quit();
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
//		driver.quit();
	}
		
//	@Disabled
	@Test
	void ContactFormManualTest() throws InterruptedException {
		
		driver.get("https://novoprovigilance.com");

		wait = new WebDriverWait(driver, Duration.ofMillis(20000));
		
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
//		Thread.sleep(200);

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
		Thread.sleep(200);

		// go to contact us link and click
		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		highlight.highlightElement(driver, contactLink);
		hoverJS.mouseHoverJScript(contactLink, driver);
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
		actions.moveToElement(contactUsHeading).perform();

		// Form Handling
		WebElement nameInput = driver.findElement(By.xpath("//input[contains(@name,'name')]"));
		WebElement emailInput = driver.findElement(By.xpath("//input[contains(@name,'email')]"));
		WebElement subjectElement = driver.findElement(By.xpath("//input[contains(@name,'subject')]"));
		WebElement messageElement = driver.findElement(By.xpath("//textarea[contains(@name,'message')]"));
//		WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class,'btn-submit')]"));
		WebElement submitButton = driver.findElement(By.className("btn-submit"));

		nameInput.sendKeys("test name");
		emailInput.sendKeys("test@example.com");
		subjectElement.sendKeys("test subject");
		messageElement.sendKeys("test message");

		jsExecutor.executeScript("arguments[0].scrollIntoView();", submitButton);
		wait.until(d -> submitButton.isDisplayed());

		actions.moveToElement(submitButton).perform();
//		Thread.sleep(500);
		System.out.println(submitButton.getCssValue("background-color"));
//		assertTrue(submitButton.getCssValue("background-color").equals("rgba(66, 148, 79, 1)"));
		submitButton.click();
		highlight.highlightElement(driver, submitButton);

		WebElement successMessage = driver.findElement(By.className("form_status"));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", successMessage);
		wait.until(d -> successMessage.isDisplayed());
	}
	
}
