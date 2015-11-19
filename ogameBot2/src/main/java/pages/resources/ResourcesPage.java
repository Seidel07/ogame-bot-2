package pages.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import model.Constructions;
import model.Production;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AllPages;
import pages.generalVision.GeneralVisionPage;
import pages.generalVision.MenuOptions;

public class ResourcesPage extends AllPages {

	public ResourcesPage(Boolean flag) {
		if (flag) {
			this.waitUntilElementExistsInPage(By.id("button1"),TimeUnit.SECONDS.toMillis(10));
		}
	}

	private static final Logger LOGGER = Logger.getLogger(ResourcesPage.class
			.getName());

	@FindBy(id = "button1")
	private WebElement metalMineElement;
	@FindBy(id = "button2")
	private WebElement crystalMineElement;
	@FindBy(id = "button3")
	private WebElement deuteriumSynthesizerElement;
	@FindBy(id = "button4")
	private WebElement solarPlantElement;
	@FindBy(id = "button5")
	private WebElement fusionPlantElement;
	@FindBy(id = "button6")
	private WebElement solarSatelite;
	@FindBy(id = "button7")
	private WebElement metalWarehouse;
	@FindBy(id = "button8")
	private WebElement crystalWarehouse;
	@FindBy(id = "button9")
	private WebElement deuteriumWarehouse;

	@FindBy(id = "content")
	private WebElement buildingContentElement;
	@FindBy(id = "maxresources")
	private WebElement warehousesMaxCapacityElement;

	@FindBy(id = "resourceSettings")
	private WebElement resourceSettingsTableElement;

	public static HashMap<String, Constructions> constructionHashMap = new HashMap<String, Constructions>();

	private void setBuildName(Constructions construction, String buildName) {
		LOGGER.info("Build name: " + buildName);
		construction.setBuildName(buildName);
	}

	private void setMetalRequired(Constructions construction,
			Integer metalRequired) {
		if (metalRequired != null) {
			LOGGER.info("Metal required: " + metalRequired);
			construction.setMetalRequired(metalRequired);
		} else {
			construction.setMetalRequired(0);
		}
	}

	private void setCrystalRequired(Constructions construction,
			Integer crystalRequired) {
		if (crystalRequired != null) {
			LOGGER.info("Crystal required: " + crystalRequired);
			construction.setCrystalRequired(crystalRequired);
		} else {
			construction.setCrystalRequired(0);
		}
	}

	private void setDeuteriumRequired(Constructions construction,
			Integer deuteriumRequired) {
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
		if (this.webElementIsDisplayedInElement(this.buildingContentElement,By.className(element))) {
			return Integer.parseInt(this.buildingContentElement.findElement(By.className(element)).getText().replace(".", "").replace("M", "000000"));
		} else {
			return null;
		}
	}

	private Integer getLevel() {
		if (this.webElementIsDisplayedInElement(this.buildingContentElement,
				By.className("level"))) {
			return Integer.parseInt(this.buildingContentElement
					.findElement(By.className("level")).getText()
					.replace("Nivel", "").replace("Número:", "").trim());
		} else {
			return null;
		}
	}

	private List<WebElement> generateBuildingList() {
		List<WebElement> buildingList = new ArrayList<WebElement>();
		buildingList.add(this.metalMineElement);
		buildingList.add(this.crystalMineElement);
		buildingList.add(this.deuteriumSynthesizerElement);
		buildingList.add(this.solarPlantElement);
		buildingList.add(this.fusionPlantElement);
		buildingList.add(this.solarSatelite);
		buildingList.add(this.metalWarehouse);
		buildingList.add(this.crystalWarehouse);
		buildingList.add(this.deuteriumWarehouse);
		return buildingList;
	}

