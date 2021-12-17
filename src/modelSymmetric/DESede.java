package modelSymmetric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class DESede {

	static SecureRandom rnd = new SecureRandom();
//	String iv2="12345678";
	static IvParameterSpec iv = new IvParameterSpec(rnd.generateSeed(8));

	public DESede() {
	}

	public SecretKey createAutoKey(int keySize) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
		keyGenerator.init(keySize);
		return keyGenerator.generateKey();
	}

	public SecretKey convertKeyFromByte(byte[] bkey) {
		SecretKey key = new SecretKeySpec(bkey, "DESede");
		return key;
	}

	public SecretKey createKeyByString(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] key1 = key.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		key1 = sha.digest(key1);
		key1 = Arrays.copyOf(key1, 24);
		SecretKeySpec secretKeySpec = new SecretKeySpec(key1, "DESede");
		return secretKeySpec;
	}
	

	public String printBytetoString(byte[] key) {
		return Base64.getEncoder().encodeToString(key);
	}

	public byte[] printStringtoByte(String key) {
		return Base64.getDecoder().decode(key);
	}

	public byte[] encrypt(String message, SecretKey key, String mode, String padding) throws Exception {

		System.out.println(message + "-" + mode + "-" + padding);
		System.out.println(printBytetoString(key.getEncoded()));

		Cipher cipher;
//      IvParameterSpec ips = new IvParameterSpec(iv2.getBytes());
		if (mode.equals("ECB")) {
			cipher = Cipher.getInstance("TripleDES/" + mode + "/" + padding);
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
			cipher = Cipher.getInstance("TripleDES/" + mode + "/" + padding);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		} else {
			cipher = Cipher.getInstance("TripleDES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
		}
		byte[] byteEncrypted = cipher.doFinal(message.getBytes("UTF-8"));
		return byteEncrypted;
	}

	public String decrypt(byte[] encrypt, SecretKey key, String mode, String padding) throws Exception {
		Cipher cipher;
		if (mode.equals("ECB")) {
			cipher = Cipher.getInstance("DESede/" + mode + "/" + padding);
			cipher.init(Cipher.DECRYPT_MODE, key);
		} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
			cipher = Cipher.getInstance("DESede/" + mode + "/" + padding);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
		} else {
			cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.DECRYPT_MODE, key);
		}
		byte[] byteEncrypted = cipher.doFinal(encrypt);
		return new String(byteEncrypted, "UTF-8");
	}

	public SecretKey readKeyFile(String path) throws IOException {
		File file = new File(path);
		byte[] data = new byte[2048];
		byte[] dataAfter = null;
		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

			int length = bufferedInputStream.read(data);

			dataAfter = new byte[length];

			for (int i = 0; i < dataAfter.length; i++) {
				dataAfter[i] = data[i];
			}

			bufferedInputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return convertKeyFromByte(dataAfter);

	}

	public void saveKeyFile(String filePath, String name, byte[] data) throws IOException {

		File file = new File(filePath, name);

		try {
			BufferedOutputStream bufferedInputStream = new BufferedOutputStream(new FileOutputStream(file));

			bufferedInputStream.write(data, 0, data.length);

			bufferedInputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void encryptFileDESede(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {

		System.out.println(mode + "-" + padding);
		File sfile = new File(sFile);
		if (sfile.isFile()) {
			Cipher cipher;
			if (mode.equals("ECB")) {
				cipher = Cipher.getInstance("DESede/" + mode + "/" + padding);
				cipher.init(Cipher.ENCRYPT_MODE, key);
			} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
				cipher = Cipher.getInstance("DESede/" + mode + "/" + padding);
				cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			} else {
				cipher = Cipher.getInstance("DESede");
				cipher.init(Cipher.ENCRYPT_MODE, key);
			}

			byte[] data = new byte[1024];
			int length;

			File dfile = new File(dFile);
			BufferedInputStream bufferedInputStream = null;
			BufferedOutputStream bufferedOutputStream = null;
			try {
				bufferedInputStream = new BufferedInputStream(new FileInputStream(sfile));
				bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dfile));

				while ((length = bufferedInputStream.read(data)) != -1) {
					byte[] output = cipher.update(data, 0, length);
					if (output != null) {
						bufferedOutputStream.write(output);
					}
				}
				byte[] output = cipher.doFinal();
				if (output != null) {
					bufferedOutputStream.write(output);
				}

				bufferedInputStream.close();
				bufferedOutputStream.flush();
				bufferedOutputStream.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				bufferedInputStream.close();
				bufferedOutputStream.close();
			}

		}

	}

	public void decryptFileDESede(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		File sfile = new File(sFile);
		if (sfile.isFile()) {
			Cipher cipher;
			if (mode.equals("ECB")) {
				cipher = Cipher.getInstance("DESede/" + mode + "/" + padding);
				cipher.init(Cipher.DECRYPT_MODE, key);
			} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
				cipher = Cipher.getInstance("DESede/" + mode + "/" + padding);
				cipher.init(Cipher.DECRYPT_MODE, key, iv);
			} else {
				cipher = Cipher.getInstance("DESede");
				cipher.init(Cipher.DECRYPT_MODE, key);
			}

			byte[] data = new byte[1024];
			int length;

			File dfile = new File(dFile);
			BufferedInputStream bufferedInputStream = null;
			BufferedOutputStream bufferedOutputStream = null;
			try {
				bufferedInputStream = new BufferedInputStream(new FileInputStream(sfile));
				bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dfile));

				while ((length = bufferedInputStream.read(data)) != -1) {
					byte[] output = cipher.update(data, 0, length);
					if (output != null) {
						bufferedOutputStream.write(output);
					}
				}
				byte[] output = cipher.doFinal();
				if (output != null) {
					bufferedOutputStream.write(output);
				}

				bufferedInputStream.close();
				bufferedOutputStream.flush();
				bufferedOutputStream.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				bufferedInputStream.close();
				bufferedOutputStream.close();
			}

		}
	}

	public static void main(String[] args) throws Exception {
		DESede aes = new DESede();
		String message = "ok";
		System.out.println("------------Message--------");
		System.out.println(message);

//
        System.out.println("----------Key------------");
        SecretKey secretKey = aes.createAutoKey(112);
        System.out.println(aes.printBytetoString(secretKey.getEncoded()));
        System.out.println("----------Encrypt------------");
        byte[] encrypt = aes.encrypt(message,secretKey,"ECB","PKCS5Padding");
        System.out.println(aes.printBytetoString(encrypt));
        System.out.println("----------Decrypt------------");
        System.out.println(aes.decrypt(encrypt,secretKey,"ECB","PKCS5Padding"));

		// save file key
		aes.saveKeyFile("D:\\OneDrive\\Desktop\\Desktop", "keydese.txt", secretKey.getEncoded());
//		System.out.println(aes.printBytetoString(aes.readKeyFile("C:\\Users\\Admin\\Desktop\\keydese.txt").getEncoded()));
//		byte[] encrypt1 = aes.readKeyFile("C:\\Users\\Admin\\Desktop\\keydese.txt").getEncoded();
//		System.out.println(aes.printBytetoString(encrypt1));
		
		aes.encryptFileDESede("D:\\OneDrive\\Desktop\\phanchia.docx", "D:\\OneDrive\\Desktop\\phanchia_encrypt_de.encrypt", secretKey, "CBC", "PKCS5Padding");
		aes.decryptFileDESede("D:\\OneDrive\\Desktop\\phanchia_encrypt_de.encrypt", "D:\\OneDrive\\Desktop\\phanchia_decrypt.docx", secretKey, "CBC", "PKCS5Padding");

	}
}
