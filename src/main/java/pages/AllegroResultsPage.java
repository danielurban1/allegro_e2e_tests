package pages;

import enums.PhoneColor;
import enums.SearchResultSubcategory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AllegroResultsPage extends AllegroTopBanner{

    @FindBy(xpath = "//div[@data-box-name = 'items-v3']")
    private WebElement divSearchResults;

    @FindBy(xpath = "//div[@data-box-name = 'items-v3']//article")
    private List<WebElement> lblProduct;

    @FindBy(xpath = "//div[@data-box-name = 'items-v3']//article//span[@class = '_1svub _lf05o']")
    private List<WebElement> txtProductPrice;

    public AllegroResultsPage(WebDriver driver) {
        super(driver);
    }

    public void selectSubcategory(SearchResultSubcategory subcategory){
        getSubcategory(subcategory).click();
    }

    public void selectPhoneColor(PhoneColor phoneColor){
        getPhoneColorSelector(phoneColor).click();
    }

    private WebElement getSubcategory(SearchResultSubcategory subcategory){
        By by = By.xpath("//a[contains(@data-custom-params, '" + subcategory.getSubcategory() + "')]");
        waitForElement(by);
        return driver.findElement(by);
    }

    private WebElement getPhoneColorSelector(PhoneColor phoneColor){
        By by = By.xpath("//span[contains(text(), '" + phoneColor.getColorValue() + "')]");
        waitForElement(by);
        return driver.findElement(by);
    }

    public int getNumberOfProducts() {
        waitForElement(divSearchResults);
        return lblProduct.size();
    }

    public Double getHighestPrices() {
        waitForElement(divSearchResults);
        return txtProductPrice.stream()
                .mapToDouble(element -> Double.parseDouble(element.getText()
                .replaceAll("[^0-9]", "")) / 100)
                .max()
                .orElse(0);
    }
}
