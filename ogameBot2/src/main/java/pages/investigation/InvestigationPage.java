package pages.investigation;

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

public class InvestigationPage extends AllPages {
	
	public InvestigationPage(Boolean flag) {
		if (flag) {
			this.waitUntilElementExistsInPage(By.id("details113"), TimeUnit.SECONDS.toMillis(10));
		}
	}

	private static final Logger LOGGER = Logger.getLogger(InvestigationPage.class.getName());
	
	@FindBy(id = "details113")
	private WebElement energyTecnologyElement;
	@FindBy(id = "details120")
	private WebElement laserTecnologyElement;
	@FindBy(id = "details121")
	private WebElement ionicTecnologyElement;
	@FindBy(id = "details114")
	private WebElement hiperspaceTecnologyElement;
	@FindBy(id = "details122")
	private WebElement plasmaTecnologyElement;
	@FindBy(id = "details115")
	private WebElement combustionEngineElement;
	@FindBy(id = "details117")
	private WebElement impulseEngineElement;
	@FindBy(id = "details118")
	private WebElement hiperspacialPropulsorElement;
	@FindBy(id = "details106")
	private WebElement espionageTecnologyElement;
	@FindBy(id = "details108")
	private WebElement computationTecnologyElement;
	@FindBy(id = "details124")
	private WebElement astrophysicsElement;
	@FindBy(id = "details123")
	private WebElement intergalacticInvestigationNetworkElement;
	@FindBy(id = "details199")
	private WebElement gravitonTecnologyElement;
	@FindBy(id = "details109")
	private WebElement militarTecnologyElement;
	@FindBy(id = "details110")
	private WebElement defenseTecnologyElement;
	@FindBy(id = "details111")
	private WebElement shieldTecnologyElement;
	
	@FindBy(id = "content")
	private WebElement investigationContentElement;
	
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
		if (this.webElementIsDisplayedInElement(this.investigationContentElement, By.className(element))) {
			return Integer.parseInt(this.investigationContentElement.findElement(By.className(element)).getText().replace(".", "").replace("M", "000000"));
		} else {
			return null;
		}
	}
	
	private Integer getLevel() {
		if (this.webElementIsDisplayedInElement(this.investigationContentElement, By.className("level"))) {
			return Integer.parseInt(this.investigationContentElement.findElement(By.className("level")).getText().replace("Nivel", "").replace("Número:", "").trim());
		} else {
			return null;
		}
	}
	
	private List<WebElement> generateInvestigationElementList() {
		List<WebElement> buildingList = new ArrayList<WebElement>();
		buildingList.add(this.astrophysicsElement);
		buildingList.add(this.combustionEngineElement);
		buildingList.add(this.computationTecnologyElement);
		buildingList.add(this.defenseTecnologyElement);
		buildingList.add(this.energyTecnologyElement);
		buildingList.add(this.espionageTecnologyElement);
		buildingList.add(this.gravitonTecnologyElement);
		buildingList.add(this.hiperspaceTecnologyElement);
		buildingList.add(this.hiperspacialPropulsorElement);
		buildingList.add(this.impulseEngineElement);
		buildingList.add(this.intergalacticInvestigationNetworkElement);
		buildingList.add(this.ionicTecnologyElement);
		buildingList.add(this.laserTecnologyElement);
		buildingList.add(this.militarTecnologyElement);
		buildingList.add(this.plasmaTecnologyElement);
		buildingList.add(this.shieldTecnologyElement);
		return buildingList;
	}
	
	private void setInvestigationAttributes(WebElement investigationElement, Integer orderInElementList) {
		do{
			this.clickOnButton(investigationElement);
			this.waitUntilElementExistsInElement(this.investigationContentElement, By.tagName("h2"), TimeUnit.SECONDS.toMillis(15));
		} while(!this.webElementIsDisplayedInElement(this.investigationContentElement, By.tagName("h2"))); 
		Constructions construction = new Constructions();
		this.setBuildName(construction, this.investigationContentElement.findElement(By.tagName("h2")).getText());
		this.setMetalRequired(construction, this.getElementRequired("metal"));
		this.setCrystalRequired(construction, this.getElementRequired("crystal"));
		this.setDeuteriumRequired(construction, this.getElementRequired("deuterium"));
		this.setLevel(construction, this.getLevel());
		construction.setOrderInElementList(orderInElementList);
		System.out.println(construction.getBuildName() + " set");
		InvestigationPage.constructionHashMap.put(this.getBuildingName(), construction);
	}
	
	private String getBuildingName() {
		if (this.webElementIsDisplayedInElement(this.investigationContentElement, By.tagName("h2"))) {
			return this.investigationContentElement.findElement(By.tagName("h2")).getText().trim();
		} else {
			return null;
		}
	}
	
	public void setBuildingAttributesMap() {
		List<WebElement> buildingElementList = this.generateInvestigationElementList();
		Integer orderInElementList = 0;
		for (WebElement buildingElement : buildingElementList) {
			this.setInvestigationAttributes(buildingElement, orderInElementList);
			orderInElementList += 1;
		}
	}
	
//	private boolean isUpgradePossible(Constructions construction) {
//		boolean flag = true;
//		if (construction.getMetalRequired() - this.getMetalQuantity() > 0) {
//			return false;
//		}
//		if (construction.getDeuteriumRequired() - this.getDeuteriumQuantity() > 0) {
//			return false;
//		}
//		if (construction.getCrystalRequired() - this.getCrystalQuantity() > 0) {
//			return false;
//		}
//		return flag;
//	}
	
	public GeneralVisionPage upgradeIfPossible(Constructions construction) {
		if (this.isUpgradePossible(construction)) {
			this.goTo(MenuOptions.INVESTIGATION);
			this.generateInvestigationElementList().get(construction.getOrderInElementList()).click();
			this.waitUntilElementExistsInElement(this.investigationContentElement, By.className("build-it"), TimeUnit.SECONDS.toMillis(10));
			this.investigationContentElement.findElement(By.className("build-it")).click();
			LOGGER.info("Upgrading " + construction.getBuildName());
			this.goTo(MenuOptions.GENERAL_VISION);
		}
		return new GeneralVisionPage(true);
	}
}
