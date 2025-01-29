package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		
	}

@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstName;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement  txtLastName;

@FindBy(xpath="//input[@id='input-email']")
WebElement  txtEmail;

@FindBy(xpath="//input[@id='input-telephone']")
WebElement txtTelephone;

@FindBy(xpath="//input[@id='input-password']")
WebElement  txtPassword;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement  txtConfirmPassword;

@FindBy(xpath="//input[@name='agree']")
WebElement  chkPolicy;

@FindBy(xpath="//input[@value='Continue']")
WebElement  btnContinue;
	
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

public void setFirstName(String fname)
{
	txtFirstName.sendKeys(fname);
}
public void setLastName(String lname)
{
	txtLastName.sendKeys(lname);
}
public void setEmail(String Email)
{
	txtEmail.sendKeys(Email);
}
public void setTelephone(String tele)
{
	txtTelephone.sendKeys(tele);
}
public void setpass(String pass)
{
	txtPassword.sendKeys(pass);
}
public void setconfirmPass(String pass)
{
	txtConfirmPassword.sendKeys(pass);
}
public void setprivacyPolicy()
{
	chkPolicy.click();
}
public void ContinueButton()
{
	btnContinue.click();
}
public String getconfirmationMsg()
{
	try {
	return (msgConfirmation.getText());
	}catch(Exception e) {
		return (e.getMessage());
	}
	
}
}
