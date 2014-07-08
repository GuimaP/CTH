package Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.itextpdf.text.pdf.codec.Base64;

import Model.ICrypt;

public class Criptografia<T> implements ICrypt<T>{

	private MessageDigest algorithm;
	
	public Criptografia(String algortihm){
		try {
			this.algorithm = MessageDigest.getInstance(algortihm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String encrypt(T value) {
		StringBuilder builder = new StringBuilder();
		byte[] bytes = algorithm.digest(Base64.decode(value.toString()));
		return null;
	}

	@Override
	public String unCrypt(T value) {
		// TODO Auto-generated method stub
		return null;
	}

}
