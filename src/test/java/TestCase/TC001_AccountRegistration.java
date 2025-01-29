package TestCase;


import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;
import Testbase.BaseClass;

public class TC001_AccountRegistration extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() 
	{
		try {
	
		logger.info("***********Starting TC001 AccountRegistrationTest******");
		
		HomePage hp= new HomePage(driver);
		hp.clickMyaccount();
		logger.info("***********Clicked on my account link******");
		hp.clickRegister();
		
		logger.info("***********Clicked on Register link******");
		AccountRegistrationPage ARP=new AccountRegistrationPage(driver);
		
		logger.info("***********Providing customer details******");
		ARP.setFirstName(randomString().toUpperCase());
		ARP.setLastName(randomString().toUpperCase());
		ARP.setEmail(randomString()+"@gmail.com");
		ARP.setTelephone(randomNumber());
		String pwd=randomAlphaNumeric();
		ARP.setpass(pwd);
		ARP.setconfirmPass(pwd);
		ARP.setprivacyPolicy();
		ARP.ContinueButton();
		
		logger.info("***********Validating extected message******");
		String ConfirmMsg=ARP.getconfirmationMsg();
		if(ConfirmMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else
		{
			
			logger.error("****Test Failed*****");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
	
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***********Test case TC001 passed******");
	}

	
}
