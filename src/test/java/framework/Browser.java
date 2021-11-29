package framework;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public final class Browser {

    private static Browser instance;
    private static WebDriver driver;
    private static final int IMPLICITLY_WAIT = Integer.parseInt(ConfigLoader.getProperty("implicitlyWait"));


    public static Browser getInstance() {
        if (instance == null) {
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.setUp(ConfigLoader.getProperty("browser"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigLoader.getProperty("implicitlyWait")), TimeUnit.SECONDS);
            instance = new Browser();
        }
        return instance;
    }

    public void navigate(String url) {
        driver.get(url);
    }


    private Browser() {
    }
    public static int getImplicitlyWait() {
        return IMPLICITLY_WAIT;
    }


    public WebDriver getDriver() {
        return driver;
    }

    public static void teardown() {
        try {
            instance = null;
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
