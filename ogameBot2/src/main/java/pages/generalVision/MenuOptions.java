package pages.generalVision;

public enum MenuOptions {
	
	GENERAL_VISION("Resumen"),
	RESOURCES("Recursos"),
	RESOURCES_SETTINGS(""),
	INSTALATIONS("Instalaciones"),
	INVESTIGATION("Investigación"),
	HANGAR("Hangar"),
	DEFENSE("Defensa"),
	FLOAT("Flota"),
	GALAXY("Galaxia"),
	MESSAGES("");
	
	private String code;

	private MenuOptions(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}



}
