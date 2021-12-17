package modelSymmetric;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

public class AlgorithmsModel {

	private List<AlgorithmsItem> algorithmsItems;
	private DES des;
	private DESede deSede;
	private Blowfish blowfish;
	private AES aes;
	private RC2 rc2;
	private RC4 rc4;

	public AlgorithmsModel() {
		init();
		loadData();

	}

	public void loadData() {
		// DES
		AlgorithmsItem des = new AlgorithmsItem();

		List<Integer> keysizeDES = new ArrayList<Integer>();
		keysizeDES.add(56);

		List<String> modes = new ArrayList<String>();
		modes.add("NONE");
		modes.add("CBC");
		modes.add("ECB");
		modes.add("PCBC");
		modes.add("CFB");
		modes.add("OFB");
		modes.add("CTR");

		List<String> paddings = new ArrayList<String>();
		paddings.add("NoPadding");
		paddings.add("PKCS5Padding");
		paddings.add("ISO10126Padding");
		

		des.setAlgorithms("DES");
		des.setKeySize(keysizeDES);
		des.setMode(modes);
		des.setPadding(paddings);
		algorithmsItems.add(des);

		// DEsede
		AlgorithmsItem desede = new AlgorithmsItem();

		List<Integer> keysizeDESede = new ArrayList<Integer>();
		keysizeDESede.add(112);
		keysizeDESede.add(168);

		List<String> modedesede = new ArrayList<String>();
		modedesede.add("NONE");
		modedesede.add("CBC");
		modedesede.add("ECB");
		modedesede.add("PCBC");
		modedesede.add("CFB");
		modedesede.add("OFB");
		modedesede.add("CTR");

		List<String> paddingdesede = new ArrayList<String>();
		paddingdesede.add("NoPadding");
		paddingdesede.add("PKCS5Padding");
		paddingdesede.add("ISO10126Padding");

		desede.setAlgorithms("DESede");
		desede.setKeySize(keysizeDESede);
		desede.setMode(modedesede);
		desede.setPadding(paddingdesede);

		algorithmsItems.add(desede);

		// blowfish
		AlgorithmsItem blowfish = new AlgorithmsItem();

		List<Integer> keysizeBlowfish = new ArrayList<Integer>();
		keysizeBlowfish.add(32);
		keysizeBlowfish.add(64);
		keysizeBlowfish.add(128);
		keysizeBlowfish.add(264);

		List<String> modeblowfish = new ArrayList<String>();
		modeblowfish.add("NONE");
		modeblowfish.add("CBC");
		modeblowfish.add("ECB");
		modeblowfish.add("PCBC");
		modeblowfish.add("CFB");
		modeblowfish.add("OFB");
		modeblowfish.add("CTR");

		List<String> paddingblowfish = new ArrayList<String>();
		paddingblowfish.add("NoPadding");
		paddingblowfish.add("PKCS5Padding");
		paddingblowfish.add("ISO10126Padding");


		blowfish.setAlgorithms("Blowfish");
		blowfish.setKeySize(keysizeBlowfish);
		blowfish.setMode(modeblowfish);
		blowfish.setPadding(paddingblowfish);

		algorithmsItems.add(blowfish);

		// aes
		AlgorithmsItem aes = new AlgorithmsItem();

		List<Integer> keysizeaes = new ArrayList<Integer>();
		keysizeaes.add(128);
		keysizeaes.add(192);
		keysizeaes.add(256);

		List<String> modeblowaes = new ArrayList<String>();
		modeblowaes.add("NONE");
		modeblowaes.add("CBC");
		modeblowaes.add("ECB");
		modeblowaes.add("PCBC");
		modeblowaes.add("CFB");
		modeblowaes.add("OFB");
		modeblowaes.add("CTR");


		List<String> paddingaes = new ArrayList<String>();
		paddingaes.add("NoPadding");
		paddingaes.add("PKCS1Padding");
		paddingaes.add("PKCS5Padding");
		paddingaes.add("ISO10126Padding");


		aes.setAlgorithms("AES");
		aes.setKeySize(keysizeaes);
		aes.setMode(modeblowaes);
		aes.setPadding(paddingaes);

		algorithmsItems.add(aes);

		// rc2
		AlgorithmsItem rc2 = new AlgorithmsItem();

		List<Integer> keysizerc2 = new ArrayList<Integer>();
		keysizerc2.add(56);
		keysizerc2.add(64);
		keysizerc2.add(128);
		keysizerc2.add(256);
		keysizerc2.add(512);
		keysizerc2.add(1024);

		List<String> moderc2 = new ArrayList<String>();
		moderc2.add("NONE");
		moderc2.add("CBC");
		moderc2.add("ECB");
		moderc2.add("PCBC");
		moderc2.add("CFB");
		moderc2.add("OFB");
		moderc2.add("CTR");

		List<String> paddingrc2 = new ArrayList<String>();
		paddingrc2.add("NoPadding");
		paddingrc2.add("PKCS5Padding");
		paddingrc2.add("ISO10126Padding");


		rc2.setAlgorithms("RC2");
		rc2.setKeySize(keysizerc2);
		rc2.setMode(moderc2);
		rc2.setPadding(paddingrc2);

		algorithmsItems.add(rc2);
		
		// RC4
				AlgorithmsItem rc4 = new AlgorithmsItem();

				List<Integer> keysizerc4 = new ArrayList<Integer>();
				keysizerc4.add(56);
				keysizerc4.add(64);
				keysizerc4.add(128);
				keysizerc4.add(256);
				keysizerc4.add(512);
				keysizerc4.add(1024);

				List<String> moderc4 = new ArrayList<String>();
				moderc4.add("NONE");
				moderc4.add("ECB");

				List<String> paddingrc4 = new ArrayList<String>();
				paddingrc4.add("NoPadding");
		

				rc4.setAlgorithms("RC4");
				rc4.setKeySize(keysizerc4);
				rc4.setMode(moderc4);
				rc4.setPadding(paddingrc4);

				algorithmsItems.add(rc4);

	}

