package testwithselinuim;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.net.URL;
import java.sql.Driver;
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

class ServicesSection {

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
		driver.manage().window().maximize();


		//Edge Browser
//		driver = new EdgeDriver();
		
		//Firefox Browser
//		driver = new FirefoxDriver();
		
//		//actions
		action = new Actions(driver);
//		
//		//JavaScriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		
	}
	
	@AfterAll
	static void closeAfterAll()throws Exception{
		driver.quit();
	}
	

	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;')", element);
	}
	
	
//	@Disabled
	@Test
	void cpPageTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
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

		//		Thread.sleep(100);
		wait.until(d -> driver.findElement(By.xpath("//li/a[contains(text(),'About Us')]")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//li/a[contains(text(),'About Us')]")));
		
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[contains(@href,'#services')]"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement cpElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'cp.html')]"));

		action.moveToElement(driver.findElement(By.xpath("//li/a[contains(@href,'#services')]"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//li/a[contains(@href,'#services')]"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//li/a[contains(@href,'#services')]"))).perform();
		highlightElement(driver, driver.findElement(By.xpath("//li/a[contains(@href,'#services')]")));
		wait.until(d -> servicesDropdownElement.isDisplayed());
				
		highlightElement(driver, cpElement);
		cpElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String cpURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + cpURL);

		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		//assert main heading
		//did not include commented tag
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlightElement(driver, mainHeading);
		assertEquals("Case Processing", mainHeading.getText());
		

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/cp.jpg", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		/*
		 * List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		 * assertEquals(6, ulElements.size()); assertEquals(new Dimension(616,80),
		 * ulElements.get(0).getSize()); assertEquals(new Dimension(746, 120),
		 * ulElements.get(4).getSize()); assertEquals(new Dimension(124, 36),
		 * ulElements.get(5).getSize());
		 */

		//whether ul tags are displayed or not
		/*
		 * assertTrue(ulElements.get(0).isDisplayed());
		 * assertFalse(ulElements.get(1).isDisplayed());
		 * assertFalse(ulElements.get(2).isDisplayed());
		 * assertFalse(ulElements.get(3).isDisplayed());
		 * assertTrue(ulElements.get(4).isDisplayed());
		 */

		//check for li tags
		
//		Thread.sleep(100);
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//		Thread.sleep(100);

		/*
		 * highlightElement(driver, ulElements.get(5));
		 * assertTrue(ulElements.get(5).isDisplayed()); Thread.sleep(1000);
		 */

	}

//	@Disabled
	@Test
	void miccPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoprovigilance.com/sd.html");

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

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement miccElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'micc.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		action.moveToElement(servicesLink).perform();
		Thread.sleep(100);
		
		//wait until Services dropdown appears
//		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(500);

		//click softwareDevelopment element to go that page
		highlightElement(driver, miccElement);
		miccElement.click();
		Thread.sleep(500);

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String miccURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + miccURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		assertEquals("Medical Information Call Center", mainHeading.getText());
		highlightElement(driver, mainHeading);
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/call-center.png", imageElement.getDomAttribute("src"));
		
		/*
		 * //assert ul, li tags List<WebElement> ulElements =
		 * driver.findElements(By.cssSelector("ul")); assertEquals(5,
		 * ulElements.size()); assertEquals(new Dimension(616,80),
		 * ulElements.get(0).getSize()); assertEquals(new Dimension(124, 36),
		 * ulElements.get(4).getSize());
		 * 
		 * //whether ul tags are displayed or not
		 * assertTrue(ulElements.get(0).isDisplayed());
		 * assertFalse(ulElements.get(1).isDisplayed());
		 * assertFalse(ulElements.get(2).isDisplayed());
		 * assertFalse(ulElements.get(3).isDisplayed());
		 */		
//		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");
//		Thread.sleep(1000);

		/*
		 * highlightElement(driver, ulElements.get(4));
		 * assertTrue(ulElements.get(4).isDisplayed()); Thread.sleep(1000);
		 */
	}

//	@Disabled
	@Test
	void bigDataPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoprovigilance.com/itstaff.html");

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

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement bigDataElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'bigData.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		action.moveToElement(servicesLink).perform();
		Thread.sleep(100);
		
		//wait until Services dropdown appears
//		elementWait.until(d -> servicesDropdownElement.isDisplayed());


		//click ITStaff element to go that page
		highlightElement(driver, bigDataElement);
		bigDataElement.click();
		Thread.sleep(200);

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String bigDataURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + bigDataURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlightElement(driver, mainHeading);
		assertEquals("BigData Analytics", mainHeading.getText());


		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/cp.jpg", imageElement.getDomAttribute("src"));
		
		/*
		 * //assert ul, li tags List<WebElement> ulElements =
		 * driver.findElements(By.cssSelector("ul")); assertEquals(5,
		 * ulElements.size()); assertEquals(new Dimension(616,80),
		 * ulElements.get(0).getSize()); assertEquals(new Dimension(124, 36),
		 * ulElements.get(4).getSize());
		 * 
		 * //whether ul tags are displayed or not
		 * assertTrue(ulElements.get(0).isDisplayed());
		 * assertFalse(ulElements.get(1).isDisplayed());
		 * assertFalse(ulElements.get(2).isDisplayed());
		 * assertFalse(ulElements.get(3).isDisplayed());
		 */
		
		assertTrue(driver.findElement(By.linkText("Novo Provigilance LLC.")).isDisplayed());
