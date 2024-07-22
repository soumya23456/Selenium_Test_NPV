package test_selinuim_Utilities;



import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@TestInstance(Lifecycle.PER_CLASS)
public class Highlight {

	public Highlight() {
        // TODO Auto-generated constructor stub
    }
    
    public void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;')", element);
    }

    public void removeHighlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'border: none;')", element);
    }

}
