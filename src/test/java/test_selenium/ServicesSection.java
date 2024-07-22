package test_selenium;

import static org.junit.jupiter.api.Assertions.*;


import java.time.Duration;


import org.junit.jupiter.api.*;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
class ServicesSection {

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
	
//	@Disabled
	@Test
	void cpPageTest() throws InterruptedException {
		
		
		//From home page
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();

		//Thread.sleep(100);
		wait.until(d -> driver.findElement(By.xpath("//li/a[contains(text(),'About Us')]")).isDisplayed());
		highlight.highlightElement(driver, driver.findElement(By.xpath("//li/a[contains(text(),'About Us')]")));
		
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[contains(@href,'#services')]"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement cpElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'cp.html')]"));

		actions.moveToElement(driver.findElement(By.xpath("//li/a[contains(@href,'#services')]"))).perform();
		actions.moveToElement(driver.findElement(By.xpath("//li/a[contains(@href,'#services')]"))).perform();
		actions.moveToElement(driver.findElement(By.xpath("//li/a[contains(@href,'#services')]"))).perform();
		highlight.highlightElement(driver, driver.findElement(By.xpath("//li/a[contains(@href,'#services')]")));
		wait.until(d -> servicesDropdownElement.isDisplayed());
				
		highlight.highlightElement(driver, cpElement);
		cpElement.click();

		//wait until page loads
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String cpURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + cpURL);

		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo Provigilance LLC.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlight.highlightElement(driver, mainHeading);
		assertEquals("Case Processing", mainHeading.getText());
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlight.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/cp.jpg", imageElement.getDomAttribute("src"));
		
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//		Thread.sleep(100);
	}

//	@Disabled
	@Test
	void miccPageTest() throws InterruptedException {

		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(200);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement miccElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'micc.html')]"));
		
		//wait until Services link appears and hover over it
		wait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlight.highlightElement(driver, servicesLink);
		actions.moveToElement(servicesLink).perform();
		actions.moveToElement(servicesLink).perform();
		Thread.sleep(100);
		
		//wait until Services dropdown appears
		Thread.sleep(500);

		//click softwareDevelopment element to go that page
		highlight.highlightElement(driver, miccElement);
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
		highlight.highlightElement(driver, mainHeading);
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlight.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/call-center.png", imageElement.getDomAttribute("src"));
		
	}

//	@Disabled
	@Test
	void bigDataPageTest() throws InterruptedException {
	
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//From home page
		driver.get("https://novoprovigilance.com");
		
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(200);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement bigDataElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'bigData.html')]"));
		
		//wait until Services link appears and hover over it
		wait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlight.highlightElement(driver, servicesLink);
		actions.moveToElement(servicesLink).perform();
		actions.moveToElement(servicesLink).perform();
		Thread.sleep(100);
		
		//click ITStaff element to go that page
		wait.until(ExpectedConditions.visibilityOf(bigDataElement));
		highlight.highlightElement(driver, bigDataElement);
		wait.until(ExpectedConditions.elementToBeClickable(bigDataElement));
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
		highlight.highlightElement(driver, mainHeading);
		assertEquals("BigData Analytics", mainHeading.getText());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlight.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/cp.jpg", imageElement.getDomAttribute("src"));
		
		assertTrue(driver.findElement(By.linkText("Novo Provigilance LLC.")).isDisplayed());
//		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

	}
	
//	@Disabled
	@Test
	void lmPageTest() throws InterruptedException {

		//From home page
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(200);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement lmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'lm.html')]"));
		
		//wait until Services link appears and hover over it
		wait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlight.highlightElement(driver, servicesLink);
		actions.moveToElement(servicesLink).perform();
		actions.moveToElement(servicesLink).perform();
		Thread.sleep(500);
		
		//click cloud element to go that page
		highlight.highlightElement(driver, lmElement);
		wait.until(ExpectedConditions.elementToBeClickable(lmElement));
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
		highlight.highlightElement(driver, mainHeading);
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlight.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/lm.jpg", imageElement.getDomAttribute("src"));
		
//		Thread.sleep(100);
		jsExecutor.executeScript("window.scrollBy(0,600)");

	}

//	@Disabled
	@Test
	void sdrmPageTest() throws InterruptedException {
		
		//From home page
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(200);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement sdrmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'sdrm.html')]"));
		
		//wait until Services link appears and hover over it
		wait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlight.highlightElement(driver, servicesLink);
		actions.moveToElement(servicesLink).perform();
		actions.moveToElement(servicesLink).perform();
		Thread.sleep(100);

		//click AI/ML element to go that page
		highlight.highlightElement(driver, sdrmElement);
		wait.until(ExpectedConditions.elementToBeClickable(sdrmElement));
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
		highlight.highlightElement(driver, mainHeading);
		assertEquals("Signal Detection and Risk Management", mainHeading.getText());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlight.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/sdrm.jpg", imageElement.getDomAttribute("src"));
		
//		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

	}

//	@Disabled
	@Test
	void arPageTest() throws InterruptedException {
	
		//From home page
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(200);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement arElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'ar.html')]"));
		
		//wait until Services link appears and hover over it
		wait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlight.highlightElement(driver, servicesLink);
		actions.moveToElement(servicesLink).perform();
		actions.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//click ar element to go that page
		
		
		highlight.highlightElement(driver, arElement);
		wait.until(ExpectedConditions.elementToBeClickable(arElement));
		Thread.sleep(2000);
		arElement.click();
		Thread.sleep(1000);

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
		highlight.highlightElement(driver, mainHeading);
		assertEquals("Aggregate Reports", mainHeading.getText());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlight.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/ar.jpg", imageElement.getDomAttribute("src"));
		
//		Thread.sleep(100);
		jsExecutor.executeScript("window.scrollBy(0,600)");

	}

//	@Disabled
	@Test
	void HRAPageTest() throws InterruptedException {

		//From home page
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(200);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement hraElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'SAs.html')]"));
		
		//wait until Services link appears and hover over it
		wait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlight.highlightElement(driver, servicesLink);
		actions.moveToElement(servicesLink).perform();
		actions.moveToElement(servicesLink).perform();
		Thread.sleep(100);
		
		//click hra element to go that page
		highlight.highlightElement(driver, hraElement);
		wait.until(ExpectedConditions.elementToBeClickable(hraElement));
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
		highlight.highlightElement(driver, mainHeading);
		assertEquals("Healthcare Research & Analysis", mainHeading.getText());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlight.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/cp.jpg", imageElement.getDomAttribute("src"));
		
//		Thread.sleep(100);
		jsExecutor.executeScript("window.scrollBy(0,600)");
		
	}

//	@Disabled
	@Test
	void mspvmsPageTest() throws InterruptedException {
	
		//From home page
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(200);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement mspvmsElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'mspvms.html')]"));
		
		//wait until Services link appears and hover over it
		wait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlight.highlightElement(driver, servicesLink);
		actions.moveToElement(servicesLink).perform();
		actions.moveToElement(servicesLink).perform();
		Thread.sleep(100);

		//click AI/ML element to go that page
		highlight.highlightElement(driver, mspvmsElement);
		wait.until(ExpectedConditions.elementToBeClickable(mspvmsElement));
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
		highlight.highlightElement(driver, mainHeading);
		assertEquals("Managed Service Provider/Vendor Management System/Payroll", mainHeading.getText());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlight.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/clinical.png", imageElement.getDomAttribute("src"));
		
//		Thread.sleep(100);
		jsExecutor.executeScript("window.scrollBy(0,600)");

	}
}
