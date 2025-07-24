package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import pages.*;
import utilities.Driver;
import utilities.ReusableMethods;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class E2EStepDef {

    HomePage homePage = new HomePage();
    CategoryMenuPage categoryMenuPage = new CategoryMenuPage();
    MyAccountPage myAccountPage = new MyAccountPage();
    SignInPage signInPage = new SignInPage();
    AddressPromptPage addressPromptPage = new AddressPromptPage();
    CategoryPage categoryPage = CategoryPage.getInstance();
    ProductFilterPage productFilterPage = ProductFilterPage.getInstance();
    ProductPage productPage = new ProductPage();
    MyCartPage myCartPage = new MyCartPage();

    Logger logger = LogManager.getLogger(E2EStepDef.class);

    @Given("kullanici uygulamaya girer")
    public void kullanici_uygulamaya_girer() {
        logger.info("Kullanıcı uygulamaya giriyor...");
        Driver.getAppiumDriver();
        Allure.step("Kullanıcı uygulamaya giriş yaptı..");
    }
    @Given("{string} islemini secer")
    public void islemini_secer(String operation) {
        homePage.selectHomePageCategory(operation);
    }

    @When("alt menuden {string} iconuna tiklar")
    public void alt_menuden_iconuna_tiklar(String item) {
        homePage.selectBottomNavigationItem(item);
    }

    @And("{string} butonuna tiklar")
    public void butonunaTiklar(String button) {
        myAccountPage.clickButtonByName(button);
    }

    @When("test datadaki {int}.sheetteki {string} mail ve {string} sifre ile hesaba giris yapilir")
    public void testDatadakiSheettekiMailVeSifreIleHesabaGirisYapilir(int sheet, String mail, String password) throws IOException {
        signInPage.login(sheet,mail,password);
        ReusableMethods.wait(2);
    }

    @Then("kullanici giris isleminin {string} oldugunu dogrular")
    public void kullaniciGirisIslemininOldugunuDogrular(String status) {
        signInPage.verifyLoginStatus(status);
    }

    @When("sayfadaki {string} sekmesinden, {string} sayfasina gider")
    public void sayfadaki_sekmesinden_sayfasina_gider(String mainCategory, String childCategory) {
        categoryMenuPage.selectCategory(mainCategory,childCategory);
    }

    @When("acilan Siparis Nereye Gonderilecek alanindaki adres kismi {string} olarak doldurulur")
    public void acilanSiparisNereyeGonderilecekAlanindakiAdresKismiOlarakDoldurulur(String address) {
        addressPromptPage.addressSelection(address);
    }

    @And("kullanici sayfanin ustunde bulunan {string} butonuna tiklar")
    public void kullaniciSayfaninUstundeBulunanButonunaTiklar(String buton) {
        categoryPage.clickFilterButton();
    }

    @When("filtreleme sayfasindaki {string} butonuna tiklar ve secim yapar")
    public void filtrelemeSayfasindakiButonunaTiklarVeSecimYapar(String filterTitle, DataTable dataTable) {
        List<String> filters = dataTable.asList();
        productFilterPage.filterTitleSelection(filterTitle,filters);
    }

    @Then("filtreme sayfasindaki {string} yazisinin yanindaki secim sayisi dogrulanir")
    public void filtremeSayfasindakiYazisininYanindakiSecimSayisiDogrulanir(String filterTitle) {
        productFilterPage.verifySelectedCount(filterTitle);
    }

    @And("filtreleme sayfasindaki {string} butonuna tiklanir")
    public void filtrelemeSayfasindakiButonunaTiklanir(String button) {
        productFilterPage.clickSonuclariGosterButton();
    }

    @Then("filtreleme sonrasi urunlerin listelendigi dogrulanir")
    public void filtrelemeSonrasiUrunlerinListelendigiDogrulanir() {
        categoryPage.checkProductImagesVisibility();
    }

    @When("sayfadaki {string} fonksiyonu ile urunler {string} olarak siralanir ve fiyatlarin siralanmasi dogrulanir")
    public void sayfadakiFonksiyonuIleUrunlerOlarakSiralanirVeFiyatlarinSiralanmasiDogrulanir(String button, String sortType) {
        categoryPage.sortProductsBy(sortType);
    }

    @Then("filtreleme sonrasi urun fiyatlarinin secilen aralikta oldugu dogrulanir")
    public void filtrelemeSonrasiUrunFiyatlarininSecilenAraliktaOlduguDogrulanir() {
        categoryPage.checkPricesInRange();
    }

    @When("{int}. urunun detay sayfasina gidilir ve urun bilgileri goruntulenir")
    public void urununDetaySayfasinaGidilirVeUrunBilgileriGoruntulenir(int productOrder){
        categoryPage.goToFirstProductDetailPage(productOrder);
    }

    @Then("urun detay sayfasindaki baslik ve fiyat bilgisi dogrulanir")
    public void urunDetaySayfasindakiBaslikVeFiyatBilgisiDogrulanir() {
        productPage.verifyProductNameAndPrice();
    }

    @When("urun sepete eklenir")
    public void urunSepeteEklenir() {
        productPage.addProductToCart();
    }

    @Then("sepete basariyla gecilir ve urun bilgileri dogrulanir")
    public void sepeteBasariylaGecilirVeUrunBilgileriDogrulanir() {
        myCartPage.verifyCartPageTitle();
        myCartPage.verifyProductNameAndPrice();
    }
}
