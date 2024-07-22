package test_selenium;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

import junit.framework.Assert;
import test_selinuim_Utilities.BrowserSetup;
import test_selinuim_Utilities.FooterHighlight;
import test_selinuim_Utilities.Highlight;
import test_selinuim_Utilities.MouseHover;

@TestInstance(Lifecycle.PER_CLASS)
class MainPage {
	
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
		

	}

	@AfterEach
	public void tearDown() throws Exception {
//		driver.quit();
	}

//	@Disabled
	@Test
	void cookiePolicyTest() throws InterruptedException {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://novoprovigilance.com/");
		
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		
		wait.until(d -> driver.findElement(By.className("cookie-container")).isDisplayed());
		System.out.println("cookie element displayed");
//		assertEquals("rgba(47, 54, 64, 1)",
//				driver.findElement(By.className("cookie-container")).getCssValue("background-color"));
//		assertEquals("rgba(245, 246, 250, 1)",
//				driver.findElement(By.className("cookie-container")).getCssValue("color"));
		System.out.println("P element in cookie policy: " + driver.findElements(By.xpath("//p")).get(0).getText());
		System.out.println("a element in cookie policy: " + driver.findElements(By.xpath("//p/a")).get(0).getText());
		Thread.sleep(1000);
//		actions.moveToElement(driver.findElements(By.xpath("//p/a")).get(0)).perform();
		hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//p/a")).get(0), driver);
		Thread.sleep(200);
//		assertEquals("rgba(252, 208, 93, 1)", driver.findElements(By.xpath("//p/a")).get(0).getCssValue("color"));
//		assertEquals("policy.html", driver.findElements(By.xpath("//p/a")).get(0).getDomAttribute("href"));
		WebElement btnElement = driver.findElement(By.xpath("//p/button"));
		highlight.highlightElement(driver, driver.findElements(By.xpath("//p/a")).get(0));
		highlight.highlightElement(driver, btnElement);
		WebElement cookieLocation = driver.findElement(By.className("cookie-container"));
		System.out.println("before btn click: " + cookieLocation.getRect());
		System.out.println("before btn click: " + cookieLocation.getLocation());
		btnElement.click();
		WebElement cookieNewLocation = driver.findElement(By.className("cookie-container"));
		System.out.println("after btn click: " + cookieNewLocation.getRect());
		System.out.println("after btn click: " + cookieNewLocation.getLocation());
		Thread.sleep(2000);
//		assertTrue(cookieNewLocation.getLocation().equals(cookieLocation.getLocation()));
	}



//	@Disabled
	@Test
	void aboutUsSectionTest() throws InterruptedException {
		
		// fetch elements from image block
		WebElement startnow = driver.findElement(By.linkText("START NOW"));
		highlight.highlightElement(driver, startnow);

//		 start now button upon image
		wait.until(ExpectedConditions.visibilityOf(startnow));
		startnow.click();
		System.out.println("clicked start now");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("about-us"))));
		assertTrue(driver.findElement(By.className("main-nav")).isDisplayed());
		assertTrue(driver.findElement(By.className("cookie-container")).isDisplayed());
		System.out.println("about us section is visible");

		assertEquals("url(\"https://novoprovigilance.com/images/about-bg.jpg\")",driver.findElement(By.id("about-us")).getCssValue("background-image"));
		System.out.println("about us background image checked");
		Thread.sleep(300);
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("h2"))));
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.linkText("ABOUT US")).getCssValue("background-color"));
//		assertEquals("About us", driver.findElements(By.cssSelector("h2")).get(0).getText());
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(0).getCssValue("color"));
//		assertEquals("Core Values", driver.findElements(By.cssSelector("h2")).get(1).getText());
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(1).getCssValue("color"));
//		assertEquals("Certifications", driver.findElements(By.cssSelector("h2")).get(2).getText());
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(2).getCssValue("color"));
//		assertEquals(30, driver.findElements(By.cssSelector("ul")).size());
//		assertEquals(159, driver.findElements(By.cssSelector("li")).size());
		System.out.println("checked about us elements with css selectors");
	}
		
