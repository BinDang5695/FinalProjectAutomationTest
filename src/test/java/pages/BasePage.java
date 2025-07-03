package pages;
import org.openqa.selenium.By;

import keywords.WebUI;

public class BasePage {

	public By menudashboard = By.xpath("//span[normalize-space()='Dashboard']");
	public By customers = By.xpath("//span[normalize-space()='Customers']");
	public By iconprofile = By.xpath("//li[@class='icon header-user-profile']");
	public By taskspage = By.xpath("//span[normalize-space()='Tasks']");
	public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
	public By sales = By.xpath("//span[@class='menu-text'][normalize-space()='Sales']");
	public By proposals = By.xpath("//span[normalize-space()='Proposals']");
	public By contactsTab = By.xpath("//a[@data-group='contacts']");
	public By tabProjects = By.xpath("//a[@data-group='projects']");

	public CustomerPage clickMenuCustomer()
	{
		WebUI.waitForElementVisible(customers);
		WebUI.clickElement(customers);
		return new CustomerPage();
	}
	
	public CustomerPage clickcontactsTab()
	{
		WebUI.waitForElementVisible(contactsTab);
		WebUI.clickElement(contactsTab);
		return new CustomerPage();
	}
	
	public ProjectPage clickmenuProjects()
	{
		WebUI.waitForElementVisible(menuProjects);
		WebUI.clickElement(menuProjects);
		return new ProjectPage();
	}
	
	public ProjectPage clicktabProjects()
	{
		WebUI.waitForElementVisible(tabProjects);
		WebUI.clickElement(tabProjects);
		return new ProjectPage();
	}
	
	public TaskPage clickmenuTasks()
	{
		WebUI.waitForElementVisible(taskspage);
		WebUI.clickElement(taskspage);
		return new TaskPage();
	}
}
