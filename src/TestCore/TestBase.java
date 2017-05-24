package TestCore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.MG.util.DbManager;
import com.MG.util.ExcelReader;
import com.MG.util.monitoringMail;

public class TestBase {
	
	/*
	 * 
	 * Properties
	 * Logs
	 * Mails
	 * Database
	 * Excel
	 * WebDriver
	 * 
	 */
	
	public static WebDriver driver;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\com\\w2a\\properties\\testdata.xlsx");
	public static Properties OR;
	public static Properties Config;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static monitoringMail mail = new monitoringMail();
	
	
	@BeforeSuite
	public void setUp() throws IOException, ClassNotFoundException, SQLException{
		
		
		if(driver==null){
			
			
			OR = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\w2a\\properties\\OR.properties");
			OR.load(fis);
			log.debug("OR Properties loaded");
		
			Config = new Properties();
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\w2a\\properties\\Config.properties");
			Config.load(fis);
			log.debug("Config properties loaded");
			
			if(Config.getProperty("browser").equals("firefox")){
				
				driver = new FirefoxDriver();
				
			}else if(Config.getProperty("browser").equals("ie")){
				
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
			}else if(Config.getProperty("browser").equals("chrome")){
				
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
				
			}
			
			driver.get(Config.getProperty("testsiteurl"));
			DbManager.setMysqlDbConnection();
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			
			
			
		}
		
		
	}
	
	@AfterSuite
	public void tearDown(){
		
		driver.quit();
		
	}

}
