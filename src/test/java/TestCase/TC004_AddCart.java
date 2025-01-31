package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AddCart;
import PageObjects.HomePage;
import PageObjects.LogInPage;
import PageObjects.MyAccountPage;
import Testbase.BaseClass;

public class TC004_AddCart extends BaseClass{

	@Test
	public void AddCartItem()
	{
		try {
           logger.info("Starting TC004");
		
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
		
		AddCart Ac=new AddCart(driver);
		Ac.HomeIcon();
		Ac.SearchBar("Mac");
		Ac.SearchBtn();
		Ac.SelectCatType("Tablets");
		Thread.sleep(2000);
		Ac.CatSearch();
		String ConfigMsg=Ac.CatSearchresult();
		
		if(ConfigMsg.equals("Products meeting the search criteria")) {
			Assert.assertTrue(true);
		}
		else
		{
			
			logger.error("****Test Failed*****");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		Ac.ImacIteam();
		Ac.ItemQty("2");
		Ac.Cart();
		Thread.sleep(2000);
		boolean SuccessAlert=Ac.SuccessCart();
		if(SuccessAlert==true) 
		{
			Assert.assertTrue(true);
		}else
		{
			logger.error("****Test success allert Failed*****");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		Ac.ShoopingCart();
		Thread.sleep(2000);
		boolean verycartitem=Ac.VerifyCart();
		if(verycartitem==true) 
		{
			Assert.assertTrue(true);
		}else
		{
			logger.error("****Test veryfy cart details Failed*****");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
	    
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*******test case TC004 passed**********");
	}
}
