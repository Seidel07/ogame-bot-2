package pages.hangar;

import pages.instalations.InstalationBuildings;
import pages.instalations.InstalationsPage;
import pages.investigation.InvestigationBuildings;
import pages.investigation.InvestigationPage;

public class HangarTech {

    public HangarTech() {
	this.hangarLevel = InstalationsPage.constructionHashMap.get(InstalationBuildings.HANGAR.getCode()).getLevel();
	this.combustionEngineLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.COMBUSTION_ENGINE.getCode()).getLevel();
	this.shieldTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.SHIELD_TECNOLOGY.getCode()).getLevel();
	this.impulseEngineLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.IMPULSE_ENGINE.getCode()).getLevel();
	this.ionicTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.IONIC_TENOLOGY.getCode()).getLevel();
	this.hiperspatialPropulsorLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.HIPERSPACIAL_PROPULSOR.getCode()).getLevel();
	this.defenseTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.HIPERSPACIAL_PROPULSOR.getCode()).getLevel();
	this.espionageTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.ESPIONAGE_TECNOLOGY.getCode()).getLevel();
	this.plasmaTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.PLASMA_TENOLOGY.getCode()).getLevel();
	this.hiperspaceTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.HIPERSPACE_TECNOLOGY.getCode()).getLevel();
	this.gravitonTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.GRAVITON_TECNOLOGY.getCode()).getLevel();
	this.laserTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.LASER_TECNOLOGY.getCode()).getLevel();
    }

    Integer hangarLevel;
    Integer combustionEngineLevel;
    Integer shieldTechLevel;
    Integer impulseEngineLevel;
    Integer ionicTechLevel;
    Integer hiperspatialPropulsorLevel;
    Integer defenseTechLevel;
    Integer espionageTechLevel;
    Integer plasmaTechLevel;
    Integer hiperspaceTechLevel;
    Integer gravitonTechLevel;
    Integer laserTechLevel;
    
    public boolean isSmallCargoShipAvailable() {
	return this.hangarLevel>=2 && this.combustionEngineLevel>=2;
    }
    
    public boolean isBigCargoShipAvailable() {
	return this.hangarLevel>=4 && this.combustionEngineLevel>=6;
    }
    
    public boolean isLightHunterAvailable() {
	return this.hangarLevel>=1 && this.combustionEngineLevel>=1;
    }
    
    public boolean isHeavyHunterAvailable() {
	return this.hangarLevel>=3 && this.impulseEngineLevel>=2 && this.shieldTechLevel>=2;
    }
    
    public boolean isCruiseAvailable() {
	return this.hangarLevel>=5 && this.ionicTechLevel>=2 && this.impulseEngineLevel>=4;
    }
    
    public boolean isBattleShipAvailable() {
	return this.hangarLevel>=7 && this.hiperspatialPropulsorLevel>=4;
    }
    
    public boolean isColonyShipAvailable() {
	return this.hangarLevel>=4 && this.impulseEngineLevel>=3;
    }
    
    public boolean isRecycleShipAvailable() {
	return this.hangarLevel>=4 && this.combustionEngineLevel>=6 && this.defenseTechLevel>=2;
    }
    
    public boolean isEspionageProbeAvailable() {
	return this.hangarLevel>=3 && this.combustionEngineLevel>=3 && this.combustionEngineLevel>=3 && this.espionageTechLevel>=2;
    }
    
    public boolean isBomberAvailable() {
	return this.hangarLevel>=8 && this.impulseEngineLevel>=6 && plasmaTechLevel>=5;
    }
    
    public boolean isSolarSateliteAvailable() {
	return this.hangarLevel>=1;
    }
    
    public boolean isDestructorAvailable() {
	return this.hangarLevel>=9 && this.hiperspatialPropulsorLevel>=6 && this.hiperspaceTechLevel>=5;
    }
    
    public boolean isDeathStarAvailable() {
	return this.hangarLevel>=12 && this.gravitonTechLevel>=1 && this.hiperspatialPropulsorLevel>=7 && this.hiperspaceTechLevel>=6;
    }
    
    public boolean isShieldShipAvailable() {
	return this.hangarLevel>=8 && this.hiperspaceTechLevel>=5 && this.hiperspatialPropulsorLevel>=5 && this.laserTechLevel>=12;
    }

}