	public SecretKey readKeyFile(String path, String mode) throws IOException {
		if (mode.equals("DES")) {
			return des.readKeyFile(path);
		} else if (mode.equals("DESede")) {
			return deSede.readKeyFile(path);
		} else if (mode.equals("Blowfish")) {
			System.out.println("do này" + path);
			return blowfish.readKeyFile(path);
		} else if (mode.equals("AES")) {
			return aes.readKeyFile(path);
		}else if(mode.equals("RC2")) {
			return rc2.readKeyFile(path);
		}
		else if(mode.equals("RC4")) {
			return rc4.readKeyFile(path);
		}
		return null;
	}

	public List<Integer> getDataKeySizeByID(String mode) {

		List<Integer> list = new ArrayList<Integer>();

		for (AlgorithmsItem algorithmsItem : getAlgorithmsItems()) {
			if (algorithmsItem.getAlgorithms().equals(mode)) {
				list.addAll(algorithmsItem.getKeySize());
				break;
			}
		}

		return list;
	}

	public List<String> getDataModeByID(String mode) {

		List<String> list = new ArrayList<String>();

		for (AlgorithmsItem algorithmsItem : getAlgorithmsItems()) {
			if (algorithmsItem.getAlgorithms().equals(mode)) {
				list.addAll(algorithmsItem.getMode());
				break;
			}
		}

		return list;
	}

	public List<String> getDataPaddingByID(String mode) {

		List<String> list = new ArrayList<String>();

		for (AlgorithmsItem algorithmsItem : getAlgorithmsItems()) {
			if (algorithmsItem.getAlgorithms().equals(mode)) {
				list.addAll(algorithmsItem.getPadding());
				break;
			}
		}

		return list;
	}
	
