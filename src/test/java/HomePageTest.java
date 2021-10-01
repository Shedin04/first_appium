import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.HomePage;

public class HomePageTest extends BaseTest{
    private final HomePage homePage = new HomePage();

    @Test(priority = 1)
    public void firstTest(){
        softAssert.assertTrue(homePage.checkSearchButton());
        softAssert.assertTrue(homePage.checkLeftMenuButton());
        homePage.clickSearchButton().sendKeysToSearchField("Пряжене");
        homePage.clickOnProduct(1);
        softAssert.assertAll();
        BasePage.clickBackButton();
    }
}