package model.enums;

public enum EnumTipoDespesa {
	Salario("Salário"), Manu("Manutenção"), Est("Estabelecimento"), Desp("Despesas Gerais"),Todas("Todas as Dispesas");
	
	private String desc;
	private EnumTipoDespesa(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
