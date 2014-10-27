package model.enums;

public enum EnumQuestionario {
	Internet("Internet"), Indicacoes("Indicações"), Outros("Outros");
	String desc;
	 private EnumQuestionario(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		
		return desc;
	}
}
