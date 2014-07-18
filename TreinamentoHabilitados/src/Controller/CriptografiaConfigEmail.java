package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import com.itextpdf.text.pdf.codec.Base64;
import com.mysql.jdbc.Util;

import Model.ICrypt;
import Model.UsuarioEmail;

public class CriptografiaConfigEmail implements ICrypt<UsuarioEmail> {

	private SecretKey key;
	private MessageDigest algorithm;

	public CriptografiaConfigEmail() {
		try {
			String nameFile = geraNomeCriptografado("key");
			File fileKey = new File(getClass().getResource(
					"/Resources/FilesConfig").getPath()
					+ "/" + nameFile + ".crkey");
			if (!fileKey.exists()) {
				gerarAqruivo();
			}

			FileInputStream in = new FileInputStream(fileKey);
			ObjectInputStream os = new ObjectInputStream(in);
			SecretKey key = (SecretKey) os.readObject();
			os.close();
			this.key = key;

		} catch (ClassNotFoundException | IOException
				| NoSuchAlgorithmException e) {

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
	public void encrypt(UsuarioEmail value, File dir, String nameFolder) {

		try {
			nameFolder = geraNomeCriptografado(nameFolder);
			dir = new File(dir.getAbsolutePath() + "/" + nameFolder + ".cr");
			if(!dir.exists()){
			Cipher ci = Cipher.getInstance("DES");
			ci.init(Cipher.ENCRYPT_MODE, key);
			SealedObject usuarioConfig = new SealedObject(value, ci);
			FileOutputStream ou = new FileOutputStream(dir);
			ObjectOutputStream os = new ObjectOutputStream(ou);
			os.writeObject(usuarioConfig);
			os.flush();
			os.close();
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| IOException e) {
			e.printStackTrace();
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
	public UsuarioEmail unCrypt(File dir, String nameFolder) {
		UsuarioEmail user = null;

		try {
			nameFolder = geraNomeCriptografado(nameFolder);

			dir = new File(dir.getAbsolutePath() + "/" + nameFolder + ".cr");

			Cipher dCi = Cipher.getInstance("DES");
			dCi.init(Cipher.DECRYPT_MODE, key);
			if (dir.exists()) {
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

	public void gerarAqruivo() {
		try {
			StringBuilder builder = new StringBuilder();
			algorithm = MessageDigest.getInstance("md5");
			byte[] messageDigest = algorithm.digest("key".getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			String nameFile = hexString.toString();
			File fileKey = new File(getClass().getResource(
					"/Resources/FilesConfig").getPath()
					+ "/" + nameFile + ".crkey");
			SecretKey chave = KeyGenerator.getInstance("DES").generateKey();
			FileOutputStream ou = new FileOutputStream(fileKey);
			ObjectOutputStream os = new ObjectOutputStream(ou);
			os.writeObject(chave);
		} catch (IOException e) {

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new CriptografiaConfigEmail().gerarAqruivo();
	}

}
