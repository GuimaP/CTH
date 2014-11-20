package model.enums;

public enum EnumSexo {
	Masculino("Masculino"), Feminino("Feminino");
	String desc;
	
	private EnumSexo(String desc){
		this.desc = desc;
	}
	@Override
	public String toString() {
		return desc;
	}
}
