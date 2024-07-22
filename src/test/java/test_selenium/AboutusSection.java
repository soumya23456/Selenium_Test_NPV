package test_selenium;



import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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
class AboutusSection {
	 
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
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
	void setUpBeforeClass() throws Exception {
	}

	@AfterEach
	void tearDownAfterClass() throws Exception {
//		driver.quit();
	}

//	@Disabled
	@Test
	void csrSectionTest() throws InterruptedException {

		// From home page
		driver.get("https://novoprovigilance.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);

		// click start now button
		wait.until(d -> startnow.isDisplayed());
		startnow.click();
//		Thread.sleep(200);

		WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[@href='#about-us']"));
		WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		WebElement csrElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'csr.html')]"));

		// wait until about us link appears and hover over it
		wait.until(ExpectedConditions.visibilityOf(aboutUsLink));
		hoverJS.mouseHoverJScript(aboutUsLink, driver);
		Thread.sleep(500);
		
		// wait until about us dropdown appears
//		wait.until(d -> aboutUsDropdownElement.isDisplayed());
		hoverJS.mouseHoverJScript(aboutUsDropdownElement, driver);
		Thread.sleep(500);


		// click csr element to go that page
		
		hoverJS.mouseHoverJScript(csrElement, driver);
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
		
		Assert.assertEquals("Novo Provigilance LLC.", pageTitle);
		
		// assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		Assertions.assertEquals("Corporate Social Responsibility", mainHeading.getText());

		// check for li tags
		List<WebElement> aboutUsInfoList = driver.findElements(By.className("oli"));
		Assertions.assertEquals(4, aboutUsInfoList.size());
		Assertions.assertEquals("Diversity and inclusion.", aboutUsInfoList.get(0).getText());
		highlight.highlightElement(driver, aboutUsInfoList.get(0));
		Assertions.assertEquals("Environment and energy.", aboutUsInfoList.get(1).getText());
		highlight.highlightElement(driver, aboutUsInfoList.get(1));
		Assertions.assertEquals("Governance.", aboutUsInfoList.get(2).getText());
		highlight.highlightElement(driver, aboutUsInfoList.get(2));
		Assertions.assertEquals("Community Outreach.", aboutUsInfoList.get(3).getText());
		highlight.highlightElement(driver, aboutUsInfoList.get(3));

	}

}
