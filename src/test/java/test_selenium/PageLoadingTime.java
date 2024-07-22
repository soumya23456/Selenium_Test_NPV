package test_selenium;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import test_selinuim_Utilities.BrowserSetup;



class PageLoadingTime {

	//chrome, msedge, firefox
		String browser = "msedge";
		BrowserSetup browserSetupConfig = new BrowserSetup();
		WebDriver driver;
		JavascriptExecutor jsexecutor;
//		RemoteWebDriver driver;

//		@Disabled
		@Test
		void pageLoadTest() throws InterruptedException, IOException {
			
			driver = browserSetupConfig.getBrowserGridSetUp(browser);
//			driver = browserSetupConfig.getBrowserSetUp(browser);

			//Throws TimeoutException
//			driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);

			//throws InvalidArgumentException
//			driver.manage().timeouts().pageLoadTimeout(-7, TimeUnit.SECONDS);
			
			Instant startTime = Instant.now();
			System.out.println(startTime.toString());
			driver.get("https://novoprovigilance.com/");
			
			Instant endTime = Instant.now();
			System.out.println(endTime.toString());

			Duration duration = Duration.between(startTime, endTime);
			System.out.println("page Load Time: "+ duration.toMillis() + " millisecs");
			System.out.println(driver.getTitle());
			
			driver.quit();
		}
		
//		@Disabled
		@Test
		void ScreenShotTest() throws InterruptedException, IOException {

//			driver = browserSetupConfig.getBrowserSetUp(browser);
			driver = browserSetupConfig.getBrowserGridSetUp(browser);
			driver.manage().window().maximize();
			jsexecutor = (JavascriptExecutor) driver;
			driver.get("https://novoprovigilance.com/csr.html");
			((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			//Full screenshot as file
			File screenshotsrcdata = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./screenshots/image1.jpg");
			FileUtils.copyFile(screenshotsrcdata, destFile);
			System.out.println("screenshot saved successful");

			//Full screenshot through bytes
			byte[] bytearr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			File bytedestFile = new File("./screenshots/imageBytes.jpg");
			FileOutputStream fos = new FileOutputStream(bytedestFile);
			fos.write(bytearr);
			fos.close();
			System.out.println("screenshot saved successful");
			
			//Full screenshot through 
			String base64code = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			byte[] byteArr = Base64.getDecoder().decode(base64code);
			File basedestFile = new File("./screenshots/remoteimageBase64.jpg");
			FileOutputStream fosbase = new FileOutputStream(basedestFile);
			fosbase.write(byteArr);
			fosbase.close();
			System.out.println("screenshot saved successful");
			
			//footer screenshot
			jsexecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
			WebElement section = driver.findElement(By.cssSelector("footer"));
			File footersrc = section.getScreenshotAs(OutputType.FILE);
			File footerdest = new File("./screenshots/footer.jpg");
			FileUtils.copyFile(footersrc, footerdest);
			System.out.println("screenshot saved successful");		
			
			driver.quit();
		}

}
