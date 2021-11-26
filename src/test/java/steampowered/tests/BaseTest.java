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
    public static MainPage mainPage;
    public static ActionPage actionPage;
    public static GamePage gamePage;
    public static InstallPage installPage;


    @BeforeClass
    public static void testSetup() {
        currentBrowser = Browser.getInstance();
        currentBrowser.navigate(ConfigLoader.getProperty("url"));
    }

    @Test
    @Parameters({"language", "year", "gameName"})
    public void testChooseGameWithMaxDiscount( String language, String year, String gameName) {


        mainPage = new MainPage(currentBrowser.getDriver());
        mainPage.changeLanguage(language);
        mainPage.navigateSection(language + "_section", language + "_subSection");
        actionPage = new ActionPage(currentBrowser.getDriver());
        actionPage.choseGameWithMaxDiscount();
        gamePage = new GamePage(currentBrowser.getDriver());
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
        currentBrowser.teardown();
    }
}
