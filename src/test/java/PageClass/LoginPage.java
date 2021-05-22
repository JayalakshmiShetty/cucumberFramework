package PageClass;

import Steps.Hook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.TestUtil;

import java.util.List;

public class LoginPage {
    WebDriverWait wait;
    public static WebDriver driver;
    HomePage homePage;


    public LoginPage() {
        LoginPage.driver = Hook.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Hook.ExplicitWaitTimeOutUnit);
        homePage=new HomePage();
    }
    @FindBy(xpath = "//input[@type='email']")
    WebElement txtboxEmail;

    @FindBy(xpath = "//input[@type='password']")
    WebElement txtboxPassword;

    @FindBy(xpath = "//div[contains(@class,'modal')]//button[text()='Log In']")
    WebElement btnLogin;


    public void enterLoginCredentials(String email, String password) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));
        TestUtil.enterText(txtboxEmail, email);
        TestUtil.enterText(txtboxPassword, password);
    }

    public void ClickOnLoginButton() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'modal')]//button[text()='Log In']")));
        TestUtil.clickElement(btnLogin);

    }
    public void loginTocoinMarketCap(String email, String password) throws InterruptedException {
        List<WebElement> count=driver.findElements(By.xpath("//div[contains(@class,'desktop')]//button[text()='Log In']"));
        if(count.size()==0){
            System.out.println("User is already logged In");
        }
        else{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Search']/..")));
            homePage.clickOnHomepageLogin();
            enterLoginCredentials(email, password);
            ClickOnLoginButton();}
        //driver.navigate().refresh();
    }

}
