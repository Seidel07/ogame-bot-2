package pages.hangar;

public enum HangarShips {
	
	LIGHT_HUNTER("Cazador ligero"),
	HEAVY_HUNTER("Cazador pesado"),
	CRUISE("Crucero"),
	BATTLESHIP("Nave de batalla"),
	LITTLE_CARGO_SHIP("Nave pequeña de carga"),
	BIG_CARGO_SHIP("Nave grande de carga"),
	COLONY_SHIP("Nave colonizadora"),
	BOMBER("Bombardero"),
	SHIELD_SHIP("Acorazado"),
	DESTRUCTOR("Destructor"),
	DEATH_STAR("Estrella de la muerte"),
	RECYCLE_SHIP("Reciclador"),
	ESPIONAGE_PROBE("Sonda de espionaje"),
	SOLAR_SATELITE("Satélite solar");
	
	private String code;
	
	private HangarShips(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
