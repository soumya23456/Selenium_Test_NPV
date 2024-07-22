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
public class MainPageServicesSectionLinksTest {

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
			
	
	//actions
	actions = new Actions(driver);
	
	//JavaScriptExecutor
	jsExecutor = (JavascriptExecutor) driver;
	
	hoverJS = new MouseHover();
	highlight = new Highlight();
	
	driver.get("https://novoprovigilance.com/");
	wait = new WebDriverWait(driver, Duration.ofSeconds(25));
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
void servicesSectionLinksTest() throws InterruptedException {

	wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
	WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
	wait.until(d -> startnow.isDisplayed());
	highlight.highlightElement(driver, startnow);
	startnow.click();
	System.out.println("start now clicked");

	// wait until about us link in the header appears
	Thread.sleep(1000);
//	wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
	System.out.println("header appears");

	// go to services link and click
	WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
	highlight.highlightElement(driver, servicesLink);
	System.out.println("services nav element : " + servicesLink.getText());
	actions.moveToElement(servicesLink).perform();
	Thread.sleep(500);
	servicesLink.click();
	System.out.println("clicked on services link");

	// From services section
	WebElement servicesHeading = driver.findElement(By.xpath("//b[contains(text(),'::Services::')]"));
	// wait until products section is visible
	wait.until(d -> servicesHeading.isDisplayed());

	// get services elements
	List<WebElement> services = driver.findElements(
			By.xpath("//section[contains(@id,'services')]/div/div/div/div[contains(@class,'service')]"));//8
	List<WebElement> serviceReadMoreLink = driver
			.findElements(By.xpath("//section[contains(@id,'services')]/div/div[contains(@class,'service')]/div/div/div/p/a"));//8
	wait.until(d -> services.get(0).isDisplayed());
	
	for (WebElement service : services) {
		int index = services.indexOf(service);
		Thread.sleep(200);
		WebElement newService = driver.findElements(
				By.xpath("//section[contains(@id,'services')]/div/div/div/div[contains(@class,'service')]")).get(index);
//		wait.until(ExpectedConditions.invisibilityOf(newService));
		WebElement newserviceslink = driver.findElements(By.xpath("//section[contains(@id,'services')]/div/div[contains(@class,'service')]/div/div/div/p/a")).get(index);
//		Thread.sleep(1000);
		System.out.println("index: " + index);
		jsExecutor.executeScript("arguments[0].scrollIntoView();", newService);
//		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0, -150)");
//		Thread.sleep(3000);				
							
		highlight.highlightElement(driver, newService.findElement(By.xpath("div/img")));
		System.out.println("Heading: " + newService.findElements(By.xpath("div/p")).get(0).getText());
		highlight.highlightElement(driver, newService.findElements(By.xpath("div/p")).get(0));
		
		System.out.println("Content: " + newService.findElements(By.xpath("div/p")).get(1).getText());
		highlight.highlightElement(driver, newService.findElements(By.xpath("div/p")).get(1));
		
		System.out.println("Read More Link: " + newService.findElements(By.xpath("div/p")).get(2).getText());
		Thread.sleep(500);
		highlight.highlightElement(driver, newService.findElements(By.xpath("div/p")).get(2));
		wait.until(ExpectedConditions.elementToBeClickable(newserviceslink));
//		Thread.sleep(1000);
		newserviceslink.click();
//		Thread.sleep(2000);
		driver.navigate().back();
	}
	
}
}
