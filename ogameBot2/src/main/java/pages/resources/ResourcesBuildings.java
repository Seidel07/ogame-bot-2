package pages.resources;

public enum ResourcesBuildings {
	
	METAL_MINE("Mina de metal"),
	CRYSTAL_MINE("Mina de cristal"),
	DEUTERIUM_SYNTHESIZER("Sintetizador de deuterio"),
	SOLAR_PLANT("Planta de energía solar"),
	FUSION_PLANT("Planta de fusión"),
	SOLAR_SATELITE("Satélite solar"),
	METAL_WAREHOUSE("Almacén de metal"),
	CRYSTAL_WAREHOUSE("Almacén de cristal"),
	DEUTERIUM_WAREHOUSE("Contenedor de deuterio"),
	METAL_HIDEOUT("Escondite blindado de metal"),
	CRYSTAL_HIDEOUT("Escondite subterráneo de cristal"),
	DEUTERIUM_HIDEOUT("Escondite marítimo de deuterio");
	
	private String code;
	
	private ResourcesBuildings(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
