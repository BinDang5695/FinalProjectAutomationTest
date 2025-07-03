package common;


public class LocatorsCRM {

	// Login Page
	public static String headerLogin = "//h1[normalize-space()='Login']";
	public static String inputEmail = "//input[@id='email']";
	public static String inputPassword = "//input[@id='password']";
	public static String buttonLogin = "//button[normalize-space()='Login']";
	public static String checkboxRememberme = "//input[@id='remember']";
	public static String linkForgotPassword = "//a[normalize-space()='Forgot Password?']";
	public static String alertErrorMessage = "//p[normalize-space()='The Email Address field is required.']";
	
	// Dashboard Page
	public static String menudashboard = "//span[normalize-space()='Dashboard']";
	public static String dashboardoption = "//div[@class='screen-options-btn']";
	public static String mytodoitems = "//span[normalize-space()='My To Do Items']";
	public static String estimateoverview = "//span[normalize-space()='Estimate overview']";
	
	// Sales Page
	public static String sales = "//span[@class='menu-text'][normalize-space()='Sales']";
	public static String proposals = "//span[normalize-space()='Proposals']";
	public static String newproposal = "//a[normalize-space()='New Proposal']";
	public static String hours = "//input[@id='2']";
	
	// Customers Page
	public static String customers = "//span[normalize-space()='Customers']";
	public static String headerpage = "//span[normalize-space()='Customers Summary']";
	public static String newcustomer = "//a[normalize-space()='New Customer']";
	public static String company = "//input[@id='company']";
	public static String vat = "//input[@id='vat']";
	public static String phone = "//input[@id='phonenumber']";
	public static String website = "//input[@id='website']";
	public static String groups1 = "//button[@title='Testing']";
	public static String groups = "//div[@class='dropdown bootstrap-select show-tick input-group-btn _select_input_group bs3']";
	public static String search1 = "//div[@class='dropdown bootstrap-select show-tick input-group-btn _select_input_group bs3 dropup open']//input[@aria-label='Search']";
	public static String testing = "//span[normalize-space()='Testing']";
	public static String divtesting = "//div[contains(text(),'Testing')]";
	public static String currency = "//button[@class='btn dropdown-toggle btn-default bs-placeholder']//div[@class='filter-option-inner-inner'][normalize-space()='System Default']";
	public static String eur = "//a[@id='bs-select-2-2']//span[@class='text']";
	public static String eur2 = "//button[@data-id='default_currency']//div[@class='filter-option-inner-inner']";
	public static String language = "//div[contains(text(),'System Default')]";
	public static String language2 = "//div[contains(text(),'Vietnamese')]";
	public static String vn = "//span[normalize-space()='Vietnamese']";
	public static String vndiv = "//div[contains(text(),'Vietnamese')]";
	public static String defaultlanguage  = "//div[contains(text(),'System Default')]";
	public static String address  = "//textarea[@id='address']";
	public static String city  = "//input[@id='city']";
	public static String state  = "//input[@id='state']";
	public static String zip  = "//input[@id='zip']";
	public static String country  = "//div[@app-field-wrapper='country']//div[@class='filter-option-inner-inner'][normalize-space()='Nothing selected']";
	public static String country2  = "//div[@class='filter-option-inner-inner'][normalize-space()='Vietnam']";
	public static String search2  = "//div[@class='dropdown bootstrap-select bs3 dropup open']//input[@aria-label='Search']";
	public static String vn2 = "//span[normalize-space()='Vietnam']";
	public static String save  = "//button[@class='btn btn-primary only-save customer-form-submiter']";
	public static String search3  = "//input[@class='form-control input-sm']";
	public static String bindz  = "//a[normalize-space()='Bindz']";
	public static String droptoggle  = "//a[@class='dropdown-toggle btn-link']";
	public static String delete  = "//a[normalize-space()='Delete']";

	// Tasks Page
	public static String taskspage = "//span[normalize-space()='Tasks']";
	public static String kanban = "//i[@class='fa-solid fa-grip-vertical']";

	
	//Icon Profile
	public static String iconprofile = "//li[@class='icon header-user-profile']";
	
	//Projects Page
	public static String menuProjects = "//span[normalize-space()='Projects']";

}
