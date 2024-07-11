package testwithselinuim;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
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

class ImageSlideTest {

//	WebDriver driver;
//	JavascriptExecutor jsExecutor;
//	WebDriverWait wait, imageSliderWait, imageWait, elementWait, initialWait;
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
//		//Chrome Browser
//				chromeoptions = new ChromeOptions();
//				chromeoptions.addArguments("start-maximized");
//				driver = new ChromeDriver(chromeoptions);

//				//Edge Browser
//				driver = new EdgeDriver();
				
//				//Firefox Browser
//				driver = new FirefoxDriver();
				
//				//actions
//				action = new Actions(driver);
//				
//				//JavaScriptExecutor
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

//	@Disabled
	@Test
	void sliderImagesLongTest() throws InterruptedException, IOException {
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoprovigilance.com");		
			
		wait = new WebDriverWait(driver, Duration.ofMillis(20000));

		System.out.println("bgImage elements: " + driver.findElements(By.className("item")).size());
		System.out.println("h1 elements: " + driver.findElements(By.cssSelector("h1")).size());
		System.out.println("h3 elements: " + driver.findElements(By.cssSelector("h3")).size());
		System.out.println("start now elements: " + driver.findElements(By.className("btn-start")).size());

		assertEquals("rgba(0, 0, 0, 0)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(0));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(0));
		highlightElement(driver, driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(0).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(0).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(0).getText());
		System.out.println(driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getText());
		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(0).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(0).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("color"));
		System.out.println("\n");
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("h1")).get(1)));
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(1));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(1));
		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(1).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(1).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(1).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getText());
		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(1).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(1).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("color"));
		System.out.println("\n");

		wait.until(d->driver.findElements(By.cssSelector("h1")).get(2).isDisplayed());
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(2));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(2));
		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(2).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(2).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(2).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getText());
		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(2).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(2).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("color"));
		System.out.println("\n");		
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(3).isDisplayed());
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(3));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(3));
		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(3).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(3).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(3).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getText());
		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(3).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(3).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("color"));
		System.out.println("\n");
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(4).isDisplayed());
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(4));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(4));
//		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(3).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(4).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(4).getText());
//		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getText());
		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(4).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(4).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getCssValue("color"));
		System.out.println("\n");
//		Thread.sleep(500);
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(4).isDisplayed());
//		Thread.sleep(500);
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(5));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(5));
//		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(3).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(3).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(3).getText());
//		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getText());
		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(5).getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(5).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getCssValue("color"));
		System.out.println("\n");
		
		Thread.sleep(200);
	}

}
