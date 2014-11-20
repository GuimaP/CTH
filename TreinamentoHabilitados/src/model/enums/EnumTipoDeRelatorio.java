package model.enums;

public enum EnumTipoDeRelatorio {
	Financeiro("Financeiro"),  AulasPorInstrutor ("Aulas por Instrutor");
	
	private String desc;
	private EnumTipoDeRelatorio(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
