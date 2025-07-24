package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import pages.ProductFilterPage;
import static utilities.Driver.quitAppiumDriver;

public class Hooks {

    Logger logger = LogManager.getLogger(ProductFilterPage.class);
    @After
    public void driver_turns_off(){
        quitAppiumDriver();
        logger.info("Driver Kapatıldı.");
    }
}
