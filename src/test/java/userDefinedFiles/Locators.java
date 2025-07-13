package userDefinedFiles;

import org.openqa.selenium.By;

public class Locators {
	//Navbar Locators
	public static final By stationary = By.xpath("//a[@class='m-menu__link m-menu__link--main'][normalize-space()='Stationery']");
	public static final By artSupplies = By.xpath("//a[@class='m-menu__link m-menu__link--main'][normalize-space()='Art Supplies']");
//	public static final By carftMaterals = By.xpath();
	public static final By journaling = By.xpath("//a[@class='m-menu__link m-menu__link--main'][normalize-space()='Journaling']");
	/*
	 * public static final By artForms = 
	 * public static final By officeStationery =
	 */
	
	public static final By packageSupplies = By.xpath("//a[@class='m-menu__link m-menu__link--main'][normalize-space()='Packaging supplies']");
//	public static final By BulkOrder = 
	
	public static final By searchButton = By.className("m-search-form__button");
	public static final By accountButton = By.className("m-header__account");
	public static final By wishListButton = By.className("m-header__wishlist");
	public static final By cartButton = By.xpath("//div[@class='m-header__right']//a[@aria-label='0']//span[@class='m-tooltip m:block m-tooltip--bottom m-tooltip--style-2']//*[name()='svg']//*[name()='path' and contains(@d,'M352 128C3')]");
	
	//Account Page Locators
	
	public static final By newCustomerScoll = By.className("m-sign-up"); //Scroll to New Customer section
	public static final By registeration = By.xpath("/html[1]/body[1]/main[1]/div[2]/div[1]/div[3]/a[1]"); //Registeration button on Account Page
	
	//Account Page Login Locators
	public static final By loginEmail = By.name("customer[email]");
	public static final By loginPassword = By.name("customer[password]");
	public static final By signIn = By.xpath("//*[@id=\"customer_login\"]/button");
	
	//Account/Registeration Locators
	public static final By firstName = By.name("customer[first_name]");
	public static final By lastName = By.name("customer[last_name]");
	public static final By email = By.name("customer[email]");
	public static final By password = By.name("customer[password]");
	
	public static final By registerButton_account = By.xpath("//button[normalize-space()='Register']");
	public static final By registerScroll = By.xpath("//div[@class='m-register-form__wrapper']");
	
	//Registration Fail Locators
	public static final By accountExist = By.xpath("/html[1]/body[1]/main[1]/div[2]/div[1]/div[1]/form[1]/div[1]");
	public static final By loginSuccess = By.xpath("/html[1]/body[1]/main[1]/div[2]/div[1]/div[2]/div[2]/div[1]");
	public static final By loginFailed = By.xpath("/html[1]/body[1]/main[1]/div[2]/div[1]/div[2]/form[1]/div[1]");
	public static final By alreadyLoggedIn = By.xpath("/html/body");
}
