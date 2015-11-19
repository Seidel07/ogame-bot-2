package pages.resources;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import model.Production;
import pages.AllPages;
import pages.generalVision.MenuOptions;

public class ResourcesSettingsPage extends AllPages{

	public ResourcesSettingsPage(Boolean flag) {
		if (flag) {
			this.waitUntilElementExistsInPage(By.id("inhalt"),TimeUnit.SECONDS.toMillis(10));
		}
	}

	@FindBy(className = "summary")
	private WebElement hourlyProduction;

	public Production setProduction() {
		this.goTo(MenuOptions.RESOURCES_SETTINGS);
		List<WebElement> productionElementList = this.hourlyProduction.findElements(By.className("tooltipCustom"));
		Integer metalProduction = Integer.valueOf(productionElementList.get(0).getText().replace(".", ""));
		Integer crystalProduction = Integer.valueOf(productionElementList.get(1).getText().replace(".", ""));
		Integer deuteriumProduction = Integer.valueOf(productionElementList.get(2).getText().replace(".", ""));
		this.goTo(MenuOptions.GENERAL_VISION);
		return new Production(metalProduction, crystalProduction, deuteriumProduction);
	}



}
