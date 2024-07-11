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
import org.openqa.selenium.support.ui.WebDriverWait;

class FooterTest {

//	WebDriver driver;
//	JavascriptExecutor jsScript;
//	Actions action;
//	WebDriverWait wait, elementWait;
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
//		driver = new ChromeDriver();
//		jsScript = (JavascriptExecutor) driver;
//		action = new Actions(driver);

	}

	@AfterEach
	void tearDown() throws Exception {
//		driver.quit();
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
	
	public void driver(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public void twitterHandle(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	
	public void facebookHandle(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	public void currentHandle(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public void windowHandles(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public void spanLinkElements(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public void footerElement(WebDriver driver) {
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
	void footerTest() throws InterruptedException {

		driver.manage().window().maximize();
		driver.get("https://novoprovigilance.com/csr.html");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		

		// scroll to bottom -Method 1
		jsExecutor.executeScript("window.scrollBy(0,550)", "");

		List<WebElement> siteLinks = driver.findElements(By.linkText("NOVO PROVIGILANCE LLC"));
//		assertEquals(3, siteLinks.size());
		assertEquals("https://novoprovigilance.com/", siteLinks.get(1).getDomAttribute("href"));
		String currentHandle = driver.getWindowHandle();
		assertNotNull(currentHandle);
		driver.findElement(By.linkText("NOVO PROVIGILANCE LLC")).click();

//		Thread.sleep(1000);
		Object[] windowHandles = driver.getWindowHandles().toArray();
		assertEquals(1, windowHandles.length);
		assertEquals("https://novoprovigilance.com/index.html", driver.getCurrentUrl());
		
//		Thread.sleep(1000);
		driver.navigate().back();
		assertEquals("https://novoprovigilance.com/csr.html",driver.getCurrentUrl());

//		Thread.sleep(1000);
		WebElement twitterElement = driver.findElement(By.className("twitter"));
		WebElement facebookElement = driver.findElement(By.className("facebook"));
		WebElement linkedInElement = driver.findElement(By.className("linkedin"));

		assertEquals("https://mobile.twitter.com/novoprovigilance", twitterElement.getDomAttribute("href"));
		assertEquals("https://www.facebook.com/novoprovigilance", facebookElement.getDomAttribute("href"));
		assertEquals("https://linkedin.com/company/novo-provigilance-llc", linkedInElement.getDomAttribute("href"));

		twitterElement.click();
		String twitterHandle = driver.getWindowHandle();
		assertNotNull(twitterHandle);
//		Thread.sleep(1000);
		driver.switchTo().window(currentHandle);
//		Thread.sleep(1000);

		facebookElement.click();
		String facebookHandle = driver.getWindowHandle();
		assertNotNull(facebookHandle);
//		Thread.sleep(1000);
		driver.switchTo().window((String) currentHandle);
//		Thread.sleep(1000);
		
		linkedInElement.click();
//		Thread.sleep(1000);
		driver.navigate().back();
//		Thread.sleep(1000);
		driver.switchTo().window(twitterHandle);
		
		System.out.println(twitterHandle);
		System.out.println(facebookHandle);
		System.out.println(currentHandle);
		assertEquals(currentHandle, twitterHandle);
		Object[] currentWindowHandles = driver.getWindowHandles().toArray(); 
		assertEquals(3, currentWindowHandles.length);
		
//		Thread.sleep(1000);
		driver.switchTo().window((String) currentWindowHandles[2]);
		Thread.sleep(1000);
		driver.close();
		assertEquals(2, driver.getWindowHandles().toArray().length);
//		Thread.sleep(1000);

		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
//		Thread.sleep(1000);
		driver.close();
		assertEquals(1, driver.getWindowHandles().toArray().length);
//		Thread.sleep(1000);

		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		assertEquals("https://novoprovigilance.com/csr.html", driver.getCurrentUrl());
		WebElement policyElement = driver.findElement(By.linkText("Disclaimer, Cookie & Privacy Policy"));
		policyElement.click();
//		Thread.sleep(1000);
		assertEquals("https://novoprovigilance.com/policy.html", driver.getCurrentUrl());
		assertEquals("NOVO PROVIGILANCE LLC", driver.getTitle());
		driver.navigate().back();
//		Thread.sleep(1000);
		
		WebElement copyrightElement = driver.findElement(By.id("md"));
		assertEquals("© 2024 Novo Provigilance LLC.", copyrightElement.getText());
		siteLinks.get(1).click();
//		Thread.sleep(1000);
		assertEquals("https://novoprovigilance.com/", driver.getCurrentUrl());
		driver.navigate().back();
		
//		Thread.sleep(1000);
		WebElement designedByElement = driver.findElement(By.className("pull-right"));
		assertEquals("Designed by NOVO PROVIGILANCE LLC", designedByElement.getText());
		WebElement designedByLinkElement = driver.findElement(By.linkText("NOVO PROVIGILANCE LLC"));
		designedByLinkElement.click();
//		Thread.sleep(1000);
		assertEquals("https://novoprovigilance.com/index.html", driver.getCurrentUrl());
		driver.navigate().back();
	}

//	@Disabled
	@Test
	void footertestWindowMethod() throws InterruptedException {
	driver.manage().window().setSize(new Dimension(673, 690));
	driver.get("https://novoprovigilance.com/csr.html");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	wait = new WebDriverWait(driver, Duration.ofSeconds(4));
	elementWait = new WebDriverWait(driver, Duration.ofMillis(700));
	
	//scroll to bottom page *method 2
	jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	
	//scroll down until you find the page element *method
//	WebElement footerBottomElemnet = driver.findElement(By.className("pull-right"));
//	jsScript.executeScript("argument[0].scrollIntoView", footerBottomElemnet);
	
	WebElement siteLinks = driver.findElement(By.linkText("NOVO PROVIGILANCE LLC"));
	WebElement footerElement = driver.findElement(By.linkText("NOVO PROVIGILANCE LLC"));
	WebElement twitterElement = driver.findElement(By.xpath("//a[contains(@class,'twitter')]"));
	WebElement facebookElement = driver.findElement(By.xpath("//a[contains(@class,'facebook')]"));
	WebElement linkedinElement = driver.findElement(By.xpath("//a[contains(@class,'linkedin')]"));
	WebElement policyElement = driver.findElement(By.linkText("Disclaimer, Cookie & Privacy Policy"));
	WebElement siteLink = driver.findElement(By.xpath("//a[contains(text(),'Novo Provigilance LLC.')]"));
	WebElement spanLinkElements = driver.findElement(By.xpath("//footer/div/div/div/div/p/a/span"));
	Thread.sleep(200);
	action.moveToElement(footerElement).build().perform();
	Thread.sleep(200);
	assertEquals("rgba(252, 208, 93, 1)", footerElement.getCssValue("color"));
	
	Thread.sleep(200);
	action.moveToElement(twitterElement).build().perform();
	Thread.sleep(200);
	assertEquals("rgba(0, 0, 0, 1)", driver.findElement(By.className("twitter")).getCssValue("background-color"));
	assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("twitter")).getCssValue("color"));
	
	Thread.sleep(200);
	action.moveToElement(facebookElement).build().perform();
	Thread.sleep(200);
	assertEquals("rgba(59, 89, 153, 1)", facebookElement.getCssValue("background-color"));
	assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("facebook")).getCssValue("color"));		
	
	Thread.sleep(200);
	action.moveToElement(linkedinElement).build().perform();
	Thread.sleep(200);
	assertEquals("rgba(3, 109, 192, 1)", linkedinElement.getCssValue("background-color"));
	assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("linkedin")).getCssValue("color"));

	Thread.sleep(200);
	action.moveToElement(policyElement).build().perform();
	Thread.sleep(200);
	assertEquals("rgba(252, 208, 93, 1)", policyElement.getCssValue("color"));
	
	Thread.sleep(200);
	action.moveToElement(siteLinks).build().perform();
	Thread.sleep(200);
//	elementWait.until(d -> spanLinkElements.getCssValue("color").equals("rgba(8, 8, 8, 1)"));
//	elementWait.until(d -> spanLinkElements.getCssValue("color").equals("none solid rgba(252, 208, 93)"));
	assertEquals("rgba(8, 8, 8, 1)", siteLink.getCssValue("color"));
	assertEquals("none solid rgb(252, 208, 93)", siteLinks.getCssValue("text-decoration"));
	
//	Thread.sleep(200);
	action.moveToElement(spanLinkElements).build().perform();
//	Thread.sleep(200);
	elementWait.until(d -> spanLinkElements.getCssValue("color").equals("rgba(0, 221, 0, 1)"));
	assertEquals("rgba(0, 221, 0, 1)", spanLinkElements.getCssValue("color"));

	Thread.sleep(200);
	action.moveToElement(driver.findElement(By.linkText("NOVO PROVIGILANCE LLC"))).build().perform();
//	elementWait.until(d -> driver.findElement(By.linkText("NOVO PROVIGILANCE LLC")).getCssValue("text-decoration").equals("none solid rgb(253, 217, 125)"));
//	assertEquals("none solid rgb(253, 217, 125)", driver.findElement(By.linkText("NOVO PROVIGILANCE LLC")).getCssValue("text-decoration"));
	Thread.sleep(200);
	}

}
