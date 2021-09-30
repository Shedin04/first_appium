import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.HomePage;

public class HomePageTest extends BaseTest{
    private final HomePage homePage = new HomePage();

    @Test(priority = 1)
    public void firstTest(){
//        HomePage homePage = new HomePage();
//        homePage.clickSearchButton().sendKeysToSearchField("Пряжене");
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertTrue(true);
//        homePage.clickOnProduct(4);
        homePage.clickOnProduct("Молоко питне пастеризоване 2,5% жиру, 0,500");
        BasePage.clickBackButton();
    }


}
