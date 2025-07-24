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
public class ProductFilterPage {

    private static ProductFilterPage instance;

    public ProductFilterPage(){PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver()),this);}

    public static ProductFilterPage getInstance() {
        if (instance == null) {
            instance = new ProductFilterPage();
        }
        return instance;
    }

    @AndroidFindBy(xpath = "(//*[@resource-id='com.ciceksepeti.ciceksepeti:id/name'])")
    private List<WebElement> filterTitles;

    @AndroidFindBy(xpath = "//*[@class='android.widget.Button']")
    private WebElement uygulaButton;

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/search_src_text")
    private WebElement filterSearchButton;

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/search_close_btn")
    private WebElement clearFiltersButton;

    @AndroidFindBy(xpath = "//*[@text='Altın Kolye']")
    private WebElement altinKolyeButton;

    @AndroidFindBy(xpath = "//*[@text='Kolye Ucu']")
    private WebElement kolyeUcuButton;

    @AndroidFindBy(xpath = "//*[@text='Doğum Günü']")
    private WebElement dogumGunuButton;

    @AndroidFindBy(xpath = "//*[@text='Sevgiliye']")
    private WebElement sevgiliyeButton;

    @AndroidFindBy(xpath = "//*[@text='Mavi']")
    private WebElement colorBlueButton;

    @AndroidFindBy(xpath = "//*[@text='Kırmızı']")
    private WebElement colorRedButton;

    @AndroidFindBy(xpath = "//*[@text='Beyaz']")
    private WebElement colorWhiteButton;

    @AndroidFindBy(xpath = "//*[@text='Engin Gold']")
    private WebElement enginGoldBrandButton;

    @AndroidFindBy(xpath = "//*[@text='Glorria']")
    private WebElement glorriaBrandButton;

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/txtMinPrice")
    private WebElement minPriceTextBox;

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/txtMaxPrice")
    private WebElement maxPriceTextBox;

    @AndroidFindBy(xpath = "(//*[@class='android.widget.Switch'])[2]")
    private WebElement ucretsizKargoSwitchButton;

    @AndroidFindBy(id = "com.ciceksepeti.ciceksepeti:id/filterApply")
    private WebElement sonuclariGosterButton;

    @Getter
    @Setter
    private  int actualFilterCount;
    @Getter
    @Setter
    private  int expectedFilterCount;
    @Getter
    @Setter
    private  int actualMinPrice;
    @Setter
    @Getter
    private  int actualMaxPrice;

    Logger logger = LogManager.getLogger(ProductFilterPage.class);

    public void filterTitleSelection(String filterTitle,List<String> filters){
        filterTitle = filterTitle.toLowerCase();
        actualFilterCount = filters.size();

        switch (filterTitle){
            case "kategori":
                ReusableMethods.waitForElementToBeVisibleAndClickElement(filterTitles.get(0));
                logger.info("'Kategori' filtresi açılıyor ve kullanıcı seçimi yapıyor...");
                for (String filter : filters){
                    switch (filter.toLowerCase()){
                        case "kolye ucu":
                            kolyeUcuButton.click();
                            break;

                        case "altın kolye":
                            ReusableMethods.wait(1);
                            ReusableMethods.swipeWithW3CActions(230,650,230,200,200);
                            altinKolyeButton.click();
                            break;
                    }
                }
                Allure.step("'Kategori' filtresinden kullanıcı seçim yaptı.");
                break;

            case "ne için":
                ReusableMethods.waitForElementToBeVisibleAndClickElement(filterTitles.get(1));
                logger.info("'Ne İçin' filtresi açılıyor ve kullanıcı seçimi yapıyor...");
                for (String filter : filters){
                    switch (filter.toLowerCase()){
                        case "doğum günü":
                            dogumGunuButton.click();
                            break;
                    }
                }
                Allure.step("'Ne İçin' filtresinden kullanıcı seçim yaptı.");
                break;

            case "kime":
                ReusableMethods.waitForElementToBeVisibleAndClickElement(filterTitles.get(2));
                logger.info("'Kime' filtresi açılıyor ve kullanıcı seçimi yapıyor...");
                for (String filter : filters){
                    switch (filter.toLowerCase()){
                        case "sevgiliye":
                            sevgiliyeButton.click();
                            break;
                    }
                }
                Allure.step("'Kime' filtresinden kullanıcı seçim yaptı.");
                break;

            case "renk":
                ReusableMethods.waitForElementToBeVisibleAndClickElement(filterTitles.get(3));
                logger.info("'Renk' filtresi açılıyor ve kullanıcı seçimi yapıyor...");
                for (String filter : filters){
                    switch (filter.toLowerCase()){
                        case "mavi":
                            colorBlueButton.click();
                            break;
                        case "kırmızı":
                            colorRedButton.click();
                            break;
                        case "beyaz":
                            colorWhiteButton.click();
                            break;
                    }
                }
                Allure.step("'Renk' filtresinden kullanıcı seçim yaptı.");
                break;

            case "marka":
                ReusableMethods.waitForElementToBeVisibleAndClickElement(filterTitles.get(4));
                logger.info("'Marka' filtresi açılıyor ve kullanıcı seçimi yapıyor...");
                for (String filter : filters){
                    filterSearchButton.sendKeys(filter);
                    switch (filter.toLowerCase()){
                        case "engin gold":
                            ReusableMethods.waitForElementToBeVisibleAndClickElement(enginGoldBrandButton);
                            clearFiltersButton.click();
                            break;

                        case "glorria":
                            ReusableMethods.waitForElementToBeVisibleAndClickElement(glorriaBrandButton);
                            clearFiltersButton.click();
                            break;
                    }
                }
                Allure.step("'Marka' filtresinden kullanıcı seçim yaptı.");
                break;

            case "fiyat":
                logger.info("'Fiyat' filtresi açılıyor ve kullanıcı fiyat aralığı belirliyor...");
                ReusableMethods.waitForElementToBeVisibleAndClickElement(filterTitles.get(5));
                minPriceTextBox.sendKeys(filters.get(0));
                setActualMinPrice(Integer.parseInt(filters.get(0)));

                maxPriceTextBox.sendKeys(filters.get(1));
                setActualMaxPrice(Integer.parseInt(filters.get(1)));
                Allure.step("'Fiyat' filtresinden kullanıcı fiyat aralığı belirledi.");
                break;
        }
        ReusableMethods.waitForElementToBeVisibleAndClickElement(uygulaButton);
    }

    public void verifySelectedCount(String filterTitle){
        filterTitle = filterTitle.toLowerCase();

        switch (filterTitle){
            case "kategori":
                ReusableMethods.waitForElementToBeVisible(filterTitles.get(0));
                setExpectedFilterCount(Integer.parseInt(filterTitles.get(0).getText().replaceAll("\\D","")));
                break;
            case "ne için":
                ReusableMethods.waitForElementToBeVisible(filterTitles.get(1));
                setExpectedFilterCount(Integer.parseInt(filterTitles.get(1).getText().replaceAll("\\D","")));
                break;
            case "kime":
                ReusableMethods.waitForElementToBeVisible(filterTitles.get(2));
                setExpectedFilterCount(Integer.parseInt(filterTitles.get(2).getText().replaceAll("\\D","")));
                break;
            case "renk":
                ReusableMethods.waitForElementToBeVisible(filterTitles.get(3));
                setExpectedFilterCount(Integer.parseInt(filterTitles.get(3).getText().replaceAll("\\D","")));
                break;
            case "marka":
                ReusableMethods.waitForElementToBeVisible(filterTitles.get(4));
                setExpectedFilterCount(Integer.parseInt(filterTitles.get(4).getText().replaceAll("\\D","")));
                break;
        }
        logger.info("'" + filterTitle + "' filtresi için beklenen seçim sayısı: " + expectedFilterCount + ", görünen seçim sayısı: " + actualFilterCount);
        Assert.assertEquals("Filtreleme sonrası '"+filterTitle+"' başlığının yanındaki filtre sayısı beklenenden farklı.",actualFilterCount,expectedFilterCount);
        Allure.step("'" + filterTitle + "' filtresi için seçim sayısı doğrulandı.");
    }

    public void clickSonuclariGosterButton(){
        logger.info("Filtreleme sayfasında 'Sonuçları Göster' butonuna tıklanıyor...");
        sonuclariGosterButton.click();
        Allure.step("Filtreleme sayfasında 'Sonuçları Göster' butonuna tıklandı.");
    }

}
