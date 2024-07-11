package testwithselinuim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class MainPage {

//	WebDriver driver;
//	WebDriverWait wait, imageSliderWait, imageWait, elementWait, initialWait;
//	RemoteWebDriver remotedriver; // it includes JavascriptExecutor, TakesScreenshot functionality
//	Actions action;
//	JavascriptExecutor jsScript;
//	SeleniumManager driverManager;
//	ChromeOptions chromeoptions;
//	Highlight highlightElement;
	
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
	public void setUp() throws Exception {
//		driver = new ChromeDriver();
//		EdgeOptions edgeOptions = new EdgeOptions();		
//		edgeOptions.addArguments("start-maximized");
//		driver = new EdgeDriver(edgeOptions);

//		chromeoptions = new ChromeOptions();
//		driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeoptions);
//		chromeoptions.addArguments("start-maximized");
//		driver = new ChromeDriver(chromeoptions);

//		remotedriver = new ChromeDriver();
//		action = new Actions(driver);
//		jsScript = (JavascriptExecutor) driver;
		// navigate to URL
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		driver.get("https://novoprovigilance.com/");
	}

	@AfterEach
	public void tearDown() throws Exception {
		// has to do later
//		driver.quit();
//		remotedriver.quit();
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript(
				"arguments[0].setAttribute('style', 'border: 2px solid red;')", element);
	}
	
	public void elementWait(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
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
	
	public void servicesLink(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public void closeUp(WebDriver driver) {
		synchronized (driver) {
			try {
				driver.wait(2000);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	public void productsLink(WebDriver driver) {
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
	void cookiePolicyTest() throws InterruptedException {
		

		elementWait = new WebDriverWait(driver, Duration.ofMillis(500));
		elementWait.until(d -> driver.findElement(By.className("cookie-container")).isDisplayed());
		assertEquals("rgba(47, 54, 64, 1)",
				driver.findElement(By.className("cookie-container")).getCssValue("background-color"));
		assertEquals("rgba(245, 246, 250, 1)",
				driver.findElement(By.className("cookie-container")).getCssValue("color"));
		System.out.println("P element in cookie policy: " + driver.findElements(By.xpath("//p")).get(0).getText());
		System.out.println("a element in cookie policy: " + driver.findElements(By.xpath("//p/a")).get(0).getText());
		Thread.sleep(200);
		action.moveToElement(driver.findElements(By.xpath("//p/a")).get(0)).perform();
		Thread.sleep(200);
		assertEquals("rgba(252, 208, 93, 1)", driver.findElements(By.xpath("//p/a")).get(0).getCssValue("color"));
		assertEquals("policy.html", driver.findElements(By.xpath("//p/a")).get(0).getDomAttribute("href"));
		WebElement btnElement = driver.findElement(By.xpath("//p/button"));
		highlightElement(driver, driver.findElements(By.xpath("//p/a")).get(0));
		highlightElement(driver, btnElement);
		WebElement cookieLocation = driver.findElement(By.className("cookie-container"));
		System.out.println("before btn click: " + cookieLocation.getRect());
		System.out.println("before btn click: " + cookieLocation.getLocation());
		btnElement.click();
		WebElement cookieNewLocation = driver.findElement(By.className("cookie-container"));
		System.out.println("after btn click: " + cookieNewLocation.getRect());
		System.out.println("after btn click: " + cookieNewLocation.getLocation());
//		assertTrue(cookieNewLocation.getLocation().equals(cookieLocation.getLocation()));
	}

//	@Disabled
	@Test
	public void headerEvents() throws InterruptedException {

		

		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));

		// start button check

		imageWait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		Thread.sleep(200);
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(100);

		/*
		 * List<WebElement> button = driver.findElements(By.className("btn"));
		 * System.out.println(button); assertEquals(13, button.size());
		 * assertEquals("START NOW", button.get(0).getText()); Thread.sleep(1000);
		 * 
		 * assertFalse(button.get(1).isDisplayed()); Thread.sleep(1000);
		 * 
		 * button.get(0).click(); Thread.sleep(1000);
		 */

		WebElement homeLink = driver.findElement(By.xpath("//li/a[@href='#home']"));
//		System.out.println("home nav element : " + homeLink.getText());
		highlightElement(driver, homeLink);

		// assert title

		String title = driver.getTitle();
		assertEquals("NOVO PROVIGILANCE LLC", title);

		Thread.sleep(500);
		// close cookies click
//		WebElement closeUp = driver.findElement(By.xpath("//button[contains(@class,'cookie-btn')]"));
//		closeUp.click();
//		Thread.sleep(200);

		// mouse hover NPV
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebElement NPV = driver.findElement(By.linkText("NOVO PROVIGILANCE LLC"));
		Thread.sleep(100);

		// mouse action NPV
		action.moveToElement(NPV).perform();
		Thread.sleep(100);

		WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[@href='#about-us']"));
		WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		elementWait.until(ExpectedConditions.visibilityOf(aboutUsLink));
		System.out.println("about us nav element : " + aboutUsLink.getText());
		assertEquals("rgba(0, 0, 0, 0)", aboutUsLink.getCssValue("background-color"));
		highlightElement(driver, aboutUsLink);
		action.moveToElement(aboutUsLink).perform();
		elementWait.until(d -> aboutUsDropdownElement.isDisplayed());
		WebElement csrElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'csr.html')]"));
		action.moveToElement(csrElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", csrElement.getCssValue("color"));
		assertEquals("csr.html", csrElement.getDomAttribute("href"));
		highlightElement(driver, csrElement);

		Thread.sleep(100);
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		highlightElement(driver, productsLink);
		action.moveToElement(productsLink).perform();
		System.out.println("products nav element : " + productsLink.getText());
		WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		elementWait.until(d -> productsDropdownElement.isDisplayed());
		Thread.sleep(100);
		WebElement danElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'DSAN.html')]"));
		action.moveToElement(danElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", danElement.getCssValue("color"));
		assertEquals("DSAN.html", danElement.getDomAttribute("href"));
		highlightElement(driver, danElement);

		Thread.sleep(100);
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		System.out.println("services nav element : " + servicesLink.getText());
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(100);
		WebElement cpElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href,'cp.html')]"));
		WebElement miccElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'micc.html')]"));
		WebElement bigDataElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'bigData.html')]"));
		WebElement lmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'lm.html')]"));
		WebElement sdrmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'sdrm.html')]"));
		WebElement arElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'ar.html')]"));
		WebElement SAsElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'SAs.html')]"));
		WebElement mspvmsElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href,'mspvms.html')]"));

		action.moveToElement(cpElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", cpElement.getCssValue("color"));
		assertEquals("cp.html", cpElement.getDomAttribute("href"));
		highlightElement(driver, cpElement);
		action.moveToElement(miccElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", miccElement.getCssValue("color"));
		assertEquals("micc.html", miccElement.getDomAttribute("href"));
		highlightElement(driver, miccElement);
		action.moveToElement(bigDataElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", bigDataElement.getCssValue("color"));
		assertEquals("bigData.html", bigDataElement.getDomAttribute("href"));
		highlightElement(driver, bigDataElement);
		action.moveToElement(lmElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", lmElement.getCssValue("color"));
		assertEquals("lm.html", lmElement.getDomAttribute("href"));
		highlightElement(driver, lmElement);
		action.moveToElement(sdrmElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", sdrmElement.getCssValue("color"));
		assertEquals("sdrm.html", sdrmElement.getDomAttribute("href"));
		highlightElement(driver, sdrmElement);
		action.moveToElement(arElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", arElement.getCssValue("color"));
		assertEquals("ar.html", arElement.getDomAttribute("href"));
		highlightElement(driver, arElement);
		action.moveToElement(SAsElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", SAsElement.getCssValue("color"));
		assertEquals("SAs.html", SAsElement.getDomAttribute("href"));
		highlightElement(driver, SAsElement);
		action.moveToElement(mspvmsElement).perform();
		Thread.sleep(200);
		assertEquals("rgba(32, 158, 72, 1)", mspvmsElement.getCssValue("color"));
		assertEquals("mspvms.html", mspvmsElement.getDomAttribute("href"));
		highlightElement(driver, mspvmsElement);

		Thread.sleep(100);
		WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
		System.out.println("careers nav element : " + careersLink.getText());
		highlightElement(driver, careersLink);
		action.moveToElement(careersLink).perform();
		Thread.sleep(100);

		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		highlightElement(driver, contactLink);
		System.out.println("contact us nav element : " + contactLink.getText());
		action.moveToElement(contactLink).perform();
		Thread.sleep(100);

		Thread.sleep(100);
		WebElement careersElement = driver.findElement(By.xpath("//li/a[@href='#career']"));
		System.out.println("careers nav element : " + careersLink.getText());
		highlightElement(driver, careersLink);
		action.moveToElement(careersLink).perform();
		Thread.sleep(100);

		WebElement contactElement = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		highlightElement(driver, contactLink);
		System.out.println("contact us nav element : " + contactLink.getText());
		action.moveToElement(contactLink).perform();
		Thread.sleep(100);

	}

//	@Disabled
	@Test
	void aboutUsSectionTest() throws InterruptedException {
		

		//
		wait = new WebDriverWait(driver, Duration.ofMillis(600));

		//

		// fetch elements from image block
		WebElement startnow = driver.findElement(By.linkText("START NOW"));
		highlightElement(driver, startnow);

		// start now button upon image
		wait.until(ExpectedConditions.visibilityOf(startnow));
		startnow.click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("about-us"))));
		assertTrue(driver.findElement(By.className("main-nav")).isDisplayed());
		assertTrue(driver.findElement(By.className("cookie-container")).isDisplayed());

		assertEquals("url(\"https://novoprovigilance.com/images/about-bg.jpg\")",
				driver.findElement(By.id("about-us")).getCssValue("background-image"));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("h2"))));
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.linkText("ABOUT US")).getCssValue("background-color"));
//		assertEquals("About us", driver.findElement(By.xpath("//h2[.='About us']")).getText());
		assertEquals("About us", driver.findElements(By.cssSelector("h2")).get(0).getText());
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(0).getCssValue("color"));
		assertEquals("Core Values", driver.findElements(By.cssSelector("h2")).get(1).getText());
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(1).getCssValue("color"));
		assertEquals("Certifications", driver.findElements(By.cssSelector("h2")).get(2).getText());
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(2).getCssValue("color"));
		assertEquals(30, driver.findElements(By.cssSelector("ul")).size());
		assertEquals(159, driver.findElements(By.cssSelector("li")).size());
	}

