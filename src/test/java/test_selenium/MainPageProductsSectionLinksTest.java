package test_selenium;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class MainPageProductsSectionLinksTest {

	//chrome, edge, firefox
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
//	driver = browserSetupConfig.getBrowserSetUp(browser);
	driver = browserSetupConfig.getBrowserGridSetUp(browser);
			
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	
	//actions
	actions = new Actions(driver);
	
	//JavaScriptExecutor
	jsExecutor = (JavascriptExecutor) driver;
	
	hoverJS = new MouseHover();
	highlight = new Highlight();
	
	driver.get("https://novoprovigilance.com/");
	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
	
}

@AfterAll
void closeAfterAll()throws Exception{
	driver.navigate().refresh();
	driver.quit();
}

@BeforeEach
public void setUp() throws Exception {
	

}

@AfterEach
public void tearDown() throws Exception {
//	driver.quit();
}

//@Disabled
@Test
void productsSectionLinksTest() throws InterruptedException {

	wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	Thread.sleep(2000);
	wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
	WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
	wait.until(d -> startnow.isDisplayed());
	highlight.highlightElement(driver, startnow);
	startnow.click();
	System.out.println("start now displayed");

	// wait until about us link in the header appears
	wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
	System.out.println("header appears");

	// go to products link and click
	WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
	highlight.highlightElement(driver, productsLink);
	System.out.println("products nav element : " + productsLink.getText());
	actions.moveToElement(productsLink).perform();
	Thread.sleep(500);
	productsLink.click();
	System.out.println("clicked on products section link");

	// From products section
	WebElement productsHeading = driver.findElement(By.xpath("//b[contains(text(),'::Products::')]"));
	// wait until products section is visible
	wait.until(d -> productsHeading.isDisplayed());
	System.out.println("products section displayed");

	// get product elements
	WebElement productReadMoreLink = driver
			.findElement(By.xpath("//div[contains(@class,'service-info')]/p/a[contains(@href,'DSAN.html')]"));
	
//	Thread.sleep(500);
	// hover over first product read more link
	Thread.sleep(3000);
//	jsExecutor.executeScript("arguements[0].scrollIntoView();", productReadMoreLink);
	wait.until(ExpectedConditions.elementToBeClickable(productReadMoreLink));
	Thread.sleep(3000);
	productReadMoreLink.click();
	String url = driver.getCurrentUrl();
	System.out.println("Current URL: " + url);
	wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
	highlight.highlightElement(driver, driver.findElement(By.xpath("//h2")));
	driver.navigate().back();
	Thread.sleep(200);
	wait.until(d -> driver.findElement(By.xpath("//b[contains(text(),'::Products::')]")).isDisplayed());
	System.out.println("back to products section");
}
	
}

