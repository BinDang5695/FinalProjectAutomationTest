package dataproviders;

import org.testng.annotations.DataProvider;

import helpers.ExcelHelper;
import helpers.SystemHelper;

public class DataProviderFactory {
	
    @DataProvider(name = "data_provider_login_unsuccess_invalid_email")
    public Object[][] dataProviderLoginUnsuccessWithEmailInvalid() {
        return new Object[][]{
                {"admin123@example.com", "123456"}
        };
    }
    
    @DataProvider(name = "data_provider_login_unsuccess_invalid_password")
    public Object[][] dataProviderLoginUnsuccessWithPasswordInvalid() {
        return new Object[][]{
                {"admin@example.com", "123456789"}
        };
    }
	
    @DataProvider(name = "data_provider_login_success")
    public Object[][] dataProviderLoginSuccess() {
        return new Object[][]{
                {"admin@example.com", "123456"}
        };
    }
}
