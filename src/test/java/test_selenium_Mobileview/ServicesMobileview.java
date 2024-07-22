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
class ServicesMobileview {

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
		void ideaPageTest() throws InterruptedException {
			
	

			
			
			//From home page
			driver.get("https://novoproso.com");
			
			
			
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
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
			
			WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
			WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
			WebElement ideaElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'idea.html')]"));
			
			//wait until Services link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(servicesLink));
			highLight.highlightElement(driver, servicesLink);
			hoverJS.mouseHoverJScript(servicesLink, driver);
			
			//wait until Services dropdown appears
			wait.until(d -> servicesDropdownElement.isDisplayed());

			//click idea element to go that page
			highLight.highlightElement(driver, ideaElement);
			ideaElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String IdeaURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + IdeaURL);

			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
			//did not include commented tag
			WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
			highLight.highlightElement(driver, mainHeading);
			assertEquals("Your Own Idea", mainHeading.getText());

			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
			
			//sub heading
			//did not include commented tag
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Bring Your Vision to Life!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			assertEquals(6, ulElements.size());

			//whether ul tags are displayed or not
			assertFalse(ulElements.get(0).isDisplayed());
			assertFalse(ulElements.get(1).isDisplayed());		
			assertFalse(ulElements.get(2).isDisplayed());
			assertFalse(ulElements.get(3).isDisplayed());
			
			jsExecutor.executeScript("arguments[0].scrollIntoView();", ulElements.get(4));
			wait.until(ExpectedConditions.visibilityOf(ulElements.get(4)));
			assertTrue(ulElements.get(4).isDisplayed());
			highLight.highlightElement(driver, content.get(0));
			highLight.highlightElement(driver, content.get(1));
			highLight.highlightElement(driver, ulElements.get(4));
			highLight.highlightElement(driver, content.get(2));
			
			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

		@Disabled
		@Test
		void softwarePageTest() throws InterruptedException {
			



			//From home page
			driver.get("https://novoproso.com");
		
			
			
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
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());

			WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
			WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
			WebElement sdElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'sd.html')]"));
			
			//wait until Services link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(servicesLink));
			highLight.highlightElement(driver, servicesLink);
			hoverJS.mouseHoverJScript(servicesLink, driver);
			
			//wait until Services dropdown appears
			wait.until(d -> servicesDropdownElement.isDisplayed());

			//click softwareDevelopment element to go that page
			highLight.highlightElement(driver, sdElement);
			sdElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String sdURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + sdURL);
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
			WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));
			assertEquals("Software Development", mainHeading.getText());
			highLight.highlightElement(driver, mainHeading);
			
			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/sd.png", imageElement.getDomAttribute("src"));
			
			//sub heading
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("We design custom software to boost your business using the latest tech and agile methods!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			assertEquals(5, ulElements.size());

			//whether ul tags are displayed or not
			assertFalse(ulElements.get(0).isDisplayed());
			assertFalse(ulElements.get(1).isDisplayed());		
			assertFalse(ulElements.get(2).isDisplayed());
			assertFalse(ulElements.get(3).isDisplayed());
			
			jsExecutor.executeScript("arguments[0].scrollIntoView();", content.get(0));
			wait.until(ExpectedConditions.visibilityOf(content.get(0)));
			highLight.highlightElement(driver, content.get(0));
			highLight.highlightElement(driver, content.get(1));
			highLight.highlightElement(driver, content.get(2));
			
			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

		@Disabled
		@Test
		void ITStaffPageTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));



			//From home page
			driver.get("https://novoproso.com");
		
			
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
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
			
			WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
			WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
			WebElement itstaffElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'itstaff.html')]"));
			
			//wait until Services link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(servicesLink));
			highLight.highlightElement(driver, servicesLink);
			hoverJS.mouseHoverJScript(servicesLink, driver);
					
			//wait until Services dropdown appears
			wait.until(d -> servicesDropdownElement.isDisplayed());

			//click ITStaff element to go that page
			highLight.highlightElement(driver, itstaffElement);
			itstaffElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String itStaffURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + itStaffURL);
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
			WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));
			highLight.highlightElement(driver, mainHeading);
			assertEquals("IT Staffing", mainHeading.getText());

			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
			
			//sub heading
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Unlock your IT project potential with our "+ "tailored staffing "+ "solutions�let's build your winning team!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			assertEquals(5, ulElements.size());

			//whether ul tags are displayed or not
			assertFalse(ulElements.get(0).isDisplayed());
			assertFalse(ulElements.get(1).isDisplayed());		
			assertFalse(ulElements.get(2).isDisplayed());
			assertFalse(ulElements.get(3).isDisplayed());
			
			assertTrue(driver.findElement(By.linkText("Novo ProSo")).isDisplayed());
			
			jsExecutor.executeScript("arguments[0].scrollIntoView();", content.get(0));
			wait.until(ExpectedConditions.visibilityOf(content.get(0)));
			highLight.highlightElement(driver, content.get(0));
			highLight.highlightElement(driver, content.get(1));
			highLight.highlightElement(driver, content.get(2));
			highLight.highlightElement(driver, content.get(3));
			
			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}
		
		@Disabled
		@Test
		void cloudPageTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


			//From home page
			driver.get("https://novoproso.com");
			
			
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
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
			
			WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
			WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
			WebElement cloudElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'cloud.html')]"));
			
			//wait until Services link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(servicesLink));
			highLight.highlightElement(driver, servicesLink);
			hoverJS.mouseHoverJScript(servicesLink, driver);
					
			//wait until Services dropdown appears
			wait.until(d -> servicesDropdownElement.isDisplayed());

			//click cloud element to go that page
			highLight.highlightElement(driver, cloudElement);
			cloudElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String cloudURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + cloudURL);
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
			WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));
			assertEquals("Cloud Computing", mainHeading.getText());
			highLight.highlightElement(driver, mainHeading);
			
			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
			
			//sub heading
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Take your business to new heights with our cloud computing services!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			assertEquals(6, ulElements.size());

			//whether ul tags are displayed or not
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
			
			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

		@Disabled
		@Test
		void AIMLPageTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));



			//From home page
			driver.get("https://novoproso.com");
			
			
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
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
			
			WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
			WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
			WebElement AIMlElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'ai.html')]"));
			
			//wait until Services link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(servicesLink));
			highLight.highlightElement(driver, servicesLink);
			hoverJS.mouseHoverJScript(servicesLink, driver);
			
			//wait until Services dropdown appears
			wait.until(d -> servicesDropdownElement.isDisplayed());

			//click AI/ML element to go that page
			highLight.highlightElement(driver, AIMlElement);
			AIMlElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String AIMLURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + AIMLURL);
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
			WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));
			highLight.highlightElement(driver, mainHeading);
			assertEquals("Artificial Intelligence/ Machine Learning", mainHeading.getText());

			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
			
			//sub heading
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Unlock the power of Artificial Intelligence and Machine Learning to transform your business!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			assertEquals(6, ulElements.size());

			//whether ul tags are displayed or not
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
			
			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

		@Disabled
		@Test
		void BDanalyticsPageTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


			//From home page
			driver.get("https://novoproso.com");
			
			
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
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
			
			WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
			WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
			WebElement bigDataElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'bigData.html')]"));
			
			//wait until Services link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(servicesLink));
			highLight.highlightElement(driver, servicesLink);

			hoverJS.mouseHoverJScript(servicesLink, driver);
			
			//wait until Services dropdown appears
			wait.until(d -> servicesDropdownElement.isDisplayed());

			//click BigData Analytics element to go that page
			highLight.highlightElement(driver, bigDataElement);
			bigDataElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String bigDataURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + bigDataURL);
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
//			List<WebElement> mainHeading = driver.findElements(By.cssSelector("h2"));
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));

			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
			
			//sub heading
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Unlock the hidden insights in your data and drive business success with our Big Data Analytics services!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());
			
			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			assertEquals(7, ulElements.size());

			//whether ul tags are displayed or not
			assertFalse(ulElements.get(0).isDisplayed());
			assertFalse(ulElements.get(1).isDisplayed());		
			assertFalse(ulElements.get(2).isDisplayed());
			assertFalse(ulElements.get(3).isDisplayed());
			
			jsExecutor.executeScript("arguments[0].scrollIntoView();", ulElements.get(4));
			wait.until(ExpectedConditions.visibilityOf(ulElements.get(4)));		
			assertTrue(ulElements.get(4).isDisplayed());
			assertTrue(ulElements.get(5).isDisplayed());		
			highLight.highlightElement(driver, content.get(0));
			highLight.highlightElement(driver, ulElements.get(4));
			highLight.highlightElement(driver, content.get(1));
			highLight.highlightElement(driver, ulElements.get(5));
			highLight.highlightElement(driver, content.get(2));
			
			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

		@Disabled
		@Test
		void HRAPageTest() throws InterruptedException {
			
			

	

			//From home page
			driver.get("https://novoproso.com");
			
			
			
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
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());

			WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
			WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
			WebElement hraElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'hra.html')]"));
			
			//wait until Services link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(servicesLink));
			highLight.highlightElement(driver, servicesLink);
			hoverJS.mouseHoverJScript(servicesLink, driver);
			

			
			//wait until Services dropdown appears
			wait.until(d -> servicesDropdownElement.isDisplayed());

			//click AI/ML element to go that page
			highLight.highlightElement(driver, hraElement);
			hraElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String hraURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + hraURL);
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
			WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));
			highLight.highlightElement(driver, mainHeading);
			assertEquals("Healthcare Research & Analysis", mainHeading.getText());

			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));

			//sub heading
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Transform healthcare outcomes with data-driven insights!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());
			
			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			assertEquals(7, ulElements.size());

			//whether ul tags are displayed or not
			assertFalse(ulElements.get(0).isDisplayed());
			assertFalse(ulElements.get(1).isDisplayed());		
			assertFalse(ulElements.get(2).isDisplayed());
			assertFalse(ulElements.get(3).isDisplayed());

			jsExecutor.executeScript("arguments[0].scrollIntoView();", ulElements.get(4));
			wait.until(ExpectedConditions.visibilityOf(ulElements.get(4)));
			assertTrue(ulElements.get(4).isDisplayed());
			assertTrue(ulElements.get(5).isDisplayed());
			highLight.highlightElement(driver, content.get(0));
			highLight.highlightElement(driver, ulElements.get(4));
			highLight.highlightElement(driver, content.get(1));
			highLight.highlightElement(driver, ulElements.get(5));
			highLight.highlightElement(driver, content.get(2));
			
			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

		@Disabled
		@Test
		void ITPMPageTest() throws InterruptedException {
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


			//From home page
			driver.get("https://novoproso.com");
			
			
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
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
			
			WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
			WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
			WebElement itpmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'itpm.html')]"));
			
			//wait until Services link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(servicesLink));
			highLight.highlightElement(driver, servicesLink);
			hoverJS.mouseHoverJScript(servicesLink, driver);
			
			//wait until Services dropdown appears
			wait.until(d -> servicesDropdownElement.isDisplayed());

			//click AI/ML element to go that page
			highLight.highlightElement(driver, itpmElement);
			itpmElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String itpmURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + itpmURL);		
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
			WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));
			highLight.highlightElement(driver, mainHeading);
			assertEquals("IT Project/Program Management", mainHeading.getText());

			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
			
			//sub heading
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Deliver your IT projects on time, within budget, and with exceptional quality!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());
			
			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			assertEquals(6, ulElements.size());

			//whether ul tags are displayed or not
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
			
			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

		@Disabled
		@Test
		void networkPageTest() throws InterruptedException {
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));



			//From home page
			driver.get("https://novoproso.com");
			
			
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
			wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());

			WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
			WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
			WebElement networkElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'network.html')]"));
			
			//wait until Services link appears and hover over it
			wait.until(ExpectedConditions.visibilityOf(servicesLink));
			highLight.highlightElement(driver, servicesLink);
			hoverJS.mouseHoverJScript(servicesLink, driver);

