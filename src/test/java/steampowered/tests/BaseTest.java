package steampowered.tests;

import framework.Browser;
import framework.ConfigLoader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steampowered.pages.*;


public class BaseTest {

    private static Browser currentBrowser;
    private static InstallPage installPage ;


    @BeforeClass
    public static void testSetup() {
        currentBrowser = Browser.getInstance();
        currentBrowser.navigate(ConfigLoader.getProperty("url"));
    }

    @Test
    @Parameters({"language", "year", "gameName"})
    public void testChooseGameWithMaxDiscount( String language, String year, String gameName) {


        MainPage mainPage = new MainPage(currentBrowser.getDriver());
        mainPage.changeLanguage(language);
        mainPage.navigateSection(language + "_section", language + "_subSection");
        ActionPage actionPage = new ActionPage(currentBrowser.getDriver());
        actionPage.choseGameWithMaxDiscount();
        GamePage gamePage = new GamePage(currentBrowser.getDriver());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(gamePage.getGameName(year), gameName);
        gamePage.clickInstallSteam("Install Steam");
        installPage = new InstallPage(currentBrowser.getDriver());
        installPage.installGame();
        softAssert.assertTrue(installPage.isDownloadsExists());
    }

    @AfterClass
    public void closeBrowser() {
       installPage.deleteInstalledFile();
        Browser.teardown();
    }
}
