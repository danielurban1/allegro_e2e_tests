package allegro_tests;

import enums.PhoneColor;
import org.testng.annotations.Test;
import pages.AllegroBasePage;
import pages.AllegroMainPage;
import pages.AllegroResultsPage;
import static utils.LoggerUtils.info;
import utils.TaxUtils;

import static enums.PhoneColor.BLACK;

public class AllegroSearchTests extends BaseTest{
    private AllegroMainPage allegroMainPage;
    private AllegroResultsPage allegroResultsPage;

    private final PhoneColor phoneColor = BLACK;
    private final double taxValue = 0.23;

    @Test
    public void openAllegroPage(){
        allegroMainPage = new AllegroMainPage(driver);
        driver.get(AllegroBasePage.URL);
        allegroMainPage.acceptCookiesPolicy();
    }

    @Test(dependsOnMethods = "openAllegroPage")
    public void searchForAProduct(){
        allegroMainPage.searchForAProduct("Iphone 11");
    }

    @Test(dependsOnMethods = "searchForAProduct")
    public void selectPhoneColor() {
        allegroResultsPage = new AllegroResultsPage(driver);
        allegroResultsPage.selectPhoneColor(phoneColor);
        allegroResultsPage.getHighestPrices();
    }

    @Test(dependsOnMethods = "selectPhoneColor")
    public void displayNumberOfSearchResultsOnTheFirstPage() {
        int numberOfProducts = allegroResultsPage.getNumberOfProducts();
        info("Number of products: " + numberOfProducts);
    }

    @Test(dependsOnMethods = "displayNumberOfSearchResultsOnTheFirstPage")
    public void displayPriceOfTheMostExpensiveProductWithAdditionalTax()  {
        double priceAfterTax = TaxUtils.getValueWithAddedTax(allegroResultsPage.getHighestPrices(), taxValue);
        info("Price of the most expensive product with added tax (" + taxValue + "): " +
                priceAfterTax);
    }
}
