package pages.instalations;

public enum InstalationBuildings {
	
	ROBOT_FACTORY("Fábrica de Robots"),
	HANGAR("Hangar"),
	INVESTIGATION_LABORATORY("Laboratorio de investigación"),
	ALLIANCE_DEPOSIT("Depósito de la Alianza"),
	SILO("Silo"),
	NANOBOT_FACTORY("Fábrica de Nanobots"),
	TERRAFORMER("Terraformer");
	
	private String code;
	
	private InstalationBuildings(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
