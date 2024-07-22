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
public class MainPageServiceSectionTest {
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
	
}

@AfterAll
void closeAfterAll()throws Exception{
	driver.navigate().refresh();
	driver.quit();
}

@BeforeEach
public void setUp() throws Exception {
	driver.get("https://novoprovigilance.com/");
	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());

}

@AfterEach
public void tearDown() throws Exception {
//	driver.quit();
}

//@Disabled
@Test
void ServicesSectionTest() throws InterruptedException {
	
	Thread.sleep(2000);
	wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
	WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
	wait.until(d -> startnow.isDisplayed());
	highlight.highlightElement(driver, startnow);
	wait.until(ExpectedConditions.elementToBeClickable(startnow));
	startnow.click();
	System.out.println("start now btn clicked");

	// wait until about us link in the header appears
//	wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
	System.out.println("header appears");

	// go to services link and click
	WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
	highlight.highlightElement(driver, servicesLink);
	System.out.println("services nav element : " + servicesLink.getText());
	actions.moveToElement(servicesLink).perform();
	System.out.println("hovered on services link");

	Thread.sleep(200);
	wait.until(ExpectedConditions.elementToBeClickable(servicesLink));
	servicesLink.click();
	System.out.println("services link clicked");

	// From services section
	WebElement servicesHeading = driver.findElement(By.xpath("//b[contains(text(),'::Services::')]"));

	// assert main heading
	highlight.highlightElement(driver, servicesHeading);
//	Thread.sleep(1000);
	System.out.println("asserting main heading");

	// get services elements
	List<WebElement> services = driver.findElements(
			By.xpath("//section[contains(@id,'services')]/div/div/div/div[contains(@class,'service')]")); //8
	List<WebElement> serviceImage = driver.findElements(
			By.xpath("//section[contains(@id,'services')]/div/div/div/div/div[contains(@class,'service-icon')]/img"));//8
	List<WebElement> serviceName = driver.findElements(
			By.xpath("//div[contains(@class,'our-services')]/div/div/div[contains(@class,'service-info')]/p/span"));
	List<WebElement> serviceContent = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));//25
	List<WebElement> serviceReadMoreLink = driver
			.findElements(By.xpath("//section[contains(@id,'services')]/div/div[contains(@class,'service')]/div/div/div/p/a"));//8

	for (WebElement service : services) {
		int index = services.indexOf(service);
		System.out.println("index : " + index);
		if (!service.isDisplayed()) {
			System.out.println("index: " + index);
			jsExecutor.executeScript("arguments[0].scrollIntoView();", service);
		}
		Thread.sleep(200);
		wait.until(d -> service.isDisplayed());

		System.out.println("Service Image: " + serviceImage.get(index).getDomAttribute("src"));
		System.out.println("Service Name: " + serviceName.get(index).getText());
		System.out.println("Service Content: " + serviceContent.get(((index) * 3) + 2).getText());
		System.out.println("Service Readmore Link: " + serviceReadMoreLink.get(index).getText());
		System.out.println("Service Readmore Link: " + serviceReadMoreLink.get(index).getDomAttribute("href"));

		highlight.highlightElement(driver, serviceImage.get(index));
		highlight.highlightElement(driver, serviceName.get(index));
		highlight.highlightElement(driver, serviceContent.get(((index) * 3) + 2));

		// hover over read more link
		actions.moveToElement(serviceReadMoreLink.get(index)).perform();
		Thread.sleep(800);
		highlight.highlightElement(driver, serviceReadMoreLink.get(index));
//		Thread.sleep(2000);
	}
}
}
