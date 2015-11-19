package pages.hangar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import model.Constructions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AllPages;
import pages.generalVision.GeneralVisionPage;
import pages.generalVision.MenuOptions;

public class HangarPage extends AllPages {
	
	public HangarPage(Boolean flag) {
		if (flag) {
			this.waitUntilElementExistsInPage(By.id("details204"), TimeUnit.SECONDS.toMillis(10));
		}
	}
	
	private static final Logger LOGGER = Logger.getLogger(HangarPage.class.getName());

	@FindBy(id = "details204")
	private WebElement littleHunterElement;
	@FindBy(id = "details205")
	private WebElement heavyHunterElement;
	@FindBy(id = "details206")
	private WebElement cruiseElement;
	@FindBy(id = "details207")
	private WebElement battleShipElement;
	@FindBy(id = "details202")
	private WebElement littleCargoShipElement;
	@FindBy(id = "details203")
	private WebElement bigCargoShipElement;
	@FindBy(id = "details208")
	private WebElement colonyShipElement;
	@FindBy(id = "details215")
	private WebElement shieldShipElement;
	@FindBy(id = "details211")
	private WebElement bomberElement;
	@FindBy(id = "details213")
	private WebElement destructorElement;
	@FindBy(id = "details214")
	private WebElement deathStarElement;
	@FindBy(id = "details209")
	private WebElement recyclerElement;
	@FindBy(id = "details210")
	private WebElement espionageProbeElement;
	@FindBy(id = "details212")
	private WebElement solarSateliteElement;
	
	@FindBy(id = "content")
	private WebElement hangarContentElement;
	@FindBy(id = "number")
	private WebElement quantityElement;
	
public static HashMap<String, Constructions> constructionHashMap = new HashMap<String, Constructions>();
	
	private void setBuildName(Constructions construction, String buildName) {
		LOGGER.info("Build name: " + buildName);
		construction.setBuildName(buildName);
	}
	
	private void setMetalRequired(Constructions construction, Integer metalRequired) {
		if (metalRequired != null) {
			LOGGER.info("Metal required: " + metalRequired);
			construction.setMetalRequired(metalRequired);
		} else {
			construction.setMetalRequired(0);
		}
	}
	
	private void setCrystalRequired(Constructions construction, Integer crystalRequired) {
		if (crystalRequired != null) {
			LOGGER.info("Crystal required: " + crystalRequired);
			construction.setCrystalRequired(crystalRequired);
		} else {
			construction.setCrystalRequired(0);
		}
	}
	
	private void setDeuteriumRequired(Constructions construction, Integer deuteriumRequired) {
		if (deuteriumRequired != null) {
			LOGGER.info("Deuterium required: " + deuteriumRequired);
			construction.setDeuteriumRequired(deuteriumRequired);
		} else {
			construction.setDeuteriumRequired(0);
		}
	}
	
	private void setLevel(Constructions construction, Integer level) {
		if (level != null) {
			LOGGER.info("Level: " + level);
			construction.setLevel(level);
		} else {
			construction.setLevel(0);
		}
	}
	
	private Integer getElementRequired(String element) {
		if (this.webElementIsDisplayedInElement(this.hangarContentElement, By.className(element))) {
			return Integer.parseInt(this.hangarContentElement.findElement(By.className(element)).getText().replace(".", "").replace("M", "000000"));
		} else {
			return null;
		}
	}
	
	private Integer getLevel() {
		if (this.webElementIsDisplayedInElement(this.hangarContentElement, By.className("level"))) {
			return Integer.parseInt(this.hangarContentElement.findElement(By.className("level")).getText().replace("Nivel", "").replace("Número:", "").trim());
		} else {
			return null;
		}
	}
	
	private List<WebElement> generateHangarElementList() {
		List<WebElement> hangarElementList = new ArrayList<WebElement>();
		hangarElementList.add(this.battleShipElement);
		hangarElementList.add(this.bigCargoShipElement);
		hangarElementList.add(this.bomberElement);
		hangarElementList.add(this.colonyShipElement);
		hangarElementList.add(this.cruiseElement);
		hangarElementList.add(this.deathStarElement);
		hangarElementList.add(this.destructorElement);
		hangarElementList.add(this.espionageProbeElement);
		hangarElementList.add(this.heavyHunterElement);
		hangarElementList.add(this.littleCargoShipElement);
		hangarElementList.add(this.littleHunterElement);
		hangarElementList.add(this.recyclerElement);
		hangarElementList.add(this.shieldShipElement);
		hangarElementList.add(this.solarSateliteElement);
		return hangarElementList;
	}
	
	private void setHangarAttributes(WebElement hangarElement, Integer orderInElementList) {
		do{ 
			this.clickOnButton(hangarElement);
			this.waitUntilElementExistsInElement(this.hangarContentElement, By.tagName("h2"), TimeUnit.SECONDS.toMillis(15));
		} while(!hangarElement.getAttribute("class").contains("active")); 
		Constructions construction = new Constructions();
		this.setBuildName(construction, this.hangarContentElement.findElement(By.tagName("h2")).getText());
		this.setMetalRequired(construction, this.getElementRequired("metal"));
		this.setCrystalRequired(construction, this.getElementRequired("crystal"));
		this.setDeuteriumRequired(construction, this.getElementRequired("deuterium"));
		this.setLevel(construction, this.getLevel());
		construction.setOrderInElementList(orderInElementList);
		System.out.println(construction.getBuildName() + " set");
		HangarPage.constructionHashMap.put(this.getBuildingName(), construction);
	}
	
	private String getBuildingName() {
		if (this.webElementIsDisplayedInElement(this.hangarContentElement, By.tagName("h2"))) {
			return this.hangarContentElement.findElement(By.tagName("h2")).getText().trim();
		} else {
			return null;
		}
	}
	
	public void setBuildingAttributesMap() {
		List<WebElement> buildingElementList = this.generateHangarElementList();
		Integer orderInElementList = 0;
		for (WebElement buildingElement : buildingElementList) {
			this.setHangarAttributes(buildingElement, orderInElementList);
			orderInElementList += 1;
		}
	}
	
	public GeneralVisionPage constructIfPossible(Constructions construction, Integer howMany) {
		// Quantity missing
		if (this.isUpgradePossible(construction)) {
			this.goTo(MenuOptions.HANGAR);
			this.generateHangarElementList().get(construction.getOrderInElementList()).click();
			this.waitUntilElementExistsInElement(this.hangarContentElement, By.className("build-it"), TimeUnit.SECONDS.toMillis(10));
			this.quantityElement.sendKeys(howMany.toString());
			this.hangarContentElement.findElement(By.className("build-it")).click();
			LOGGER.info("Constructing " + howMany + " " + construction.getBuildName());
			this.goTo(MenuOptions.GENERAL_VISION);
		}
		return new GeneralVisionPage(true);
	}
}
