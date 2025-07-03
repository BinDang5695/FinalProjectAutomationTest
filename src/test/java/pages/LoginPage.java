package pages;

import driver.DriverManager;
import helpers.PropertiesHelper;
import keywords.WebUI;
import pages.DashboardPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class LoginPage {

	// Khai báo các element dạng đối tượng By (phương thức tìm kiếm)
	private By headerPage = By.xpath("//h1[normalize-space()='Login']");
	private By inputEmail = By.xpath("//input[@id='email']");
	private By inputPassword = By.xpath("//input[@id='password']");
	private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
	private By errorMessage = By.xpath("//div[contains(@class, 'alert-danger')]");
	private By errorMessage1 = By.xpath("(//div[contains(@class, 'alert-danger')])[1]");
	private By errorMessage2 = By.xpath("(//div[contains(@class, 'alert-danger')])[2]");

	public void setEmail(String email) {
		//driver.findElement(inputEmail).sendKeys(email);
		WebUI.setText(inputEmail, email);
	}

	public void setPassword(String password) {
		//driver.findElement(inputPassword).sendKeys(password);
		WebUI.setText(inputPassword, password);
	}

	public void clickLoginButton() {
		//driver.findElement(buttonLogin).click();
		WebUI.clickElement(buttonLogin);
	}

	public void verifyLoginSuccess(){
		WebUI.sleep(1);
		WebUI.assertNotContains(WebUI.getCurrentUrl(), "authentication", "FAIL Because Still in the Login page:");
	}

	public void verifyLoginFail() {
		WebUI.assertContains(WebUI.getCurrentUrl(), "authentication", "FAIL Because Still in the Login page:");
		Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Error message NOT displays");
		WebUI.assertEquals(WebUI.getTextElement(errorMessage), "Invalid email or password", "Content of error massage NOT match.");
	}

	public void verifyLoginFail(String message) {
		WebUI.assertContains(WebUI.getCurrentUrl(), "authentication", "FAIL Because Still in the Login page:");
		Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Error message NOT displays");
		WebUI.assertEquals(WebUI.getTextElement(errorMessage), message, "Content of error massage NOT match.");
	}

	public void verifyLoginFailWithNullFields() {
		WebUI.assertContains(WebUI.getCurrentUrl(), "authentication", "FAIL Because Still in the Login page:");
		Assert.assertTrue(WebUI.isElementDisplayed(errorMessage1), "Error message 1 NOT displays");
		Assert.assertTrue(WebUI.isElementDisplayed(errorMessage2), "Error message 2 NOT displays");

		Assert.assertEquals(WebUI.getTextElement(errorMessage1), "The Password field is required.", "Content of error massage 1 NOT match.");
		Assert.assertEquals(WebUI.getTextElement(errorMessage2), "The Email Address field is required.", "Content of error massage 2 NOT match.");
	}

	public void verifyLoginFailWithNullFields_ArrayList(int totalNullFields) {

		WebUI.assertContains(WebUI.getCurrentUrl(), "authentication", "FAIL Because Still in the Login page:");

		List<String> messageString = new ArrayList<>();
		messageString.add("The Password field is required.");
		messageString.add("The Email Address field is required.");

		boolean check = false;

		for (int i = 1; i <= totalNullFields; i++) {
			Assert.assertTrue(
					DriverManager.getDriver().findElement(By.xpath("(//div[contains(@class, 'alert-danger')])[" + i + "]")).isDisplayed(),
					"Error message " + i + " NOT displays");

			for (int j = 0; j < messageString.size(); j++) {
				if (WebUI.getTextElement(By.xpath("(//div[contains(@class, 'alert-danger')])[" + i + "]"))
						.equals(messageString.get(j))) {
					check = true;
					break;
				}
			}
			Assert.assertTrue(check, "The content of error message " + i + " not match.");
		}

	}

	// Các hàm xử lý cho chính trang này
	public void loginCRM(String email, String password) {
		
		WebUI.openURL(PropertiesHelper.getValue("URL"));
		WebUI.waitForPageLoaded();
		WebUI.setText(inputEmail, email);
		WebUI.setText(inputPassword, password);
		WebUI.clickElement(buttonLogin);
		WebUI.waitForPageLoaded();
	}
	
	public DashboardPage loginCRM(){
		
		WebUI.openURL(PropertiesHelper.getValue("URL"));
		WebUI.waitForPageLoaded();
		WebUI.clearText(inputEmail);
		WebUI.clearText(inputPassword);
		WebUI.setText(inputEmail, "admin@example.com");
		WebUI.setText(inputPassword, "123456");
		WebUI.clickElement(buttonLogin);
		WebUI.waitForPageLoaded();
		verifyLoginSuccess();
		return new DashboardPage();
	}
}