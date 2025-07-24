package pages;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Allure;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

@Getter
public class CategoryMenuPage {
    public CategoryMenuPage(){PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver()),this);}

    @AndroidFindBy(xpath = "(//*[@text='Doğum Günü'])[1]")
    private WebElement birthdayMenuButton;

    @AndroidFindBy(xpath = "//*[@text='Kadına Hediye']")
    private WebElement giftForWomenButton;

    Logger logger = LogManager.getLogger(CategoryMenuPage.class);

    public void selectCategory(String mainCategory, String childCategory){
        mainCategory = mainCategory.toLowerCase();
        childCategory = childCategory.toLowerCase();

        logger.info("Kullanıcı '"+mainCategory+"' kategorisinden '"+childCategory+"' kategorisine erişiyor...");

        switch (mainCategory){
            case "doğum günü":
                birthdayMenuButton.click();

                switch (childCategory){
                    case "kadına hediye":
                        giftForWomenButton.click();
                        break;
                }
                break;
        }
        Allure.step("Kullanıcı '"+mainCategory+"' kategorisinden '"+childCategory+"' kategorisine erişti.");
    }
}
