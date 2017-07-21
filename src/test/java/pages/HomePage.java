package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	public WebDriver driver;
	public HomePage(WebDriver driver){
		this.driver=driver;
	}

	@FindBy(css="input[name='txtUserEmailId']")
	public WebElement username;
	@FindBy(css="input[name='txtPassword']")
	public WebElement password;
	@FindBy(css="select[name='drDominlist']")
	public WebElement domainlist;
	@FindBy(css="input[name='btnsignin']")
	public WebElement signIn;
	@FindBy(css="li#ctl00_liProject > a[href='/BA360_BS_Training/Project/ProjectConfig/BusinessBenifitsMain.aspx']")
	public WebElement project;

	public void login(String username, String password, String domain){
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		domainlist.click();
		Select select=new Select(domainlist);
		select.selectByVisibleText(domain);
		signIn.click();
	}
	
	public ProjectPage project(){
		project.click();
		return PageFactory.initElements(driver, ProjectPage.class);
		
	}
}
