package modelAsymmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import modelKey.GenKeyPairModel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RSA {

	private static Base64.Encoder encoder = Base64.getEncoder();
	private static Base64.Decoder decoder = Base64.getDecoder();
	static SecureRandom rnd = new SecureRandom();

	public KeyPair createAutoKey(int keysize) throws NoSuchAlgorithmException {
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		keyGenerator.initialize(keysize);
		KeyPair keys = keyGenerator.generateKeyPair();
		return keys;
	}

	public String printBytetoString(byte[] key) {
		return Base64.getEncoder().encodeToString(key);
	}

	public PublicKey readPublicKey(final String path)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] bytedata = Files.readAllBytes(Paths.get(path));
		final String publicStringKey = new String(bytedata, StandardCharsets.UTF_8);
		bytedata = decoder.decode(publicStringKey);
		final X509EncodedKeySpec ks = new X509EncodedKeySpec(bytedata);
		final KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(ks);
	}

	public PrivateKey readPrivateKey(final String path)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] bytedata = Files.readAllBytes(Paths.get(path));
		final String publicStringKey = new String(bytedata, StandardCharsets.UTF_8);
		bytedata = decoder.decode(publicStringKey);
		final PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytedata);
		final KeyFactory kf = KeyFactory.getInstance("RSA");
		final PrivateKey pub = kf.generatePrivate(ks);
		return pub;
	}
	 public boolean saveKeyFile(final String filePath, final byte[] data) throws IOException {
	        final File file = new File(filePath);
	        try {
	            final BufferedOutputStream bufferedInputStream = new BufferedOutputStream(new FileOutputStream(file));
	            bufferedInputStream.write(encoder.encodeToString(data).getBytes(StandardCharsets.UTF_8));
	            bufferedInputStream.close();
	            return true;
	        }
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	public void encryptRSAWithAES(PublicKey pub, String inputFile, String outFile, String mode, String padding)
			throws NoSuchAlgorithmException {

		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		SecretKey skey = kgen.generateKey();

		byte[] iv = new byte[128 / 8];
		rnd.nextBytes(iv);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outFile)))) {

			{
				Cipher cipher;

				if (mode.equals("ECB")) {
					cipher = Cipher.getInstance("RSA/" + mode + "/" + padding);
					cipher.init(Cipher.ENCRYPT_MODE, pub);
				} else if (mode.equals("CBC") || mode.equals("PCBC") || mode.equals("CFB") || mode.equals("OFB")
						|| mode.equals("CTR")) {
					cipher = Cipher.getInstance("RSA/" + mode + "/" + padding);
					cipher.init(Cipher.ENCRYPT_MODE, pub, ivSpec);
				} else {
					cipher = Cipher.getInstance("RSA");
					cipher.init(Cipher.ENCRYPT_MODE, pub);
				}

				byte[] b = cipher.doFinal(skey.getEncoded());
				out.write(b);
				System.out.println("AES" + b.length);
			}
			out.write(iv);
			System.out.println("IV " + iv.length);

			Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ci.init(Cipher.ENCRYPT_MODE, skey, ivSpec);
			try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File(inputFile)))) {

				proccessFile(ci, inputStream, out);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void decryptRSAWithAES(PrivateKey pub, String inputFile, String outFile, String mode, String padding)
			throws NoSuchAlgorithmException {

//		KeyGenerator kgen = KeyGenerator.getInstance("AES");
//		kgen.init(128);
//		SecretKey skey = kgen.generateKey();
//
		byte[] iv = new byte[128 / 8];
		rnd.nextBytes(iv);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(inputFile)))) {
			
			SecretKeySpec skey = null;

			{
				Cipher cipher;

				if (mode.equals("ECB")) {
					cipher = Cipher.getInstance("RSA/" + mode + "/" + padding);
					cipher.init(Cipher.DECRYPT_MODE, pub);
				} else if (mode.equals("CBC") || mode.equals("PCBC") || mode.equals("CFB") || mode.equals("OFB")
						|| mode.equals("CTR")) {
					cipher = Cipher.getInstance("RSA/" + mode + "/" + padding);
					cipher.init(Cipher.DECRYPT_MODE, pub, ivSpec);
				} else {
					cipher = Cipher.getInstance("RSA");
					cipher.init(Cipher.DECRYPT_MODE, pub);
				}

				byte[] b = new byte[128];
				in.read(b);
				byte[] keyb = cipher.doFinal(b);
				skey = new SecretKeySpec(keyb, "AES");
				
			}
			
			byte[] iv1 = new byte[128/8];
			in.read(iv1);
			IvParameterSpec iSpec = new IvParameterSpec(iv1);
			
			Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ci.init(Cipher.DECRYPT_MODE, skey, iSpec);
			
			try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outFile)))) {

				proccessFile(ci, in, out);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void proccessFile(Cipher ci, BufferedInputStream inputStream, BufferedOutputStream outputStream)
			throws IOException, IllegalBlockSizeException, BadPaddingException {

		byte[] ibuf = new byte[1024];
		int len;
		while ((len = inputStream.read(ibuf)) != -1) {
			byte[] output = ci.update(ibuf, 0, len);
			if (output != null)
				outputStream.write(output);
		}
		byte[] output = ci.doFinal();
		if (output != null)
			outputStream.write(output);
		outputStream.close();

	}

	public byte[] encrypt(String message, KeyPair key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
		byte[] byteEncrypted = cipher.doFinal(message.getBytes("UTF-8"));
		return byteEncrypted;
	}

	public String decrypt(byte[] encrypt, KeyPair key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
		byte[] byteEncrypted = cipher.doFinal(encrypt);
		return new String(byteEncrypted, "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		RSA aes = new RSA();
//		String message = "ghet mochyy";
//		System.out.println("------------Message--------");
//		System.out.println(message);

		//System.out.println("----------Key------------");
		KeyPair secretKey = aes.createAutoKey(1024);
//		System.out.println(aes.printBytetoString(secretKey.getPublic().getEncoded()));
//		System.out.println("----------Encrypt------------");
//		byte[] encrypt = aes.encrypt(message, secretKey);
//		System.out.println(aes.printBytetoString(encrypt));
//		System.out.println("----------Decrypt------------");
//		System.out.println(aes.decrypt(encrypt, secretKey));
		
        System.out.println(aes.saveKeyFile("D:\\OneDrive\\Desktop\\privatekey1.key", secretKey.getPrivate().getEncoded()));
        System.out.println(aes.saveKeyFile("D:\\OneDrive\\Desktop\\publickey1.key", secretKey.getPublic().getEncoded()));
		
		aes.encryptRSAWithAES(aes.readPublicKey("D:\\OneDrive\\Desktop\\publickey2.key"),"D:\\OneDrive\\Desktop\\TruongNguyenThienAn.docx", "D:\\OneDrive\\Desktop\\TruongNguyenThienAn.enc","ECB", "OAEPWithSHA1AndMGF1Padding");
		aes.decryptRSAWithAES(aes.readPrivateKey("D:\\OneDrive\\Desktop\\privatekey2.key"),"D:\\OneDrive\\Desktop\\TruongNguyenThienAn.enc", "D:\\OneDrive\\Desktop\\TruongNguyenThienAn_de_2.docx", "ECB", "OAEPWithSHA1AndMGF1Padding");

	}
}
