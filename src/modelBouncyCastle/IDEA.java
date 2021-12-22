package modelBouncyCastle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class IDEA {
	static SecureRandom rnd = new SecureRandom();
//	String iv2="12345678";
	static IvParameterSpec iv = new IvParameterSpec(rnd.generateSeed(8));

	Key key;
	KeyGenerator keyGen;
	Cipher encrypt;

	public IDEA() {
		Security.addProvider(new BouncyCastleProvider());
	}

	public SecretKey createAutoKey(int keySize) throws Exception {

		
			KeyGenerator keyGen = KeyGenerator.getInstance("IDEA");
			keyGen.init(keySize);

			return keyGen.generateKey();

//		encrypt.init(Cipher.ENCRYPT_MODE, key);
//		
//		System.out.println(printBytetoString(encrypt.doFinal("jhjhj".getBytes())));
		
	}

	public byte[] encrypt(String message, SecretKey key, String mode, String padding) throws Exception {
		Cipher cipher;
//      IvParameterSpec ips = new IvParameterSpec(iv2.getBytes());
		if (mode.equals("ECB")) {
			cipher = Cipher.getInstance("IDEA/" + mode + "/" + padding);
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
			cipher = Cipher.getInstance("IDEA/" + mode + "/" + padding);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		} else {
			cipher = Cipher.getInstance("IDEA");
			cipher.init(Cipher.ENCRYPT_MODE, key);
		}
		byte[] byteEncrypted = cipher.doFinal(message.getBytes("UTF-8"));
		return byteEncrypted;
	}
	public String decryptAES(byte[] encrypt,SecretKey key,String mode, String padding) throws Exception{
    	Cipher cipher;
		if (mode.equals("IDEA")) {
			cipher = Cipher.getInstance("IDEA/" + mode + "/" + padding);
			cipher.init(Cipher.DECRYPT_MODE, key);
		} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
			cipher = Cipher.getInstance("IDEA/" + mode + "/" + padding);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
		} else {
			cipher = Cipher.getInstance("IDEA");
			cipher.init(Cipher.DECRYPT_MODE, key);
		}
		byte[] byteEncrypted = cipher.doFinal(encrypt);
		return new String(byteEncrypted, "UTF-8");
    }
	
	public void encryptFileAES(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {

		File sfile = new File(sFile);
		if (sfile.isFile()) {
			Cipher cipher;
			if (mode.equals("ECB")) {
				cipher = Cipher.getInstance("IDEA/" + mode + "/" + padding);
				cipher.init(Cipher.ENCRYPT_MODE, key);
			} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
				cipher = Cipher.getInstance("IDEA/" + mode + "/" + padding);
				cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			} else {
				cipher = Cipher.getInstance("IDEA");
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
	public SecretKey convertKeyFromByte(byte[] bkey) {
		SecretKey key = new SecretKeySpec(bkey, "IDEA");
		return key;
	}
	
	
	public void decryptFileAES(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		File sfile = new File(sFile);
		if (sfile.isFile()) {
			Cipher cipher;
			if (mode.equals("ECB")) {
				cipher = Cipher.getInstance("IDEA/" + mode + "/" + padding);
				cipher.init(Cipher.DECRYPT_MODE, key);
			} else if (mode.equals("CBC")||mode.equals("PCBC")||mode.equals("CFB")||mode.equals("OFB")||mode.equals("CTR")) {
				cipher = Cipher.getInstance("IDEA/" + mode + "/" + padding);
				cipher.init(Cipher.DECRYPT_MODE, key, iv);
			} else {
				cipher = Cipher.getInstance("IDEA");
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
	

	public String printBytetoString(byte[] key) {
		return Base64.getEncoder().encodeToString(key);
	}

	public byte[] printStringtoByte(String key) {
		return Base64.getDecoder().decode(key);
	}

	public static void main(String[] args) throws Exception {

		IDEA hjhj = new IDEA();

		String message = "ghet mochyy";
		System.out.println("------------Message--------");
		System.out.println(message);

		// System.out.println("----------Key------------");
		SecretKey secretKey = hjhj.createAutoKey(1024);
		System.out.println(hjhj.printBytetoString(secretKey.getEncoded()));
		System.out.println("----------Encrypt------------");
		byte[] encrypt = hjhj.encrypt(message, secretKey, "CBC", "PKCS5Padding");
		System.out.println(hjhj.printBytetoString(encrypt));
		System.out.println(hjhj.decryptAES(encrypt,secretKey,"CBC","PKCS5Padding"));

	//	hjhj.encryptFileAES("D:\\OneDrive\\Desktop\\TruongNguyenThienAn.docx", "D:\\OneDrive\\Desktop\\TruongNguyenThienAn.enc",secretKey,"ECB", "Nopadding");
	//	hjhj.decryptFileAES("D:\\OneDrive\\Desktop\\TruongNguyenThienAn.enc", "D:\\OneDrive\\Desktop\\TruongNguyenThienAn_de_2.docx", secretKey, "ECB", "Nopadding");
		
	}
}
