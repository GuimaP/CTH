package Model;

public interface ICrypt <T>{
	String encrypt(T value);
	
	String unCrypt(T value);
}
