package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public abstract class AllegroBasePage {
    public static final String URL = "https://allegro.pl/";
    protected static WebDriver driver;
    protected static Wait<WebDriver> wait;

    private static final int timeOutInSeconds = 5;
    private static final int waitTimeOutInSeconds = 10;
    private static final int waitPoolingInMillis = 500;

    @FindBy(xpath = "//button[@data-role = 'accept-consent']")
    private WebElement btnAcceptCookiesPolicy;

    public AllegroBasePage(WebDriver driver){
        if(AllegroBasePage.driver == null){
            AllegroBasePage.driver = driver;
        }
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,timeOutInSeconds), this);
        //PageFactory.initElements(driver, this);
    }

    protected void waitForElement(WebElement element){
        initializeFluentWait();
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElement(By by){
        initializeFluentWait();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    private void initializeFluentWait(){
        if(wait == null){
            wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(waitTimeOutInSeconds))
                    .pollingEvery(Duration.ofMillis(waitPoolingInMillis))
                    .ignoring(NoSuchElementException.class,
                            StaleElementReferenceException.class);
        }
    }

    public void acceptCookiesPolicy(){
        waitForElement(btnAcceptCookiesPolicy);
        btnAcceptCookiesPolicy.click();
    }
}
