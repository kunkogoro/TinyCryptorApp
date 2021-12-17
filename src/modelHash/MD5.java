package modelHash;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public String hashString(String mess) {
		
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			
			byte[] messdigest =digest.digest(mess.getBytes());
			
			BigInteger number = new BigInteger(1,messdigest);
			String hashtext = number.toString(16);
			
			return hashtext;
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return "error: " + e.getMessage();
		}
		
	}
	public String hashFile(String path) throws NoSuchAlgorithmException, IOException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(path)));
		
		DigestInputStream dis = new DigestInputStream(bufferedInputStream, digest);
		
		byte[] bytedata = new byte[1024];
		
		int read = dis.read(bytedata);
		
		while(read != -1) {
			read = dis.read(bytedata);
		}
		
		BigInteger number = new BigInteger(1,dis.getMessageDigest().digest());
		String hashtext = number.toString(16);
 		
		return hashtext;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		MD5 md5 = new MD5();
		
		System.out.println(md5.hashString("hjhj"));
		
		System.out.println(md5.hashFile("D:\\OneDrive\\Desktop\\TruongNguyenThienAn.docx"));
		
	}
}
