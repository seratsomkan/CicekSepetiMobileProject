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
import utilities.ReusableMethods;

@Getter
public class AddressPromptPage {
    public AddressPromptPage(){PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver()),this);}

    @AndroidFindBy(xpath = "//*[@resource-id='com.ciceksepeti.ciceksepeti:id/search']")
    private WebElement addressTextBox;

    @AndroidFindBy(xpath = "(//*[@resource-id='com.ciceksepeti.ciceksepeti:id/csr_tv'])[1]")
    private WebElement firstLocationSuggestion;

    Logger logger = LogManager.getLogger(AddressPromptPage.class);

    public void addressSelection(String address){
        logger.info("Kullanıcı adres bilgisini kaydediyor...");
        addressTextBox.click();
        addressTextBox.sendKeys(address);
        ReusableMethods.waitForElementToBeVisibleAndClickElement(firstLocationSuggestion);
        Allure.step("Kullanıcı adres bilgisini kaydedildi.");
    }
}