//		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		/*
		 * wait.until(ExpectedConditions.visibilityOf(ulElements.get(4)));
		 * highlightElement(driver, ulElements.get(4));
		 * assertTrue(ulElements.get(4).isDisplayed()); Thread.sleep(1000);
		 */
	}
	
//	@Disabled
	@Test
	void lmPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoprovigilance.com/cloud.html");

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

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement lmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'lm.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		action.moveToElement(servicesLink).perform();
		Thread.sleep(500);
		
		//wait until Services dropdown appears
//		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click cloud element to go that page
		highlightElement(driver, lmElement);
		lmElement.click();
		Thread.sleep(500);

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String lmURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + lmURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		assertEquals("Literature Monitoring", mainHeading.getText());
		highlightElement(driver, mainHeading);
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/lm.jpg", imageElement.getDomAttribute("src"));
		
		/*
		 * //assert ul, li tags List<WebElement> ulElements =
		 * driver.findElements(By.cssSelector("ul")); assertEquals(6,
		 * ulElements.size()); assertEquals(new Dimension(616,80),
		 * ulElements.get(0).getSize()); assertEquals(new Dimension(750, 120),
		 * ulElements.get(4).getSize()); assertEquals(new Dimension(124, 36),
		 * ulElements.get(5).getSize());
		 * 
		 * //whether ul tags are displayed or not
		 * assertTrue(ulElements.get(0).isDisplayed());
		 * assertFalse(ulElements.get(1).isDisplayed());
		 * assertFalse(ulElements.get(2).isDisplayed());
		 * assertFalse(ulElements.get(3).isDisplayed());
		 * assertTrue(ulElements.get(4).isDisplayed());
		 */
		
//		Thread.sleep(100);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		/*
		 * wait.until(d-> ulElements.get(5).isDisplayed()); highlightElement(driver,
		 * ulElements.get(5)); assertTrue(ulElements.get(5).isDisplayed());
		 * Thread.sleep(1000);
		 */
	}

//	@Disabled
	@Test
	void sdrmPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoprovigilance.com/ai.html");

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

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement sdrmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'sdrm.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		action.moveToElement(servicesLink).perform();
		Thread.sleep(100);
		
		//wait until Services dropdown appears
//		elementWait.until(d -> servicesDropdownElement.isDisplayed());
//		Thread.sleep(500);

		//click AI/ML element to go that page
		highlightElement(driver, sdrmElement);
		sdrmElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String sdrmURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + sdrmURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlightElement(driver, mainHeading);
		assertEquals("Signal Detection and Risk Management", mainHeading.getText());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/sdrm.jpg", imageElement.getDomAttribute("src"));
		
		/*
		 * //assert ul, li tags List<WebElement> ulElements =
		 * driver.findElements(By.cssSelector("ul")); assertEquals(6,
		 * ulElements.size()); assertEquals(new Dimension(616,80),
		 * ulElements.get(0).getSize()); assertEquals(new Dimension(746, 144),
		 * ulElements.get(4).getSize()); assertEquals(new Dimension(124, 36),
		 * ulElements.get(5).getSize());
		 * 
		 * //whether ul tags are displayed or not
		 * assertTrue(ulElements.get(0).isDisplayed());
		 * assertFalse(ulElements.get(1).isDisplayed());
		 * assertFalse(ulElements.get(2).isDisplayed());
		 * assertFalse(ulElements.get(3).isDisplayed());
		 */
		
//		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		/*
		 * wait.until(ExpectedConditions.visibilityOf(ulElements.get(5)));
		 * highlightElement(driver, ulElements.get(5));
		 * assertTrue(ulElements.get(5).isDisplayed()); Thread.sleep(1000);
		 */
	}

//	@Disabled
	@Test
	void arPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoprovigilance.com/bigData.html");

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

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement arElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'ar.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		action.moveToElement(servicesLink).perform();
		Thread.sleep(100);
		
		//wait until Services dropdown appears
