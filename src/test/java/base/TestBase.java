package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProjectPage;
import utilities.ExcelReader;
import utilities.TestUtils;


public class TestBase {

	public static WebDriver driver=null;
	DesiredCapabilities cap = null;
	public static Properties config=new Properties();
	//public static Properties OR=new Properties();
	public static FileInputStream fis;
	//devpinoyLogger is the standard name
	public static Logger log=Logger.getLogger("devpinoyLogger");
	//Read the excel file
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	//And the explicit wait to handle alerts
	public static WebDriverWait wait;
	public static String browser;
	//get the instance of the Extent reports class
    //public ExtentReports report=ExtentManager.getInstance();
	//defines the logs within the extent test cases
    //public static ExtentTest test;
    
	/* setUp() where we initialize everything. As we need to execute this 
	 method before we run the entire test suite containing multiple classes, we use the @BeforeSuite Annotation.*/
  
	@BeforeSuite
	public void setUp() throws IOException{
		if(driver==null){
			
			//load the properties files
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			config.load(fis);
			log.debug("Config file loaded");
			
		    //Code to get the browser details from jenkins
			/*
		    if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
		    	browser=System.getenv("browser");
		    }
		    else{
		    	browser=config.getProperty("browser");
		    }
		    
		    config.setProperty("browser", browser);*/
			//initialize the driver
		    if(config.getProperty("browser").equals("chrome")){
			   System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			   driver=new ChromeDriver();
			   /*
		    	cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox"); 
				//Platform represents Operating System.
				cap.setPlatform(Platform.ANY); */
				
			   log.debug("Chrome Launched");
		    }else if(config.getProperty("browser").equals("firefox")){
			   System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
			   driver=new FirefoxDriver();
			   log.debug("Firefox Launched");
		    }else if(config.getProperty("browser").equals("ie")){
			   System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
			   driver=new InternetExplorerDriver();
			   log.debug("IE Launched");
		    }
		    
		    //driver = new RemoteWebDriver(new URL("http://192.168.99.101:4446/wd/hub"),cap);
		    driver.get(config.getProperty("testsiteURL"));
		    log.debug("BA360 site launched");
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
	      //  wait=new WebDriverWait(driver,5);
		}
	}
	/*Method to validate whether the element is present */
	public boolean isElementPresent(By by){
		try{
		driver.findElement(by);
		return true;
		}
		catch(NoSuchElementException e){
		return false;
		}
	
	}
	//tearDown() where we quit everything
	@AfterSuite
	public void tearDown(){
		if(driver!=null){
			driver.quit();
		}
		log.debug("The testing is completed");
	}
	
	@Test(dataProviderClass=TestUtils.class,dataProvider="dp")
	public void login(String username, String password, String domain,String projectName, String projectType, String engagementModel, String planStrtDt, String actualStrtDt,String releaseDt,String elsDt,String desc,String notes,String factor,String projectComplexity,String codeDrop, String planEndDt,String actualEndDt,String goLiveDt,String scrumMaster) throws Throwable{
		 HomePage home=PageFactory.initElements(driver, HomePage.class); 
		 home.login(username, password, domain);
		 ProjectPage page=home.project();
		 page.newProjectCreate(projectName,projectType,engagementModel,planStrtDt,actualStrtDt,releaseDt,elsDt,desc,notes,factor,projectComplexity,codeDrop,planEndDt,actualEndDt,goLiveDt,scrumMaster);
		 
	}
	
	

}