//	@Disabled
	@Test
	void productsSectionTest() throws InterruptedException {
//		

		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));

		imageWait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		Thread.sleep(200);
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to products link and click
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		highlightElement(driver, productsLink);
		System.out.println("products nav element : " + productsLink.getText());
		action.moveToElement(productsLink).perform();
//		Thread.sleep(500);
//		productsLink.click();

		// From products section
		WebElement productsHeading = driver.findElement(By.xpath("//b[contains(text(),'::Products::')]"));
		// wait until products section is visible
		elementWait.until(d -> productsHeading.isDisplayed());

		// assert main heading
		highlightElement(driver, productsHeading);
		System.out.println("Main Heading in Products: " + productsHeading);
		assertEquals("::Products::", productsHeading.getText());

		// sub heading
		WebElement subHeading = driver.findElement(By.xpath("//h4[contains(text(),'::Projects under development::')]"));
		System.out.println("Sub Heading in Products: " + subHeading);
		assertEquals("::Projects under development::", subHeading.getText());
		assertEquals("rgba(51, 51, 51, 1)", subHeading.getCssValue("color"));
		highlightElement(driver, subHeading);

		// get product elements
		WebElement productImage = driver.findElement(By.xpath("//img[contains(@src,'images/net1.png')]"));
		WebElement productName = driver
				.findElement(By.xpath("//span[contains(text(),'Drug Safety Analytics by Novo Provigilance - DSAN')]"));

		WebElement productContent = driver.findElement(By.xpath(
				"//p[contains(text(),'Data Analytics is a predictive, transforming, and mining, used for decision making, data visualization from unstructured to structured data...')]"));
		WebElement productReadMoreLink = driver
				.findElement(By.xpath("//div[contains(@class,'service-info')]/p/a[contains(@href,'DSAN.html')]"));

