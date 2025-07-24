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

@Getter
public class MyAccountPage {
    public MyAccountPage(){PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver()),this);}

    @AndroidFindBy(xpath = "//*[@class='androidx.appcompat.widget.LinearLayoutCompat']")
    private WebElement accountSections;

    @AndroidFindBy(xpath = "(//*[@class='android.widget.LinearLayout'])[1]")
    private WebElement myOrdersButton;

    Logger logger = LogManager.getLogger(MyAccountPage.class);

    public void clickButtonByName(String button){
        button = button.toLowerCase();
        logger.info("Kullanıcı '"+button+"' butonuna tıklıyor.");
        switch (button){
            case "üye girişi":
                accountSections.click();
                break;
        }
        Allure.step("Kullanıcı '"+ button +"' butonuna tıkladı.");
    }
}
