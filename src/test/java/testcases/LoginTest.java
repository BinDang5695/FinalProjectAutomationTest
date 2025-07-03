package testcases;

import common.BaseTest;
import dataproviders.DataProviderFactory;
import helpers.CaptureHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listeners.TestListener;
import pages.LoginPage;
import utils.LogUtils;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Hashtable;
	
	@Listeners(TestListener.class)
	public class LoginTest extends BaseTest{
		
			LoginPage loginPage;
			
			@Epic("Regression")
			@Feature("DMS")
			@Story("Login")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.NORMAL)
			@Link("https://anhtester.com/dms/login/123")
			@Issue("https://jira.com/anhtester/dms/issue")
			@Test(priority = 0, dataProvider = "data_provider_login_unsuccess_invalid_email", dataProviderClass = DataProviderFactory.class)
			public void loginFailWithEmailInvalid(String email, String password)
			{
				loginPage = new LoginPage();
				loginPage.loginCRM(email, password);
				loginPage.verifyLoginFail("Invalid email or password");
			}
			
			@Epic("Regression")
			@Feature("DMS")
			@Story("Login")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.NORMAL)
			@Link("https://anhtester.com/dms/login/124")
			@Issue("https://jira.com/anhtester/dms/issue")
			@Test(priority = 1, dataProvider = "data_provider_login_unsuccess_invalid_password", dataProviderClass = DataProviderFactory.class)
			public void loginFailWithPasswordInvalid(String email, String password)
			{
				loginPage = new LoginPage();
				loginPage.loginCRM(email, password);
				loginPage.verifyLoginFail("Invalid email or password");
			}
			
			@Epic("Regression")
			@Feature("DMS")
			@Story("Login")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.NORMAL)
			@Link("https://anhtester.com/dms/login/125")
			@Issue("https://jira.com/anhtester/dms/issue")
			@Test(priority = 2, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
			public void loginSuccess(String email, String password) throws Exception
			{
				loginPage = new LoginPage();
				loginPage.loginCRM(email, password);
				loginPage.verifyLoginSuccess();
			}
}