//			elementWait.until(d -> product.isDisplayed());
		System.out.println("Product Image: " + productImage.getDomAttribute("src"));
		System.out.println("Product Name: " + productName.getText());

		System.out.println("Product Content: " + productContent.getText());
		System.out.println("Product Readmore Link: " + productReadMoreLink.getDomAttribute("href"));

		// Checking Styles
		assertEquals("rgba(57, 144, 30, 1)", productName.getCssValue("color"));
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath(
				"//p[contains(text(),'Data Analytics is a predictive, transforming, and mining, used for decision making, data visualization from unstructured to structured data...')]"))
				.getCssValue("background-color"));
		assertEquals("rgba(102, 102, 102, 1)", productContent.getCssValue("color"));
		assertEquals("rgba(255, 255, 255, 1)", productReadMoreLink.getCssValue("color"));

		highlightElement(driver, productImage);
		highlightElement(driver, productName);
		highlightElement(driver, productContent);

		// hover over readmore link
		action.moveToElement(productReadMoreLink).perform();
//		Thread.sleep(600);
		assertEquals("rgba(255, 255, 255, 1)", productReadMoreLink.getCssValue("color"));
		assertEquals("0px none rgb(255, 255, 255)", productReadMoreLink.getCssValue("border"));
		highlightElement(driver, productReadMoreLink);
