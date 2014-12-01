package pages.instalations;

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

public class InstalationsPage extends AllPages {
	
	public InstalationsPage(Boolean flag) {
		if (flag) {
			this.waitUntilElementExistsInPage(By.id("details14"), TimeUnit.SECONDS.toMillis(10));
		}
	}
	
	private static final Logger LOGGER = Logger.getLogger(InstalationsPage.class.getName());
	
	@FindBy(id = "details14")
	private WebElement robotFactoryElement;
	@FindBy(id = "details21")
	private WebElement hangarElement;
	@FindBy(id = "details31")
	private WebElement investigationLaboratoryElement;
	@FindBy(id = "details34")
	private WebElement allianceDepositElement;
	@FindBy(id = "details44")
	private WebElement siloElement;
	@FindBy(id = "details15")
	private WebElement nanobotsFactoryElement;
	@FindBy(id = "details33")
	private WebElement terraformerElement;
	
	@FindBy(id = "content")
	private WebElement buildingContent;
	
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
		if (this.webElementIsDisplayedInElement(this.buildingContent, By.className(element))) {
			return Integer.parseInt(this.buildingContent.findElement(By.className(element)).getText().replace(".", "").replace("M", "000000"));
		} else {
			return null;
		}
	}
	
	private Integer getLevel() {
		if (this.webElementIsDisplayedInElement(this.buildingContent, By.className("level"))) {
			return Integer.parseInt(this.buildingContent.findElement(By.className("level")).getText().replace("Nivel", "").replace("Número:", "").trim());
		} else {
			return null;
		}
	}
	
	private String getBuildingName() {
		if (this.webElementIsDisplayedInElement(this.buildingContent, By.tagName("h2"))) {
			return this.buildingContent.findElement(By.tagName("h2")).getText().trim();
		} else {
			return null;
		}
	}
	
	private List<WebElement> generateBuildingList() {
		List<WebElement> buildingList = new ArrayList<WebElement>();
		buildingList.add(this.robotFactoryElement);
		buildingList.add(this.hangarElement);
		buildingList.add(this.investigationLaboratoryElement);
		buildingList.add(this.allianceDepositElement);
		buildingList.add(this.siloElement);
		buildingList.add(this.nanobotsFactoryElement);
		buildingList.add(this.terraformerElement);
		return buildingList;
	}
	
	private void setBuildingAttributes(WebElement buildingElement, Integer orderInElementList) {
		do{
			buildingElement.click();
			this.waitUntilElementExistsInElement(this.buildingContent, By.tagName("h2"), TimeUnit.SECONDS.toMillis(15));
		} while(!this.webElementIsDisplayedInElement(this.buildingContent, By.tagName("h2"))); 
		Constructions construction = new Constructions();
		this.setBuildName(construction, this.buildingContent.findElement(By.tagName("h2")).getText());
//		construction.setBuildName(this.buildingContent.findElement(By.tagName("h2")).getText());
		this.setMetalRequired(construction, this.getElementRequired("metal"));
		this.setCrystalRequired(construction, this.getElementRequired("crystal"));
		this.setDeuteriumRequired(construction, this.getElementRequired("deuterium"));
		this.setLevel(construction, this.getLevel());
		construction.setOrderInElementList(orderInElementList);
		System.out.println(construction.getBuildName() + " set");
		InstalationsPage.constructionHashMap.put(this.getBuildingName(), construction);
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
			this.goTo(MenuOptions.INSTALATIONS);
			this.generateBuildingList().get(construction.getOrderInElementList()).click();
			this.waitUntilElementExistsInElement(this.buildingContent, By.className("build-it"), TimeUnit.SECONDS.toMillis(10));
			this.buildingContent.findElement(By.className("build-it")).click();
			LOGGER.info("Upgrading " + construction.getBuildName());
			this.goTo(MenuOptions.GENERAL_VISION);
		}
		return new GeneralVisionPage(true);
	}

}
