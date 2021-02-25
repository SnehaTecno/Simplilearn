package Test;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
     WebDriver driver;
    SoftAssert assertobj = new SoftAssert();
   
   ExtentReports extent;
   ExtentTest test;
   
    
@BeforeMethod    
public void setup() {
        
        
       System.setProperty("webdriver.chrome.driver","/home/snehakrishnatec/Downloads/chromedriver");
      
       System.setProperty("webdriver.gecko.driver","/home/snehakrishnatec/Downloads/geckodriver");        extent = new ExtentReports("Extentreport.html",true);
       extent= new ExtentReports("ExtentReports.html",true);
       driver = new ChromeDriver();
       
       driver.get("https://www.simplilearn.com/");
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        
    }

	@Parameters({"uname","password"})
    @Test
    public void testcase1(String Username,String password) {
	test = extent.startTest("Log Test");

        WebElement linkLogin = driver.findElement(By.linkText("Log in"));
       linkLogin.click();
        test.log(LogStatus.PASS ,"clicked on login button");
       
       WebElement editUsername = driver.findElement(By.name("user_login"));
       editUsername.sendKeys("abc@xyz.com");
        test.log(LogStatus.PASS ,"user login");
        
       WebElement editPwd = driver.findElement(By.name("user_pwd"));
      editPwd.sendKeys("124");
       test.log(LogStatus.PASS ,"user password");
       
       WebElement chkBox= driver.findElement(By.className("rememberMe"));
       chkBox.click();
       test.log(LogStatus.PASS ,"remember me");
       
        WebElement btnPwd= driver.findElement(By.name("btn_login"));
        btnPwd.click();  
        test.log(LogStatus.PASS ,"button login");
       
       WebElement error = driver.findElement(By.id("msg_box"));
        String ActError = error.getText();
        
        String ExpError = "The email or password you have entered is invalid";
       //hardassert
      // Assert.assertEquals(ActError, ExpError);
        //softassert
        assertobj.assertEquals(ActError, ExpError);
        System.out.println("After Failiure");
  
       //btnPwd.click();  
           }
    
    @AfterMethod
	public void teardown() {
    driver.quit();
    extent.endTest(test);
    extent.flush();
    extent.close();
     
           // assertobj.assertAll();
   }
}
