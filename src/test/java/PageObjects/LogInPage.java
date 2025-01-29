package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {

	//constructor
	public LogInPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_email;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_pwd;
	@FindBy(xpath="//input[@value='Login']") WebElement btn_logIn;
	
	public void setemailid(String Email)
	{
		txt_email.sendKeys(Email);
	}
	public void setpwd(String pwd)
	{
		txt_pwd.sendKeys(pwd);
	}

	public void ClickLogInBtn()
	{
		btn_logIn.click();
	}
	
	
}
