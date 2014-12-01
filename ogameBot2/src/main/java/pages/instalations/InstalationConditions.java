package pages.instalations;

import model.Constructions;

public class InstalationConditions {

    public boolean isRobotFactoryConvinient(Constructions robotFactory) {
	return robotFactory.getLevel() <10;
    }
    
    public boolean isHangarConvinient(Constructions hangar) {
	return hangar.getLevel() <12;
    }
    
    public boolean isInvestigationLaboratoryConvinient(Constructions investigationLaboratory) {
	return investigationLaboratory.getLevel() <10;
    }
    
    public boolean isSiloConvinient() {
	//TODO make a criteria
	return false;
    }
    
    public boolean isNanobotFactoryConvinient() {
	//TODO make a criteria depending on the space of the planet
	return true;
    }
    
    public boolean isTerraformerConvinient() {
	// TODO make a criteria depending on the space of the planet
	return false;
    }

}
