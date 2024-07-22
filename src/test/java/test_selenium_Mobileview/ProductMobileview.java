package test_selenium_Mobileview;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
class ProductMobileview {

	// chrome, msedge, firefox
		String browser = "chrome";
		RemoteWebDriver driver;

//		WebDriver driver;
		JavascriptExecutor jsExecutor;
		WebDriverWait wait;
		Actions action;
		MouseHover hoverJS;
		Highlight highLight;
		FooterHighlight footerHighlight;
		BrowserSetup browserSetupConfig = new BrowserSetup();
		
		@BeforeAll
		void setUp() throws Exception {
			
			driver = browserSetupConfig.getBrowserGridSetUp(browser);
//			driver = browserSetUpConfig.getBrowserSetUp(browser);
			
			driver.manage().window().setSize(new Dimension(673, 690));

			// actions
			action = new Actions(driver);

			// JavaScriptExecutor
			jsExecutor = (JavascriptExecutor) driver;

			hoverJS = new MouseHover();
			highLight = new Highlight();
			footerHighlight = new FooterHighlight();
		}

		@AfterAll
		void tearDown() throws Exception {
			driver.quit();
		}

		@Disabled
		@Test
		void DANPageTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			
			//From home page
			driver.get("https://novoprovigilance.com");
			 
			wait.until(d->driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
			WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
			
			//click start now button
			wait.until(d -> startnow.isDisplayed());
			highLight.highlightElement(driver, startnow);
			startnow.click();

			//wait until about us section appears
			wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
			wait.until(d -> driver.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")).isDisplayed());

			//click on nav element to open nav menu
			driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
			
			WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
			WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);
			WebElement danElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'DAN.html')]"));
			
			highLight.highlightElement(driver, productsLink);
			hoverJS.mouseHoverJScript(productsLink, driver);
			
			//wait until about us dropdown appears
			wait.until(d -> productsDropdownElement.isDisplayed());

			//click localSports element to go that page
			highLight.highlightElement(driver, danElement);
			danElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/DAN.html"));
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());

			//get Current URL
			String DANUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + DANUrl);
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
			WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
			highLight.highlightElement(driver, mainHeading);
			assertEquals("Data Analytics by Novo ProSo (DAN)", mainHeading.getText());
			
			//sub heading
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Unlocking Hidden Insights!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
			
			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			
			//whether ul tags are displayed or not
			assertEquals(7, ulElements.size());
			assertFalse(ulElements.get(0).isDisplayed());
			assertFalse(ulElements.get(1).isDisplayed());		
			assertFalse(ulElements.get(2).isDisplayed());
			assertFalse(ulElements.get(3).isDisplayed());
			
			jsExecutor.executeScript("arguments[0].scrollIntoView();", ulElements.get(4));
			wait.until(ExpectedConditions.visibilityOf(ulElements.get(4)));
			assertTrue(ulElements.get(4).isDisplayed());
			highLight.highlightElement(driver, content.get(0));		
			highLight.highlightElement(driver, ulElements.get(4));
			highLight.highlightElement(driver, content.get(1));		
			assertTrue(ulElements.get(5).isDisplayed());
			highLight.highlightElement(driver, ulElements.get(5));
			highLight.highlightElement(driver, content.get(2));		

			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

		@Disabled
		@Test
		void BDIngensionPageTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			
			//Direct URL
			driver.get("https://novoproso.com/BDIngension.html");

			//From home page
			driver.get("https://novoproso.com");
			wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			
			
			wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
			WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
			
			//click start now button
			wait.until(d -> startnow.isDisplayed());
			highLight.highlightElement(driver, startnow);
			startnow.click();

			//wait until about us section appears
			wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
			wait.until(d -> driver.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")).isDisplayed());

			//click on nav element to open nav menu
			driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

			WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
			WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);
			WebElement bdIngensionElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'BDIngension.html')]"));
			
			//wait until about us link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(productsLink));
			highLight.highlightElement(driver, productsLink);
			hoverJS.mouseHoverJScript(productsLink, driver);
			
			//wait until about us dropdown appears
			wait.until(d -> productsDropdownElement.isDisplayed());

			//click localSports element to go that page
			highLight.highlightElement(driver, bdIngensionElement);
			bdIngensionElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String bdIngensionUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + bdIngensionUrl);
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
//			List<WebElement> mainHeading = driver.findElements(By.cssSelector("h2"));
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));

			//sub heading
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Unleashing the Power of Raw Data!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
			
			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));

			//whether ul tags are displayed or not
			assertEquals(7, ulElements.size());
			assertFalse(ulElements.get(0).isDisplayed());
			assertFalse(ulElements.get(1).isDisplayed());		
			assertFalse(ulElements.get(2).isDisplayed());
			assertFalse(ulElements.get(3).isDisplayed());
			
			jsExecutor.executeScript("arguments[0].scrollIntoView();", ulElements.get(4));
			wait.until(ExpectedConditions.visibilityOf(ulElements.get(4)));
			assertTrue(ulElements.get(4).isDisplayed());
			highLight.highlightElement(driver, content.get(0));
			highLight.highlightElement(driver, ulElements.get(4));
			highLight.highlightElement(driver, content.get(1));
			assertTrue(ulElements.get(5).isDisplayed());
			highLight.highlightElement(driver, ulElements.get(5));
			highLight.highlightElement(driver, content.get(2));

			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

}
