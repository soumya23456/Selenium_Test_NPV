package test_selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
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
public class MainPageHeaderEvents {
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
//	driver = browserSetupConfig.getBrowserSetUp(browser);
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

@BeforeEach
public void setUp() throws Exception {
	

}

@AfterEach
public void tearDown() throws Exception {
//	driver.quit();
}

//@Disabled
@Test
public void headerEvents() throws InterruptedException {
	
	driver.get("https://novoprovigilance.com/");
	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());

//	Thread.sleep(200);
//	 start button check
	wait.until(d -> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
//	Thread.sleep(1000);
	WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
	wait.until(d -> startnow.isDisplayed());
	highlight.highlightElement(driver, startnow);
	startnow.click();
//	Thread.sleep(100);

	WebElement homeLink = driver.findElement(By.xpath("//li/a[@href='#home']"));
	highlight.highlightElement(driver, homeLink);

	// assert title
	String title = driver.getTitle();
	assertEquals("NOVO PROVIGILANCE LLC", title);
//	Thread.sleep(500);

	// mouse hover NPV
	WebElement NPV = driver.findElement(By.linkText("NOVO PROVIGILANCE LLC"));
//	Thread.sleep(100);

	// mouse action NPV
	actions.moveToElement(NPV).perform();
	Thread.sleep(100);

	WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[@href='#about-us']"));
	WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
	wait.until(ExpectedConditions.visibilityOf(aboutUsLink));
	System.out.println("about us nav element : " + aboutUsLink.getText());
	assertEquals("rgba(0, 0, 0, 0)", aboutUsLink.getCssValue("background-color"));
	highlight.highlightElement(driver, aboutUsLink);
	actions.moveToElement(aboutUsLink).perform();
//	Thread.sleep(100);
	wait.until(d -> aboutUsDropdownElement.isDisplayed());
	WebElement csrElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'csr.html')]"));
	actions.moveToElement(csrElement).perform();
//	Thread.sleep(200);
//	assertEquals("rgba(32, 158, 72, 1)", csrElement.getCssValue("color"));
//	assertEquals("csr.html", csrElement.getDomAttribute("href"));
	highlight.highlightElement(driver, csrElement);

	Thread.sleep(100);
	WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
	highlight.highlightElement(driver, productsLink);
	actions.moveToElement(productsLink).perform();
	System.out.println("products nav element : " + productsLink.getText());
	WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
	wait.until(d -> productsDropdownElement.isDisplayed());
	Thread.sleep(100);
	WebElement danElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'DSAN.html')]"));
	actions.moveToElement(danElement).perform();
	Thread.sleep(200);
	assertEquals("rgba(32, 158, 72, 1)", danElement.getCssValue("color"));
	assertEquals("DSAN.html", danElement.getDomAttribute("href"));
	highlight.highlightElement(driver, danElement);

	Thread.sleep(100);
	WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
	System.out.println("services nav element : " + servicesLink.getText());
	highlight.highlightElement(driver, servicesLink);
	actions.moveToElement(servicesLink).perform();
	WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
	Thread.sleep(100);
	wait.until(d -> servicesDropdownElement.isDisplayed());
	
	WebElement cpElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href,'cp.html')]"));
	WebElement miccElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'micc.html')]"));
	WebElement bigDataElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'bigData.html')]"));
	WebElement lmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'lm.html')]"));
	WebElement sdrmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'sdrm.html')]"));
	WebElement arElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'ar.html')]"));
	WebElement SAsElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'SAs.html')]"));
	WebElement mspvmsElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href,'mspvms.html')]"));

	actions.moveToElement(cpElement).perform();
	Thread.sleep(200);
	assertEquals("rgba(32, 158, 72, 1)", cpElement.getCssValue("color"));
	assertEquals("cp.html", cpElement.getDomAttribute("href"));
	highlight.highlightElement(driver, cpElement);
	actions.moveToElement(miccElement).perform();
	Thread.sleep(200);
	assertEquals("rgba(32, 158, 72, 1)", miccElement.getCssValue("color"));
	assertEquals("micc.html", miccElement.getDomAttribute("href"));
	highlight.highlightElement(driver, miccElement);
	actions.moveToElement(bigDataElement).perform();
	Thread.sleep(200);
	assertEquals("rgba(32, 158, 72, 1)", bigDataElement.getCssValue("color"));
	assertEquals("bigData.html", bigDataElement.getDomAttribute("href"));
	highlight.highlightElement(driver, bigDataElement);
	actions.moveToElement(lmElement).perform();
	Thread.sleep(200);
	assertEquals("rgba(32, 158, 72, 1)", lmElement.getCssValue("color"));
	assertEquals("lm.html", lmElement.getDomAttribute("href"));
	highlight.highlightElement(driver, lmElement);
	actions.moveToElement(sdrmElement).perform();
	Thread.sleep(200);
	assertEquals("rgba(32, 158, 72, 1)", sdrmElement.getCssValue("color"));
	assertEquals("sdrm.html", sdrmElement.getDomAttribute("href"));
	highlight.highlightElement(driver, sdrmElement);
	actions.moveToElement(arElement).perform();
	Thread.sleep(200);
	assertEquals("rgba(32, 158, 72, 1)", arElement.getCssValue("color"));
	assertEquals("ar.html", arElement.getDomAttribute("href"));
	highlight.highlightElement(driver, arElement);
	actions.moveToElement(SAsElement).perform();
	Thread.sleep(200);
	assertEquals("rgba(32, 158, 72, 1)", SAsElement.getCssValue("color"));
	assertEquals("SAs.html", SAsElement.getDomAttribute("href"));
	highlight.highlightElement(driver, SAsElement);
	actions.moveToElement(mspvmsElement).perform();
	Thread.sleep(200);
	assertEquals("rgba(32, 158, 72, 1)", mspvmsElement.getCssValue("color"));
	assertEquals("mspvms.html", mspvmsElement.getDomAttribute("href"));
	highlight.highlightElement(driver, mspvmsElement);

	Thread.sleep(100);
	WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
	System.out.println("careers nav element : " + careersLink.getText());
	highlight.highlightElement(driver, careersLink);
	actions.moveToElement(careersLink).perform();
	Thread.sleep(100);

	WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
	highlight.highlightElement(driver, contactLink);
	System.out.println("contact us nav element : " + contactLink.getText());
	actions.moveToElement(contactLink).perform();
	Thread.sleep(100);

	Thread.sleep(100);
	WebElement careersElement = driver.findElement(By.xpath("//li/a[@href='#career']"));
	System.out.println("careers nav element : " + careersElement.getText());
	highlight.highlightElement(driver, careersElement);
	actions.moveToElement(careersElement).perform();
	Thread.sleep(100);

	WebElement contactElement = driver.findElement(By.xpath("//li/a[@href='#contact']"));
	highlight.highlightElement(driver, contactLink);
	System.out.println("contact us nav element : " + contactElement.getText());
	actions.moveToElement(contactElement).perform();
	Thread.sleep(100);
}

}
