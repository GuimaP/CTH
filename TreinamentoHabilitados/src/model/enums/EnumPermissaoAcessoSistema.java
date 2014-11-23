package model.enums;

public enum EnumPermissaoAcessoSistema {
	Administrador("Administrador"), Funcionario("Funcion�rio");
	String desc;
	
	private EnumPermissaoAcessoSistema(String desc){
		this.desc = desc;
	}
	@Override
	public String toString() {
		return desc;
	}
	
}
