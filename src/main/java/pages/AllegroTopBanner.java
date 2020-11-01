package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AllegroTopBanner extends AllegroBasePage{

    @FindBy(xpath = "//input[@data-role= 'search-input']")
    private WebElement inputSearch;

    @FindBy(xpath = "//button[@data-role= 'search-button']")
    private WebElement btnSearch;

    public AllegroTopBanner(WebDriver driver) {
        super(driver);
    }

    public void searchForAProduct(String productName){
        waitForElement(inputSearch);
        inputSearch.sendKeys(productName);
        waitForElement(btnSearch);
        btnSearch.click();
    }
}