//	@Disabled
	@Test
	void careersSectionTest() throws InterruptedException {
		
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		wait.until(d -> startnow.isDisplayed());
		System.out.println("start now button appears");
	
		Thread.sleep(500);
		highlight.highlightElement(driver, startnow);
		startnow.click();
		System.out.println("clicked on start now button");

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
		System.out.println("header appears");

		// go to careers link and click
		WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
		highlight.highlightElement(driver, careersLink);
		System.out.println("careers nav element : " + careersLink.getText());
		actions.moveToElement(careersLink).perform();
		Thread.sleep(200);
		careersLink.click();

		// From careers section
		WebElement careersHeading = driver.findElement(By.xpath("//h2[contains(b,'::Careers::')]"));
		Thread.sleep(200);
		// wait until careers section is visible
		wait.until(d -> careersHeading.isDisplayed());

		// assert main heading
		highlight.highlightElement(driver, careersHeading);
		System.out.println("Main Heading in Careers: " + careersHeading.getText());
//		assertEquals("::Careers::", careersHeading.getText());

		// sub heading info
		WebElement subHeading = driver.findElement(By.xpath("//section[contains(@id,'career')]/div/div/div/h4"));
		highlight.highlightElement(driver, subHeading);
		System.out.println("::Work with us and Grow with us::" + subHeading.getText());

		// resume contact
		WebElement contactMailElement = driver.findElements(By.xpath("//section[contains(@id,'career')]/div/div/div/h4")).get(1);
		highlight.highlightElement(driver, contactMailElement);
		System.out.println("Send Your resume to " + contactMailElement.getText());

		// get career elements
		List<WebElement> careers = driver.findElements(By.xpath("//div[contains(@class,'table-container')]"));
		List<WebElement> designation = driver.findElements(By.xpath("//div[contains(@class,'table-container')]/table/tbody/tr"));

		for (WebElement career : careers) {
			int index = careers.indexOf(career);
//			Thread.sleep(1000);
			System.out.println("index: " + index);
			jsExecutor.executeScript("arguments[0].scrollIntoView();", career);
//			Thread.sleep(1000);
			jsExecutor.executeScript("window.scrollBy(0, -150)");
//			Thread.sleep(3000);				
					
			List<WebElement> tablerows = career.findElements(By.xpath("table/tbody/tr"));
			
			for (WebElement row : tablerows) {
				highlight.highlightElement(driver, row);
				System.out.println(row.getText());
			}

	
		}
	}

//	@Disabled
	@Test
	void ContactSectionTest() throws InterruptedException {
		
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to contact us link and click
		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		highlight.highlightElement(driver, contactLink);
		System.out.println("contact nav element : " + contactLink.getText());
		actions.moveToElement(contactLink).perform();
		Thread.sleep(500);

		// From contact us section
		WebElement contactUsHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2"));
		WebElement subHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/p"));

		// assert image
		assertEquals("url(\"https://novoprovigilance.com/images/contact-bg.jpg\")",
				driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]")).getCssValue("background-image"));

		// assert main heading
		highlight.highlightElement(driver, contactUsHeading);
		System.out.println("Main Heading in Contact us: " + contactUsHeading.getText());

		// assert main heading
		highlight.highlightElement(driver, subHeading);
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
		highlight.highlightElement(driver, address);
		System.out.println("Address given: " + address.getText());
		highlight.highlightElement(driver, phone);
		System.out.println("Phone number : " + phone.getText());
		highlight.highlightElement(driver, emailAddress);
		System.out.println("Email address : " + emailAddress.getText());

	}

//	@Disabled//test created
	@Test
	void footerEvents() throws InterruptedException {
		
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		wait.until(d -> startnow.isDisplayed());
		highlight.highlightElement(driver, startnow);
		startnow.click();
		
		// scroll down
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(1000);

		// hover action
		WebElement footerTitle = driver.findElement(By.linkText("NOVO PROVIGILANCE LLC"));
		actions.moveToElement(footerTitle).perform();
//		Thread.sleep(1000);

		// social links
		WebElement twitter = driver.findElement(By.className("twitter"));
		actions.moveToElement(twitter).perform();
//		Thread.sleep(200);
		assertNotEquals(driver.findElement(By.className("twitter")).getCssValue("background-color"), "rgba(0, 0, 0, 1)");
//		assertNotEquals("rgba(0, 0, 0, 1)", driver.findElement(By.className("twitter")).getCssValue("background-color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("twitter")).getCssValue("color"));
//		Thread.sleep(1000);
		

		WebElement fBook = driver.findElement(By.className("facebook"));
		actions.moveToElement(fBook).perform();
//		Thread.sleep(200); // color check
//		assertNotEquals("rgba(56, 85, 146, 0.84)",driver.findElement(By.className("facebook")).getCssValue("background-color"));
//		assertNotEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("facebook")).getCssValue("color"));

		WebElement lIn = driver.findElement(By.className("linkedin"));
		actions.moveToElement(lIn).perform();
//		Thread.sleep(200); // color check
//		assertNotEquals("rgba(3, 109, 192, 1)",driver.findElement(By.className("linkedin")).getCssValue("background-color"));
//		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("linkedin")).getCssValue("color"));

		// disclaimer WebElement policy =
		driver.findElement(By.linkText("Disclaimer, Cookie & Privacy Policy"));
//		assertNotEquals("rgba(0, 0, 0, 0)",driver.findElement(By.linkText("Disclaimer, Cookie & Privacy Policy")).getCssValue("background-color"));
//		assertEquals("rgba(255, 255, 255, 1)",driver.findElement(By.linkText("Disclaimer, Cookie & Privacy Policy")).getCssValue("color")); 
		
//		WebElement closeUp = driver.findElement(By.className("cookie-btn"));
//		wait.until(d->closeUp.isDisplayed());
//		wait.until(ExpectedConditions.elementToBeClickable(closeUp));
//		closeUp.click();

		// footerbar Thread.sleep(1000); WebElement copyrights =
		WebElement copyrights = driver.findElement(By.linkText("Novo Provigilance LLC."));
		actions.moveToElement(copyrights).build().perform();
//		assertNotEquals("rgba(8, 8, 8, 1)",driver.findElement(By.linkText("Novo Provigilance LLC.")).getCssValue("color"));
//		assertEquals("none solid rgb(8, 8, 8)",driver.findElement(By.linkText("Novo Provigilance LLC.")).getCssValue("text-decoration"));
	}
}