	private void setBuildingAttributes(WebElement buildingElement,
			Integer orderInElementList) {
		try {
			do {
				this.clickOnButton(buildingElement);
				this.waitUntilElementExistsInElement(this.buildingContentElement, By.tagName("h2"),TimeUnit.SECONDS.toMillis(15));
			} while (!this.webElementIsDisplayedInElement(this.buildingContentElement, By.tagName("h2")));
			Constructions construction = new Constructions();
			this.setBuildName(construction, this.buildingContentElement.findElement(By.tagName("h2")).getText());
			this.setMetalRequired(construction,this.getElementRequired("metal"));
			this.setCrystalRequired(construction,this.getElementRequired("crystal"));
			this.setDeuteriumRequired(construction,this.getElementRequired("deuterium"));
			this.setLevel(construction, this.getLevel());
			construction.setOrderInElementList(orderInElementList);
			System.out.println(construction.getBuildName() + " set");
			ResourcesPage.constructionHashMap.put(this.getBuildingName(),construction);
		} catch (Exception e) {
			this.setBuildingAttributes(buildingElement, orderInElementList);
		}
	}

	private String getBuildingName() {
		if (this.webElementIsDisplayedInElement(this.buildingContentElement,By.tagName("h2"))) {
			return this.buildingContentElement.findElement(By.tagName("h2")).getText().trim();
		} else {
			return null;
		}
	}

	public void setBuildingAttributesMap() {
		List<WebElement> buildingElementList = this.generateBuildingList();
		Integer orderInElementList = 0;
		for (WebElement buildingElement : buildingElementList) {
			this.setBuildingAttributes(buildingElement, orderInElementList);
			orderInElementList += 1;
		}
	}

	public GeneralVisionPage upgradeIfPossible(Constructions construction) {
		if (this.isUpgradePossible(construction)) {
			this.goTo(MenuOptions.RESOURCES);
			this.generateBuildingList()
			.get(construction.getOrderInElementList()).click();
			this.waitUntilElementExistsInElement(this.buildingContentElement,
					By.className("build-it"), TimeUnit.SECONDS.toMillis(10));
			this.buildingContentElement.findElement(By.className("build-it"))
			.click();
			LOGGER.info("Upgrading " + construction.getBuildName());
			this.goTo(MenuOptions.GENERAL_VISION);
		}
		return new GeneralVisionPage(true);
	}

	public Integer getResourceCapacity(ResourcesBuildings building) {
		Constructions construction = ResourcesPage.constructionHashMap.get(building.getCode());
		this.goTo(MenuOptions.RESOURCES);
		this.generateBuildingList().get(construction.getOrderInElementList()).click();
		this.waitUntilElementExistsInPage(By.id("content"),TimeUnit.SECONDS.toMillis(10));
		Integer resourceCapacity = Integer.valueOf(this.warehousesMaxCapacityElement.getText().replace(".", ""));
		this.goTo(MenuOptions.GENERAL_VISION);
		return resourceCapacity;
	}

	public Production getProduction() {
		this.goTo(MenuOptions.RESOURCES_SETTINGS);
		Integer metalProduction = this.setProductionForElement(ResourcesBuildings.METAL_MINE) + 60;
		Integer crystalProduction = this.setProductionForElement(ResourcesBuildings.CRYSTAL_MINE) + 30;
		Integer deuteriumProduction = this.setProductionForElement(ResourcesBuildings.DEUTERIUM_SYNTHESIZER);
		this.goTo(MenuOptions.GENERAL_VISION);
		return new Production(metalProduction, crystalProduction,deuteriumProduction);
	}

	private Integer setProductionForElement(ResourcesBuildings building) {
		List<WebElement> productionElementList = this.resourceSettingsTableElement.findElements(By.tagName("tr"));
		for (WebElement productionElement : productionElementList) {
			if (productionElement.getText().contains(building.getCode())) {
				List<WebElement> actualProductionElementList = productionElement.findElements(By.className("undermark"));
				for (WebElement actualProductionElement : actualProductionElementList) {
					if (!actualProductionElement.getText().contains("/")) {
						return Integer.valueOf(actualProductionElement.getText().replace(".", ""));
					}
				}

			}
		}
		return null;
	}

}
