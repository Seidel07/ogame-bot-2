package pages.investigation;

import pages.instalations.InstalationBuildings;
import pages.instalations.InstalationsPage;

public class InvestigationTech {
    
    public InvestigationTech() {
	this.laboratoryInvestigationLevel = InstalationsPage.constructionHashMap.get(InstalationBuildings.INVESTIGATION_LABORATORY.getCode()).getLevel();
	this.energyTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.ENERGY_TECNOLOGY.getCode()).getLevel();
	this.computationTechLevel= InvestigationPage.constructionHashMap.get(InvestigationBuildings.COMPUTATION_TECNOLOGY.getCode()).getLevel();
	this.defenseTechLevel= InvestigationPage.constructionHashMap.get(InvestigationBuildings.DEFENSE_TECNOLOGY.getCode()).getLevel();
	this.hiperspaceTechLevel= InvestigationPage.constructionHashMap.get(InvestigationBuildings.HIPERSPACE_TECNOLOGY.getCode()).getLevel();
	this.laserTechLevel= InvestigationPage.constructionHashMap.get(InvestigationBuildings.LASER_TECNOLOGY.getCode()).getLevel();
	this.ionicTechLevel= InvestigationPage.constructionHashMap.get(InvestigationBuildings.IONIC_TENOLOGY.getCode()).getLevel();
	this.espionageTechLevel= InvestigationPage.constructionHashMap.get(InvestigationBuildings.ESPIONAGE_TECNOLOGY.getCode()).getLevel();
	this.impulseEngineLevel= InvestigationPage.constructionHashMap.get(InvestigationBuildings.IMPULSE_ENGINE.getCode()).getLevel();
    }
    
    Integer laboratoryInvestigationLevel;
    Integer energyTechLevel;
    Integer computationTechLevel;
    Integer defenseTechLevel;
    Integer hiperspaceTechLevel;
    Integer laserTechLevel;
    Integer ionicTechLevel;
    Integer espionageTechLevel;
    Integer impulseEngineLevel;
    
    public boolean isEspionageTechAvailable() {
	return this.laboratoryInvestigationLevel>=3;
    }
    
    public boolean isComputationTechAvailable() {
	return this.laboratoryInvestigationLevel>=1;
    }
    
    public boolean isMilitarTechAvailable() {
	return this.laboratoryInvestigationLevel>=4;
    }
    
    public boolean isDefenseTechAvailable() {
	return this.laboratoryInvestigationLevel>=6 && this.energyTechLevel>=3;
    }
    
    public boolean isShieldTechAvailable() {
	return this.laboratoryInvestigationLevel>=2;
    }
    
    public boolean isEnergyTechAvailable() {
	return this.laboratoryInvestigationLevel>=1;
    }
    
    public boolean isHiperspaceTechAvailable() {
	return this.laboratoryInvestigationLevel>=7 && this.energyTechLevel>=5 && this.defenseTechLevel>=5;
    }
    
    public boolean isCombustionEngineAvailable() {
	return this.laboratoryInvestigationLevel>=1 && this.energyTechLevel>=1;
    }
    
    public boolean isImpulseEngineAvailable() {
	return this.laboratoryInvestigationLevel>=2 && this.energyTechLevel>=1;
    }
    
    public boolean isHiperspacialPropulsorAvailable() {
	return this.laboratoryInvestigationLevel>=7 && this.hiperspaceTechLevel>=3;
    }
    
    public boolean isLaserTechAvailable() {
	return this.laboratoryInvestigationLevel>=1 && this.energyTechLevel>=2;
    }
    
    public boolean isPlasmaTechAvailable() {
	return this.laboratoryInvestigationLevel>=4 && this.energyTechLevel>=8 && this.laserTechLevel>=10 && this.ionicTechLevel>=4;
    }
    
    public boolean isIonicTechAvailable() {
	return this.laboratoryInvestigationLevel>=4 && this.energyTechLevel>=4 && this.laserTechLevel>=5;
    }
    
    public boolean isIntergalacticInvestigationNetworkAvailable() {
	return this.laboratoryInvestigationLevel>=10 && this.computationTechLevel>=8 && this.hiperspaceTechLevel>=8;
    }
    
    public boolean isAstrophysicsAvailable() {
	return this.laboratoryInvestigationLevel>=3 && this.espionageTechLevel>=4 && this.impulseEngineLevel>=3;
    }
    
    public boolean isGravitonTechAvailable() {
	return this.laboratoryInvestigationLevel>=12;
    }

}
