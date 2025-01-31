package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;



public class AddCart extends BasePage {

	public AddCart(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath="//ul[@class='breadcrumb']//li[1]") WebElement homeicon;
	@FindBy(xpath="//input[@placeholder='Search']") WebElement searchbar;
	@FindBy(xpath="//i[@class='fa fa-search']") WebElement searchbtn;
	@FindBy(xpath="//select[@name='category_id']")WebElement cattype;
	@FindBy(xpath="//input[@id='button-search']") WebElement catsearch;
	@FindBy(xpath="//h2[normalize-space()='Products meeting the search criteria']") WebElement result;
	@FindBy(xpath="//div[@class='caption']//a[contains(text(),'iMac')]")WebElement imac;
	@FindBy(xpath="//input[@id='input-quantity']") WebElement qty;
	@FindBy(xpath="//button[@id='button-cart']") WebElement addtocart;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']") WebElement success;
	@FindBy(xpath="//span[normalize-space()='Shopping Cart']") WebElement shoopingcart;
	@FindBy(xpath="//h1[normalize-space()='iMac']") WebElement prodname;
	@FindBy(xpath="//body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")WebElement itemadded;
	
	
	
	public void HomeIcon()
	{
		homeicon.click();
	}
	public void SearchBar(String search)
	{
		searchbar.sendKeys(search);
	}
	public void SearchBtn()
	{
		searchbtn.click();
	}
	public void  SelectCatType(String Categories)
	{
		cattype.click();
	Select CatType=new Select(cattype);
	CatType.selectByVisibleText(Categories);
	}
	public void CatSearch() 
	{
		catsearch.click();
	}
	public String CatSearchresult()
	{
		try {
		 return (result.getText());
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
	public void ImacIteam()
	{
		imac.click();
	}
	public void ItemQty(String QTY) 
	{
		qty.clear();
		qty.sendKeys(QTY);
	}
	public void Cart()
	{
		addtocart.click();
	}
	public boolean SuccessCart()
	{
		try 
		{
		boolean SC=success.isDisplayed();
		return(SC);
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	public void ShoopingCart()
	{
		shoopingcart.click();
	}
	public boolean VerifyCart()
	{
			String Pr=prodname.getText();
			String Ir=itemadded.getText();
		if(Pr.equalsIgnoreCase(Ir)) 
		{
			return true;
		}else {
			return false;
		}
		
		
		
	}
}
