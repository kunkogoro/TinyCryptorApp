package modelPBE;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import modelSymmetric.AlgorithmsItem;


public class PBE {
	
	private List<String> listALg;
	
	public PBE() {
		listALg = new ArrayList<String>();
		loadData();
	}
	
	private void loadData() {
	listALg.add("PBEWithMD5AndDES");
	listALg.add("PBEWithMD5AndTripleDES");
	listALg.add("PBEWithSHA1AndRC2_40");
	listALg.add("PBEWithSHA1AndRC4_40");
	listALg.add("PBEWithSHA1AndRC2_128");
	listALg.add("PBEWithSHA1AndRC4_128");
		
	}

	private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
	private static final byte[] SALT = {
	    (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
	    (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
	};

	public static void main(String[] args) throws Exception {
	    String originalPassword = "hihi";
	    System.out.println("Original password: " + originalPassword);
	    String encryptedPassword = encrypt(originalPassword,"123","PBEWithSHA1AndRC2_40");
	    System.out.println("Encrypted password: " + encryptedPassword);
	    String decryptedPassword = decrypt(encryptedPassword,"123","PBEWithSHA1AndRC2_40");
	    System.out.println("Decrypted password: " + decryptedPassword);
	}
	

	public static String encrypt(String property,String password,String mode) 
	throws GeneralSecurityException, UnsupportedEncodingException {
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(mode);
	    SecretKey key = keyFactory.generateSecret(new PBEKeySpec(password.toCharArray()));
	    Cipher pbeCipher = Cipher.getInstance(mode);
	    pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	    return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
	}

	private static String base64Encode(byte[] bytes) {
	    // NB: This class is internal, and you probably should use another impl
	    return Base64.getEncoder().encodeToString(bytes);
	}

	public static String decrypt(String property,String password,String mode) 
	throws GeneralSecurityException,       IOException {
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(mode);
	    SecretKey key = keyFactory.generateSecret(new PBEKeySpec(password.toCharArray()));
	    Cipher pbeCipher = Cipher.getInstance(mode);
	    pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	    return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	}

	private static byte[] base64Decode(String property) throws IOException {
	    // NB: This class is internal, and you probably should use another impl
	    return Base64.getDecoder().decode(property);
	}

	public List<String> getAlgorithmsItems() {
		// TODO Auto-generated method stub
		return listALg;
	}


	}
