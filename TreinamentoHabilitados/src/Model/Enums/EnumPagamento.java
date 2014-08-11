package model.enums;

public enum EnumPagamento {
	Debito("Débito"), Credito("Crédito"), Dinheiro("Dinheiro"), Cheque("Cheque");
	String desc;
	private EnumPagamento(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return desc;
	}
}
