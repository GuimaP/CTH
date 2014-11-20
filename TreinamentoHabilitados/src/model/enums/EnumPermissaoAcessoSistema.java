package model.enums;

public enum EnumPermissaoAcessoSistema {
	Gerente("Gerente"), Administrador("Administrador"), Funcionario("Funcionário");
	String desc;
	
	private EnumPermissaoAcessoSistema(String desc){
		this.desc = desc;
	}
	@Override
	public String toString() {
		return desc;
	}
	
}
