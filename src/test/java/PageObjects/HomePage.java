package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	//constructor
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	//locating the elements
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyaccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLogIn;
	//Action methods
	
	public void clickMyaccount()
	{
		lnkMyaccount.click();
	}
	 
	public void clickRegister() 
	{
	  lnkRegister.click();	
	}
	
	public void clickLogIn()
	{
		lnkLogIn.click();
	}
}
