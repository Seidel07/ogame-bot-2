package pages.instalations;

import pages.investigation.InvestigationBuildings;
import pages.investigation.InvestigationPage;

public class InstalationTech {
    
    public InstalationTech() {
	this.nanobotsFactoryLevel = InstalationsPage.constructionHashMap.get(InstalationBuildings.NANOBOT_FACTORY.getCode()).getLevel();
	this.robotFactoryLevel = InstalationsPage.constructionHashMap.get(InstalationBuildings.ROBOT_FACTORY.getCode()).getLevel();
	this.energyTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.ENERGY_TECNOLOGY.getCode()).getLevel();
	this.computationTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.COMPUTATION_TECNOLOGY.getCode()).getLevel();
	this.hangarLevel = InstalationsPage.constructionHashMap.get(InstalationBuildings.HANGAR.getCode()).getLevel();
    }
    
    Integer nanobotsFactoryLevel;
    Integer energyTechLevel;
    Integer hangarLevel;
    Integer computationTechLevel;
    Integer robotFactoryLevel;
    
    public boolean isTerraformerAvailable() {
	return this.nanobotsFactoryLevel >=1 && this.energyTechLevel >=12;
    }
    
    public boolean isSileAvailable() {
	return this.hangarLevel >=1;
    }
    
    public boolean isNanobotsFactoryAvailable() {
	return this.hangarLevel >=10 && this.computationTechLevel>=10;
    }
    
    public boolean isHangarAvailable() {
	return this.robotFactoryLevel >=2;
    }

}
