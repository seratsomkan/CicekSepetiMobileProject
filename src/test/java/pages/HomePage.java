package pages;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Allure;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.E2EStepDef;
import utilities.Driver;
import utilities.ReusableMethods;
import java.util.List;

@Getter
public class HomePage {
    public HomePage(){PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver()),this);}

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/rightImage")
    private WebElement giftAndExtraButton;

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/leftImage")
    private WebElement flowerButton;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement notificationAllowButton;

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/navigation_bar_item_icon_view']")
    private List<WebElement> bottomNavigationBar;

    Logger logger = LogManager.getLogger(HomePage.class);

    public void selectHomePageCategory(String operation){
        operation = operation.toLowerCase();
        logger.info("Kullanıcı "+operation+" işlemini seçiyor...");
        switch (operation){
            case "hediye & ekstra":
                ReusableMethods.waitForElementToBeVisibleAndClickElement(giftAndExtraButton);
                break;
            case "çiçek":
                ReusableMethods.waitForElementToBeVisibleAndClickElement(flowerButton);
                break;
            default:
                System.out.println("Seçim bulunamamıştır.");
                break;
        }
        Allure.step("Kullanıcı '"+operation+"' işlemini seçti.");
    }

    public void selectBottomNavigationItem(String item){
        item = item.toLowerCase();
        logger.info("Kullanıcı alt menüden '" + item + "' butonuna tıklıyor...");
        switch (item){
            case "anasayfa":
                bottomNavigationBar.get(0).click();
                break;
            case "kategoriler":
                bottomNavigationBar.get(1).click();
                break;
            case "favorilerim":
                bottomNavigationBar.get(2).click();
                break;
            case "sepetim":
                bottomNavigationBar.get(3).click();
                break;
            case "hesabım":
                bottomNavigationBar.get(4).click();
                break;
            default:
                System.out.println("Seçim bulunamamıştır.");
                break;
        }
        Allure.step("Kullanıcı alt menüden '"+ item +"' butonuna tıkladı.");
    }
}
