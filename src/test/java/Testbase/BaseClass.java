package Testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {


	public static WebDriver driver;
	public Logger logger; //logs 
	public Properties pr;
	
	@BeforeClass(groups= {"Sanity","Regression","Master","datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		//loading config.properties file
		FileInputStream file= new FileInputStream("./src//test//resources//config.properties");
		pr=new Properties();
		pr.load(file);
		logger=LogManager.getLogger(this.getClass());//logging log to log file
		
		if(pr.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities =new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			}else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else {
				System.out.println("No matching OS");
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			default :System.out.println("no matching browser"); return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		if(pr.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
		switch(br.toLowerCase())//for parallel and series testing 
		{
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		default : System.out.println("Invalid browser name:");return;
		}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(pr.getProperty("appURL"));//reading url from properties file
		driver.manage().window().maximize();		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master","datadriven"})
	public void tearDown()
	{
		driver.quit();
	}
	//Random Data entry into test case
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	public String randomAlphaNumeric()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		String generatedNumber=RandomStringUtils.randomNumeric(3);
		return (generatedNumber+generatedString);
	}
	
	public String captureScreen(String tname) throws IOException {
		
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
		File sourcefile=takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		sourcefile.renameTo(targetFile);
		return targetFilePath;
		
	}
}
