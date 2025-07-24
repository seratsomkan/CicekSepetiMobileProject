package pages;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.ReusableMethods;
import java.util.List;

@Getter
public class CategoryPage {

    private static CategoryPage instance;

    public CategoryPage(){PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver()),this);}

    public static CategoryPage getInstance() {
        if (instance == null) {
            instance = new CategoryPage();
        }
        return instance;
    }

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/filter']")
    private WebElement filterButton;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/order']")
    private WebElement sortButton;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/sortItemName']")
    private List<WebElement> sortOptions;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/image']")
    private List<WebElement> productImageList;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/currentPrice']")
    private List<WebElement> currentPriceList;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/productName']")
    private List<WebElement> productNameList;

    ProductFilterPage productFilterPage = ProductFilterPage.getInstance();

    @Getter
    @Setter
    private String categoryPageProductName;

    @Getter
    @Setter
    private int categoryPageFirstProductPrice;

    Logger logger = LogManager.getLogger(CategoryPage.class);

    public void clickFilterButton(){
        logger.info("Kullanıcı filtreleme sayfasına yönlendiriliyor...");
        ReusableMethods.waitForElementToBeVisibleAndClickElement(filterButton);
        Allure.step("Kullanıcı filtreleme sayfasına yönlendirildi.");
    }

    public void checkProductImagesVisibility(){
        logger.info("Sayfada en az bir ürün görseli olduğu doğrulanıyor...");
        Assert.assertFalse("Sayfada ürün bulunamamıştır.",productImageList.isEmpty());
        Allure.step("Sayfada en az bir ürün görseli olduğu doğrulandı.");
    }

    public void sortProductsBy(String sortType){
        sortType = sortType.toLowerCase();
        ReusableMethods.waitForElementToBeVisibleAndClickElement(sortButton);
        logger.info("Ürünlerin sıralaması '"+sortType+"' olarak seçiliyor...");
        switch (sortType){
            case "ucuzdan pahalıya":
                ReusableMethods.waitForElementToBeVisibleAndClickElement(sortOptions.get(1));

                for (int i=0;i<productImageList.size()-1;i++){
                    int price1 = Integer.parseInt(currentPriceList.get(i).getText().replaceAll("\\D",""));
                    int price2 = Integer.parseInt(currentPriceList.get(i+1).getText().replaceAll("\\D",""));
                    Assert.assertTrue("Fiyat sıralaması yanlıştır.",price1<=price2);
                }
                break;

            case "pahalıdan ucuza":
                ReusableMethods.waitForElementToBeVisibleAndClickElement(sortOptions.get(2));

                for (int i=0;i<productImageList.size()-1;i++){
                    int price1 = Integer.parseInt(currentPriceList.get(i).getText().replaceAll("\\D",""));
                    int price2 = Integer.parseInt(currentPriceList.get(i+1).getText().replaceAll("\\D",""));
                    Assert.assertTrue("Fiyat sıralaması yanlıştır.",price1>=price2);
                }
                break;
        }
        Allure.step("Ürünlerin sıralaması '"+ sortType +"' olarak seçildi.");
    }

    public void checkPricesInRange(){
        String firstProductPriceStr = currentPriceList.get(0).getText().replaceAll("\\D","");
        categoryPageFirstProductPrice =  Integer.parseInt(firstProductPriceStr.substring(0,firstProductPriceStr.length()-2));

        String lastProductPriceStr = currentPriceList.get(3).getText().replaceAll("\\D","");
        int lastProductPrice =  Integer.parseInt(lastProductPriceStr.substring(0,lastProductPriceStr.length()-2));

        logger.info("Ürün fiyatlarının belirlenen aralıkta olduğu doğrulanıyor...");
        Assert.assertTrue("Ürün fiyatları, belirlenen aralıkta değildir.",
                productFilterPage.getActualMinPrice()<= categoryPageFirstProductPrice && productFilterPage.getActualMaxPrice()>=lastProductPrice);
        Allure.step("Ürün fiyatlarının belirlenen aralıkta olduğu doğrulandı.");
    }

    public void goToFirstProductDetailPage(int productOrder){
        setCategoryPageProductName(productNameList.get(productOrder-1).getText());
        logger.info(productOrder+". ürün sayfasına gidiliyor.");
        productNameList.get(productOrder-1).click();
        Allure.step(productOrder+". ürün sayfasına gidildi.");
    }
}
