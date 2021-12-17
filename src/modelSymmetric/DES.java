package modelSymmetric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;

public class DES {

	static SecureRandom rnd = new SecureRandom();
//	String iv2="12345678";
	static IvParameterSpec iv = new IvParameterSpec(rnd.generateSeed(8));

	public DES() {
	}

	public SecretKey createAutoKey(int keysie) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(keysie);
		return keyGenerator.generateKey();
	}

	public SecretKey convertKeyFromByte(byte[] bkey) {
		SecretKey key = new SecretKeySpec(bkey, "DES");
		return key;
	}

	public SecretKey createKeyByString(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] key1 = key.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		key1 = sha.digest(key1);
		key1 = Arrays.copyOf(key1, 8);
		SecretKeySpec secretKeySpec = new SecretKeySpec(key1, "DES");
		return secretKeySpec;
	}

	public String printBytetoString(byte[] key) {
		return Base64.getEncoder().encodeToString(key);
	}

	public byte[] printStringtoByte(String key) {
		return Base64.getDecoder().decode(key);
	}

	public byte[] encrypt(String message, SecretKey key, String mode, String padding) throws Exception {

		Cipher cipher;
//        IvParameterSpec ips = new IvParameterSpec(iv2.getBytes());
		if (mode.equals("ECB")) {
			cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
			cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		} else {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
		}
		byte[] byteEncrypted = cipher.doFinal(message.getBytes("UTF-8"));
		return byteEncrypted;
	}

	public void encryptFileDES(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {

		File sfile = new File(sFile);
		if (sfile.isFile()) {
			Cipher cipher;
			if (mode.equals("ECB")) {
				cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
				cipher.init(Cipher.ENCRYPT_MODE, key);
			} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
				cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
				cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			} else {
				cipher = Cipher.getInstance("DES");
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
					byte[] output = cipher.update(data,0,length);
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
	public void decryptFileDES(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		File sfile = new File(sFile);
		if (sfile.isFile()) {
			Cipher cipher;
			if (mode.equals("ECB")) {
				cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
				cipher.init(Cipher.DECRYPT_MODE, key);
			} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
				cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
				cipher.init(Cipher.DECRYPT_MODE, key, iv);
			} else {
				cipher = Cipher.getInstance("DES");
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
					byte[] output = cipher.update(data,0,length);
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

	public String decrypt(byte[] encrypt, SecretKey key, String mode, String padding) throws Exception {
		Cipher cipher;
//        IvParameterSpec ips = new IvParameterSpec(iv2.getBytes());

		if (mode.equals("ECB")) {
			cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
			cipher.init(Cipher.DECRYPT_MODE, key);
		} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
			cipher = Cipher.getInstance("DES/" + mode + "/" + padding);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
		} else {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
		}
		byte[] byteEncrypted = cipher.doFinal(encrypt);
		return new String(byteEncrypted, "UTF-8");
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

	public static void main(String[] args) throws Exception {
		DES aes = new DES();
		String message = "ghet mochyy";
		System.out.println("------------Message--------");
		System.out.println(message);

		System.out.println("Sử dụng KeyGenerator để tạo key");
		System.out.println("----------Key------------");
		SecretKey secretKey = aes.createAutoKey(56);
		System.out.println(aes.printBytetoString(secretKey.getEncoded()));
		System.out.println(aes.convertKeyFromByte(secretKey.getEncoded()));
		System.out.println("----------Encrypt------------");
		byte[] encrypt = aes.encrypt(message, secretKey, "", "NoPadding");
		System.out.println(aes.printBytetoString(encrypt));
		System.out.println("----------Decrypt------------");
		System.out.println(aes.decrypt(encrypt, secretKey, "", "NoPadding"));

		// save file key
		aes.saveKeyFile("C:\\Users\\Admin\\Desktop", "key.txt", secretKey.getEncoded());
		System.out.println(aes.readKeyFile("C:\\Users\\Admin\\Desktop\\key.txt"));

//		
//		if(aes.printBytetoString(secretKey.getEncoded()) == aes.printBytetoString(aes.readKeyFile("C:\\Users\\Admin\\Desktop\\key.txt").getEncoded())){
//			System.out.println("ok");
//		}else {
//			System.out.println(aes.printBytetoString(secretKey.getEncoded()));
//			System.out.println(aes.printBytetoString(aes.readKeyFile("C:\\Users\\Admin\\Desktop\\key.txt").getEncoded()));
//			System.out.println("ko");
//		}

//		
//		SecretKey secretKey1 = aes.readKeyFile("C:\\Users\\Admin\\Desktop\\key.txt");
//		
//		System.out.println("----------Encrypt------------");
//		byte[] encrypt1 = aes.encrypt(message, secretKey1, "CBC", "PKCS5Padding");
//		System.out.println(aes.printBytetoString(encrypt1));
//		System.out.println("----------Decrypt------------");
//		System.out.println(aes.decrypt(encrypt1, secretKey1, "CBC", "PKCS5Padding"));
		
		aes.encryptFileDES("D:\\OneDrive\\Desktop\\phanchia.docx", "D:\\OneDrive\\Desktop\\phanchia_encrypt.encrypt", secretKey, "CBC", "PKCS5Padding");
		aes.decryptFileDES("D:\\OneDrive\\Desktop\\phanchia_encrypt.encrypt", "D:\\OneDrive\\Desktop\\phanchia_decrypt.docx", secretKey, "CBC", "PKCS5Padding");

	}
}
