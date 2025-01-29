package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LogInPage;
import PageObjects.MyAccountPage;
import Testbase.BaseClass;
import Utilities.DataProviders;

public class TC003LogInTestDDT extends BaseClass {

	@Test(dataProvider="LogInData", dataProviderClass=DataProviders.class,groups="datadriven")
	public void Verify_logInDDT(String email,String pwd,String exp) 
	{
		
		
		logger.info("*******Starting TC003***********");
		try 
		{
			
		//homePgae
		HomePage hp=new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogIn();
		
		//LogInpage
		LogInPage lp=new LogInPage(driver);
		lp.setemailid(email);
		lp.setpwd(pwd);
		lp.ClickLogInBtn();
		
		//MyAccpuntPage
		MyAccountPage MAP=new MyAccountPage(driver);
		boolean tragetpage=MAP.isMyAccountPageExists();
		
		//data is valid---- loginSuccess----pass
		//                  LogInUsuccess----fail
		//data is Invalid---- loginSuccess----fail
		//                  LogInUsuccess----pass
		
		if(exp.equalsIgnoreCase("Valid")) 
		{
			if(tragetpage==true)
			{
				MAP.logOut();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(tragetpage==true)
			{
				MAP.logOut();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*********testing ended**********");
	}
}


