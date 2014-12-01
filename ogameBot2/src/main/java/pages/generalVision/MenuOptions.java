package pages.generalVision;

public enum MenuOptions {
	
	GENERAL_VISION("Visión general"),
	RESOURCES("Recursos"),
	INSTALATIONS("Instalaciones"),
	INVESTIGATION("Investigación"),
	HANGAR("Hangar"),
	DEFENSE("Defensa"),
	FLOAT("Flota"),
	GALAXY("Galaxia");
	
	private String code;

	private MenuOptions(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}



}