//		Thread.sleep(2000);
	}

//	@Disabled
	@Test
	void productsSectionLinksTest() throws InterruptedException {
		

		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));

		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to products link and click
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		highlightElement(driver, productsLink);
		System.out.println("products nav element : " + productsLink.getText());
		action.moveToElement(productsLink).perform();
		Thread.sleep(500);
		productsLink.click();

		// From products section
		WebElement productsHeading = driver.findElement(By.xpath("//b[contains(text(),'::Products::')]"));
		// wait until products section is visible
		elementWait.until(d -> productsHeading.isDisplayed());

		// get product elements
		WebElement productReadMoreLink = driver
				.findElement(By.xpath("//div[contains(@class,'service-info')]/p/a[contains(@href,'DSAN.html')]"));
		
		Thread.sleep(500);
		// hover over first product read more link
//		action.moveToElement(productReadMoreLink).perform();
		Thread.sleep(500);
		productReadMoreLink.click();
		String url = driver.getCurrentUrl();
		System.out.println("Current URL: " + url);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		Thread.sleep(200);
		wait.until(d -> driver.findElement(By.xpath("//b[contains(text(),'::Products::')]")).isDisplayed());

	}

//	@Disabled
	@Test
	void ServicesSectionTest() throws InterruptedException {
		

		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		Thread.sleep(200);
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));

		imageWait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to services link and click
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		highlightElement(driver, servicesLink);
		System.out.println("services nav element : " + servicesLink.getText());
		action.moveToElement(servicesLink).perform();

		Thread.sleep(200);
		servicesLink.click();

		// From services section
		WebElement servicesHeading = driver.findElement(By.xpath("//b[contains(text(),'::Services::')]"));
		// wait until products section is visible
//		elementWait.until(d -> servicesHeading.isDisplayed());

		// assert main heading
		highlightElement(driver, servicesHeading);
//		Thread.sleep(1000);
//		System.out.println("Main Heading in Services: " + servicesHeading);
//		assertEquals("::Services::", servicesHeading.getText());

		// get services elements
		List<WebElement> services = driver.findElements(
				By.xpath("//section[contains(@id,'services')]/div/div/div/div[contains(@class,'service')]")); //8
		List<WebElement> serviceImage = driver.findElements(
				By.xpath("//section[contains(@id,'services')]/div/div/div/div/div[contains(@class,'service-icon')]/img"));//8
		//div[contains(@class,'our-services')]/div/div/div[contains(@class,'service-icon')]/img//9
		List<WebElement> serviceName = driver.findElements(
				By.xpath("//div[contains(@class,'our-services')]/div/div/div[contains(@class,'service-info')]/p/span"));
		List<WebElement> serviceContent = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));//25
		List<WebElement> serviceReadMoreLink = driver
				.findElements(By.xpath("//section[contains(@id,'services')]/div/div[contains(@class,'service')]/div/div/div/p/a"));//8
		//div[contains(@class,'service-info')]/p/a//9

		for (WebElement service : services) {
			int index = services.indexOf(service);
			System.out.println("index : " + index);
			if (!service.isDisplayed()) {
				System.out.println("index: " + index);
				jsExecutor.executeScript("arguments[0].scrollIntoView();", service);
			}
			Thread.sleep(200);
			elementWait.until(d -> service.isDisplayed());

			System.out.println("Service Image: " + serviceImage.get(index).getDomAttribute("src"));
			System.out.println("Service Name: " + serviceName.get(index).getText());
			System.out.println("Service Content: " + serviceContent.get(((index) * 3) + 2).getText());
			System.out.println("Service Readmore Link: " + serviceReadMoreLink.get(index).getText());
			System.out.println("Service Readmore Link: " + serviceReadMoreLink.get(index).getDomAttribute("href"));

			highlightElement(driver, serviceImage.get(index));
			highlightElement(driver, serviceName.get(index));
			highlightElement(driver, serviceContent.get(((index) * 3) + 2));

			// hover over readmore link
			action.moveToElement(serviceReadMoreLink.get(index)).perform();
//			Thread.sleep(800);
			highlightElement(driver, serviceReadMoreLink.get(index));
//			Thread.sleep(2000);
		}
	}
	
