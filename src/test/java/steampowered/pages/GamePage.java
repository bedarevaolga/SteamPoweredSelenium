package steampowered.pages;

import org.openqa.selenium.*;

public class GamePage extends Page {
    public GamePage(WebDriver driver) {
        super(driver, By.xpath("//img[@class='game_header_image_full']"));
    }

    private final By divAgeValidation = By.xpath("//div[text()='Please enter your birth date to continue:']");
    private final By sltAgeYear = By.xpath("//select[@name='ageYear']");
    private final By aViewPage = By.xpath("//span[text()='View Page']/..");
    private final By divGameName = By.xpath("//div[@id='appHubAppName']");
    private final String aButton = "//a[contains(text(),'%s')]";

    public void clickInstallSteam(String installBtn) {
        baseElement.clickAndWait(aButton, installBtn);
    }


    public String getGameName(String year) {
        String gameName;
        try {
            gameName = baseElement.getText(divGameName);
        } catch (NoSuchElementException e) {
            if (baseElement.isElementPresentedOnPage(divAgeValidation)) {
                baseElement.select(sltAgeYear, year);
                baseElement.clickElement(aViewPage);
            }
            gameName = baseElement.getText(divGameName);
        }
        return gameName;
    }


}