//		elementWait.until(d -> servicesDropdownElement.isDisplayed());
//		Thread.sleep(500);

		//click ar element to go that page
		highlightElement(driver, arElement);
		arElement.click();
		Thread.sleep(500);

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String arURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + arURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlightElement(driver, mainHeading);
		assertEquals("Aggregate Reports", mainHeading.getText());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/ar.jpg", imageElement.getDomAttribute("src"));
		
		
		/*
		 * //assert ul, li tags List<WebElement> ulElements =
		 * driver.findElements(By.cssSelector("ul")); assertEquals(7,
		 * ulElements.size()); assertEquals(new Dimension(616,80),
		 * ulElements.get(0).getSize()); assertEquals(new Dimension(750, 120),
		 * ulElements.get(4).getSize()); assertEquals(new Dimension(750, 120),
		 * ulElements.get(5).getSize()); assertEquals(new Dimension(124, 36),
		 * ulElements.get(6).getSize());
		 * 
		 * //whether ul tags are displayed or not
		 * assertTrue(ulElements.get(0).isDisplayed());
		 * assertFalse(ulElements.get(1).isDisplayed());
		 * assertFalse(ulElements.get(2).isDisplayed());
		 * assertFalse(ulElements.get(3).isDisplayed());
		 * assertTrue(ulElements.get(4).isDisplayed());
		 * assertTrue(ulElements.get(5).isDisplayed());
		 */
		
//		Thread.sleep(100);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		/*
		 * wait.until(d->ulElements.get(6).isDisplayed()); highlightElement(driver,
		 * ulElements.get(6)); assertTrue(ulElements.get(6).isDisplayed());
		 * Thread.sleep(1000);
		 */
	}

//	@Disabled
	@Test
	void HRAPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoprovigilance.com/hra.html");

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

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement hraElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'SAs.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		action.moveToElement(servicesLink).perform();
		Thread.sleep(100);
		
		//wait until Services dropdown appears
//		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		

		//click hra element to go that page
		highlightElement(driver, hraElement);
		hraElement.click();
		Thread.sleep(500);

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String hraURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + hraURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlightElement(driver, mainHeading);
		assertEquals("Healthcare Research & Analysis", mainHeading.getText());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/cp.jpg", imageElement.getDomAttribute("src"));
		
		/*
		 * //assert ul, li tags List<WebElement> ulElements =
		 * driver.findElements(By.cssSelector("ul")); assertEquals(7,
		 * ulElements.size()); assertEquals(new Dimension(616,80),
		 * ulElements.get(0).getSize()); assertEquals(new Dimension(750, 120),
		 * ulElements.get(4).getSize()); assertEquals(new Dimension(750, 120),
		 * ulElements.get(5).getSize()); assertEquals(new Dimension(124, 36),
		 * ulElements.get(6).getSize());
		 * 
		 * //whether ul tags are displayed or not
		 * assertTrue(ulElements.get(0).isDisplayed());
		 * assertFalse(ulElements.get(1).isDisplayed());
		 * assertFalse(ulElements.get(2).isDisplayed());
		 * assertFalse(ulElements.get(3).isDisplayed());
		 * assertTrue(ulElements.get(4).isDisplayed());
		 * assertTrue(ulElements.get(5).isDisplayed());
		 */
		
//		Thread.sleep(100);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		/*
		 * wait.until(d-> ulElements.get(6).isDisplayed()); highlightElement(driver,
		 * ulElements.get(6)); assertTrue(ulElements.get(6).isDisplayed());
		 * Thread.sleep(1000);
		 */
	}

//	@Disabled
	@Test
	void mspvmsPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoprovigilance.com/itpm.html");

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

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement mspvmsElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'mspvms.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		action.moveToElement(servicesLink).perform();
		Thread.sleep(100);
		
		//wait until Services dropdown appears
//		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(500);

		//clicck AI/ML element to go that page
		highlightElement(driver, mspvmsElement);
		mspvmsElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String mspvmsURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + mspvmsURL);		
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlightElement(driver, mainHeading);
		assertEquals("Managed Service Provider/Vendor Management System/Payroll", mainHeading.getText());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/clinical.png", imageElement.getDomAttribute("src"));
		
		/*
		 * //assert ul, li tags List<WebElement> ulElements =
		 * driver.findElements(By.cssSelector("ul")); assertEquals(6,
		 * ulElements.size()); assertEquals(new Dimension(616,80),
		 * ulElements.get(0).getSize()); assertEquals(new Dimension(750, 168),
		 * ulElements.get(4).getSize()); assertEquals(new Dimension(124, 36),
		 * ulElements.get(5).getSize());
		 * 
		 * //whether ul tags are displayed or not
		 * assertTrue(ulElements.get(0).isDisplayed());
		 * assertFalse(ulElements.get(1).isDisplayed());
		 * assertFalse(ulElements.get(2).isDisplayed());
		 * assertFalse(ulElements.get(3).isDisplayed());
		 * assertTrue(ulElements.get(4).isDisplayed());
		 */
		
//		Thread.sleep(100);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		/*
		 * wait.until(ExpectedConditions.visibilityOf(ulElements.get(5)));
		 * highlightElement(driver, ulElements.get(5));
		 * assertTrue(ulElements.get(5).isDisplayed()); Thread.sleep(1000);
		 */
	}

}