//	@Disabled
	@Test
	void servicesSectionLinksTest() throws InterruptedException {
		

		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));

		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to services link and click
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		highlightElement(driver, servicesLink);
		System.out.println("services nav element : " + servicesLink.getText());
		action.moveToElement(servicesLink).perform();
		Thread.sleep(500);
		servicesLink.click();

		// From services section
		WebElement servicesHeading = driver.findElement(By.xpath("//b[contains(text(),'::Services::')]"));
		// wait until products section is visible
		wait.until(d -> servicesHeading.isDisplayed());

		// get services elements
		List<WebElement> services = driver.findElements(
				By.xpath("//section[contains(@id,'services')]/div/div/div/div[contains(@class,'service')]"));//8
		List<WebElement> serviceReadMoreLink = driver
				.findElements(By.xpath("//section[contains(@id,'services')]/div/div[contains(@class,'service')]/div/div/div/p/a"));//8
		elementWait.until(d -> services.get(0).isDisplayed());
		
		for (WebElement service : services) {
			int index = services.indexOf(service);
			WebElement newService = driver.findElements(
					By.xpath("//section[contains(@id,'services')]/div/div/div/div[contains(@class,'service')]")).get(index);
			WebElement newserviceslink = driver.findElements(By.xpath("//section[contains(@id,'services')]/div/div[contains(@class,'service')]/div/div/div/p/a")).get(index);
//			Thread.sleep(1000);
			System.out.println("index: " + index);
			jsExecutor.executeScript("arguments[0].scrollIntoView();", newService);
//			Thread.sleep(1000);
			jsExecutor.executeScript("window.scrollBy(0, -150)");
//			Thread.sleep(3000);				
								
			highlightElement(driver, newService.findElement(By.xpath("div/img")));
			System.out.println("Heading: " + newService.findElements(By.xpath("div/p")).get(0).getText());
			highlightElement(driver, newService.findElements(By.xpath("div/p")).get(0));
			
			System.out.println("Content: " + newService.findElements(By.xpath("div/p")).get(1).getText());
			highlightElement(driver, newService.findElements(By.xpath("div/p")).get(1));
			
			System.out.println("Read More Link: " + newService.findElements(By.xpath("div/p")).get(2).getText());
			Thread.sleep(500);
			highlightElement(driver, newService.findElements(By.xpath("div/p")).get(2));
			wait.until(ExpectedConditions.elementToBeClickable(newserviceslink));
			Thread.sleep(500);
			newserviceslink.click();
			Thread.sleep(500);
			driver.navigate().back();
			

		}
		
	}
		
//	@Disabled
	@Test
	void careersSectionTest() throws InterruptedException {
		

		
		
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));

		imageWait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		
		Thread.sleep(500);
		// close cookie
//		driver.findElement(By.xpath("//button[contains(@class,'cookie-btn')]")).click();
		Thread.sleep(500);
		highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to careers link and click
		WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
		highlightElement(driver, careersLink);
		System.out.println("careers nav element : " + careersLink.getText());
		action.moveToElement(careersLink).perform();
		Thread.sleep(200);
		careersLink.click();

		// From careers section
		WebElement careersHeading = driver.findElement(By.xpath("//h2[contains(b,'::Careers::')]"));
		Thread.sleep(200);
		// wait until careers section is visible
		elementWait.until(d -> careersHeading.isDisplayed());

		

		// heading block background color
