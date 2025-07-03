package testcases;

import listeners.TestListener;
import pages.CustomerPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectPage;
import common.BaseTest;
import dataproviders.DataProviderFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import keywords.WebUI;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ProjectTest extends BaseTest {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	ProjectPage projectPage;
	CustomerPage customerPage;
	
	@Epic("Regression")
	@Feature("DMS")
	@Story("Project")
	@Owner("Bin Tester")
	@Severity(SeverityLevel.NORMAL)
	@Link("https://crm.anhtester.com/admin/projects")
	@Issue("https://nashtech-global.atlassian.net/")
	@Test(priority = 0, description = "Verify Project of Customer before and after delete")
	public void verifyProjectOfCustomer()
	{
		loginPage = new LoginPage();
		customerPage = new CustomerPage();
		dashboardPage = loginPage.loginCRM();
		dashboardPage.verifyInvoicesAwaitingPaymentTotal("0 / 2");
		projectPage = dashboardPage.clickmenuProjects();
		String projectName = "Bin Project";
		String customerName = "Bin Customer";
		projectPage.verifyNavigateToProjectPage();
		projectPage.clickButtonAddNewCustomer();
		projectPage.submitDataForNewCustomer(projectName);
		projectPage.verifyProjectCreated();
		projectPage.searchAndCheckCustomerInTable(customerName);
		projectPage.clickFirstCustomer();
		projectPage.clicktabProjects();
		projectPage.verifyProjectCreatedOnProjectTab();
		projectPage.moveToProjectName();
		projectPage.clickAndDeleteProject();
		projectPage.clickmenuProjects();
		projectPage.searchAndCheckProjectInTable(projectName);
		projectPage.verifyNoDataAfterDeletedProject();
		projectPage.logOutCRM();
	}
}