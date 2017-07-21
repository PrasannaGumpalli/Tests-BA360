package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectPage {
	public WebDriver driver;
	public ProjectPage(WebDriver driver){
		this.driver=driver;
	}

	@FindBy(css="a#ctl00_btnAddProject")
	public WebElement newProject;
	@FindBy(name="txtDisplayPJName")
	public WebElement projectName;
	@FindBy(css="select[name='ddlProjectType']")
	public WebElement projectType;
	@FindBy(css="select[name='ddlEngagementModel']")
	public WebElement engagementModel;
	@FindBy(css="input[name='dtPlanStartDt']")
	public WebElement planStrtDt;
	@FindBy(css="input[name='dtActualStartDt']")
	public WebElement actualStrtDt;
	@FindBy(css="input[name='txtReleaseDate']")
	public WebElement releaseDt;
	@FindBy(css="input[name='dtELSDate']")
	public WebElement elsDt;
	@FindBy(css="textarea[name='txtProjDesc']")
	public WebElement desc;
	@FindBy(css="textarea[name='txtProjNotes']")
	public WebElement notes;
	@FindBy(css="input[name='txtFactor']")
	public WebElement factor;
	@FindBy(css="select[name='ddlProjectComplexity']")
	public WebElement projectComplexity;
	@FindBy(css="button.multiselect.dropdown-toggle.btn.btn-default")
	public WebElement codeDrop;
	@FindBy(css="input[name='dtPlanEndDt']")
	public WebElement planEndDt;
	@FindBy(css="input[name='dtActualEndDt']")
	public WebElement actualEndDt;
	@FindBy(css="input[name='dtGoLiveDt']")
	public WebElement goLiveDt;
	@FindBy(css="select[name='ddlQDM']")
	public WebElement scrumMaster;
	@FindBy(css="a.chzn-single.chzn-single-with-drop")
	public WebElement switchProjects;
	
	public void newProjectCreate(String projectName, String projectType, String engagementModel, String planStrtDt, String actualStrtDt,String releaseDt,String elsDt,String desc,String notes,String factor,String projectComplexity,String codeDrop, String planEndDt,String actualEndDt,String goLiveDt,String scrumMaster) throws Throwable{
		newProject.click();
		Thread.sleep(3000);
		this.projectName.sendKeys(projectName);
	}

	
}
