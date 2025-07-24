package utilities;

import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.*;

public class Hooks {

    Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("📘 Başlayan senaryo: " + scenario.getName());
        logger.info("Kullanıcı uygulamaya giriyor...");
        Driver.getAppiumDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        logger.info("📕 Senaryo sonucu: " + scenario.getStatus() + " | " + scenario.getName());
        logger.info("Appium driver kapatılıyor...");
        Driver.quitAppiumDriver();
    }
}
