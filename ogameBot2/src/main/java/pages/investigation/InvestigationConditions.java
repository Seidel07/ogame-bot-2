package pages.investigation;

public class InvestigationConditions {
    
    public boolean isEnergyTechConvenient() {
	return InvestigationPage.constructionHashMap.get(InvestigationBuildings.ENERGY_TECNOLOGY.getCode()).getLevel()<8;
    }
    
    public boolean isLaserTechConvenient() {
	return InvestigationPage.constructionHashMap.get(InvestigationBuildings.LASER_TECNOLOGY.getCode()).getLevel()<12;
    }
    
    public boolean isIonicTechConvenient() {
	return InvestigationPage.constructionHashMap.get(InvestigationBuildings.IONIC_TENOLOGY.getCode()).getLevel()<4;
    }
    
    public boolean isHiperspaceTechConvenient() {
	return InvestigationPage.constructionHashMap.get(InvestigationBuildings.HIPERSPACE_TECNOLOGY.getCode()).getLevel()<8;
    }
    
    public boolean isPlasmaTechConvenient() {
	return InvestigationPage.constructionHashMap.get(InvestigationBuildings.PLASMA_TENOLOGY.getCode()).getLevel()<5;
    }
    
    public boolean isGravitonConvenient() {
	return InvestigationPage.constructionHashMap.get(InvestigationBuildings.GRAVITON_TECNOLOGY.getCode()).getLevel()<1;
    }

}
