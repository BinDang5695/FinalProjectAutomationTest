package testcases;

import listeners.TestListener;
import pages.CustomerPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.TaskPage;
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

import java.awt.AWTException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class TaskTest extends BaseTest {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	TaskPage taskPage;
	
	@Epic("Regression")
	@Feature("DMS")
	@Story("Task")
	@Owner("Bin Tester")
	@Severity(SeverityLevel.NORMAL)
	@Link("https://crm.anhtester.com/admin/tasks")
	@Issue("https://nashtech-global.atlassian.net/")
	@Test(priority = 0, description = "Verify can create & delete Task")
	public void verifyTask() throws InterruptedException, AWTException
	{
		loginPage = new LoginPage();
		taskPage = new TaskPage();
		dashboardPage = loginPage.loginCRM();
		dashboardPage.verifyInvoicesAwaitingPaymentTotal("0 / 2");
		taskPage = dashboardPage.clickmenuTasks();
		String taskName = "Bin Task";
		taskPage.verifyNavigateToTasksPage();
		taskPage.clickButtonSwitchToKanBan();
		taskPage.verifyNavigateToKanbanPage();
		taskPage.clickButtonAddNewTask();
		taskPage.verifyAddNewTaskPopUp();
		taskPage.submitDataForNewTask(taskName);
		taskPage.verifyNewTaskAfterCreated();
		taskPage.markCompletedAndRefreshPage();
		taskPage.verifyCompleteTasksAfterRefreshed();
		//taskPage.addNewDescriptionAndVerifyDescription();
		taskPage.searchAndVerifyAfterSearch(taskName);
		taskPage.dragAndDrop();
		taskPage.verifyTotalTasksAfterDragDrop();
		//taskPage.clickAndVerifyDescriptionAfterDragDrop();
		taskPage.searchAndDeleteTask(taskName);
		taskPage.searchAfterDeleted(taskName);
		taskPage.verifyNoDataAfterDeleted();
		taskPage.logOutCRM();
	}
}