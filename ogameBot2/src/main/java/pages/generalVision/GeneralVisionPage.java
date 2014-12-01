package pages.generalVision;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AllPages;
import pages.defense.DefensePage;
import pages.floats.FloatPage;
import pages.galaxy.GalaxyPage;
import pages.hangar.HangarPage;
import pages.instalations.InstalationsPage;
import pages.investigation.InvestigationPage;
import pages.resources.ResourcesPage;

public class GeneralVisionPage extends AllPages {
	
	public GeneralVisionPage(Boolean flag) {
		if (flag) {
			this.waitUntilElementExistsInPage(By.className("menubutton"), TimeUnit.SECONDS.toMillis(15));
		}
	}
	
	@FindBy(className = "content-box-s")
	private List<WebElement> constructionStatusElementList;
	
	@FindBy(id = "diameterContentField")
	private WebElement planetAreaElement;
	
	
	
	
	private WebElement getBuildingStatus() {
		return constructionStatusElementList.get(0);
	}
	
	private WebElement getInvestigationStatus() {
		return constructionStatusElementList.get(1);
	}
	
	private WebElement getShipStatus() {
		return constructionStatusElementList.get(2);
	}
	
	public boolean buildingUnderConstruction() {
		return !this.getBuildingStatus().getText().contains("No hay edificios en construcción.");
	}
	
	public boolean investigationInProcess() {
		return !this.getInvestigationStatus().getText().contains("No hay ninguna investigación en progreso.");
	}
	
	public boolean shipsUnderConstruction() {
		return !this.getShipStatus().getText().contains("No se está construyendo ninguna nave o defensa en este momento.");
	}
	
	public Integer getPlanetSpaceOcuppied() {
	    return Integer.valueOf(this.planetAreaElement.findElements(By.tagName("span")).get(0).getText());
	}
	
	public Integer getPlanetSpaceTotal() {
	    return Integer.valueOf(this.planetAreaElement.findElements(By.tagName("span")).get(1).getText());
	}
	
}
