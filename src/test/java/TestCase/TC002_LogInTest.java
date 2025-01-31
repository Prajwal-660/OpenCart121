package TestCase;


import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LogInPage;
import PageObjects.MyAccountPage;
import Testbase.BaseClass;

public class TC002_LogInTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void LogInTest() throws FileNotFoundException 
	{
		
		try
		{
		logger.info("Starting TC002");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogIn();
		
		LogInPage lp=new LogInPage(driver);
		lp.setemailid(pr.getProperty("email"));
		
		lp.setpwd(pr.getProperty("password"));
		lp.ClickLogInBtn();
		
		MyAccountPage MAP=new MyAccountPage(driver);
		boolean tragetpage=MAP.isMyAccountPageExists();
		Assert.assertTrue(tragetpage);
		logger.info("*******test case TC002 passed**********");
		
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		
	}
}
