package testwithselinuim;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class ProductSection {

//	WebDriver driver;
//	JavascriptExecutor jsExecutor;
//	WebDriverWait wait, imageWait, elementWait;
//	Actions action;
//	ChromeOptions chromeoptions;
	
//	static WebDriver driver;
	static RemoteWebDriver driver;
	static DesiredCapabilities capabilities_chrome;
	
	static JavascriptExecutor jsExecutor;
	static WebDriverWait wait, imageWait, elementWait;
	static Actions action;
	static ChromeOptions chromeoptions;
	static String excelFilePath = "./testFiles/contactFormTestData.xls";
	static Object[][] contacttestData;
	
	@BeforeAll
	static void closeBeforeAll() throws Exception{
		capabilities_chrome = new DesiredCapabilities();
		capabilities_chrome.setBrowserName("chrome");
		capabilities_chrome.setPlatform(Platform.WIN11);
		driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities_chrome);
		
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
		//Chrome Browser
//				chromeoptions = new ChromeOptions();
//				chromeoptions.addArguments("start-maximized");
//				driver = new ChromeDriver(chromeoptions);

				//Edge Browser
//				driver = new EdgeDriver();
				
				//Firefox Browser
//				driver = new FirefoxDriver();
				
				//actions
//				action = new Actions(driver);
				
				//JavaScriptExecutor
//				jsExecutor = (JavascriptExecutor) driver;
	}

	@AfterEach
	void tearDown() throws Exception {
		//has to do later
//				driver.quit();
	}
	
	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow;border: 2px solid red;')", element);
	}

	public void highlightElement(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public void moveToElement(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
//	@Disabled
	@Test
	void DSANPageTest() throws InterruptedException{
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//Direct URL
//		driver.get("https://novoprovigilance.com/DSAN.html");

		//From home page
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(200);

		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);
		WebElement danElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'DSAN.html')]"));
		
		//wait until about us link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(productsLink));
		highlightElement(driver, productsLink);
		action.moveToElement(productsLink).perform();
		action.moveToElement(productsLink).perform();
		Thread.sleep(500);
		
		//wait until about us dropdown appears
//		elementWait.until(d-> productsDropdownElement.isDisplayed());
//		Thread.sleep(200);

		//click localSports element to go that page
		highlightElement(driver, danElement);
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
		highlightElement(driver, mainHeading);
		assertEquals("Drug Safety Analytics by Novo Provigilance - DSAN", mainHeading.getText());
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/cp.jpg", imageElement.getDomAttribute("src"));
		
		
		  //assert ul, li tags List<WebElement> ulElements =
//		  driver.findElements(By.cssSelector("ul")); assertEquals(7,
//		  ulElements.size()); assertEquals(new Dimension(616,80),
//		  ulElements.get(0).getSize());
//		  System.out.printf(ulElements.get(1).getSize().toString(),ulElements.get(2).
//		  getSize().toString(),ulElements.get(3).getSize().toString()); //
//		  assertEquals(new Dimension(750, 140), ulElements.get(1).getSize()); //
//		  assertEquals(new Dimension(750, 140), ulElements.get(2).getSize()); //
//		  assertEquals(new Dimension(750, 140), ulElements.get(3).getSize());
//		  assertEquals(new Dimension(750, 96), ulElements.get(4).getSize());
//		  assertEquals(new Dimension(750, 120), ulElements.get(5).getSize());
//		  assertEquals(new Dimension(124, 36), ulElements.get(6).getSize());
//		  
//		  //
//		  System.out.println(ulElements.getLast().findElement(By.tagName("li")).getText
//		  ());//mistake //whether ul tags are displayed or not
//		  assertTrue(ulElements.get(0).isDisplayed());
//		  assertFalse(ulElements.get(1).isDisplayed());
//		  assertFalse(ulElements.get(2).isDisplayed());
//		  assertFalse(ulElements.get(3).isDisplayed());
//		  assertTrue(ulElements.get(4).isDisplayed());
//		  assertTrue(ulElements.get(5).isDisplayed());
		 

		//check for li tags
		
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(200);

		
//		  highlightElement(driver, ulElements.get(6));
//		  assertTrue(ulElements.get(6).isDisplayed());
//		 
		
		

		//scroll to element
		jsExecutor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//b[contains(text(),'Conclusion')]")) );
				
	}
	

}
