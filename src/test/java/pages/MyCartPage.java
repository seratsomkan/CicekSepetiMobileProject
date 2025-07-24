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

public class MyCartPage {
    public MyCartPage(){PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver()),this);}

    @AndroidFindBy(xpath = "(//*[@class='android.widget.TextView'])[8]")
    private WebElement myCartTitle;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/productName']")
    private WebElement productName;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/basket_price_view']")
    private WebElement productPrice;

    CategoryPage categoryPage = CategoryPage.getInstance();
    Logger logger = LogManager.getLogger(MyCartPage.class);

    public void verifyCartPageTitle(){
        logger.info("Sepet sayfasının görüntülendiği doğrulanıyor...");
        Assert.assertTrue(myCartTitle.isDisplayed());
        Allure.step("Sepet sayfasının görüntülendiği doğrulandı.");
    }

    public void verifyProductNameAndPrice(){
        logger.info("Ürün adı doğrulaması yapılıyor... Beklenen:"+categoryPage.getCategoryPageProductName()+" | Görünen:"+productName.getText());
        Assert.assertEquals("Ürün adı eşleşmedi.",productName.getText(), categoryPage.getCategoryPageProductName());
        Allure.step("Sepet sayfasında ürün adı doğrulandı.");

        String  productPriceStr = productPrice.getText().replaceAll("\\D","");
        int productPriceInt = Integer.parseInt(productPriceStr.substring(0,productPriceStr.length()-2));
        logger.info("Ürün fiyatı doğrulaması yapılıyor. Beklenen:"+categoryPage.getCategoryPageFirstProductPrice()+" | Görünen:"+productPriceInt);
        Assert.assertEquals("Ürün fiyatı eşleşmedi.",categoryPage.getCategoryPageFirstProductPrice(), productPriceInt);
        Allure.step("Sepet saufasında ürün fiyatı doğrulandı.");
    }
}
