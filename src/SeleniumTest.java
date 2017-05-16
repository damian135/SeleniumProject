

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  //System.setProperty("webdriver.gecko.driver", "C:/Program Files/Geco/geckodriver.exe");
	  //System.setProperty("webdriver.gecko.driver", "C:/Selenium(x86)/Mozilla Firefox\firefox.exe");
	  //WebDriver driver = new FirefoxDriver();
	  System.setProperty("webdriver.chrome.driver", "C:/Program Files/ChromeDriver//chromedriver.exe");
	  //System.setProperty("webdriver.chrome.driver","C:/Program Files/Selenium/chromedriver.exe");
	  driver = new ChromeDriver();
      //InternetExplorerDriver driver = new InternetExplorerDriver();
	  baseUrl = "http://www.wp.pl/";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
  }
  
  @Test
  public void testSelenium() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Poczta")).click();
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("dwittchn");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("Samo");
    driver.findElement(By.id("btnSubmit")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
