package testcases;

import listeners.TestListener;
import pages.CustomerPage;
import pages.DashboardPage;
import pages.LoginPage;
import common.BaseTest;
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
public class CustomerTest extends BaseTest {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	CustomerPage customerPage;
	
	@Epic("Regression")
	@Feature("DMS")
	@Story("Customer")
	@Owner("Bin Tester")
	@Severity(SeverityLevel.NORMAL)
	@Link("https://crm.anhtester.com/admin/clients")
	@Issue("https://nashtech-global.atlassian.net/")
	@Test(priority = 0, description = "Verify can add & edit & delete Customer success")
	public void verifyCreateUpdateDeleteCustomerSuccess()
	{
		loginPage = new LoginPage();
		dashboardPage = loginPage.loginCRM();
		dashboardPage.verifyInvoicesAwaitingPaymentTotal("0 / 2");
		customerPage = dashboardPage.clickMenuCustomer();
		String customerName = "NashTech Company";	
		customerPage.verifyNavigateToCustomerPage();
		int beforeTotal = customerPage.getCustomerTotal();
		customerPage.clickButtonAddNewCustomer();
		customerPage.submitDataForNewCustomer(customerName);
		customerPage.verifyAddNewCustomerSuccess(customerName);
		customerPage = dashboardPage.clickMenuCustomer();
		int afterTotal = customerPage.getCustomerTotal();
		WebUI.assertEquals(afterTotal, beforeTotal + 1, "The total Customers before and after add new not match.");
		customerPage.searchAndCheckCustomerInTable(customerName);
		customerPage.clickFirstCustomer();
		customerPage.verifyNavigateToCustomerDetailPage();
		String updatedCustomerName = "Bin123 Company";
		customerPage.updateCustomerNameSuccess(updatedCustomerName);
		customerPage = dashboardPage.clickcontactsTab();
		customerPage.verifyContactsTab(updatedCustomerName);
		customerPage.clickButtonNewContact();
		customerPage.verifyAddNewContactPopUp(updatedCustomerName);
		customerPage.createContact();
		customerPage.verifyContactCreated();
		customerPage.clickMenuCustomer();
		customerPage.searchAndCheckCustomerInTableAfterUpdated(updatedCustomerName);
		customerPage.verifyDataOnCustomerPage();
		customerPage.moveToCompanyName();
		customerPage.clickAndDeleteCustomer();
		customerPage.searchAndCheckCustomerInTableAfterDeleted(updatedCustomerName);
		customerPage.verifyNoDataCustomer();
		customerPage.logOutCRM();
	}
}