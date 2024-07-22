package test_selenium_Mobileview;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test_selinuim_Utilities.*;

@TestInstance(Lifecycle.PER_CLASS)
class MainPageMobileview {

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

	@BeforeEach
	void openUrl() throws Exception {
		driver.manage().window().setSize(new Dimension(673, 690));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		wait = new WebDriverWait(driver, Duration.ofSeconds(6));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
	}

		@Disabled
	@Test
	void cookiePolicyTest() throws InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]"))));
		assertEquals("rgba(47, 54, 64, 1)",
				driver.findElement(By.className("cookie-container")).getCssValue("background-color"));
		assertEquals("rgba(245, 246, 250, 1)",
				driver.findElement(By.className("cookie-container")).getCssValue("color"));
		System.out.println("element in cookie policy: "
				+ driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p")).getText());

		WebElement closebtnElement = driver
				.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button"));
		highLight.highlightElement(driver,
				driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/a")));
		highLight.highlightElement(driver, closebtnElement);

		WebElement cookieLocation = driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]"));
		String oldLocation = cookieLocation.getLocation().toString();
		System.out.println("before btn click: " + cookieLocation.getRect().toString());
		System.out.println("before btn click: " + cookieLocation.getLocation().toString());
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[contains(@class,'cookie-container')]/p/button")));
		try {
			closebtnElement.click();

			WebElement cookieNewLocation = driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]"));
			String newLocation = cookieNewLocation.getLocation().toString();
			System.out.println("after btn click: " + cookieNewLocation.getRect().toString());
			System.out.println("after btn click: " + cookieNewLocation.getLocation().toString());

			if (!newLocation.equals(oldLocation)) {
				System.out.println("Cookie Container closed!");
			}
		} catch (ElementClickInterceptedException e) {
			System.out.println("Element is not clickable at present!");
		}
	}

		@Disabled
	@Test
	void navBarMobileViewTest() throws InterruptedException {
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		hoverJS.mouseHoverJScript(startnow, driver);
		highLight.highlightElement(driver, startnow);
		startnow.click();

		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.isDisplayed());

		// click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());

		WebElement homeLink = driver.findElement(By.xpath("//li/a[@href='#home']"));
		System.out.println("home nav element : " + homeLink.getText());
		highLight.highlightElement(driver, homeLink);

		WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[@href='#about-us']"));
		WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		hoverJS.mouseHoverJScript(aboutUsLink, driver);
		System.out.println("about us nav element : " + aboutUsLink.getText());
		wait.until(ExpectedConditions.attributeContains(By.xpath("//li/a[@href='#about-us']"), "background-color",
				"rgba(252, 208, 93, 1)"));
		assertEquals("rgba(252, 208, 93, 1)", aboutUsLink.getCssValue("background-color"));
		highLight.highlightElement(driver, aboutUsLink);
		wait.until(d -> aboutUsDropdownElement.isDisplayed());

		WebElement csrElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'csr.html')]"));
		WebElement localSportsElement = driver
				.findElement(By.xpath("//li/ul/li/a[contains(@href, 'localSports.html')]"));

		hoverJS.mouseHoverJScript(csrElement, driver);
		action.moveToElement(csrElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(csrElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("rgba(32, 158, 72, 1)", csrElement.getCssValue("color"));
		assertEquals("csr.html", csrElement.getDomAttribute("href"));
		highLight.highlightElement(driver, csrElement);

		hoverJS.mouseHoverJScript(localSportsElement, driver);
		action.moveToElement(localSportsElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(localSportsElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("localSports.html", localSportsElement.getDomAttribute("href"));
		highLight.highlightElement(driver, localSportsElement);

		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		hoverJS.mouseHoverJScript(productsLink, driver);
		highLight.highlightElement(driver, productsLink);
		System.out.println("products nav element : " + productsLink.getText());
		WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);

		wait.until(d -> productsDropdownElement.isDisplayed());
		WebElement danElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'DAN.html')]"));
		WebElement bdIngensionElement = driver
				.findElement(By.xpath("//li/ul/li/a[contains(@href, 'BDIngension.html')]"));

		hoverJS.mouseHoverJScript(danElement, driver);
		action.moveToElement(danElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(danElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("DAN.html", danElement.getDomAttribute("href"));
		highLight.highlightElement(driver, danElement);

		hoverJS.mouseHoverJScript(bdIngensionElement, driver);
		action.moveToElement(bdIngensionElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(bdIngensionElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("BDIngension.html", bdIngensionElement.getDomAttribute("href"));
		highLight.highlightElement(driver, bdIngensionElement);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		System.out.println("services nav element : " + servicesLink.getText());
		highLight.highlightElement(driver, servicesLink);
		hoverJS.mouseHoverJScript(servicesLink, driver);
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);

		wait.until(d -> servicesDropdownElement.isDisplayed());
		WebElement ideaElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'idea.html')]"));
		WebElement sdElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'sd.html')]"));
		WebElement itstaffElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'itstaff.html')]"));
		WebElement cloudElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'cloud.html')]"));
		WebElement aiElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'ai.html')]"));
		WebElement bigDataElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'bigData.html')]"));
		WebElement hraElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'hra.html')]"));
		WebElement itpmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'itpm.html')]"));
		WebElement networkElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'network.html')]"));

		hoverJS.mouseHoverJScript(ideaElement, driver);
		action.moveToElement(ideaElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(ideaElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("idea.html", ideaElement.getDomAttribute("href"));
		highLight.highlightElement(driver, ideaElement);

		hoverJS.mouseHoverJScript(sdElement, driver);
		action.moveToElement(sdElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(sdElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("sd.html", sdElement.getDomAttribute("href"));
		highLight.highlightElement(driver, sdElement);

		hoverJS.mouseHoverJScript(itstaffElement, driver);
		action.moveToElement(itstaffElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(itstaffElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("itstaff.html", itstaffElement.getDomAttribute("href"));
		highLight.highlightElement(driver, itstaffElement);

		hoverJS.mouseHoverJScript(cloudElement, driver);
		action.moveToElement(cloudElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(cloudElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("cloud.html", cloudElement.getDomAttribute("href"));
		highLight.highlightElement(driver, cloudElement);

		hoverJS.mouseHoverJScript(aiElement, driver);
		action.moveToElement(aiElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(aiElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("ai.html", aiElement.getDomAttribute("href"));
		highLight.highlightElement(driver, aiElement);

		hoverJS.mouseHoverJScript(bigDataElement, driver);
		action.moveToElement(bigDataElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(bigDataElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("bigData.html", bigDataElement.getDomAttribute("href"));
		highLight.highlightElement(driver, bigDataElement);

		hoverJS.mouseHoverJScript(hraElement, driver);
		action.moveToElement(hraElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(hraElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("hra.html", hraElement.getDomAttribute("href"));
		highLight.highlightElement(driver, hraElement);

		hoverJS.mouseHoverJScript(itpmElement, driver);
		action.moveToElement(itpmElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(itpmElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("itpm.html", itpmElement.getDomAttribute("href"));
		highLight.highlightElement(driver, itpmElement);

		hoverJS.mouseHoverJScript(networkElement, driver);
		action.moveToElement(networkElement).build().perform();
		wait.until(ExpectedConditions.attributeToBe(networkElement, "color", "rgba(32, 158, 72, 1)"));
		assertEquals("network.html", networkElement.getDomAttribute("href"));
		highLight.highlightElement(driver, networkElement);

		WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
		hoverJS.mouseHoverJScript(careersLink, driver);
		highLight.highlightElement(driver, careersLink);
		System.out.println("careers nav element : " + careersLink.getText());

		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		hoverJS.mouseHoverJScript(contactLink, driver);
		highLight.highlightElement(driver, contactLink);
		System.out.println("contact us nav element : " + contactLink.getText());
	}

		@Disabled
	@Test
	void aboutUsSectionTest() throws InterruptedException {
		// fetch elements from image block
		WebElement startnow = driver.findElement(By.linkText("START NOW"));
		hoverJS.mouseHoverJScript(startnow, driver);
		highLight.highlightElement(driver, startnow);
		startnow.click();

		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.isDisplayed());

		// click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());

		assertTrue(driver.findElement(By.className("main-nav")).isDisplayed());
		assertTrue(driver.findElement(By.className("cookie-container")).isDisplayed());

		assertEquals("url(\"https://novoproso.com/images/about-bg.jpg\")",
				driver.findElement(By.id("about-us")).getCssValue("background-image"));
		wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("h2")).get(0)));
		assertEquals("rgba(252, 208, 93, 1)",
				driver.findElement(By.linkText("ABOUT US")).getCssValue("background-color"));
		assertEquals("About us", driver.findElement(By.xpath("//h2[.='About us']")).getText());
		assertEquals("About us", driver.findElements(By.cssSelector("h2")).get(0).getText());
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(0).getCssValue("color"));
		assertEquals("Core Values", driver.findElements(By.cssSelector("h2")).get(1).getText());
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(1).getCssValue("color"));
		assertEquals(24, driver.findElements(By.cssSelector("ul")).size());
		assertEquals(55, driver.findElements(By.cssSelector("li")).size());

		highLight.highlightElement(driver, driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")));
		highLight.highlightElement(driver, driver
				.findElements(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/p"))
				.get(0));
		highLight.highlightElement(driver, driver
				.findElements(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/p"))
				.get(1));
		highLight.highlightElement(driver, driver
				.findElements(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.get(1));
		highLight.highlightElement(driver, driver
				.findElements(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/ul"))
				.get(0));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.cssSelector("h2")).get(2));
		wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("h2")).get(2)));
		assertEquals("Certifications", driver.findElements(By.cssSelector("h2")).get(2).getText());
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(2).getCssValue("color"));
		highLight.highlightElement(driver, driver
				.findElements(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.get(2));
		highLight.highlightElement(driver, driver
				.findElements(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/ul"))
				.get(1));
	}

		@Disabled
	@Test
	void productsSectionTest() throws InterruptedException {
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		hoverJS.mouseHoverJScript(startnow, driver);
		highLight.highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.isDisplayed());

		// click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to products link and click
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		hoverJS.mouseHoverJScript(productsLink, driver);
		highLight.highlightElement(driver, productsLink);
		System.out.println("products nav element : " + productsLink.getText());

		try {
			productsLink.click();

			// From products section
			WebElement productsHeading = driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2"));
			// wait until products section is visible
			wait.until(d -> productsHeading.isDisplayed());

			// assert main heading
			highLight.highlightElement(driver, productsHeading);
			System.out.println("Main Heading in Products: " + productsHeading);
			assertEquals("PRODUCTS", productsHeading.getText());

			// sub heading
			WebElement subHeading = driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h4"));
			System.out.println("Sub Heading in Products: " + subHeading);
			assertEquals("Projects Under Development", subHeading.getText());
			assertEquals("rgba(128, 199, 131, 1)", subHeading.getCssValue("color"));
			highLight.highlightElement(driver, subHeading);

			// get product elements
			List<WebElement> products = driver.findElements(
					By.xpath("//div[contains(@class,'products-content')]/div[contains(@class,'product')]"));
			List<WebElement> productName = driver.findElements(
					By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-heading')]/h4"));
			List<WebElement> productImage = driver.findElements(
					By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-image')]/img"));
			List<WebElement> productContent = driver.findElements(
					By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-content')]/p"));
			List<WebElement> productReadMoreLink = driver
					.findElements(By.xpath("//div[contains(@class,'product-button')]/a"));

			driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();

			// close cookie button
			driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();

			for (WebElement product : products) {
				int index = products.indexOf(product);
				jsExecutor.executeScript("arguments[0].scrollIntoView();", product);
				wait.until(ExpectedConditions.visibilityOf(product));
				System.out.println("Product Name: " + productName.get(index).getText());
				System.out.println("Product Image: " + productImage.get(index).getDomAttribute("src"));
				System.out.println("Product Content: " + productContent.get(index).getText());
				System.out.println("Product Readmore Link: " + productReadMoreLink.get(index).getText());
				System.out.println("Product Readmore Link: " + productReadMoreLink.get(index).getDomAttribute("href"));

				// Checking Styles
				assertEquals("rgba(255, 255, 255, 1)", productName.get(index).getCssValue("color"));
				assertEquals("0px none rgba(0, 0, 0, 0.6)",
						driver.findElements(
								By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-content')]"))
								.get(index).getCssValue("border"));
				assertEquals("rgba(220, 245, 214, 1)",
						driver.findElements(
								By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-content')]"))
								.get(index).getCssValue("background-color"));
				assertEquals("rgba(0, 0, 0, 0.6)", productContent.get(index).getCssValue("color"));
				assertEquals("rgba(128, 128, 128, 1)", productReadMoreLink.get(index).getCssValue("color"));

				highLight.highlightElement(driver, productName.get(index));
				highLight.highlightElement(driver, productImage.get(index));
				highLight.highlightElement(driver, productContent.get(index));

				// hover over readmore link
				hoverJS.mouseHoverJScript(productReadMoreLink.get(index), driver);
				highLight.highlightElement(driver, productReadMoreLink.get(index));
			}
		} catch (ElementClickInterceptedException e) {
			System.out.println("Products link is not clickable at the moment!");
		}
	}

		@Disabled
	@Test
	void loopProductsLinkTest() throws InterruptedException {
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);

		hoverJS.mouseHoverJScript(startnow, driver);
		highLight.highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.isDisplayed());

		// click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to products link and click
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		highLight.highlightElement(driver, productsLink);
		System.out.println("products nav element : " + productsLink.getText());

		productsLink.click();

		// From products section
		WebElement productsHeading = driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2"));
		// wait until products section is visible
		wait.until(d -> productsHeading.isDisplayed());

		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();

		// get products elements
		List<WebElement> products = driver
				.findElements(By.xpath("//div[contains(@class,'products-content')]/div[contains(@class,'product')]"));

		for (WebElement product : products) {
			int index = products.indexOf(product);

			System.out.println("index: " + index);

			jsExecutor.executeScript("arguments[0].scrollIntoView();",
					driver.findElements(
							By.xpath("//div[contains(@class,'products-content')]/div[contains(@class,'product')]"))
							.get(index));
			wait.until(d -> driver
					.findElements(
							By.xpath("//div[contains(@class,'products-content')]/div[contains(@class,'product')]"))
					.get(index).isDisplayed());

			// hover over first service read more link
			hoverJS.mouseHoverJScript(
					driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a")).get(index), driver);
			highLight.highlightElement(driver,
					driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a")).get(index));
			driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a")).get(index).click();

//				wait.until(ExpectedConditions.urlToBe("https://novoproso.com/hra.html"));
			String firsturl = driver.getCurrentUrl();
			System.out.println("Current URL: " + firsturl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLight.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			wait.until(
					d -> driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2")).isDisplayed());
		}
	}

		@Disabled
	@Test
	void ServicesSectionTest() throws InterruptedException {
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		hoverJS.mouseHoverJScript(startnow, driver);
		highLight.highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.isDisplayed());

		// click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to services link and click
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		hoverJS.mouseHoverJScript(servicesLink, driver);
		highLight.highlightElement(driver, servicesLink);
		System.out.println("services nav element : " + servicesLink.getText());
		servicesLink.click();

		// From services section
		WebElement servicesHeading = driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2"));
		// wait until products section is visible
		wait.until(d -> servicesHeading.isDisplayed());

		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();

		// assert main heading
		highLight.highlightElement(driver, servicesHeading);
		System.out.println("Main Heading in Services: " + servicesHeading);
		assertEquals("SERVICES", servicesHeading.getText());

		// get services elements
		List<WebElement> services = driver
				.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceImage = driver
				.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'image')]/img"));
		List<WebElement> serviceName = driver
				.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'content')]/h4"));
		List<WebElement> serviceContent = driver
				.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'content')]/p"));
		List<WebElement> serviceReadMoreLink = driver
				.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));

		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();

		// close cookie button
		driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();

		for (WebElement service : services) {
			int index = services.indexOf(service);
			if (!service.isDisplayed()) {
				System.out.println("index: " + index);
				jsExecutor.executeScript("arguments[0].scrollIntoView();", service);
			}

			wait.until(d -> service.isDisplayed());
			System.out.println("Service Name: " + serviceName.get(index).getText());
			System.out.println("Service Image: " + serviceImage.get(index).getDomAttribute("src"));
			System.out.println("Service Content: " + serviceContent.get(index).getText());
			System.out.println("Service Readmore Link: " + serviceReadMoreLink.get(index).getText());
			System.out.println("Service Readmore Link: " + serviceReadMoreLink.get(index).getDomAttribute("href"));

			// Checking Styles
			assertEquals("rgba(57, 144, 30, 1)", serviceName.get(index).getCssValue("color"));
			assertEquals("0.8px solid rgb(0, 0, 0)",
					driver.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'content')]"))
							.get(index).getCssValue("border"));
			assertEquals("rgba(220, 245, 214, 1)",
					driver.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'content')]"))
							.get(index).getCssValue("background-color"));
			assertEquals("rgba(128, 128, 128, 1)", serviceContent.get(index).getCssValue("color"));
			assertEquals("rgba(128, 128, 128, 1)", serviceReadMoreLink.get(index).getCssValue("color"));

			highLight.highlightElement(driver, serviceName.get(index));
			highLight.highlightElement(driver, serviceImage.get(index));
			highLight.highlightElement(driver, serviceContent.get(index));

			// hover over readmore link
			hoverJS.mouseHoverJScript(serviceReadMoreLink.get(index), driver);
			highLight.highlightElement(driver, serviceReadMoreLink.get(index));
		}
	}

		@Disabled
	@Test
	void loopServicesLinkTest() throws InterruptedException {
		// close cookie button
		driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		hoverJS.mouseHoverJScript(startnow, driver);
		highLight.highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.isDisplayed());

		// click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to services link and click
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		highLight.highlightElement(driver, servicesLink);
		System.out.println("services nav element : " + servicesLink.getText());

		driver.findElement(By.xpath("//li/a[contains(@href,'#services')]")).click();

		// From services section
		WebElement servicesHeading = driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2"));
		// wait until services section is visible
		wait.until(d -> servicesHeading.isDisplayed());

		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();

		// get services elements
		List<WebElement> services = driver
				.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));

		for (WebElement service : services) {
			int index = services.indexOf(service);

			System.out.println("index: " + index);

			jsExecutor.executeScript("arguments[0].scrollIntoView();",
					driver.findElements(
							By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"))
							.get(index));
			wait.until(d -> driver
					.findElements(
							By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"))
					.get(index).isDisplayed());

			// hover over first service read more link
			hoverJS.mouseHoverJScript(
					driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(index), driver);
			highLight.highlightElement(driver,
					driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(index));
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(index).click();

			String firsturl = driver.getCurrentUrl();
			System.out.println("Current URL: " + firsturl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLight.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			wait.until(
					d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
		}
	}

		@Disabled
	@Test
	void careersSectionTest() throws InterruptedException {
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		hoverJS.mouseHoverJScript(startnow, driver);
		highLight.highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.isDisplayed());

		// click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to careers link and click
		WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
		hoverJS.mouseHoverJScript(careersLink, driver);
		highLight.highlightElement(driver, careersLink);
		System.out.println("careers nav element : " + careersLink.getText());

		careersLink.click();

		// From careers section
		WebElement careersHeading = driver.findElement(By.xpath("//div[contains(@class,'careers-heading')]/h2"));

		// wait until careers section is visible
		wait.until(d -> careersHeading.isDisplayed());

		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();

		// close cookie
		driver.findElement(By.xpath("//button[contains(@class,'cookie-btn')]")).click();

		try {

			// heading block background color
			assertEquals("rgba(220, 245, 214, 1)",
					driver.findElement(By.xpath("//div[contains(@class,'careers-heading-info')]"))
							.getCssValue("background-color"));

			// assert main heading
			highLight.highlightElement(driver, careersHeading);
			System.out.println("Main Heading in Careers: " + careersHeading.getText());
			assertEquals("CAREERS", careersHeading.getText());

			// sub heading info
			WebElement subHeading = driver.findElement(By.xpath("//div[contains(@class,'careers-heading')]/h4"));
			assertEquals("rgba(57, 144, 30, 1)", subHeading.getCssValue("color"));
			highLight.highlightElement(driver, subHeading);
			System.out.println("SubHeading in Careers: " + subHeading.getText());

			// resume contact
			WebElement contactMailElement = driver
					.findElement(By.xpath("//div[contains(@class,'career-mail-info')]/h4"));
			assertEquals("rgba(128, 128, 128, 1)", contactMailElement.getCssValue("color"));
			assertEquals("rgba(57, 144, 30, 1)", driver
					.findElement(By.xpath("//div[contains(@class,'career-mail-info')]/h4/span")).getCssValue("color"));
			assertEquals("600", driver.findElement(By.xpath("//div[contains(@class,'career-mail-info')]/h4/span"))
					.getCssValue("font-weight"));
			highLight.highlightElement(driver, contactMailElement);
			System.out.println("Resume contact mail: " + contactMailElement.getText());

			// get career elements
			List<WebElement> careers = driver.findElements(By.xpath("//div[contains(@class,'career-card')]"));
			List<WebElement> jobName = driver.findElements(By.xpath("//div[contains(@class,'career-card')]/h4"));
			List<WebElement> jobPositionheading = driver.findElements(
					By.xpath("//div[contains(@class,'card-body')]/div/span[contains(text(),'Position ID')]"));
			List<WebElement> jobPosition = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/p"));
			List<WebElement> jobDurationHeading = driver
					.findElements(By.xpath("//div[contains(@class,'card-body')]/div/span[contains(text(),'Length')]"));
			List<WebElement> jobDuration = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/p"));
			List<WebElement> jobLocationHeading = driver.findElements(
					By.xpath("//div[contains(@class,'card-body')]/div/span[contains(text(),'Location')]"));
			List<WebElement> jobLocation = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/p"));
			List<WebElement> jobEducationHeading = driver.findElements(
					By.xpath("//div[contains(@class,'card-body')]/div/span[contains(text(),'Education')]"));
			List<WebElement> jobEducation = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/p"));
			List<WebElement> jobDetailsLink = driver
					.findElements(By.xpath("//div[contains(@class,'card-footer')]/span"));

			for (WebElement career : careers) {
				int index = careers.indexOf(career);
				if (!career.isDisplayed()) {
					Thread.sleep(1000);
					System.out.println("index: " + index);
					jsExecutor.executeScript("arguments[0].scrollIntoView();", career);
					Thread.sleep(3000);
				}

				wait.until(d -> career.isDisplayed());
				System.out.println("job Name: " + jobName.get(index).getText());
				System.out.println(
						jobPositionheading.get(index).getText() + " : " + jobPosition.get(index * 4).getText());
				System.out.println(
						jobDurationHeading.get(index).getText() + " : " + jobDuration.get((index * 4) + 1).getText());
				System.out.println(
						jobLocationHeading.get(index).getText() + " : " + jobLocation.get((index * 4) + 2).getText());
				System.out.println(
						jobEducationHeading.get(index).getText() + " : " + jobEducation.get((index * 4) + 3).getText());
				System.out.println("job Details Link: " + jobDetailsLink.get(index).getText());
				System.out.println("\n");

				// Checking Styles
				assertEquals("rgba(1, 110, 14, 1)", jobName.get(index).getCssValue("color"));
//					assertEquals("2.4px solid rgb(128, 199, 131)", driver.findElements(By.xpath("//div[contains(@class,'career-card')]")).get(index).getCssValue("border"));
				assertEquals("rgba(255, 255, 255, 1)",
						driver.findElements(By.xpath("//div[contains(@class,'career-card')]")).get(index)
								.getCssValue("background-color"));
				assertEquals("rgba(0, 0, 0, 1)", jobPositionheading.get(index).getCssValue("color"));
				assertEquals("rgba(128, 128, 128, 1)", jobPosition.get((index * 4)).getCssValue("color"));
				assertEquals("rgba(0, 0, 0, 1)", jobDurationHeading.get(index).getCssValue("color"));
				assertEquals("rgba(128, 128, 128, 1)", jobDuration.get((index * 4) + 1).getCssValue("color"));
				assertEquals("rgba(0, 0, 0, 1)", jobLocationHeading.get(index).getCssValue("color"));
				assertEquals("rgba(128, 128, 128, 1)", jobLocation.get((index * 4) + 2).getCssValue("color"));
				assertEquals("rgba(0, 0, 0, 1)", jobEducationHeading.get(index).getCssValue("color"));
				assertEquals("rgba(128, 128, 128, 1)", jobEducation.get((index * 4) + 3).getCssValue("color"));

				assertEquals("0.8px solid rgb(128, 199, 131)", jobDetailsLink.get(index).getCssValue("border"));
				assertEquals("rgba(220, 245, 214, 1)", jobDetailsLink.get(index).getCssValue("background-color"));
				assertEquals("rgba(128, 128, 128, 1)", jobDetailsLink.get(index).getCssValue("color"));

				highLight.highlightElement(driver, jobName.get(index));
				highLight.highlightElement(driver, jobPositionheading.get(index));
				highLight.highlightElement(driver, jobPosition.get((index * 4)));
				highLight.highlightElement(driver, jobDurationHeading.get(index));
				highLight.highlightElement(driver, jobDuration.get((index * 4) + 1));
				highLight.highlightElement(driver, jobLocationHeading.get(index));
				highLight.highlightElement(driver, jobLocation.get((index * 4) + 2));
				highLight.highlightElement(driver, jobEducationHeading.get(index));
				highLight.highlightElement(driver, jobEducation.get((index * 4) + 3));

				// hover over jobDetails link
				hoverJS.mouseHoverJScript(jobDetailsLink.get(index), driver);
				action.moveToElement(jobDetailsLink.get(index)).perform();
				assertEquals("rgba(57, 144, 30, 1)", jobDetailsLink.get(index).getCssValue("color"));
				assertEquals("rgba(220, 245, 214, 1)", jobDetailsLink.get(index).getCssValue("background-color"));
				assertEquals("2.4px solid rgb(128, 199, 131)", jobDetailsLink.get(index).getCssValue("border"));
				highLight.highlightElement(driver, jobDetailsLink.get(index));
			}
		} catch (ElementClickInterceptedException e) {
			System.out.println("Careers Link is not clickable at present!");
		}
	}

		@Disabled
	@Test
	void careersSectionDetailsTest() throws InterruptedException {
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		hoverJS.mouseHoverJScript(startnow, driver);
		highLight.highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.isDisplayed());

		// click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to careers link and click
		WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
		highLight.highlightElement(driver, careersLink);
		System.out.println("careers nav element : " + careersLink.getText());
		careersLink.click();

		// From careers section
		WebElement careersHeading = driver.findElement(By.xpath("//div[contains(@class,'careers-heading')]/h2"));

		// wait until careers section is visible
		wait.until(d -> careersHeading.isDisplayed());
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();

		// close cookie
		driver.findElement(By.xpath("//button[contains(@class,'cookie-btn')]")).click();

		// get career elements
		List<WebElement> careers = driver.findElements(By.xpath("//div[contains(@class,'career-card')]"));
		List<WebElement> jobDetailsLink = driver.findElements(By.xpath("//div[contains(@class,'card-footer')]/span"));

		for (WebElement career : careers) {
			int index = careers.indexOf(career);
			jsExecutor.executeScript("arguments[0].scrollIntoView();", career);
			System.out.println(careers.indexOf(career));
			wait.until(d -> career.isDisplayed());
			// hover over jobDetails link
			hoverJS.mouseHoverJScript(jobDetailsLink.get(index), driver);
//				actions.moveToElement(jobDetailsLink.get(index)).perform();
			jobDetailsLink.get(index).click();

			String[] jobIDArray = { "AIML", "PEGA5", "QASEL7", "SD241", "SD242", "NSE18" };
			hoverJS.mouseHoverJScript(driver.findElement(By.xpath("//div[contains(@id,'" + jobIDArray[index] + "')]")),
					driver);
//				actions.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'"+jobIDArray[index]+"')]"))).perform();
			WebElement jobName = driver
					.findElement(By.xpath("//div[contains(@id,'" + jobIDArray[index] + "')]/div/div/h2"));
			WebElement jobBasicInfo = driver.findElements(By.xpath("//div[contains(@class,'job-basic-info')]"))
					.get((index));
			WebElement jobMessage = driver
					.findElement(By.xpath("//div[contains(@id,'" + jobIDArray[index] + "')]/div/div/p"));
			WebElement jobQualification = driver.findElements(By.xpath("//div[contains(@class,'job-qualification')]"))
					.get(index);
			WebElement jobApplyInfo = driver.findElements(By.xpath("//div[contains(@class,'job-apply-info')]"))
					.get(index);

			System.out.println("Job Name : " + jobName.getText());
			highLight.highlightElement(driver, jobName);
			System.out.println("Job Basic Info : " + jobBasicInfo.getText());
			highLight.highlightElement(driver, jobBasicInfo);
			System.out.println("Job Qualifications heading: " + jobQualification.getText());
			highLight.highlightElement(driver, jobQualification);
			System.out.println("Job ApplyInfo : " + jobApplyInfo.getText());
			highLight.highlightElement(driver, jobApplyInfo);
			jsExecutor.executeScript("arguments[0].scrollIntoView();", jobMessage);
			wait.until(ExpectedConditions.visibilityOf(jobMessage));
			System.out.println("Job message : " + jobMessage.getText());
			highLight.highlightElement(driver, jobMessage);
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'overlay')]")).get(index * 2),
					driver);
//				actions.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'overlay')]")).get(index*2)).perform();
			driver.findElements(By.xpath("//div[contains(@class,'overlay')]")).get(index * 2).click();
		}
	}

		@Disabled
	@Test
	void ContactSectionTest() throws InterruptedException {
		wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		hoverJS.mouseHoverJScript(startnow, driver);
		highLight.highlightElement(driver, startnow);
		startnow.click();

		// wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver
				.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2"))
				.isDisplayed());

		// click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		// go to contact us link and click
		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		hoverJS.mouseHoverJScript(contactLink, driver);
		highLight.highlightElement(driver, contactLink);
		System.out.println("contact nav element : " + contactLink.getText());

		try {
			contactLink.click();

			// From contact us section
			WebElement contactUsHeading = driver
					.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2"));
			WebElement subHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/p"));

			// wait until contact us section is visible
			wait.until(d -> contactUsHeading.isDisplayed());

			driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();

			// assert image
			assertEquals("url(\"https://novoproso.com/images/contact-bg.jpg\")",
					driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]")).getCssValue("background-image"));

			// assert main heading
			highLight.highlightElement(driver, contactUsHeading);
			System.out.println("Main Heading in Contact us: " + contactUsHeading.getText());
			assertEquals("Contact Us", contactUsHeading.getText());

			// assert main heading
			highLight.highlightElement(driver, subHeading);
			System.out.println("SubHeading in Contact us: " + subHeading.getText());

			WebElement conatctFormBlock = driver
					.findElements(By.xpath("//div[contains(@class, 'contact-form')]/div/div")).get(0);
			WebElement addressBlock = driver.findElements(By.xpath("//div[contains(@class, 'contact-form')]/div/div"))
					.get(1);

			System.out.println("contact us location: " + conatctFormBlock.getLocation());
			System.out.println("Address block location" + addressBlock.getLocation());

			// address
			WebElement address = driver.findElements(By.xpath("//div[contains(@class, 'contact-info')]/ul/li")).get(0);
			WebElement phone = driver.findElements(By.xpath("//div[contains(@class, 'contact-info')]/ul/li")).get(1);
			WebElement emailAddress = driver.findElements(By.xpath("//div[contains(@class, 'contact-info')]/ul/li"))
					.get(2);
			highLight.highlightElement(driver, address);
			System.out.println("Address given: " + address.getText());
			highLight.highlightElement(driver, phone);
			System.out.println("Phone number : " + phone.getText());
			highLight.highlightElement(driver, emailAddress);
			System.out.println("Email address : " + emailAddress.getText());

			// map
			WebElement mapElement = driver
					.findElement(By.xpath("//div[contains(@class, 'contact-info')]/ul/li/iframe"));
			jsExecutor.executeScript("arguments[0].scrollIntoView();", mapElement);
			wait.until(ExpectedConditions.visibilityOf(mapElement));

			driver.findElement(By.xpath("//div[contains(@class, 'contact-info')]/ul/li/iframe"));
			driver.switchTo().frame(mapElement);

			action.moveByOffset(150, 150);
			wait.until(ExpectedConditions.visibilityOf(mapElement));
			action.clickAndHold().moveByOffset(30, 30);
			wait.until(ExpectedConditions.visibilityOf(mapElement));
			action.release();

			wait.until(ExpectedConditions.visibilityOf(mapElement));
			WebElement locationAddress = driver.findElement(By.xpath("//*[@id=\"mapDiv\"]/div/div[3]/div[3]/div/div"));
			highLight.highlightElement(driver, locationAddress);

			System.out.println("Location Address : " + locationAddress.getText());

			WebElement mapLink = driver.findElement(By.xpath("//div[contains(@class,'google-maps-link')]/a"));
			jsExecutor.executeScript("arguments[0].scrollIntoView();", mapLink);
			wait.until(ExpectedConditions.visibilityOf(mapLink));
			hoverJS.mouseHoverJScript(mapLink, driver);
			mapLink.click();

			Object[] windowHandles = driver.getWindowHandles().toArray();
			driver.switchTo().window(windowHandles[1].toString());

			System.out.println("map URL: " + driver.getCurrentUrl());
			driver.close();
			driver.switchTo().window(windowHandles[0].toString());
			System.out.println("map URL: " + driver.getCurrentUrl());

		} catch (TimeoutException e) {
			System.out.println("Time exceeded to go for contact us heading element!");
		} catch (ElementClickInterceptedException e) {
			System.out.println("contact us link is not clickable at the moment!");
		}
	}

}