//			action.moveToElement(servicesLink).perform();
			
			//wait until Services dropdown appears
			wait.until(d -> servicesDropdownElement.isDisplayed());
			
			//click AI/ML element to go that page
			highLight.highlightElement(driver, networkElement);
			networkElement.click();

			//wait until page loads (waiting until page main heading loads)
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			
			//get Current URL
			String networkURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + networkURL);		
			
			//assert page title
			String pageTitle = driver.getTitle();
			assertEquals("Novo ProSo, Inc.", pageTitle);
			
			//assert main heading
			WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
			WebElement subHeading = driver.findElement(By.cssSelector("h4"));
			highLight.highlightElement(driver, mainHeading);
			assertEquals("IT Network & Security", mainHeading.getText());

			//assert image
			WebElement imageElement = driver.findElement(By.cssSelector("img"));
			highLight.highlightElement(driver, imageElement);
			assertTrue(imageElement.isDisplayed());
			assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
			
			//sub heading
			jsExecutor.executeScript("arguments[0].scrollIntoView();", subHeading);
			wait.until(ExpectedConditions.visibilityOf(subHeading));
			assertEquals("Protect your business from cyber threats and optimize your network performance with our comprehensive IT network and security services!", subHeading.getText());
			assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
			assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
			assertEquals("27px", subHeading.getCssValue("line-height"));
			assertEquals("600", subHeading.getCssValue("font-weight"));
			highLight.highlightElement(driver, subHeading);
			
			//hr
			assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

			//assert ul, li tags
			List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
			List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
			assertEquals(6, ulElements.size());

			//whether ul tags are displayed or not
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

			//Footer
			footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
		}

}
