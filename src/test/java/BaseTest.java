import driver.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void createSession(){
        DriverManager.getDriver();
    }

    @AfterMethod
    public void resetApp(){
        try {
            Thread.sleep(5000);
        }catch (Exception ignored){}
        DriverManager.getDriver().resetApp();
    }

    @AfterClass
    public void closeSession(){
        DriverManager.closeDriver();
        DriverManager.closeAppium();
        DriverManager.closeEmulator();
    }
}
