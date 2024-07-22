package test_selenium;

import java.io.IOException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test_selinuim_Utilities.BrowserSetup;
import test_selinuim_Utilities.FooterHighlight;
import test_selinuim_Utilities.Highlight;
import test_selinuim_Utilities.MouseHover;

@TestInstance(Lifecycle.PER_CLASS)
class ImageSlideTest {

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
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
	void sliderImagesLongTest() throws InterruptedException, IOException {
		
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Thread.sleep(2000);
		driver.get("https://novoprovigilance.com");
//		wait.until(ExpectedConditions.urlToBe("https://novoprovigilance.com"));
		
				
		System.out.println("bgImage elements: " + driver.findElements(By.className("item")).size());
		System.out.println("h1 elements: " + driver.findElements(By.cssSelector("h1")).size());
		System.out.println("h3 elements: " + driver.findElements(By.cssSelector("h3")).size());
		System.out.println("start now elements: " + driver.findElements(By.className("btn-start")).size());

//		assertEquals("rgba(0, 0, 0, 0)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("border-color"));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(0));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(0));
		highlight.highlightElement(driver, driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(0).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(0).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(0).getText());
		System.out.println(driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getText());
//		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(0).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(0).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("color"));
		System.out.println("\n");
		
//		wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("h1")).get(1)));
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("border-color"));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(1));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(1));
		highlight.highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(1).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(1).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(1).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getText());
//		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(1).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(1).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("color"));
		System.out.println("\n");

		wait.until(d->driver.findElements(By.cssSelector("h1")).get(2).isDisplayed());
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("border-color"));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(2));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(2));
		highlight.highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(2).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(2).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(2).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getText());
//		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(2).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(2).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("color"));
		System.out.println("\n");		
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(3).isDisplayed());
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("border-color"));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(3));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(3));
		highlight.highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(3).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(3).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(3).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getText());
//		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(3).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(3).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("color"));
		System.out.println("\n");
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(4).isDisplayed());
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(4));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(4));
		System.out.println(driver.findElements(By.className("item")).get(3).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(4).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(4).getText());
//		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(4).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(4).getCssValue("color"));System.out.println("\n");
//		Thread.sleep(500);
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(4).isDisplayed());
//		Thread.sleep(500);
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(5));
		highlight.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(5));System.out.println(driver.findElements(By.className("item")).get(3).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(3).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(3).getText());
//		assertEquals("rgba(255, 235, 59, 1)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h1")).get(5).getCssValue("color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h3")).get(5).getCssValue("color"));System.out.println("\n");
		
		Thread.sleep(200);
	}

}
