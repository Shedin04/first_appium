package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

import java.util.List;

public class HomePage extends BasePage{

    public static final int WAIT_EL = 15;
    @AndroidFindBy(id = "com.ithinkers.agromol:id/left_big_handle")
    private MobileElement leftBigHandle;

    @AndroidFindBy(id = "com.ithinkers.agromol:id/search")
    private MobileElement searchButton;

    @AndroidFindBy(id = "search_view")
    private MobileElement searchField;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout//android.widget.TextView[@resource-id='com.ithinkers.agromol:id/productName']")
    private List<MobileElement> goods;

    public boolean checkLeftMenuButton(){
        return waitElement(leftBigHandle, WAIT_EL);
    }

    public HomePage clickLeftMenuButton(){
        waitElement(leftBigHandle, WAIT_EL);
        leftBigHandle.click();
        return this;
    }

    public HomePage clickSearchButton(){
        waitElement(searchButton, WAIT_EL);
        searchButton.click();
        return this;
    }

    public boolean checkSearchButton(){
        return waitElement(searchButton, WAIT_EL);
    }

    public HomePage sendKeysToSearchField(String text){
        searchField.sendKeys(text);
        return this;
    }

    public HomePage clickOnProduct(int num){
        waitElement(leftBigHandle, WAIT_EL);
        try {
            action.tap(TapOptions.tapOptions().withElement(ElementOption.element(goods.get(num)))).perform();
        }catch (Exception e){
        }
        return this;
    }

    public HomePage clickOnProduct(String productName){
        waitElement(leftBigHandle, WAIT_EL);
        try{
            action.tap(TapOptions.tapOptions().withElement(ElementOption.element(goods.stream().filter(prod -> prod.getText().equals(productName)).findFirst().get()))).perform();
        }catch (Exception e){
        }
        return this;
    }
}
