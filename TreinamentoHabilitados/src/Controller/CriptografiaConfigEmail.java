package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import model.ICrypt;
import model.UsuarioEmail;

import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.text.pdf.hyphenation.CharVector;

public class CriptografiaConfigEmail implements ICrypt<UsuarioEmail> {

	private SecretKey key;
	private MessageDigest algorithm;

	public CriptografiaConfigEmail() {
		try {
			gerarAqruivo();
		} catch (InvalidKeySpecException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String encryptPass(Object value) {
		StringBuilder builder = new StringBuilder();
		byte[] messageDigest = algorithm
				.digest(Base64.decode(value.toString()));
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String senha = hexString.toString();
		System.out.println(senha);
		return null;
	}

	@Override
	public void encrypt(UsuarioEmail value, String nameFolder) throws Exception {

		nameFolder = geraNomeCriptografado(nameFolder);
		System.out.println(nameFolder);
		
		String pasta = System.getProperty("user.home");
		String nameFile = System.getProperty("file.separator") + "Treinamento"+System.getProperty("file.separator");
		
		
		
		File dir = new File(pasta+nameFile+nameFolder + ".cr");
		if (!dir.exists()) {
			Cipher ci = Cipher.getInstance("DESede");
			ci.init(Cipher.ENCRYPT_MODE, key);
			SealedObject usuarioConfig = new SealedObject(value, ci);
			FileOutputStream ou = new FileOutputStream((dir));
			ObjectOutputStream os = new ObjectOutputStream(ou);
			os.writeObject(usuarioConfig);
			os.flush();
			os.close();
		}
	

	}

	//
	private String geraNomeCriptografado(String v)
			throws NoSuchAlgorithmException {
		algorithm = MessageDigest.getInstance("MD5");
		byte[] messageDigest = algorithm.digest(v.getBytes());
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String nameFile = hexString.toString();
		return nameFile;

	}

	@Override
	public UsuarioEmail unCrypt(String nameFolder) {
		UsuarioEmail user = null;

		try {
			nameFolder = geraNomeCriptografado(nameFolder);
			String pasta = System.getProperty("user.home");
			String nameFile = System.getProperty("file.separator") + "Treinamento"+System.getProperty("file.separator");
			
			
			
			File dir = new File(pasta+nameFile+nameFolder + ".cr");
			// dir = new File(dir.getAbsolutePath() + "/" + nameFolder + ".cr");
			
			if (dir.exists()) {
				Cipher dCi = Cipher.getInstance("DESede");
				dCi.init(Cipher.DECRYPT_MODE, key);
					FileInputStream in = new FileInputStream(dir);

					ObjectInputStream os = new ObjectInputStream(in);
					SealedObject sealed = (SealedObject) os.readObject();
					user = (UsuarioEmail) sealed.getObject(dCi);

					os.close();
			}

		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| IOException | ClassNotFoundException | BadPaddingException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void gerarAqruivo() throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
		String pass = "@uto03sc0l4G3n3r3t3dK3y!";
	    byte[] bytes = pass.getBytes("UTF-8");
	    SecretKey key = new SecretKeySpec(bytes, "DESede");
	    this.key = key;

	}

	public static void main(String[] args) {
		try {
			new CriptografiaConfigEmail().gerarAqruivo();
		} catch (InvalidKeySpecException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
