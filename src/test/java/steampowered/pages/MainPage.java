package steampowered.pages;

import framework.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver, By.xpath("//div[@class='home_page_body_ctn   ']"));
    }

    private final By spnLanguage = By.xpath("//span[@class='pulldown global_action_link']");
    private final By aLanguages = By.xpath("//a[@class=\"popup_menu_item tight\"]");
    private final String aSection = "//a[@class= 'pulldown_desktop' and text()='%s']";
    private final String aSubSection = "//div[contains(@class, 'popup_menu_subheader popup_genre_expand_header')]/child::a[contains(text(), '%s')]";


    public void changeLanguage(String language) {
        baseElement.clickElement(spnLanguage);
        List<WebElement> languages = baseElement.findElements(aLanguages);
        for (WebElement element : languages) {
            if (baseElement.getText(element).contains(language)) {
                baseElement.clickElement(element);
                return;
            }
        }
       baseElement.sendKeysEsc();
    }

    public void navigateSection(String section, String subsection) {
        Actions action = new Actions(driver);
        action.moveToElement(baseElement.findElementByName(aSection, ConfigLoader.getProperty(section))).build().perform();
        baseElement.clickElement(baseElement.findElementByName(aSubSection, ConfigLoader.getProperty(subsection)));
    }
}


