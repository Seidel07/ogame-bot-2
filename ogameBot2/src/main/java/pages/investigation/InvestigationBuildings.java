package pages.investigation;

public enum InvestigationBuildings {
	
	ENERGY_TECNOLOGY("Tecnología de energía"),
	LASER_TECNOLOGY("Tecnología láser"),
	IONIC_TENOLOGY("Tecnología iónica"),
	HIPERSPACE_TECNOLOGY("Tecnología de hiperespacio"),
	PLASMA_TENOLOGY("Tecnología de plasma"),
	COMBUSTION_ENGINE("Motor de combustión"),
	IMPULSE_ENGINE("Motor de impulso"),
	HIPERSPACIAL_PROPULSOR("Propulsor hiperespacial"),
	ESPIONAGE_TECNOLOGY("Tecnología de espionaje"),
	COMPUTATION_TECNOLOGY("Tecnología de computación"),
	ASTROPHYSICS("Astrofísica"),
	INTERGALACTIC_INVESTIGATION_NETWORK("Red de investigación intergaláctica"),
	GRAVITON_TECNOLOGY("Tecnología de gravitón"),
	MILITAR_TECNOLOGY("Tecnología militar"),
	DEFENSE_TECNOLOGY("Tecnología de defensa"),
	SHIELD_TECNOLOGY("Tecnología de blindaje");
	
	private String code;
	
	private InvestigationBuildings(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
