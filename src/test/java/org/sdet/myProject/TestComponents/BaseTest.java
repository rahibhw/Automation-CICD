package org.sdet.myProject.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.sdet.myProject.pageObject.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\sdet\\myProject\\resources\\GlobalData.properties");
		prop.load(fis);
		// if Maven cmd passing browser name then we need to accomodate below code to choose browser
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//	prop.getProperty("browser");
		
		if (browserName.contains("chrome"))
		{
			ChromeOptions option = new ChromeOptions();
			//WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
			option.addArguments("headless");}
			
		 driver = new ChromeDriver(option);
		 driver.manage().window().setSize(new Dimension(1440,900)); //full screen mode 

		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent =FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	
	    //convert string to HashMap ( use Jackson Databind )
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String ,String>>>(){});
	    return data;
	}
    public String getScreenshot(String testCaseName ,WebDriver driver) throws IOException
    {
    TakesScreenshot ts = (TakesScreenshot)driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
    FileUtils.copyFile(source, file);
    return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }
    
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
    public void tearDown()
    {
    	driver.close();
    }
}