package pages.defense;

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
import pages.resources.ResourcesPage;

public class DefensePage extends AllPages {
	
	public DefensePage(Boolean flag) {
		if (flag) {
			this.waitUntilElementExistsInPage(By.id("details401"), TimeUnit.SECONDS.toMillis(10));
		}
	}
	
	private static final Logger LOGGER = Logger.getLogger(DefensePage.class.getName());
	
	@FindBy(id = "details401")
	private WebElement missileLauncherElement;
	@FindBy(id = "details402")
	private WebElement smallLaserElement;
	@FindBy(id = "details403")
	private WebElement bigLaserElement;
	@FindBy(id = "details404")
	private WebElement gaussCannonElement;
	@FindBy(id = "details405")
	private WebElement ionicCannonElement;
	@FindBy(id = "details406")
	private WebElement plasmaCannonElement;
	@FindBy(id = "details407")
	private WebElement smallDefenseDomeElement;
	@FindBy(id = "details408")
	private WebElement bigDefenseDomeElement;
	@FindBy(id = "details502")
	private WebElement interceptionMissileElement;
	@FindBy(id = "details503")
	private WebElement interplanetaryMissileElement;
	
	@FindBy(id = "content")
	private WebElement defenseContentElement;
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
		if (this.webElementIsDisplayedInElement(this.defenseContentElement, By.className(element))) {
			return Integer.parseInt(this.defenseContentElement.findElement(By.className(element)).getText().replace(".", "").replace("M", "000000"));
		} else {
			return null;
		}
	}
	
	private Integer getLevel() {
		if (this.webElementIsDisplayedInElement(this.defenseContentElement, By.className("level"))) {
			return Integer.parseInt(this.defenseContentElement.findElement(By.className("level")).getText().replace("Nivel", "").replace("Número:", "").trim());
		} else {
			return null;
		}
	}
	
	private List<WebElement> generateDefenseElementList() {
		List<WebElement> defenseElementList = new ArrayList<WebElement>();
		defenseElementList.add(this.bigDefenseDomeElement);
		defenseElementList.add(this.bigLaserElement);
		defenseElementList.add(this.gaussCannonElement);
		defenseElementList.add(this.interceptionMissileElement);
		defenseElementList.add(this.interplanetaryMissileElement);
		defenseElementList.add(this.ionicCannonElement);
		defenseElementList.add(this.missileLauncherElement);
		defenseElementList.add(this.plasmaCannonElement);
		defenseElementList.add(this.smallDefenseDomeElement);
		defenseElementList.add(this.smallLaserElement);
		return defenseElementList;
	}
	
	private void setDefenseAttributes(WebElement hangarElement, Integer orderInElementList) {
		do{
			this.clickOnButton(hangarElement);
			this.waitUntilElementExistsInElement(this.defenseContentElement, By.tagName("h2"), TimeUnit.SECONDS.toMillis(5));
		} while(!hangarElement.getAttribute("class").contains("active")); 
		Constructions construction = new Constructions();
		this.setBuildName(construction, this.defenseContentElement.findElement(By.tagName("h2")).getText());
		this.setMetalRequired(construction, this.getElementRequired("metal"));
		this.setCrystalRequired(construction, this.getElementRequired("crystal"));
		this.setDeuteriumRequired(construction, this.getElementRequired("deuterium"));
		this.setLevel(construction, this.getLevel());
		construction.setOrderInElementList(orderInElementList);
		System.out.println(construction.getBuildName() + " set");
		DefensePage.constructionHashMap.put(this.getBuildingName(), construction);
	}
	
	private String getBuildingName() {
		if (this.webElementIsDisplayedInElement(this.defenseContentElement, By.tagName("h2"))) {
			return this.defenseContentElement.findElement(By.tagName("h2")).getText().trim();
		} else {
			return null;
		}
	}
	
	public void setBuildingAttributesMap() {
		List<WebElement> buildingElementList = this.generateDefenseElementList();
		Integer orderInElementList = 0;
		for (WebElement buildingElement : buildingElementList) {
			this.setDefenseAttributes(buildingElement, orderInElementList);
			orderInElementList += 1;
		}
	}
	
	public GeneralVisionPage constructIfPossible(Constructions construction, Integer howMany) {
		// Quantity missing
		if (this.isUpgradePossible(construction)) {
			this.goTo(MenuOptions.DEFENSE);
			this.generateDefenseElementList().get(construction.getOrderInElementList()).click();
			this.waitUntilElementExistsInElement(this.defenseContentElement, By.className("build-it"), TimeUnit.SECONDS.toMillis(10));
			this.quantityElement.sendKeys(howMany.toString());
			this.defenseContentElement.findElement(By.className("build-it")).click();
			LOGGER.info("Constructing " + howMany + " " + construction.getBuildName());
			this.goTo(MenuOptions.GENERAL_VISION);
		}
		return new GeneralVisionPage(true);
	}

}
