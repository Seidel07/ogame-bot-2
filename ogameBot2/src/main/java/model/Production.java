package model;

public class Production {
	
	public Production(Integer metalProduction, Integer crystalProduction, Integer deuteriumProduction) {
		this.metal = metalProduction;
		this.crystal = crystalProduction;
		this.deuterium = deuteriumProduction;
	}
	
	private Integer metal;
	private Integer crystal;
	private Integer deuterium;
	
	public Integer getMetal() {
		return metal;
	}
	public void setMetal(Integer metal) {
		this.metal = metal;
	}
	public Integer getCrystal() {
		return crystal;
	}
	public void setCrystal(Integer crystal) {
		this.crystal = crystal;
	}
	public Integer getDeuterium() {
		return deuterium;
	}
	public void setDeuterium(Integer deuterium) {
		this.deuterium = deuterium;
	}

}
