package various_Concepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest_TestNG {

	WebDriver driver;
	String browser = null;
	String url = null;
	
	
	@BeforeClass
	public void readConfig() {
		
		Properties prop = new Properties();
		
		// InputStream  // BufferedReader  // FileReader  // Scanner
		
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser"); 
			System.out.println("Used Browser : " + browser);
			url = prop.getProperty("url");
			System.out.println("Used Environment : " + url);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@BeforeMethod
	public void init() {
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if(browser.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			driver = new FirefoxDriver();	
		}

		
//		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test(priority=1) 
	public void loginTest() {

		String expectedTitle = "Login - iBilling";
		String actualTitile = driver.getTitle();
		Assert.assertEquals(actualTitile, expectedTitle, "This is not the right Page!!");

		// Element Library
		// String using WebElement class
		WebElement INSERT_USERNAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement INSERT_PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[text()='Sign in']"));

		INSERT_USERNAME_ELEMENT.clear();
		INSERT_USERNAME_ELEMENT.sendKeys("demo@techfios.com");
		INSERT_PASSWORD_ELEMENT.sendKeys("abc123");
		SIGNIN_BUTTON_ELEMENT.click();

		String expectedDashboardTitle = "Dashboard- iBilling";
		String actualDashboardTitle = driver.getTitle();
		Assert.assertEquals(actualDashboardTitle, expectedDashboardTitle, "Wrong Page!!");

	}

	 @Test(priority=2)
	public void dashboardPage() {

		String expectedTitle = "Login - iBilling";
		String actualTitile = driver.getTitle();
		Assert.assertEquals(actualTitile, expectedTitle, "Page not found!!");

		// Login Data
		String loginId = "demo@techfios.com";
		String password = "abc123";

		WebElement INSERT_USERNAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement INSERT_PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[text()='Sign in']"));

		INSERT_USERNAME_ELEMENT.clear();
		INSERT_USERNAME_ELEMENT.sendKeys(loginId);
		INSERT_PASSWORD_ELEMENT.sendKeys(password);
		SIGNIN_BUTTON_ELEMENT.click();
		
		
		WebElement DASHBOARD_ELEMENT = driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]"));
		waitForElement(driver, 5, DASHBOARD_ELEMENT);
		DASHBOARD_ELEMENT.click();

		
	
		String expectedDashboardTitle = "Dashboard- iBilling";
		String actualDashboardTitle = driver.getTitle();
		Assert.assertEquals(actualDashboardTitle, expectedDashboardTitle, "Wrong Page!!");

		// Add Customer
		WebElement CUSTOMERS_ELEMENT = driver.findElement(By.xpath("//span[text()='Customers']"));
		WebElement ADDCUSTOMER_ELEMENT = driver.findElement(By.xpath("//a[text()='Add Customer']"));
		
		
		CUSTOMERS_ELEMENT.click();
		ADDCUSTOMER_ELEMENT.click();

		String expectedcontactsTitle = "Contacts - iBilling";
		String actualcontactsTitle = driver.getTitle();
		Assert.assertEquals(actualcontactsTitle, expectedcontactsTitle, "Ahha Get the Wrong Page!!");

	}

   	@Test(priority = 3)
	public void addCustomer() throws InterruptedException {

		//Login Page Assertion
		String expectedTitle = "Login - iBilling";
		String actualTitile = driver.getTitle();
		Assert.assertEquals(actualTitile, expectedTitle, "Page not found!!");

		// Login Data
		String loginId = "demo@techfios.com";
		String password = "abc123";

		// Test Data
		String fullName = "zyx";
		String companyName = "Google";
		String email = "newuser@gmail.com";
		String phone = "54789";
		String address = "123NEWUSERHOME";
		String city = "DreamCity";
		String state = "New York";
		String postalCode = "11432";
		String country = "United States";
		String currency  = "USD";
		String group = "June2020";

		
		//Login Element
		WebElement INSERT_USERNAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement INSERT_PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[text()='Sign in']"));

		INSERT_USERNAME_ELEMENT.clear();
		INSERT_USERNAME_ELEMENT.sendKeys(loginId);
		INSERT_PASSWORD_ELEMENT.sendKeys(password);
		SIGNIN_BUTTON_ELEMENT.click();
		
		
		WebElement DASHBOARD_ELEMENT = driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]"));
		waitForElement(driver, 5, DASHBOARD_ELEMENT);
		DASHBOARD_ELEMENT.click();

		//Dashboard Page Assertion
		String expectedDashboardTitle = "Dashboard- iBilling";
		String actualDashboardTitle = driver.getTitle();
		Assert.assertEquals(actualDashboardTitle, expectedDashboardTitle, "Wrong Page!!");
		
	
		//Customer Element
		WebElement CUSTOMERS_ELEMENT = driver.findElement(By.xpath("//span[text()='Customers']"));
		WebElement ADDCUSTOMER_ELEMENT = driver.findElement(By.xpath("//a[text()='Add Customer']"));
		
	
		CUSTOMERS_ELEMENT.click();
		waitForElement(driver, 10, ADDCUSTOMER_ELEMENT );
		ADDCUSTOMER_ELEMENT.click();
		
		
		//AddCustomer Page Assertion
		String expectedcontactsTitle = "Contacts - iBilling";
		String actualcontactsTitle = driver.getTitle();
		Assert.assertEquals(actualcontactsTitle, expectedcontactsTitle, "Ahha Get the Wrong Page!!");

	//	randomgenerateNumber(99999);
		
		// Generate Random Number
//		Random rnd = new Random();
//		int randomNumber = rnd.nextInt(99999);
//		
		//Add Customers Element
		WebElement FULLNAME_FIELDELEMENT = driver.findElement(By.xpath("//input[@id='account']"));
		WebElement COMPANY_FIELD_ELEMENT = driver.findElement(By.xpath("//select[@id='cid']"));
		WebElement EMAIL_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement PHONE_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='phone']"));
		WebElement ADDRESS_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='address']"));
		WebElement CITY_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='city']"));
		WebElement STATE_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='state']"));
		WebElement POSTAL_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='zip']"));
		WebElement COUNTRY_FIELD_ELEMENT = driver.findElement(By.xpath("//SELECT[@id='country']"));
		WebElement CURRENCY_FIELD_ELEMENT = driver.findElement(By.xpath("//select[@id='currency']"));
		WebElement GROUP_FIELD_ELEMENT = driver.findElement(By.xpath("//select[@id='group']"));
		WebElement SUBBIT__BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@id='submit']"));
		
		
		waitForElement(driver, 5, FULLNAME_FIELDELEMENT );
		int generatedNumber = randomgenerateNumber(99999);
		FULLNAME_FIELDELEMENT.sendKeys(fullName + generatedNumber);
		
		selectDropDown(COMPANY_FIELD_ELEMENT, companyName);
		
		EMAIL_FIELD_ELEMENT.sendKeys(generatedNumber+ email);
		PHONE_FIELD_ELEMENT.sendKeys(generatedNumber + phone);
		ADDRESS_FIELD_ELEMENT.sendKeys(address);
		CITY_FIELD_ELEMENT.sendKeys(city);
		STATE_FIELD_ELEMENT.sendKeys(state);
		POSTAL_FIELD_ELEMENT.sendKeys(postalCode);
		
		selectDropDown(COUNTRY_FIELD_ELEMENT , country);
		selectDropDown(CURRENCY_FIELD_ELEMENT, currency);
		selectDropDown(GROUP_FIELD_ELEMENT, group);
		
		SUBBIT__BUTTON_ELEMENT.click();
		
		String expectedContactTitle  = "Contacts - iBilling";
		String actualTitle = driver.getTitle();
		
	Assert.assertEquals(actualTitle, expectedContactTitle);
	

	}
	
	//DropDown 
	public void selectDropDown(WebElement Element, String visibleText) {
		Select sel = new Select(Element);
		sel.selectByVisibleText(visibleText);
		
	}
	
	
	// Generate Random Number
	public int randomgenerateNumber(int bound) {
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(bound);
		return randomNumber;
		
	}

	//Explicity wait
	public void waitForElement(WebDriver driver, int timeInSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	

	// @AfterMethod
	public void tearDown()  {
		try {
			Thread.sleep(1000);
			driver.close();
		} catch (InterruptedException e) {
			driver.quit();
			e.printStackTrace();
		}
		
		
		
	}

}
