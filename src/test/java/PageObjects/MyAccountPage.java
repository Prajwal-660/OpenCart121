package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage (WebDriver driver)
	{
		super(driver);
	}
	 
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement MyaccountHeading;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement LogOut;
	
	public boolean isMyAccountPageExists ()
	{
		try 
		{
		boolean MAPE=MyaccountHeading.isDisplayed();
		return(MAPE);
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	public void logOut()
	{
		LogOut.click();
	}
}
