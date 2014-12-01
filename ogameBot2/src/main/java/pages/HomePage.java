package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pages.generalVision.GeneralVisionPage;
import parameters.Parameters;

public class HomePage extends AllPages {
	
	@FindBy(id = "loginBtn")
	private WebElement loginButton;
	@FindBy(id = "serverLogin")
	private WebElement universeElement;
	@FindBy(id = "usernameLogin")
	private WebElement usernameField;
	@FindBy(id = "passwordLogin")
	private WebElement passwordField;
	@FindBy(id = "loginSubmit")
	private WebElement submitButton;
	
	Parameters parameters = new Parameters();
	
	public GeneralVisionPage login() {
		this.openMenu();
		this.setUniverse();
		this.setUsername();
		this.setPassword();
		this.submit();
		System.out.println("Logged in");
		return new GeneralVisionPage(true);
	}
	
	private void openMenu() {
		this.waitUntilElementExistsInPage(By.id("loginBtn"), TimeUnit.SECONDS.toMillis(15));
		if (this.loginButton.getText().contains("Entrar")) {
		    loginButton.click();
		}
	}
	
	private void setUniverse() {
		this.waitUntilElementExistsInPage(By.id("serverLogin"), TimeUnit.SECONDS.toMillis(15));
		Select universeSelect = new Select(universeElement);
		universeSelect.selectByValue(parameters.getUniverseValue());
	}
	
	private void setUsername() {
		this.usernameField.clear();
		this.usernameField.sendKeys(parameters.getUsername());
	}
	
	private void setPassword() {
		this.passwordField.clear();
		this.passwordField.sendKeys(parameters.getPassword());
	}
	
	private void submit() {
		this.submitButton.click();
	}

}
