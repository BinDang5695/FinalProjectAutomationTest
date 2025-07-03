package pages;

import driver.DriverManager;
import keywords.WebUI;
import keywords.WebUI.WaitType;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import common.LocatorsCRM;

	public class TaskPage extends BasePage {
	    
		//verify projects page
		private By titleTaskPage = By.xpath("//span[normalize-space()='Tasks Summary']");
		private By switchToKanBan = By.xpath("//i[@class='fa-solid fa-grip-vertical']");
		private By switchToList = By.xpath("//i[@class='fa-solid fa-table-list']");
		
		//verify after switchToKanBan / Refresh page and verify different total with verify after switchToKanBan / Drag and Drop and verify Total task
		private By completeTaskTotal = By.xpath("//body/div[@id='wrapper']/div[@class='content']/div[@class='row']/div[@class='col-md-12']/div[@id='kan-ban-tab']/div[@class='row']/div[@class='container-fluid']/div[@id='kan-ban']/ul[5]/li[1]/div[1]/div[1]");
		private By notStartedTaskTotal = By.xpath("//body/div[@id='wrapper']/div[@class='content']/div[@class='row']/div[@class='col-md-12']/div[@id='kan-ban-tab']/div[@class='row']/div[@class='container-fluid']/div[@id='kan-ban']/ul[1]/li[1]/div[1]/div[1]");
		private By completeTaskAfterDragDrop = By.xpath("//div[@class='panel-heading' and normalize-space()='Complete - 0 Tasks']");
		
		//verify Add new task page
		private By newTaskButton = By.xpath("//a[normalize-space()='New Task']");
		private By titleAddNewTaskPopUp = By.xpath("//h4[@id='myModalLabel']");
		private By checkboxPublic = By.xpath("//input[@id='task_is_public']");
		private By textCheckboxPublic = By.xpath("//label[normalize-space()='Public']");
		private By checkboxBillable = By.xpath("//input[@id='task_is_billable']");
		private By textCheckboxBillable = By.xpath("//label[normalize-space()='Billable']");
		
		
		//Add new task page
		private By inputsubject = By.xpath("//input[@id='name']");
		private By saveTask = By.xpath("//button[normalize-space()='Save']");
		private By alertAddNewTaskSuccess = By.xpath("//span[@class='alert-title']");
		
		//Verify Add new task page, mark complete and complete status
		private By taskName = By.xpath("//h4[@class='modal-title tw-flex tw-items-center']");
		private By taskStatus = By.xpath("//span[@class='trigger pointer manual-popover text-has-action tw-text-neutral-800']");
		private By taskDescription = By.xpath("//span[@class='text-muted']");
		private By markComplete = By.xpath("//i[@class='fa fa-check']");
		private By closePopUp = By.xpath("//div[@class='modal-header task-single-header']//span[@aria-hidden='true'][normalize-space()='×']");
		
		//Verify Search Task Created and other Status don't have
		private By searchOnKanBan = By.xpath("//input[@id='search']");
		private By nodataNotStarted = By.xpath("//ul[1]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]//h4[1]");
		private By nodataInprogress = By.xpath("//ul[2]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]//h4[1]");
		private By nodataTesting = By.xpath("//ul[3]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]//h4[1]");
		private By nodataAwaitingFeedback = By.xpath("//ul[4]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]//h4[1]");
		
		//Click and add new description
		private By taskCreated = By.xpath("//span[@class='inline-block full-width mtop10 tw-truncate']");
		private By description = By.xpath("//i[@class='fa-regular fa-pen-to-square']");
		private By inputDescription = By.xpath("//div[@id='task_view_description']");
		
		//Close popup and verify description
		private By verifyDescription = By.xpath("//p[normalize-space()='Bin add new description']");
		private By completeTask = By.xpath("//ul[5]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");
		private By notStartedTask = By.xpath("//ul[1]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");
		private By binTask = By.xpath("//span[normalize-space()='Bin Task']");
		
		//DragDrop
		private By from = By.xpath("//ul[5]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");
		private By to = By.xpath("//ul[1]//li[1]//div[1]//div[2]//div[1]//ul[1]//li[1]");
		
		//click switch to list, search and delete
		private By searchOnList = By.xpath("//input[@class='form-control input-sm']");
		private By binTaskOnList = By.xpath("//a[normalize-space()='Bin Task']");
		private By deleteTask = By.xpath("//a[normalize-space()='Delete']");
		
		//verify delete success, search and verify nodata after search
		private By nodataTask = By.xpath("//td[@class='dataTables_empty']");
		
		//logout
		private By iconUser = By.xpath("//img[@class='img img-responsive staff-profile-image-small tw-ring-1 tw-ring-offset-2 tw-ring-primary-500 tw-mx-1 tw-mt-2.5']");
		private By logOutoption = By.xpath("//ul[@class='dropdown-menu animated fadeIn']//li[@class='header-logout']//a[@href='#'][normalize-space()='Logout']");
		private By dismissAlert = By.xpath("//button[@data-dismiss='alert']");
		
		public void verifyNavigateToTasksPage()
		{
			WebUI.waitUntil(titleTaskPage, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(titleTaskPage), "The TaskPage title not display.");
			WebUI.assertEquals(WebUI.getTextElement(titleTaskPage), "Tasks Summary", "The TaskPage title not match.");
			Assert.assertTrue(WebUI.checkElementExist(switchToKanBan), "The switchToKanBan not display.");
		}
		
		public void clickButtonSwitchToKanBan()
		{
			WebUI.waitUntil(switchToKanBan, WaitType.CLICKABLE);
			WebUI.clickElement(switchToKanBan);
		}
		
		public void verifyNavigateToKanbanPage()
		{
			WebUI.waitUntil(completeTaskTotal, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(completeTaskTotal), "The completeTaskTotal title not display.");
			WebUI.assertEquals(WebUI.getTextElement(completeTaskTotal), "Complete - 0 Tasks", "The completeTaskTotal title not match.");
		}
		
		public void clickButtonAddNewTask()
		{
			WebUI.waitUntil(newTaskButton, WaitType.CLICKABLE);
			WebUI.clickElement(newTaskButton);
		}
		
		public void verifyAddNewTaskPopUp()
		{
			WebUI.waitUntil(titleAddNewTaskPopUp, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(titleAddNewTaskPopUp), "The AddNewTask title not display.");
			WebUI.assertEquals(WebUI.getTextElement(titleAddNewTaskPopUp), "Add new task", "The AddNewTask title not match.");
			Assert.assertTrue(WebUI.checkElementExist(checkboxPublic), "The checkboxPublic not display.");
			Assert.assertFalse(WebUI.isElementSelected(checkboxPublic), "Checkbox Public should NOT be selected.");
			Assert.assertTrue(WebUI.checkElementExist(textCheckboxPublic), "The textCheckboxPublic not display.");
			WebUI.assertEquals(WebUI.getTextElement(textCheckboxPublic), "Public", "The textCheckboxPublic not match.");
			Assert.assertTrue(WebUI.checkElementExist(checkboxBillable), "The checkboxBillable not display.");
			Assert.assertTrue(WebUI.isElementSelected(checkboxBillable), "Checkbox Public should be selected.");
			Assert.assertTrue(WebUI.checkElementExist(textCheckboxBillable), "The textCheckboxBillable title not display.");
			WebUI.assertEquals(WebUI.getTextElement(textCheckboxBillable), "Billable", "The textCheckboxBillable not match.");
		}
		
		public void submitDataForNewTask(String taskName)
		{
			WebUI.waitUntil(inputsubject, WaitType.VISIBLE);
			WebUI.setText(inputsubject, taskName);
			WebUI.waitUntil(saveTask, WaitType.CLICKABLE);
			WebUI.clickElement(saveTask);
		}
		
		public void verifyNewTaskAfterCreated()
		{
			WebUI.waitUntil(alertAddNewTaskSuccess, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(alertAddNewTaskSuccess), "The alertAddNewTaskSuccess title not display.");
			WebUI.assertEquals(WebUI.getTextElement(alertAddNewTaskSuccess), "Task added successfully.", "The alertAddNewTaskSuccess title not match.");
			WebUI.waitUntil(taskName, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(taskName), "The taskName not display.");
			WebUI.assertEquals(WebUI.getTextElement(taskName).replaceAll("\\s+", " ").trim(),
	                   "Bin Task In Progress",
	                   "The taskName not match.");
			WebUI.waitUntil(taskStatus, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(taskStatus), "The taskStatus not display.");
			WebUI.assertEquals(WebUI.getTextElement(taskStatus), "In Progress", "The taskStatus not match.");
		}
		
		public void markCompletedAndRefreshPage()
		{
			WebUI.waitUntil(markComplete, WaitType.VISIBLE);
			WebUI.clickElement(markComplete);
			WebUI.waitUntil(closePopUp, WaitType.VISIBLE);
			WebUI.clickElement(closePopUp);
			DriverManager.getDriver().navigate().refresh();
		}
		
		public void verifyCompleteTasksAfterRefreshed()
		{
			WebUI.waitUntil(completeTaskTotal, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(completeTaskTotal), "The completeTaskTotal title not display.");
			WebUI.assertEquals(WebUI.getTextElement(completeTaskTotal), "Complete - 1 Tasks", "The completeTaskTotal title not match.");
		}
		
	    public static void inputText() throws InterruptedException, AWTException
	    {
	    	WebUI.sleep(0.5);
	    	Robot robot = new Robot();
	    	robot.keyPress(KeyEvent.VK_CONTROL);
	    	robot.keyPress(KeyEvent.VK_A);
	    	robot.keyRelease(KeyEvent.VK_A);
	    	robot.keyRelease(KeyEvent.VK_CONTROL);
	    	WebUI.sleep(0.5);
	    	robot.keyPress(KeyEvent.VK_DELETE);
	    	robot.keyRelease(KeyEvent.VK_DELETE);
	    }
		
		public void addNewDescriptionAndVerifyDescription() throws InterruptedException, AWTException
		{			
			WebUI.waitUntil(binTask, WaitType.VISIBLE);
			WebUI.clickElement(binTask);
			WebUI.waitUntil(description, WaitType.VISIBLE);
			WebUI.clickElement(description);
			WebUI.waitUntil(inputDescription, WaitType.VISIBLE);
	    	inputText();
			WebUI.setText(inputDescription, "Bin add new description");
			WebUI.clickElement(closePopUp);
		}
		
		public void searchAndVerifyAfterSearch(String taskName)
		{
			WebUI.waitUntil(searchOnKanBan, WaitType.VISIBLE);
			WebUI.setText(searchOnKanBan, taskName);
			WebUI.waitUntil(nodataNotStarted, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(nodataNotStarted), "The nodataNotStarted title not display.");
			WebUI.assertEquals(WebUI.getTextElement(nodataNotStarted), "No Tasks Found", "The nodataNotStarted title not match.");
			WebUI.waitUntil(nodataInprogress, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(nodataInprogress), "The nodataInprogress title not display.");
			WebUI.assertEquals(WebUI.getTextElement(nodataInprogress), "No Tasks Found", "The nodataInprogress title not match.");
			WebUI.waitUntil(nodataTesting, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(nodataTesting), "The nodataTesting title not display.");
			WebUI.assertEquals(WebUI.getTextElement(nodataTesting), "No Tasks Found", "The nodataTesting title not match.");
			WebUI.waitUntil(nodataAwaitingFeedback, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(nodataAwaitingFeedback), "The nodataAwaitingFeedback title not display.");
			WebUI.assertEquals(WebUI.getTextElement(nodataAwaitingFeedback), "No Tasks Found", "The nodataAwaitingFeedback title not match.");
		}
		
		public void dragAndDrop()
		{
			WebUI.dragAndDrop(from, to);
		}
		
		public void verifyTotalTasksAfterDragDrop()
		{
			WebUI.waitUntil(completeTaskAfterDragDrop, WaitType.CLICKABLE);
			Assert.assertTrue(WebUI.checkElementExist(completeTaskAfterDragDrop), "The completeTaskTotal not display.");
			WebUI.assertEquals(WebUI.getTextElement(completeTaskAfterDragDrop), "Complete - 0 Tasks", "The completeTaskTotal not match.");
			Assert.assertTrue(WebUI.checkElementExist(notStartedTaskTotal), "The notStartedTaskTotal not display.");
			WebUI.assertEquals(WebUI.getTextElement(notStartedTaskTotal), "Not Started - 1 Tasks", "The notStartedTaskTotal not match.");
		}
		
		public void clickAndVerifyDescriptionAfterDragDrop()
		{
			WebUI.waitUntil(taskCreated, WaitType.CLICKABLE);
			WebUI.clickElement(taskCreated);
			WebUI.waitUntil(verifyDescription, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(verifyDescription), "The Description not display.");
			WebUI.assertEquals(WebUI.getTextElement(verifyDescription), "Bin add new description", "The Description not match.");
			WebUI.clickElement(closePopUp);
		}
		
		public void searchAndDeleteTask(String taskName)
		{
			WebUI.waitUntil(switchToList, WaitType.VISIBLE);
			WebUI.clickElement(switchToList);
			WebUI.setText(searchOnList, taskName);
			WebUI.waitUntil(binTaskOnList, WaitType.VISIBLE);
			WebUI.moveToElement(binTaskOnList);
			WebUI.waitUntil(deleteTask, WaitType.VISIBLE);
			WebUI.clickElement(deleteTask);
			WebUI.handleAlertAccept();
		}
		
		public void searchAfterDeleted(String taskName)
		{
			WebUI.waitUntil(searchOnList, WaitType.VISIBLE);
			WebUI.setText(searchOnList, taskName);
		}
		
		public void verifyNoDataAfterDeleted()
		{
			WebUI.waitUntil(nodataTask, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(nodataTask), "The nodataTask not display.");
			WebUI.assertEquals(WebUI.getTextElement(nodataTask), "No matching records found", "The nodataTask not match.");	
		}
		
		public void logOutCRM()
		{
			WebUI.waitUntil(dismissAlert, WaitType.VISIBLE);
			WebUI.clickElement(dismissAlert);
			WebUI.waitUntil(iconUser, WaitType.CLICKABLE);
			WebUI.clickElement(iconUser);
			WebUI.waitUntil(logOutoption, WaitType.CLICKABLE);
			WebUI.clickElement(logOutoption);
		}
}
