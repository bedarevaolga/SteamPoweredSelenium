package steampowered.pages;

import framework.Browser;
import framework.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;



public class InstallPage extends Page{

    public InstallPage(WebDriver driver) {
        super(driver,By.xpath("//div[@class='online_stats']"));
    }
    private final By aInstallBtn = By.xpath("//a[@class='about_install_steam_link']");

    public void installGame() {
     baseElement.clickElement(aInstallBtn);
    }


    public boolean isDownloadsExists() {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        String downloadPath = "";
        if (Browser.getInstance().getDriver().toString().contains("Chrome")) {
            downloadPath = System.getProperty("user.dir") + ConfigLoader.getProperty("downloadPathForChrome");
        }
        if (Browser.getInstance().getDriver().toString().contains("Firefox")) {
            downloadPath = System.getProperty("user.dir") + ConfigLoader.getProperty("downloadPathForFirefox");
        }
        File file = new File(downloadPath + ConfigLoader.getProperty("downloadedFile"));
        wait.until(driver -> file.exists());
        return file.exists();
    }

    public void deleteInstalledFile() {

        String downloadPath = "";
        if (Browser.getInstance().getDriver().toString().contains("Chrome")) {
            downloadPath = System.getProperty("user.dir") + ConfigLoader.getProperty("downloadPathForChrome");
        }
        if (Browser.getInstance().getDriver().toString().contains("Firefox")) {
            downloadPath = System.getProperty("user.dir") + ConfigLoader.getProperty("downloadPathForFirefox");
        }
        File file = new File(downloadPath + ConfigLoader.getProperty("downloadedFile"));
        file.delete();
    }
}
