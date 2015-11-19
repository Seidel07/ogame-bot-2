package pages.defense;

import java.util.ArrayList;
import java.util.List;

import pages.instalations.InstalationBuildings;
import pages.instalations.InstalationsPage;
import pages.investigation.InvestigationBuildings;
import pages.investigation.InvestigationPage;

public class DefenseTech {

	public DefenseTech() {
		this.hangarLevel = InstalationsPage.constructionHashMap.get(InstalationBuildings.HANGAR.getCode()).getLevel();
		this.ionicTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.IONIC_TENOLOGY.getCode()).getLevel();
		this.defenseTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.HIPERSPACIAL_PROPULSOR.getCode()).getLevel();
		this.plasmaTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.PLASMA_TENOLOGY.getCode()).getLevel();
		this.laserTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.LASER_TECNOLOGY.getCode()).getLevel();
		this.energyTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.ENERGY_TECNOLOGY.getCode()).getLevel();
		this.militarTechLevel = InvestigationPage.constructionHashMap.get(InvestigationBuildings.MILITAR_TECNOLOGY.getCode()).getLevel();
		this.smallDefenseDomeQuantity = DefensePage.constructionHashMap.get(DefenseConstructions.SMALL_DEFENSE_DOME.getCode()).getLevel();
		this.largeDefenseDomeQuantity = DefensePage.constructionHashMap.get(DefenseConstructions.BIG_DEFENSE_DOME.getCode()).getLevel();
	}

	Integer hangarLevel;
	Integer ionicTechLevel;
	Integer defenseTechLevel;
	Integer plasmaTechLevel;
	Integer laserTechLevel;
	private Integer energyTechLevel;
	private Integer militarTechLevel;
	private Integer smallDefenseDomeQuantity;
	private Integer largeDefenseDomeQuantity;

	public boolean isMissileLauncherAvailable() {
		return this.hangarLevel>=2;
	}

	public boolean isSmallLaserAvailable() {
		return this.hangarLevel>=2 && this.laserTechLevel>=2;
	}

	public boolean isLargeLaserAvailable() {
		return this.hangarLevel>=4 && this.laserTechLevel>=2 && this.energyTechLevel >=3;
	}

	public boolean isGaussCannonAvailable() {
		return this.hangarLevel>=6 && this.militarTechLevel>=3 && this.energyTechLevel>=6 && this.defenseTechLevel>=1;
	}

	public boolean isIonicCannonAvailable() {
		return this.hangarLevel>=4 && this.ionicTechLevel>=4;
	}

	public boolean isPlasmaCannonAvailable() {
		return this.hangarLevel>=8 && this.plasmaTechLevel>=7;
	}

	public boolean isSmallDefenseDomeAvailable() {
		return this.hangarLevel>=1 && this.defenseTechLevel>=2 && this.smallDefenseDomeQuantity == 0;
	}

	public boolean isLargeDefenseDomeAvailable() {
		return this.hangarLevel>=6 && this.defenseTechLevel>=6 && this.largeDefenseDomeQuantity==0;
	}
	
	public List<DefenseConstructions> getAvailableConstructionList() {
		List<DefenseConstructions> output = new ArrayList<DefenseConstructions>();
		if(this.isLargeDefenseDomeAvailable()) output.add(DefenseConstructions.BIG_DEFENSE_DOME);
		if(this.isSmallDefenseDomeAvailable()) output.add(DefenseConstructions.SMALL_DEFENSE_DOME);
		if(this.isPlasmaCannonAvailable()) output.add(DefenseConstructions.PLASMA_CANNON);
		if(this.isGaussCannonAvailable()) output.add(DefenseConstructions.GAUSS_CANNON);
		if(this.isIonicCannonAvailable()) output.add(DefenseConstructions.IONIC_CANNON);
		if(this.isLargeLaserAvailable()) output.add(DefenseConstructions.BIG_LASER);
		if(this.isSmallLaserAvailable()) output.add(DefenseConstructions.LITTLE_LASER);
		if(this.isMissileLauncherAvailable()) output.add(DefenseConstructions.MISSILE_LAUNCHER);
		return output;
	}

}
