package modelHash;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SHA {

	private List<String> listmode;

	public SHA() {
		init();
		loadData();
	}

	public void loadData() {
		listmode.add("SHA-1");
		listmode.add("SHA-224");
		listmode.add("SHA-256");
		listmode.add("SHA-384");
		listmode.add("SHA-512/224");
		listmode.add("SHA-512/256");

	}

	public String hashString(String mess,String mode) {

		try {
			MessageDigest digest = MessageDigest.getInstance(mode);

			byte[] messdigest = digest.digest(mess.getBytes());

			BigInteger number = new BigInteger(1, messdigest);
			String hashtext = number.toString(16);

			return hashtext;

		} catch (Exception e) {
			// TODO: handle exception
			return "error: " + e.getMessage();
		}

	}

	public String hashFile(String path,String mode) throws NoSuchAlgorithmException, IOException {
		MessageDigest digest = MessageDigest.getInstance(mode);

		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(path)));

		DigestInputStream dis = new DigestInputStream(bufferedInputStream, digest);

		byte[] bytedata = new byte[1024];

		int read = dis.read(bytedata);

		while (read != -1) {
			read = dis.read(bytedata);
		}

		BigInteger number = new BigInteger(1, dis.getMessageDigest().digest());
		String hashtext = number.toString(16);

		return hashtext;
	}

	private void init() {
		listmode = new ArrayList<>();

	}
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		SHA  sha = new SHA();
		System.out.println(sha.hashString("hjhj","SHA-1"));
		System.out.println(sha.hashFile("D:\\OneDrive\\Desktop\\TruongNguyenThienAn.docx","SHA-1"));
		
	}

	public List<String> getListmode() {
		return listmode;
	}

	public void setListmode(List<String> listmode) {
		this.listmode = listmode;
	}
	
}
