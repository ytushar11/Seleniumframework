package automationProject.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.LandingPage;

public class BaseTest {

	WebDriver driver;
	public LandingPage page;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C://Users//Tushar Yadav//eclipse-workspace//SeleniumTestNgFramework//src//main//java//pageObject//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firfox

		} else if (browserName.equalsIgnoreCase("edge")) {
			// edge

		}

		driver.manage().window().maximize();
		return driver;

	}
	public List<HashMap<String, String>> getJSONData(String filePath) throws IOException {

//		read json to string 
		@SuppressWarnings("deprecation")
		String jsonContenet = FileUtils.readFileToString(new File(filePath));
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data=	mapper.readValue(jsonContenet,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
		

	}
	
	public String getScreenshot(String testname,WebDriver driver) throws IOException  {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File path=new File(System.getProperty("user.dir")+"Screenshot"+testname+"//.png");
		FileUtils.copyFile(source,path );
		return System.getProperty("user.dir")+"Screenshot"+testname+"//.png";
	}

	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		page = new LandingPage(driver);

		page.goTopage();
		return page;
	}

	@AfterMethod
	public void closeTheTab() {
		driver.close();
	}

}
