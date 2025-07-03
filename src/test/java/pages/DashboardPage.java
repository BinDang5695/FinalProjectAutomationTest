package pages;
import org.openqa.selenium.By;
import org.testng.Assert;
import keywords.WebUI;

	public class DashboardPage extends BasePage {

		// Khai báo các element dạng đối tượng By (phương thức tìm kiếm)
		private By invoicesAwaitingPayment = By.xpath("//span[normalize-space()='Invoices Awaiting Payment']");
	    private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");
		
		public void verifyInvoicesAwaitingPaymentTotal(String total)
		{
			Assert.assertTrue(WebUI.isElementDisplayed(invoicesAwaitingPayment), "The Invoices Awaiting Payment total label not display");
			WebUI.assertEquals(WebUI.getTextElement(totalInvoicesAwaitingPayment), total, "The Invoices Awaiting Payment total not match");
		}
	}