//		assertEquals("rgb(220, 245, 214)",
//				driver.findElement(By.xpath("//div[contains(@class,'row')]/div[contains(@class,'heading')]"))
//						.getCssValue("background-color"));//2

		// assert main heading
		highlightElement(driver, careersHeading);
		System.out.println("Main Heading in Careers: " + careersHeading.getText());
		assertEquals("::Careers::", careersHeading.getText());

		// sub heading info
		WebElement subHeading = driver.findElement(By.xpath("//section[contains(@id,'career')]/div/div/div/h4"));
//		assertEquals("rgb(57, 144, 30)", subHeading.getCssValue("color"));
		highlightElement(driver, subHeading);
		System.out.println("::Work with us and Grow with us::" + subHeading.getText());

		// resume contact
		WebElement contactMailElement = driver.findElements(By.xpath("//section[contains(@id,'career')]/div/div/div/h4")).get(1);
//		assertEquals("rgb(128, 128, 128)", contactMailElement.getCssValue("color"));
//		assertEquals("rgb(57, 144, 30)", driver
//				.findElement(By.xpath("//section[contains(@id,'career')]/div/div/div/h4/span")).getCssValue("color"));
//		assertEquals("600", driver.findElement(By.xpath("//section[contains(@id,'career')]/div/div/div/h4/span"))
//				.getCssValue("font-weight"));
		highlightElement(driver, contactMailElement);
		System.out.println("Send Your resume to " + contactMailElement.getText());

		// get career elements
		List<WebElement> careers = driver.findElements(By.xpath("//div[contains(@class,'table-container')]"));
		List<WebElement> designation = driver.findElements(By.xpath("//div[contains(@class,'table-container')]/table/tbody/tr"));

		for (WebElement career : careers) {
			int index = careers.indexOf(career);
//			System.out.println(career.getText());
//			Thread.sleep(1000);
			System.out.println("index: " + index);
			jsExecutor.executeScript("arguments[0].scrollIntoView();", career);
//			Thread.sleep(1000);
			jsExecutor.executeScript("window.scrollBy(0, -150)");
//			Thread.sleep(3000);				
					
			List<WebElement> tablerows = career.findElements(By.xpath("table/tbody/tr"));
			
			for (WebElement row : tablerows) {
				highlightElement(driver, row);
				System.out.println(row.getText());
			}

	
		}
	}

//	@Disabled
	@Test
	void ContactSectionTest() throws InterruptedException {
		

		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));

		imageWait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to contact us link and click
		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		highlightElement(driver, contactLink);
		System.out.println("contact nav element : " + contactLink.getText());
		action.moveToElement(contactLink).perform();
//		Thread.sleep(500);
//		contactLink.click();

		// From contact us section
		WebElement contactUsHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2"));
		WebElement subHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/p"));

		// wait until contact us section is visible
//		elementWait.until(d -> contactUsHeading.isDisplayed());

		// assert image
		assertEquals("url(\"https://novoprovigilance.com/images/contact-bg.jpg\")",
				driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]")).getCssValue("background-image"));

		// assert main heading
		highlightElement(driver, contactUsHeading);
		System.out.println("Main Heading in Contact us: " + contactUsHeading.getText());
//		assertEquals("Contact Us", contactUsHeading.getText());

		// assert main heading
		highlightElement(driver, subHeading);
		System.out.println("SubHeading in Contact us: " + subHeading.getText());

		WebElement conatctFormBlock = driver.findElements(By.xpath("//div[contains(@class, 'contact-form')]/div/div"))
				.get(0);
		WebElement addressBlock = driver.findElements(By.xpath("//div[contains(@class, 'contact-form')]/div/div"))
				.get(1);

		System.out.println("contact us location: " + conatctFormBlock.getLocation());
		System.out.println("Address block location" + addressBlock.getLocation());

		// address
		WebElement address = driver.findElements(By.xpath("//div[contains(@class, 'contact-info')]/ul/li")).get(0);
		WebElement phone = driver.findElements(By.xpath("//div[contains(@class, 'contact-info')]/ul/li")).get(1);
		WebElement emailAddress = driver.findElements(By.xpath("//div[contains(@class, 'contact-info')]/ul/li")).get(2);
		highlightElement(driver, address);
		System.out.println("Address given: " + address.getText());
		highlightElement(driver, phone);
		System.out.println("Phone number : " + phone.getText());
		highlightElement(driver, emailAddress);
		System.out.println("Email address : " + emailAddress.getText());

