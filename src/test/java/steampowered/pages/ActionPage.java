package steampowered.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ActionPage extends Page {

    public ActionPage(WebDriver driver) {
        super(driver, By.xpath("//h2[contains(text(), 'Action')]"));
    }

    private final By divDiscount = By.xpath("//div[@class='capsule header']/following-sibling::div/descendant::div[@class=\"discount_pct\"]");
    private static final String DIV_MAX_DISCOUNT = "//div[@class='capsule header']/following-sibling::div/descendant::div[@class=\"discount_pct\" and text()='%s']";

    public int findMaxDiscounts() {
        List<WebElement> discounts = baseElement.findElements(divDiscount);

        int maxDiscount = parseDiscounts(discounts.get(0).getText());

        for (WebElement element : discounts) {
            if (parseDiscounts(baseElement.getText(element)) > maxDiscount) {
                maxDiscount = parseDiscounts(baseElement.getText(element));
            }
        }
        return maxDiscount;
    }

    public void choseGameWithMaxDiscount() {

        List<WebElement> maxDiscountsList = baseElement.findElementsByName(DIV_MAX_DISCOUNT, "-" + findMaxDiscounts() + "%");
        if(maxDiscountsList.size() == 1){
            String nameDisc = "-" + findMaxDiscounts() + "%";
          baseElement.findElementByName(DIV_MAX_DISCOUNT, nameDisc).click();
        } else {
            int random = (int) (Math.random() * (maxDiscountsList.size()));
            maxDiscountsList.get(random).click();
        }
    }

    public int parseDiscounts(String text) {
        return Integer.parseInt(text.substring(1, text.indexOf("%")));
    }
}
