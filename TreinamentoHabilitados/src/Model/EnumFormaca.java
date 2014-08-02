package Model;

public enum EnumFormaca {
	Superior("Superior"), Tecnico("Técnico"), Medio("Médio"), Fundamental("Fundamental");
	String desc;
	
	private EnumFormaca(String desc){
		this.desc = desc;
	}
	@Override
	public String toString() {
		
		return desc;
	}
	
}