	// EncryptFile
	public void encryptFileDES(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		des.encryptFileDES(sFile, dFile, key, mode, padding);
	}
	public void encryptFileBlowfish(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		blowfish.encryptFileBlowfish(sFile, dFile, key, mode, padding);
	}
	public void encryptFileDeSede(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		deSede.encryptFileDESede(sFile, dFile, key, mode, padding);
	}
	public void encryptFileAES(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		aes.encryptFileAES(sFile, dFile, key, mode, padding);
	}
	public void encryptFileRC2(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		rc2.encryptFileRC2(sFile, dFile, key, mode, padding);
	}
	public void encryptFileRC4(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		rc4.encryptFileRC4(sFile, dFile, key, mode, padding);
	}

	// Encrypt
	public String encryptDES(String message, SecretKey key, String mode, String padding) throws Exception {
		return des.printBytetoString(des.encrypt(message, key, mode, padding));
	}

	public String encryptDESede(String message, SecretKey key, String mode, String padding) throws Exception {
		return deSede.printBytetoString(deSede.encrypt(message, key, mode, padding));
	}

	public String encryptBlowfish(String message, SecretKey key, String mode, String padding) throws Exception {
		return blowfish.printBytetoString(blowfish.encrypt(message, key, mode, padding));
	}

	public String encryptAES(String message, SecretKey key, String mode, String padding) throws Exception {
		return aes.printBytetoString(aes.encryptAES(message, key, mode, padding));
	}
	public String encryptRC2(String message, SecretKey key, String mode, String padding) throws Exception {
		return rc2.printBytetoString(rc2.encryptAES(message, key, mode, padding));
	}
	public String encryptRC4(String message, SecretKey key, String mode, String padding) throws Exception {
		return rc4.printBytetoString(rc4.encryptAES(message, key, mode, padding));
	}

	
	
	// DecryptFile
	public void decryptFileDES(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		des.decryptFileDES(sFile, dFile, key, mode, padding);
	}
	public void decryptFileBlowfish(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		blowfish.decryptFileBlowfish(sFile, dFile, key, mode, padding);
	}
	public void decryptFileDESede(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		deSede.decryptFileDESede(sFile, dFile, key, mode, padding);
	}
	public void decryptFileAES(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		aes.decryptFileAES(sFile, dFile, key, mode, padding);
	}
	public void decryptFileRC2(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		rc2.decryptFileRC2(sFile, dFile, key, mode, padding);
	}
	public void decryptFileRC4(String sFile, String dFile, SecretKey key, String mode, String padding) throws Exception {
		rc4.decryptFileRC4(sFile, dFile, key, mode, padding);
	}
	// Decrpyt
	public String decryptDES(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return des.decrypt(des.printStringtoByte(encrypt), key, mode, padding);
	}

	public String decryptDESede(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return deSede.decrypt(deSede.printStringtoByte(encrypt), key, mode, padding);
	}

	public String decryptBlowfish(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return blowfish.decrypt(blowfish.printStringtoByte(encrypt), key, mode, padding);
	}

	public String decryptAES(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return aes.decryptAES(aes.printStringtoByte(encrypt), key, mode, padding);
	}
	public String decryptRC2(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return rc2.decryptAES(rc2.printStringtoByte(encrypt), key, mode, padding);
	}
	public String decryptRC4(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return rc4.decryptAES(rc4.printStringtoByte(encrypt), key, mode, padding);
	}

	public void init() {
		algorithmsItems = new ArrayList<>();
		des = new DES();
		deSede = new DESede();
		blowfish = new Blowfish();
		aes = new AES();
		rc2 = new RC2();
		rc4 = new RC4();
	}

	public List<AlgorithmsItem> getAlgorithmsItems() {
		return algorithmsItems;
	}

	public void setAlgorithmsItems(List<AlgorithmsItem> algorithmsItems) {
		this.algorithmsItems = algorithmsItems;
	}

}
