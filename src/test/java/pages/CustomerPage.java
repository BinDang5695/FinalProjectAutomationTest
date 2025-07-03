package pages;

import driver.DriverManager;
import keywords.WebUI;
import keywords.WebUI.WaitType;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import common.LocatorsCRM;

	public class CustomerPage extends BasePage {

		private By headerpage = By.xpath("//span[normalize-space()='Customers Summary']");
		private By newcustomer = By.xpath("//a[normalize-space()='New Customer']");
		private By company = By.xpath("//input[@id='company']");
		private By vat = By.xpath("//input[@id='vat']");
		private By phone = By.xpath("//input[@id='phonenumber']");
		private By website = By.xpath("//input[@id='website']");
		private By groups = By.xpath("//div[@class='dropdown bootstrap-select show-tick input-group-btn _select_input_group bs3']");
		private By search1 = By.xpath("//div[@class='dropdown bootstrap-select show-tick input-group-btn _select_input_group bs3 dropup open']//input[@aria-label='Search']");
		private By testing = By.xpath("//span[normalize-space()='Testing']");
		private By divtesting = By.xpath("//div[contains(text(),'Testing')]");
		private By currency = By.xpath("//button[@class='btn dropdown-toggle btn-default bs-placeholder']//div[@class='filter-option-inner-inner'][normalize-space()='System Default']");
		private By eur = By.xpath("//a[@id='bs-select-2-2']//span[@class='text']");
		private By language = By.xpath("//div[contains(text(),'System Default')]");
		private By vn = By.xpath("//span[normalize-space()='Vietnamese']");
		private By address  = By.xpath("//textarea[@id='address']");
		private By city  = By.xpath("//input[@id='city']");
		private By state  = By.xpath("//input[@id='state']");
		private By zip  = By.xpath("//input[@id='zip']");
		private By country  = By.xpath("//div[@app-field-wrapper='country']//div[@class='filter-option-inner-inner'][normalize-space()='Nothing selected']");
		private By search2  = By.xpath("//div[@class='dropdown bootstrap-select bs3 dropup open']//input[@aria-label='Search']");
		private By vn2 = By.xpath("//span[normalize-space()='Vietnam']");
		private By save  = By.xpath("//button[@class='btn btn-primary only-save customer-form-submiter']");
	    private By vn3 = By.xpath("//div[contains(text(),'Vietnamese')]");
		private By headerCustomerDetailPage = By.xpath("//h4[normalize-space()='Profile']");
	    private By inputSearchCustomer = By.xpath("//div[@id='clients_filter']//input[@type='search']");
	    private By itemCustomerFirst = By.xpath("//table[@id='clients']/tbody/tr[1]/td[3]/a[normalize-space()='NashTech Company']");
	    private By itemCustomerSecond = By.xpath("//table[@id='clients']/tbody/tr[1]/td[3]/a[normalize-space()='Bin123 Company']");
	    private By customerID = By.xpath("//th[@id='th-number']");
	    private By alertUpdatedCustomer = By.xpath("//span[normalize-space()='Customer updated successfully.']");
	    private By nameAfterUpdated = By.xpath("//a[normalize-space()='Bin123 Company']");
	    private By primaryContact = By.xpath("//a[normalize-space()='Bin Dang']");
	    private By primaryEmail = By.xpath("//a[normalize-space()='bin.dang@gmail.com']");
	    private By nodata = By.xpath("//td[@class='dataTables_empty']");
	    private By closePopUp = By.xpath("//button[@data-dismiss='alert']//span[@aria-hidden='true'][normalize-space()='×']");
	    
	    
		//4 total in customer list
		private By totalCustomers = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");

		//Contact tab
		private By titleContactsTab = By.xpath("//h4[normalize-space()='Contacts']");
		private By newContact = By.xpath("//a[normalize-space()='New Contact']");
		private By addNewContact = By.xpath("//h4[normalize-space()='Add new contact']");
		private By customerNameOnContact = By.xpath("//p[@class='tw-mb-0']");
		private By firstName = By.xpath("//input[@id='firstname']");
		private By lastName = By.xpath("//input[@id='lastname']");
		private By email = By.xpath("//input[@id='email']");
		private By password = By.xpath("//input[@name='password']");
		private By saveContact = By.xpath("//button[normalize-space()='Save']");
		private By fullNameContact = By.xpath("//a[normalize-space()='Bin Dang']");
		private By emailContact = By.xpath("//a[normalize-space()='bin.dang@gmail.com']");
		private By alertAddedContact = By.xpath("//span[@class='alert-title']");
		private By moveToElementCompanyName = By.xpath("//a[normalize-space()='Bin123 Company']");
		private By deleteCusTomer = By.xpath("//a[normalize-space()='Delete']");
		private By iconUser = By.xpath("//img[@class='img img-responsive staff-profile-image-small tw-ring-1 tw-ring-offset-2 tw-ring-primary-500 tw-mx-1 tw-mt-2.5']");
		private By logOutoption = By.xpath("//ul[@class='dropdown-menu animated fadeIn']//li[@class='header-logout']//a[@href='#'][normalize-space()='Logout']");
		
		public void verifyNavigateToCustomerPage()
		{
			WebUI.waitUntil(headerpage, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(headerpage), "The customer header page not display.");
			WebUI.assertEquals(WebUI.getTextElement(headerpage), "Customers Summary", "The customer header page not match.");
		}
		
		public void clickButtonAddNewCustomer()
		{
			WebUI.waitUntil(newcustomer, WaitType.CLICKABLE);
			WebUI.clickElement(newcustomer);
		}
		
		public void submitDataForNewCustomer(String customerName)
		{
			WebUI.setText(company, customerName);
			WebUI.setText(vat, "10");
			WebUI.setText(phone, "0123456789");
			WebUI.setText(website, "https://www.nashtechglobal.com/");
			WebUI.clickElement(groups);
			WebUI.clickElement(search1);
			WebUI.clickElement(testing);
			WebUI.clickElement(divtesting);
			WebUI.clickElement(currency);
			WebUI.clickElement(eur);
			WebUI.clickElement(language);
			WebUI.clickElement(vn);
			WebUI.setText(address, "123");
			WebUI.setText(city, "DN");
			WebUI.setText(state, "ST");
			WebUI.setText(zip, "50000");
			WebUI.clickElement(country);
			WebUI.setText(search2, "viet");
			WebUI.clickElement(vn2);
			WebUI.clickElement(save);
		}
		
		public void verifyAddNewCustomerSuccess(String customerName)
		{
			WebUI.waitUntil(company, WaitType.VISIBLE);
			WebUI.assertEquals(DriverManager.getDriver().findElement(company).getAttribute("value"), customerName, "The Company name not match");
			WebUI.assertEquals(DriverManager.getDriver().findElement(vat).getAttribute("value"), "10", "The VAT value not match");
			WebUI.assertEquals(DriverManager.getDriver().findElement(phone).getAttribute("value"), "0123456789", "The Phone number value not match");
			WebUI.assertEquals(DriverManager.getDriver().findElement(website).getAttribute("value"), "https://www.nashtechglobal.com/", "The Website value not match");
			WebUI.assertEquals(DriverManager.getDriver().findElement(divtesting).getText(), "Testing", "The Groups value not match");
			WebUI.assertEquals(DriverManager.getDriver().findElement(vn3).getText(), "Vietnamese", "The Language value not match"); 
		}
		
		public int getCustomerTotal()
		{
			WebUI.waitUntil(totalCustomers, WaitType.VISIBLE);
			String totalString = WebUI.getTextElement(totalCustomers);
			System.out.println("getCustomerTotal: " + totalString);
			return Integer.parseInt(totalString);
		}
		
		public void searchAndCheckCustomerInTable(String customerName)
		{
			clickMenuCustomer();
			WebUI.waitUntil(inputSearchCustomer, WaitType.VISIBLE);
			WebUI.setText(inputSearchCustomer, customerName);
			String customerNameInTable = WebUI.getTextElement(itemCustomerFirst);
			System.out.println(customerNameInTable);
			WebUI.assertEquals(customerNameInTable, customerName, "The customer name in table not match");
		}
		
		public void clickFirstCustomer()
		{
			WebUI.waitUntil(customerID, WaitType.VISIBLE);
			WebUI.clickElement(customerID);
			WebUI.clickElement(customerID);
			WebUI.clickElement(itemCustomerFirst);
		}
		
		public void verifyNavigateToCustomerDetailPage()
		{
			WebUI.waitUntil(headerCustomerDetailPage, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(headerCustomerDetailPage), "The customer header page not display.");
			WebUI.assertEquals(WebUI.getTextElement(headerCustomerDetailPage), "Profile", "The customer header page not match.");
		}
		
		public void updateCustomerNameSuccess(String updatedCustomerName)
		{			
			WebUI.waitUntil(company, WaitType.VISIBLE);
			WebUI.clearText(company);
			WebUI.setText(company, updatedCustomerName);
			WebUI.clickElement(save);
			WebUI.waitUntil(alertUpdatedCustomer, WaitType.VISIBLE);
			WebUI.assertEquals(DriverManager.getDriver().findElement(alertUpdatedCustomer).getText(), "Customer updated successfully.", "The success alert incorrect");
		}
		
		public void verifyContactsTab(String updatedCustomerName)
		{
			WebUI.waitUntil(titleContactsTab, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(titleContactsTab), "The contact header tab not display.");
			WebUI.assertEquals(WebUI.getTextElement(titleContactsTab), "Contacts", "The contact header tab not match.");
			//Assert.assertTrue(WebUI.checkElementExist(blankContacts), "Still has value");
			//WebUI.assertEquals(WebUI.getTextElement(blankContacts), "No entries found", "The blank text not match.");
		}
		
		public void clickButtonNewContact()
		{
			WebUI.waitUntil(newContact, WaitType.CLICKABLE);
			WebUI.clickElement(newContact);
		}
		
		public void verifyAddNewContactPopUp(String updatedCustomerName)
		{
			WebUI.waitUntil(addNewContact, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(addNewContact), "The add new contact header pop up not display.");
			WebUI.assertEquals(WebUI.getTextElement(addNewContact), "Add new contact", "The add new contact header pop up not match.");
			Assert.assertTrue(WebUI.checkElementExist(customerNameOnContact), "The customer name not display");
			WebUI.assertEquals(WebUI.getTextElement(customerNameOnContact), "Bin123 Company", "The customer name is incorrect");
		}
		
		public void createContact()
		{			
			WebUI.waitUntil(firstName, WaitType.VISIBLE);
			WebUI.setText(firstName, "Bin");
			WebUI.setText(lastName, "Dang");
			WebUI.setText(email, "bin.dang@gmail.com");
			WebUI.setText(password, "Bin@12301");
			WebUI.clickElement(saveContact);
		}
		
		public void verifyContactCreated()
		{		
			WebUI.waitUntil(alertAddedContact, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(alertAddedContact), "The alertAddedContact not display.");
			WebUI.assertEquals(WebUI.getTextElement(alertAddedContact), "Contact added successfully.", "The alertAddedContact text not match.");
			WebUI.waitUntil(fullNameContact, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(fullNameContact), "The fullname contact not display.");
			WebUI.assertEquals(WebUI.getTextElement(fullNameContact), "Bin Dang", "The fullname contact not match.");
			Assert.assertTrue(WebUI.checkElementExist(emailContact), "The email contact not display.");
			WebUI.assertEquals(WebUI.getTextElement(emailContact), "bin.dang@gmail.com", "The email contact not match.");
		}
		
		public void searchAndCheckCustomerInTableAfterUpdated(String updatedCustomerName)
		{
			clickMenuCustomer();
			WebUI.waitUntil(inputSearchCustomer, WaitType.VISIBLE);
			WebUI.setText(inputSearchCustomer, updatedCustomerName);
			String customerNameInTableAfterUpdated = WebUI.getTextElement(itemCustomerSecond);
			System.out.println(customerNameInTableAfterUpdated);
			WebUI.assertEquals(customerNameInTableAfterUpdated, updatedCustomerName, "The customer name in table not match");
		}
		
		public void moveToCompanyName()
		{
			WebUI.moveToElement(moveToElementCompanyName);
		}
		
		public void clickAndDeleteCustomer()
		{
			WebUI.waitUntil(deleteCusTomer, WaitType.CLICKABLE);
			WebUI.clickElement(deleteCusTomer);
			WebUI.handleAlertAccept();
			WebUI.waitUntil(closePopUp, WaitType.VISIBLE);
			WebUI.clickElement(closePopUp);
		}
		
		public void searchAndCheckCustomerInTableAfterDeleted(String updatedCustomerName)
		{
			WebUI.waitUntil(inputSearchCustomer, WaitType.VISIBLE);
			WebUI.setText(inputSearchCustomer, updatedCustomerName);
		}
		
		public void verifyDataOnCustomerPage()
		{
			WebUI.waitUntil(nameAfterUpdated, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(nameAfterUpdated), "The nameAfterUpdated not display.");
			WebUI.assertEquals(WebUI.getTextElement(nameAfterUpdated), "Bin123 Company", "The nameAfterUpdated not match.");
			WebUI.waitUntil(primaryContact, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(primaryContact), "The primary Contact not display.");
			WebUI.assertEquals(WebUI.getTextElement(primaryContact), "Bin Dang", "The primary Contact not match.");
			WebUI.waitUntil(primaryEmail, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(primaryEmail), "The primary Email not display.");
			WebUI.assertEquals(WebUI.getTextElement(primaryEmail), "bin.dang@gmail.com", "The primary Email not match.");
		}
		
		public void verifyNoDataCustomer()
		{
			WebUI.waitUntil(nodata, WaitType.VISIBLE);
			Assert.assertTrue(WebUI.checkElementExist(nodata), "The nodata not display.");
			WebUI.assertEquals(WebUI.getTextElement(nodata), "No matching records found", "The nodata text not match.");
		}
		
		public void logOutCRM()
		{
			WebUI.clickElement(iconUser);
			WebUI.clickElement(logOutoption);
		}
		
		/*public int getActiveCustomerTotal()
		{
			WebUI.waitUntil(totalCustomers, WaitType.VISIBLE);
			String totalString = WebUI.getTextElement(totalCustomers);
			System.out.println("getCustomerTotal: " + totalString);
			return Integer.parseInt(totalString);
		}
		
		public int getInactiveCustomerTotal()
		{
			WebUI.waitUntil(totalCustomers, WaitType.VISIBLE);
			String totalString = WebUI.getTextElement(totalCustomers);
			System.out.println("getCustomerTotal: " + totalString);
			return Integer.parseInt(totalString);
		}*/
}
