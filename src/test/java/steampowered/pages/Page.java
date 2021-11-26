package steampowered.pages;

import framework.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Page {

    protected final WebDriver driver;
    protected final By locator;
    protected BaseElement baseElement;

    public Page(WebDriver driver, By locator) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
        baseElement= new BaseElement();
        this.locator = locator;
        Assert.assertTrue(baseElement.isElementPresentedOnPage(locator));
    }

}