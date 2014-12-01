package seidler.ogameBot2;

import pages.defense.DefensePage;
import pages.generalVision.GeneralVisionPage;
import pages.hangar.HangarPage;
import pages.hangar.HangarShips;
import pages.instalations.InstalationBuildings;
import pages.instalations.InstalationsPage;
import pages.investigation.InvestigationBuildings;
import pages.investigation.InvestigationPage;
import pages.resources.ResourcesBuildings;
import pages.resources.ResourcesPage;

public class Methods {

    boolean flag = false;

    GeneralVisionPage generalVisionPage = new GeneralVisionPage(flag);
    ResourcesPage resourcesPage = new ResourcesPage(flag);
    InstalationsPage instalationsPage = new InstalationsPage(flag);
    InvestigationPage investigationPage = new InvestigationPage(flag);
    HangarPage hangarPage = new HangarPage(flag);
    DefensePage defensePage = new DefensePage(flag);
    //	FloatPage floatPage = new FloatPage(flag);
    //	GalaxyPage galaxyPage = new GalaxyPage(flag);

    public void upgradeBuildingResources(ResourcesBuildings buildingName) {
	if (!generalVisionPage.buildingUnderConstruction()) {
	    resourcesPage.upgradeIfPossible(ResourcesPage.constructionHashMap.get(buildingName.getCode()));
	}
    }

    public void upgradeBuildingInstalations(InstalationBuildings buildingName) {
	if (!generalVisionPage.buildingUnderConstruction()) {
	    instalationsPage.upgradeIfPossible(InstalationsPage.constructionHashMap.get(buildingName.getCode()));
	}
    }

    public void upgradeBuildingInvestigation(InvestigationBuildings buildingName) {
	if (!generalVisionPage.investigationInProcess()) {
	    investigationPage.upgradeIfPossible(InvestigationPage.constructionHashMap.get(buildingName.getCode()));
	}
    }

    public void constructShip(HangarShips shipName, Integer quantity) {
	hangarPage.constructIfPossible(HangarPage.constructionHashMap.get(shipName.getCode()),quantity);
    }

    public void constructDefense(HangarShips defenseName, Integer quantity) {
	defensePage.constructIfPossible(DefensePage.constructionHashMap.get(defenseName.getCode()),quantity);
    }

}
