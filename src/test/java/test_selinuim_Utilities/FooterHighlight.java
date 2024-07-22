package test_selinuim_Utilities;


import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(Lifecycle.PER_CLASS)
public class FooterHighlight {

Highlight highLight = new Highlight();
    
    public FooterHighlight() {
        // TODO Auto-generated constructor stub
    }

    public void footerHighlightElement(WebDriver driver, JavascriptExecutor jsExecutor, WebDriverWait wait) {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");

        wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]")).isDisplayed());
        WebElement footerLogoLink = driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]"));
        WebElement footerSocialIcons = driver.findElement(By.xpath("//div[contains(@class,'social-icons')]/ul"));
        WebElement policyLink = driver.findElement(By.xpath("//div/p/a[contains(@href,'policy.html')]"));
        WebElement footerbottomCopyright = driver.findElement(By.xpath("//div[contains(@id,'md')]/p"));
        WebElement footerbottomDesignedBy = driver.findElement(By.xpath("//p[contains(@class,'pull-right')]"));

        highLight.highlightElement(driver, footerLogoLink);
        highLight.highlightElement(driver, footerSocialIcons);
        highLight.highlightElement(driver, policyLink);
        highLight.highlightElement(driver, footerbottomCopyright);
        highLight.highlightElement(driver, footerbottomDesignedBy);
    }


}
