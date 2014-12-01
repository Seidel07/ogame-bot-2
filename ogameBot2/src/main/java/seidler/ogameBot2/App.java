package seidler.ogameBot2;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import pages.HomePage;
import pages.generalVision.GeneralVisionPage;
import pages.generalVision.MenuOptions;
import pages.hangar.HangarPage;
import pages.hangar.HangarTech;
import pages.instalations.InstalationBuildings;
import pages.instalations.InstalationConditions;
import pages.instalations.InstalationTech;
import pages.instalations.InstalationsPage;
import pages.investigation.InvestigationBuildings;
import pages.investigation.InvestigationConditions;
import pages.investigation.InvestigationPage;
import pages.investigation.InvestigationTech;
import pages.resources.ResourcesBuildings;
import pages.resources.ResourcesConditions;
import pages.resources.ResourcesPage;

public class App {

    public static void main(String[] args) {

	boolean flag = false;
	HomePage homePage = new HomePage();
	ResourcesConditions resourcesConditions = new ResourcesConditions();
	InstalationConditions instalationConditions = new InstalationConditions();
	InvestigationConditions investigationConditions = new InvestigationConditions();
	while (!flag) {
	    Methods methods = new Methods();
	    try {
		homePage.getBaseUrl();
		GeneralVisionPage generalVisionPage = homePage.login();
		Long initialSecondsToWait = 27L;
		HangarPage hangarPage = (HangarPage) generalVisionPage.goTo(MenuOptions.HANGAR);
		hangarPage.setBuildingAttributesMap();
		while (!flag) {
		    ResourcesPage resourcesPage = (ResourcesPage) hangarPage.goTo(MenuOptions.RESOURCES);
		    resourcesPage.setBuildingAttributesMap();
		    InstalationsPage instalationPage = (InstalationsPage) resourcesPage.goTo(MenuOptions.INSTALATIONS);
		    instalationPage.setBuildingAttributesMap();
		    InvestigationPage investigationPage = (InvestigationPage) instalationPage.goTo(MenuOptions.INVESTIGATION);
		    investigationPage.setBuildingAttributesMap();
		    generalVisionPage = (GeneralVisionPage) investigationPage.goTo(MenuOptions.GENERAL_VISION);
		    InstalationTech instalationTech = new InstalationTech();
		    InvestigationTech investigationTech = new InvestigationTech();
		    HangarTech hangarTech = new HangarTech();

		    // Mine block
		    if (resourcesConditions.isSolarEnergyPlantConvenient(resourcesPage.getEnergyQuantity())) {
			methods.upgradeBuildingResources(ResourcesBuildings.SOLAR_PLANT);
		    } else {
			if (resourcesConditions.isMetalMineConvenient(resourcesPage.getProduction())) {
			    methods.upgradeBuildingResources(ResourcesBuildings.METAL_MINE);
			} else {
			    methods.upgradeBuildingResources(ResourcesBuildings.CRYSTAL_MINE);
			}
			if (resourcesConditions.isDeuteriumSynthesizerConvenient()) {
			    methods.upgradeBuildingResources(ResourcesBuildings.DEUTERIUM_SYNTHESIZER);
			}
		    }

		    // Capacity block
		    if (resourcesConditions.isMetalWarehouseConvenient(resourcesPage.getResourceCapacity(ResourcesBuildings.METAL_WAREHOUSE),resourcesPage.getMetalQuantity())) {
			methods.upgradeBuildingResources(ResourcesBuildings.METAL_WAREHOUSE);
		    }
		    if (resourcesConditions.isCrystalWarehouseConvenient(resourcesPage.getResourceCapacity(ResourcesBuildings.CRYSTAL_WAREHOUSE),resourcesPage.getCrystalQuantity())) {
			methods.upgradeBuildingResources(ResourcesBuildings.CRYSTAL_WAREHOUSE);
		    }
		    if (resourcesConditions.isDeuteriumWarehouseConvenient(resourcesPage.getResourceCapacity(ResourcesBuildings.DEUTERIUM_WAREHOUSE),resourcesPage.getDeuteriumQuantity())) {
			methods.upgradeBuildingResources(ResourcesBuildings.DEUTERIUM_WAREHOUSE);
		    }

		    // Instalation block
		    if (instalationTech.isNanobotsFactoryAvailable()) {
			methods.upgradeBuildingInstalations(InstalationBuildings.NANOBOT_FACTORY);
		    }
		    if (instalationConditions.isRobotFactoryConvinient(InstalationsPage.constructionHashMap.get(InstalationBuildings.ROBOT_FACTORY.getCode()))) {
			methods.upgradeBuildingInstalations(InstalationBuildings.ROBOT_FACTORY);
		    }
		    if (instalationConditions.isInvestigationLaboratoryConvinient(InstalationsPage.constructionHashMap.get(InstalationBuildings.INVESTIGATION_LABORATORY.getCode()))) {
			methods.upgradeBuildingInstalations(InstalationBuildings.INVESTIGATION_LABORATORY);
		    }
		    if (instalationTech.isHangarAvailable()) {
			methods.upgradeBuildingInstalations(InstalationBuildings.HANGAR);
		    }
		    if (instalationConditions.isTerraformerConvinient() && instalationTech.isTerraformerAvailable()) {
			methods.upgradeBuildingInstalations(InstalationBuildings.TERRAFORMER);
		    }

		    // Investigation block
		    if (investigationTech.isHiperspacialPropulsorAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.HIPERSPACIAL_PROPULSOR);
		    }
		    if (investigationTech.isImpulseEngineAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.IMPULSE_ENGINE);
		    }
		    if (investigationTech.isCombustionEngineAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.COMBUSTION_ENGINE);
		    }
		    if (investigationTech.isDefenseTechAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.DEFENSE_TECNOLOGY);
		    }
		    if (investigationTech.isMilitarTechAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.MILITAR_TECNOLOGY);
		    }
		    if (investigationTech.isShieldTechAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.SHIELD_TECNOLOGY);
		    }
		    if (investigationTech.isComputationTechAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.COMPUTATION_TECNOLOGY);
		    }
		    if (investigationTech.isAstrophysicsAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.ASTROPHYSICS);
		    }
		    if (investigationTech.isEspionageTechAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.ESPIONAGE_TECNOLOGY);
		    }
		    if (investigationTech.isIntergalacticInvestigationNetworkAvailable()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.INTERGALACTIC_INVESTIGATION_NETWORK);
		    }
		    if (investigationTech.isEnergyTechAvailable() && investigationConditions.isEnergyTechConvenient()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.ENERGY_TECNOLOGY);
		    }
		    if (investigationTech.isGravitonTechAvailable() && investigationConditions.isGravitonConvenient()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.GRAVITON_TECNOLOGY);
		    }
		    if (investigationTech.isHiperspaceTechAvailable() && investigationConditions.isHiperspaceTechConvenient()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.HIPERSPACE_TECNOLOGY);
		    }
		    if (investigationTech.isPlasmaTechAvailable() && investigationConditions.isPlasmaTechConvenient()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.PLASMA_TENOLOGY);
		    }
		    if (investigationTech.isLaserTechAvailable() && investigationConditions.isLaserTechConvenient()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.HIPERSPACIAL_PROPULSOR);methods.upgradeBuildingInvestigation(InvestigationBuildings.LASER_TECNOLOGY);
		    }
		    if (investigationTech.isIonicTechAvailable() && investigationConditions.isIonicTechConvenient()) {
			methods.upgradeBuildingInvestigation(InvestigationBuildings.IONIC_TENOLOGY);
		    }

		    // Wait stuff
		    try {
			System.out.println("Waiting " + initialSecondsToWait
				+ " second(s)");
			Date date = new Date();
			System.out.println("Sleep started at: " + date.toString());
			Thread.sleep(TimeUnit.SECONDS
				.toMillis(initialSecondsToWait));
			initialSecondsToWait = initialSecondsToWait * 21 / 20;
		    } catch (Exception e) {

		    }
		}

	    } catch (Exception e) {
		e.printStackTrace();
	    } finally {

	    }
	}
	homePage.closeDriver();
    }
}
