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
import utilities.ExcelUtil;
import java.io.IOException;

@Getter
public class SignInPage {
    public SignInPage(){PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver()),this);}

    ExcelUtil excelUtil = new ExcelUtil();

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/email")
    private WebElement mailTextBox;

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/password")
    private WebElement passwordTextBox;

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/login")
    private WebElement signInButton;

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/textinput_error")
    private WebElement errorMessageText;

    Logger logger = LogManager.getLogger(SignInPage.class);
    MyAccountPage myAccountPage = new MyAccountPage();

    public void login(int sheetIndex,String mail,String password) throws IOException {
        if (mail.equals("gecerli") && password.equals("gecerli")){
            logger.info("Kullanıcı geçerli mail ve geçerli şifre bilgileri ile giriş yapmayı deniyor.");
            mailTextBox.sendKeys(excelUtil.readExcelCell(sheetIndex-1, 0,1));
            passwordTextBox.sendKeys(excelUtil.readExcelCell(sheetIndex-1,1,1));
            signInButton.click();
            Allure.step("Kullanıcı geçerli mail ve geçerli şifre bilgileri ile giriş yaptı.");
        }
        else if (mail.equals("gecerli") && password.equals("gecersiz")){
            logger.info("Kullanıcı geçerli mail ve gecersiz sifre bilgileri ile giriş yapmayı deniyor.");
            mailTextBox.sendKeys(excelUtil.readExcelCell(sheetIndex-1, 0,1));
            passwordTextBox.sendKeys(excelUtil.readExcelCell(sheetIndex-1,3,1));
            signInButton.click();
            Allure.step("Kullanıcı geçerli mail ve geçersiz şifre bilgileri ile giriş yapamadı.");
        }
        else if (mail.equals("gecersiz") && password.equals("gecerli")){
            logger.info("Kullanıcı geçersiz mail ve geçerli sifre bilgileri ile giriş yapmayı deniyor.");
            mailTextBox.sendKeys(excelUtil.readExcelCell(sheetIndex-1, 2,1));
            passwordTextBox.sendKeys(excelUtil.readExcelCell(sheetIndex-1,1,1));
            signInButton.click();
            Allure.step("Kullanıcı geçersiz mail ve geçerli şifre bilgileri ile giriş yapamadı.");
        }
        else if (mail.equals("gecersiz") && password.equals("gecersiz")){
            logger.info("Kullanıcı gecersiz mail ve gecersiz sifre bilgileri ile giriş yapmayı deniyor.");
            mailTextBox.sendKeys(excelUtil.readExcelCell(sheetIndex-1, 2,1));
            passwordTextBox.sendKeys(excelUtil.readExcelCell(sheetIndex-1,3,1));
            signInButton.click();
            Allure.step("Kullanıcı geçersiz mail ve geçersiz şifre bilgileri ile giriş yapamadı.");
        }
        else {
            System.out.println("Yanlış seçim yapılmıştır.");
        }
    }

    public void verifyLoginStatus(String status){
        status = status.toLowerCase();
        if (status.equals("basarili")){
            logger.info("Giriş yapma işleminin '"+ status +"' olduğu doğrulanıyor...");
            myAccountPage.getMyOrdersButton().isDisplayed();
            Allure.step("Giriş yapma işleminin '"+ status +"' olduğu doğrulandı.");
        }
        else if (status.equals("basarisiz")) {
            logger.info("Giriş yapma işleminin '"+ status +"' olduğu doğrulanıyor...");
            errorMessageText.isDisplayed();
            Allure.step("Giriş yapma işleminin '"+ status +"' olduğu doğrulandı.");
        }
        else {
            System.out.println("Yanlış statü seçimi.");
        }
    }
}
