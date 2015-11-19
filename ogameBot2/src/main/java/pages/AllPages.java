package pages;

import java.util.List;

import model.Constructions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.defense.DefensePage;
import pages.floats.FloatPage;
import pages.galaxy.GalaxyPage;
import pages.generalVision.GeneralVisionPage;
import pages.generalVision.MenuOptions;
import pages.hangar.HangarPage;
import pages.instalations.InstalationsPage;
import pages.investigation.InvestigationPage;
import pages.messages.MessagesPage;
import pages.resources.ResourcesPage;
import pages.resources.ResourcesSettingsPage;

public class AllPages extends SeleniumPage{

	@FindBy(className = "menubutton")
	private List<WebElement> menuButtonList;

	@FindBy(id = "resources_metal")
	private WebElement metalQuantityElement;
	@FindBy(id = "resources_crystal")
	private WebElement crystalQuantityElement;
	@FindBy(id = "resources_deuterium")
	private WebElement deuteriumQuantityElement;
	@FindBy(id = "resources_energy")
	private WebElement energyQuantityElement;

	@FindBy(className = "smallplanet")
	private List<WebElement> planetElementList;

	@FindBy(className = "new_msg_count")
	private WebElement messageQuantityElement;
	@FindBy(className = "messages")
	private WebElement messagesLink;

	public AllPages goTo(MenuOptions option) {
		boolean waitForElementInNewPage = true;
		if (option.equals(MenuOptions.RESOURCES_SETTINGS)) {
			String url = driver.getCurrentUrl().split("\\?")[0] + "\\?page=resourceSettings";
			driver.get(url);
		} else if(option.equals(MenuOptions.MESSAGES)) {
			this.clickOnButton(this.messagesLink);
		} else {
			for (WebElement optionElement : this.menuButtonList) {
				if (optionElement.getText().contains(option.getCode())) {
					this.clickOnButton(optionElement);
					break;
				}
			}
		}
		System.out.println("Gone to " + option);
		switch(option) {
		case GENERAL_VISION:
			return new GeneralVisionPage(waitForElementInNewPage);
		case RESOURCES:
			return new ResourcesPage(waitForElementInNewPage);
		case INSTALATIONS:
			return new InstalationsPage(waitForElementInNewPage);
		case INVESTIGATION:
			return new InvestigationPage(waitForElementInNewPage);
		case HANGAR:
			return new HangarPage(waitForElementInNewPage);
		case DEFENSE:
			return new DefensePage(waitForElementInNewPage);
		case FLOAT:
			return new FloatPage(waitForElementInNewPage);
		case GALAXY:
			return new GalaxyPage(waitForElementInNewPage);
		case RESOURCES_SETTINGS:
			return new ResourcesSettingsPage(waitForElementInNewPage);
		case MESSAGES:
			return new MessagesPage(waitForElementInNewPage);
		default:
			return null;
		}
	}

	public Integer getMetalQuantity() {
		return Integer.parseInt(this.metalQuantityElement.getText().replace(".", ""));
	}

	public Integer getCrystalQuantity() {
		return Integer.parseInt(this.crystalQuantityElement.getText().replace(".", ""));
	}

	public Integer getDeuteriumQuantity() {
		return Integer.parseInt(this.deuteriumQuantityElement.getText().replace(".", ""));
	}

	public Integer getEnergyQuantity() {
		return Integer.parseInt(this.energyQuantityElement.getText().replace(".", ""));
	}

	protected boolean isUpgradePossible(Constructions construction) {
		boolean flag = true;
		if (construction.getMetalRequired() - this.getMetalQuantity() > 0) {
			return false;
		}
		if (construction.getDeuteriumRequired() - this.getDeuteriumQuantity() > 0) {
			return false;
		}
		if (construction.getCrystalRequired() - this.getCrystalQuantity() > 0) {
			return false;
		}
		return flag;
	}

	public Integer getPlanetQuantity() {
		return this.planetElementList.size();
	}

	public void goToPlanetWithCoordinates(String coordinates) {
		for (WebElement planetElement : this.planetElementList) {
			if (planetElement.findElement(By.className("planet-koords")).getText().equals(coordinates)) {
				this.clickOnButton(planetElement);
				break;
			}
		}
	}

	public Integer getMessageQuantity() {
		try {
			return Integer.valueOf(this.messageQuantityElement.getText());
		} catch (Exception e) {
			return 0;
		}
	}

}
