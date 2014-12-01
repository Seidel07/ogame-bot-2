package pages.defense;

public enum DefenseConstructions {
	
	MISSILE_LAUNCHER("Lanzamisiles"),
	LITTLE_LASER("Láser pequeño"),
	BIG_LASER("Láser grande"),
	GAUSS_CANNON("Cañón Gauss"),
	IONIC_CANNON("Cañón iónico"),
	PLASMA_CANNON("Cañón de plasma"),
	SMALL_DEFENSE_DOME("Cúpula pequeña de defensa"),
	BIG_DEFENSE_DOME("Cúpula grande de defensa"),
	INTERCEPTION_MISSILE("Misil de intercepción"),
	INTERPLANETARY_MISSILE("Misil interplanetario");
	
	private String code;
	
	private DefenseConstructions(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}


}
