package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ProductPage {
    public ProductPage(){PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver()),this);}

    @AndroidFindBy(xpath = "(//*[@class='android.widget.TextView'])[1]")
    private WebElement productBrandAndName;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/currentPrice']")
    private WebElement productPrice;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/addCart']")
    private WebElement addToCartButton;

    CategoryPage categoryPage = CategoryPage.getInstance();
    Logger logger = LogManager.getLogger(ProductPage.class);

    public void verifyProductNameAndPrice(){
        logger.info("Ürün adı doğrulaması yapılıyor... Beklenen:"+categoryPage.getCategoryPageProductName()+" | "+"Görünen:"+productBrandAndName.getText());
        Assert.assertTrue("Ürün adı beklenenden farklı. Sayfadaki:"+productBrandAndName.getText()+"\nBeklenen:"+categoryPage.getCategoryPageProductName(),
                productBrandAndName.getText().contains(categoryPage.getCategoryPageProductName()));
        Allure.step("Ürün sayfasında ürün adı doğrulandı.");

        String  productPriceStr = productPrice.getText().replaceAll("\\D","");
        int productPriceInt = Integer.parseInt(productPriceStr.substring(0,productPriceStr.length()-2));
        logger.info("Ürün fiyatı doğrulaması yapılıyor... Beklenen:"+categoryPage.getCategoryPageFirstProductPrice()+" | "+"Görünen:"+productPriceInt);
        Assert.assertEquals("Ürün fiyatı beklenenden farklı. Sayfadaki:"+productPriceInt+"\nBeklenen:"+categoryPage.getCategoryPageFirstProductPrice(),
                categoryPage.getCategoryPageFirstProductPrice(), productPriceInt);
        Allure.step("Ürün sayfasında ürün fiyatı doğrulandı.");
    }

    public void addProductToCart(){
        logger.info("Ürün sepete ekleniyor.");
        addToCartButton.click();
        Allure.step("Ürün sepete eklendi.");
    }
}
