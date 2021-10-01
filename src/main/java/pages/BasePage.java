package pages;

import driver.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected TouchAction action;

    public BasePage(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
        action = new TouchAction(DriverManager.getDriver());
    }

    public static boolean waitElement(MobileElement element, long timeToWait) {
        try {
            new WebDriverWait(DriverManager.getDriver(), timeToWait).until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static void clickBackButton(){
        DriverManager.getDriver().navigate().back();
    }
}
