package testwithselinuim;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import javax.swing.text.Highlighter.Highlight;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class AboutusSection {

//	WebDriver driver;
//	JavascriptExecutor jsExecutor;
//	WebDriverWait wait, imageWait, driverWait;
//	Actions action;
//	ChromeOptions chromeoptions;
	
//	static WebDriver driver;
	static RemoteWebDriver driver;
	static DesiredCapabilities capabilities_chrome;
	
	static JavascriptExecutor jsExecutor;
	static WebDriverWait wait, imageWait, driverWait;
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
	
		wait = new WebDriverWait(driver, Duration.ofMillis(5000));
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
		driver.navigate().refresh();
		driver.quit();
		
	}
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		// Chrome browser
		
//		chromeoptions = new ChromeOptions();
//		driver = new ChromeDriver(chromeoptions);
//		chromeoptions.addArguments("start-maximized");

		// Edge Browser
//		driver = new EdgeDriver();

		// Firefox Browser
//		driver = new FirefoxDriver();

		// actions
//		action = new Actions(driver);

		// JavaScriptExecutor
//		jsExecutor = (JavascriptExecutor) driver;

	}

	@AfterEach
	void tearDownAfterClass() throws Exception {
//		driver.quit();
	}

	public void driverWait(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public void mouseHoverJScript(WebElement HoverElement, WebDriver driver) {
		try {
			if (isElementPresent(HoverElement)) {
				
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			
				((JavascriptExecutor) driver).executeScript(mouseOverScript,
						HoverElement);

			} else {
				System.out.println("Element was not visible to hover " + "\n");

			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + HoverElement
					+ "is not attached to the page document"
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found in DOM"
					+ e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace());
		}
	}

	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed()
					|| element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow;border: 2px solid red;')", element);
	}
	
	
	
	
//	@Disabled
	@Test
	void csrSectionTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		// Direct Url
//		driver.get("https://novoprovigilance.com/csr.html");

		// From home page
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		driverWait = new WebDriverWait(driver, Duration.ofMillis(600));

		imageWait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);

		// click start now button
		driverWait.until(d -> startnow.isDisplayed());
		startnow.click();
//		Thread.sleep(200);

		WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[@href='#about-us']"));
		WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		WebElement csrElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'csr.html')]"));

		// wait until about us link appears and hover over it
		driverWait.until(ExpectedConditions.visibilityOf(aboutUsLink));
		mouseHoverJScript(aboutUsLink, driver);
		Thread.sleep(500);
		
//		action.moveToElement(aboutUsLink).perform();
//		action.moveToElement(aboutUsLink).perform();
	
		

		// wait until about us dropdown appears
//		driverWait.until(d -> aboutUsDropdownElement.isDisplayed());
		mouseHoverJScript(aboutUsDropdownElement, driver);
		Thread.sleep(500);


		// click csr element to go that page
		
		mouseHoverJScript(csrElement, driver);
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li/ul/li/a[contains(@href, 'csr.html')]")));
		Thread.sleep(500);
		csrElement.click();

		// wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());

		// get Current URL
		String csrUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + csrUrl);

		// assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		// assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		assertEquals("Corporate Social Responsibility", mainHeading.getText());

		// assert ul, li tags
//		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
//		assertEquals(6, ulElements.size());
//		assertEquals(new Dimension(java.awt.Dimension[width=616,height=80]), ulElements.get(0).getSize());
//		System.out.printf(ulElements.get(1).getSize().toString(),ulElements.get(2).getSize().toString(),ulElements.get(3).getSize().toString());
////		assertEquals(new Dimension(616, 80), ulElements.get(1).getSize());
////		assertEquals(new Dimension(616, 80), ulElements.get(2).getSize());
////		assertEquals(new Dimension(616, 80), ulElements.get(3).getSize());
//		assertEquals(new Dimension(616, 80), ulElements.get(4).getSize());
//		assertEquals(new Dimension(616, 80), ulElements.get(5).getSize());

		// whether ul tags are displayed or not
//		assertTrue(ulElements.get(0).isDisplayed());
//		assertTrue(ulElements.get(1).isDisplayed());
//		assertTrue(ulElements.get(2).isDisplayed());
//		assertTrue(ulElements.get(3).isDisplayed());
//		assertTrue(ulElements.get(4).isDisplayed());
//		assertTrue(ulElements.get(5).isDisplayed());

		// check for li tags
		List<WebElement> aboutUsInfoList = driver.findElements(By.className("oli"));
		assertEquals(4, aboutUsInfoList.size());
		assertEquals("Diversity and inclusion.", aboutUsInfoList.get(0).getText());
		highlightElement(driver, aboutUsInfoList.get(0));
		assertEquals("Environment and energy.", aboutUsInfoList.get(1).getText());
		highlightElement(driver, aboutUsInfoList.get(1));
		assertEquals("Governance.", aboutUsInfoList.get(2).getText());
		highlightElement(driver, aboutUsInfoList.get(2));
		assertEquals("Community Outreach.", aboutUsInfoList.get(3).getText());
		highlightElement(driver, aboutUsInfoList.get(3));

	}

}
