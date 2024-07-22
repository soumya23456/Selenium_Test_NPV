package test_selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class MainPageProductsSectionTest {
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
void productsSectionTest() throws InterruptedException {

	wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	
	wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
	Thread.sleep(200);
	wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
	Thread.sleep(500);
	WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
	highlight.highlightElement(driver, startnow);
	
	wait.until(ExpectedConditions.elementToBeClickable(startnow));
	startnow.click();
	System.out.println("start now displayed");

	// wait until about us link in the header appears
	Thread.sleep(1000);
//	wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
	System.out.println("header appears");

	// go to products link and click
	WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
	highlight.highlightElement(driver, productsLink);
	System.out.println("products nav element : " + productsLink.getText());
	actions.moveToElement(productsLink).perform();
	Thread.sleep(500);
	System.out.println("clicked products link");

	// From products section
	WebElement productsHeading = driver.findElement(By.xpath("//b[contains(text(),'::Products::')]"));
	// wait until products section is visible
	wait.until(d -> productsHeading.isDisplayed());
	System.out.println("checked product section visibility");

	// assert main heading
	highlight.highlightElement(driver, productsHeading);
	System.out.println("Main Heading in Products: " + productsHeading);
	assertEquals("::Products::", productsHeading.getText());

	// sub heading
	WebElement subHeading = driver.findElement(By.xpath("//h4[contains(text(),'::Projects under development::')]"));
	System.out.println("Sub Heading in Products: " + subHeading);
//	assertEquals("::Projects under development::", subHeading.getText());
//	assertEquals("rgba(51, 51, 51, 1)", subHeading.getCssValue("color"));
	highlight.highlightElement(driver, subHeading);

	// get product elements
	WebElement productImage = driver.findElement(By.xpath("//img[contains(@src,'images/net1.png')]"));
	WebElement productName = driver
			.findElement(By.xpath("//span[contains(text(),'Drug Safety Analytics by Novo Provigilance - DSAN')]"));

	WebElement productContent = driver.findElement(By.xpath(
			"//p[contains(text(),'Data Analytics is a predictive, transforming, and mining, used for decision making, data visualization from unstructured to structured data...')]"));
	WebElement productReadMoreLink = driver
			.findElement(By.xpath("//div[contains(@class,'service-info')]/p/a[contains(@href,'DSAN.html')]"));

	System.out.println("Product Image: " + productImage.getDomAttribute("src"));
	System.out.println("Product Name: " + productName.getText());

	System.out.println("Product Content: " + productContent.getText());
	System.out.println("Product Readmore Link: " + productReadMoreLink.getDomAttribute("href"));

	// Checking Styles
//	assertEquals("rgba(57, 144, 30, 1)", productName.getCssValue("color"));
//	assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath(
//			"//p[contains(text(),'Data Analytics is a predictive, transforming, and mining, used for decision making, data visualization from unstructured to structured data...')]"))
//			.getCssValue("background-color"));
//	assertEquals("rgba(102, 102, 102, 1)", productContent.getCssValue("color"));
//	assertEquals("rgba(255, 255, 255, 1)", productReadMoreLink.getCssValue("color"));

	highlight.highlightElement(driver, productImage);
	highlight.highlightElement(driver, productName);
	highlight.highlightElement(driver, productContent);
	System.out.println("highlighting product image, name, content");

	// hover over read more link
	actions.moveToElement(productReadMoreLink).perform();
//	Thread.sleep(600);
//	assertEquals("rgba(255, 255, 255, 1)", productReadMoreLink.getCssValue("color"));
//	assertEquals("0px none rgb(255, 255, 255)", productReadMoreLink.getCssValue("border"));
	highlight.highlightElement(driver, productReadMoreLink);
//	Thread.sleep(2000);
	System.out.println("hovered read more link");
}
}