//		Thread.sleep(3000);

		// map
		/*
		 * WebElement mapElement = driver.findElement(By.
		 * xpath("//div[contains(@class, 'contact-info')]/ul/li/iframe"));
		 * driver.findElement(By.
		 * xpath("//div[contains(@class, 'contact-info')]/ul/li/iframe"));
		 * driver.switchTo().frame(mapElement);
		 */

	}

//	@Disabled
	@Test
	void footerEvents() throws InterruptedException {
		

		// scroll down
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//		Thread.sleep(1000);

		// hover action

		WebElement footerTitle = driver.findElement(By.linkText("NOVO PROVIGILANCE LLC"));
		action.moveToElement(footerTitle).perform();
//		Thread.sleep(1000);

		// social links

		WebElement twitter = driver.findElement(By.className("twitter"));
		action.moveToElement(twitter).perform();
		Thread.sleep(200);
		assertEquals("rgba(0, 0, 0, 1)", driver.findElement(By.className("twitter")).getCssValue("background-color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("twitter")).getCssValue("color"));
//		Thread.sleep(1000);

		WebElement fBook = driver.findElement(By.className("facebook"));
		action.moveToElement(fBook).perform();
		Thread.sleep(200); // color check
		assertEquals("rgba(59, 89, 153, 1)",
				driver.findElement(By.className("facebook")).getCssValue("background-color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("facebook")).getCssValue("color"));

		WebElement lIn = driver.findElement(By.className("linkedin"));
		action.moveToElement(lIn).perform();
		Thread.sleep(200); // color check
		assertEquals("rgba(3, 109, 192, 1)",
				driver.findElement(By.className("linkedin")).getCssValue("background-color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("linkedin")).getCssValue("color"));

		// switch to tabs // close switch tabs

		// disclaimer WebElement policy =
		driver.findElement(By.linkText("Disclaimer, Cookie & Privacy Policy"));
//		  action.moveToElement(policy).perform(); Thread.sleep(1000); // color check
		assertEquals("rgba(0, 0, 0, 0)",
				driver.findElement(By.linkText("Disclaimer, Cookie & Privacy Policy")).getCssValue("background-color"));
		assertEquals("rgba(255, 255, 255, 1)",
				driver.findElement(By.linkText("Disclaimer, Cookie & Privacy Policy")).getCssValue("color")); // close
																												// cookies
																												// click
																												// WebElement
																												// closeUp
																												// =
		driver.findElement(By.className("cookie-btn"));
//		  closeUp.click();

		// footerbar Thread.sleep(1000); WebElement copyrights =
		driver.findElement(By.linkText("Novo Provigilance LLC."));
//		  action.moveToElement(copyrights).build().perform();
		assertEquals("rgba(8, 8, 8, 1)",
				driver.findElement(By.linkText("Novo Provigilance LLC.")).getCssValue("color")); // // underline // //
																									// color check
		assertEquals("none solid rgb(8, 8, 8)",
				driver.findElement(By.linkText("Novo Provigilance LLC.")).getCssValue("text-decoration"));

		// recaptcha

		/*
		 * WebElement recaptCha =
		 * driver.findElement(By.id("recaptcha-accessible-status"));
		 * recaptCha.isDisplayed(); action.moveToElement(recaptCha).build().perform();
		 * 
		 * action.click();
		 */
	}

	/*
	 * Map<String, Object> mockLocation = new HashMap<String, Object>();
	 * mockLocation.put("latitude", 39.03565);
	 * mockLocation.put("longitude",-93.73178); mockLocation.put("accuracy",1);
	 * 
	 * ((ChromiumDriver)
	 * driver).executeCdpCommand("Page.setGeolocationOverride",mockLocation);
	 * driver.navigate().refresh(); driver.navigate().to("http://novoproso.com/");
	 */
	
}
