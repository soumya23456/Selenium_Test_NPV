package test_selenium;

import static org.junit.jupiter.api.Assertions.*;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test_selinuim_Utilities.BrowserSetup;
import test_selinuim_Utilities.FooterHighlight;
import test_selinuim_Utilities.Highlight;
import test_selinuim_Utilities.MouseHover;

@TestInstance(Lifecycle.PER_CLASS)
class ProductSection {

	//chrome, edge, firefox
		 String browser = "chrome";
		 RemoteWebDriver driver;
		 
//		 WebDriver driver;
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
//				driver.quit();
	}
		
//	@Disabled
	@Test
	void DSANPageTest() throws InterruptedException{
		
		
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(200);

		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);
		WebElement danElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'DSAN.html')]"));
		
		//wait until about us link appears and hover over it
		wait.until(ExpectedConditions.visibilityOf(productsLink));
		highlight.highlightElement(driver, productsLink);
		actions.moveToElement(productsLink).perform();
		actions.moveToElement(productsLink).perform();
		Thread.sleep(1000);
		
		//click localSports element to go that page
	
		highlight.highlightElement(driver, danElement);
		wait.until(ExpectedConditions.elementToBeClickable(danElement));
		Thread.sleep(1000);
		danElement.click();
		Thread.sleep(200);

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String DANUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + DANUrl);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("NOVO PROVIGILANCE LLC.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlight.highlightElement(driver, mainHeading);
		assertEquals("Drug Safety Analytics by Novo Provigilance - DSAN", mainHeading.getText());
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlight.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/cp.jpg", imageElement.getDomAttribute("src"));
		
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(200);

		//scroll to element
		jsExecutor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//b[contains(text(),'Conclusion')]")) );
				
	}

}
