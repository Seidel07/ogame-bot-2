package pages.resources;

import model.Constructions;
import model.Production;

public class ResourcesConditions {

	public boolean isMetalMineConvenient(Production production) {
		Constructions metalMine = ResourcesPage.constructionHashMap.get(ResourcesBuildings.METAL_MINE.getCode());
		Constructions crystalMine = ResourcesPage.constructionHashMap.get(ResourcesBuildings.CRYSTAL_MINE.getCode());
		Constructions solarPlant = ResourcesPage.constructionHashMap.get(ResourcesBuildings.SOLAR_PLANT.getCode());
		Integer metalRequired = metalMine.getMetalRequired() + crystalMine.getMetalRequired() + solarPlant.getMetalRequired();
		Integer crystalRequired = metalMine.getCrystalRequired() + crystalMine.getCrystalRequired() + solarPlant.getCrystalRequired();
		return metalRequired / production.getMetal() > crystalRequired / production.getCrystal();
	}

	public boolean isCrystalMineConvinient(Production production) {
		return !isMetalMineConvenient(production);
	}

	public boolean isDeuteriumSynthesizerConvenient() {
		Constructions metalMine = ResourcesPage.constructionHashMap.get(ResourcesBuildings.METAL_MINE.getCode());
		Constructions crystalMine = ResourcesPage.constructionHashMap.get(ResourcesBuildings.CRYSTAL_MINE.getCode());
		Constructions deuteriumMine = ResourcesPage.constructionHashMap.get(ResourcesBuildings.DEUTERIUM_SYNTHESIZER.getCode());
		Constructions solarPlant = ResourcesPage.constructionHashMap.get(ResourcesBuildings.SOLAR_PLANT.getCode());
		return Math.floor(metalMine.getLevel() / 10) + Math.floor(crystalMine.getLevel() / 7) + Math.floor(solarPlant.getLevel() / 9) > deuteriumMine.getLevel();
	}

	public boolean isSolarEnergyPlantConvenient(Integer energy) {
		return energy < 0;
	}

	public boolean isMetalWarehouseConvenient(Integer metalCapacity,Integer metalQuantity) {
		return metalCapacity * 9 / 10 < metalQuantity;
	}

	public boolean isCrystalWarehouseConvenient(Integer crystalCapacity,Integer crystalQuantity) {
		return crystalCapacity * 9 / 10 < crystalQuantity;
	}

	public boolean isDeuteriumWarehouseConvenient(Integer deuteriumCapacity,Integer deuteriumQuantity) {
		return deuteriumCapacity * 9 / 10 < deuteriumQuantity;
	}

}
