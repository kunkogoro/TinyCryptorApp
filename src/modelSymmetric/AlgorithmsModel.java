package modelSymmetric;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import modelBouncyCastle.CAST5;
import modelBouncyCastle.CAST6;
import modelBouncyCastle.DSTU7624;
import modelBouncyCastle.IDEA;
import modelBouncyCastle.RC6;

public class AlgorithmsModel {

	private List<AlgorithmsItem> algorithmsItems;
	private List<AlgorithmsItem> bouncycastleAlg;
	private DES des;
	private DESede deSede;
	private Blowfish blowfish;
	private AES aes;
	private RC2 rc2;
	private RC4 rc4;
	private CAST5 cast5;
	private CAST6 cast6;
	private RC6 rc6;
	private DSTU7624 dstu7624;
	private IDEA idea;

	public AlgorithmsModel() {
		init();
		loadData();
		loadData2();

	}

	public void loadData2() {
		AlgorithmsItem des = new AlgorithmsItem();

		List<Integer> keysizecast5 = new ArrayList<Integer>();
		keysizecast5.add(32);
		keysizecast5.add(45);
		keysizecast5.add(56);
		keysizecast5.add(64);
		keysizecast5.add(128);

		List<String> modes = new ArrayList<String>();
		modes.add("NONE");
		modes.add("CBC");
		modes.add("ECB");
		modes.add("CFB");
		modes.add("OFB");
		modes.add("CTR");

		List<String> paddings = new ArrayList<String>();
		paddings.add("NoPadding");
		paddings.add("PKCS5Padding");
		paddings.add("ISO10126Padding");

		des.setAlgorithms("CAST5");
		des.setKeySize(keysizecast5);
		des.setMode(modes);
		des.setPadding(paddings);
		bouncycastleAlg.add(des);

		// cast 6

		AlgorithmsItem cast6 = new AlgorithmsItem();

		List<Integer> keysizeDEScast6 = new ArrayList<Integer>();
		keysizeDEScast6.add(32);
		keysizeDEScast6.add(45);
		keysizeDEScast6.add(56);
		keysizeDEScast6.add(64);
		keysizeDEScast6.add(128);
		keysizeDEScast6.add(156);
		keysizeDEScast6.add(256);

		List<String> modescast6 = new ArrayList<String>();
		modescast6.add("NONE");
		modescast6.add("CBC");
		modescast6.add("ECB");
		modescast6.add("CFB");
		modescast6.add("OFB");
		modescast6.add("CTR");

		List<String> paddingscast6 = new ArrayList<String>();
		paddingscast6.add("NoPadding");
		paddingscast6.add("PKCS5Padding");
		paddingscast6.add("ISO10126Padding");

		cast6.setAlgorithms("CAST6");
		cast6.setKeySize(keysizeDEScast6);
		cast6.setMode(modescast6);
		cast6.setPadding(paddingscast6);
		bouncycastleAlg.add(cast6);

		// rc6
		AlgorithmsItem rc6 = new AlgorithmsItem();

		List<Integer> keysizeDESrc6 = new ArrayList<Integer>();
		keysizeDESrc6.add(32);
		keysizeDESrc6.add(45);
		keysizeDESrc6.add(56);
		keysizeDESrc6.add(64);
		keysizeDESrc6.add(128);
		keysizeDESrc6.add(156);
		keysizeDESrc6.add(256);

		List<String> moderc6 = new ArrayList<String>();
		moderc6.add("NONE");
		moderc6.add("CBC");
		moderc6.add("ECB");
		moderc6.add("CFB");
		moderc6.add("OFB");
		moderc6.add("CTR");

		List<String> paddingsrct6 = new ArrayList<String>();
		paddingsrct6.add("NoPadding");
		paddingsrct6.add("PKCS5Padding");
		paddingsrct6.add("ISO10126Padding");

		rc6.setAlgorithms("RC6");
		rc6.setKeySize(keysizeDESrc6);
		rc6.setMode(moderc6);
		rc6.setPadding(paddingsrct6);
		bouncycastleAlg.add(rc6);

		// dstu7624
		AlgorithmsItem dstu7624 = new AlgorithmsItem();

		List<Integer> keysizedstu7624 = new ArrayList<Integer>();
		keysizedstu7624.add(128);
		keysizedstu7624.add(256);
		keysizedstu7624.add(512);

		List<String> modedstu7624 = new ArrayList<String>();
		modedstu7624.add("NONE");
		modedstu7624.add("CBC");
		modedstu7624.add("ECB");
		modedstu7624.add("CFB");
		modedstu7624.add("OFB");
		modedstu7624.add("CTR");

		List<String> paddingdstu7624 = new ArrayList<String>();
		paddingdstu7624.add("NoPadding");
		paddingdstu7624.add("PKCS5Padding");
		paddingdstu7624.add("ISO10126Padding");

		dstu7624.setAlgorithms("DSTU7624");
		dstu7624.setKeySize(keysizedstu7624);
		dstu7624.setMode(modedstu7624);
		dstu7624.setPadding(paddingdstu7624);
		bouncycastleAlg.add(dstu7624);

		// idea
		AlgorithmsItem idea = new AlgorithmsItem();

		List<Integer> keysizeidea= new ArrayList<Integer>();
		keysizeidea.add(32);
		keysizeidea.add(45);
		keysizeidea.add(56);
		keysizeidea.add(64);
		keysizeidea.add(128);
		keysizeidea.add(156);
		keysizeidea.add(256);
		keysizeidea.add(512);

		List<String> modelidea = new ArrayList<String>();
		modelidea.add("NONE");
		modelidea.add("CBC");
		modelidea.add("ECB");
		modelidea.add("CFB");
		modelidea.add("OFB");
		modelidea.add("CTR");

		List<String> paddingidea = new ArrayList<String>();
		paddingidea.add("NoPadding");
		paddingidea.add("PKCS5Padding");
		paddingidea.add("ISO10126Padding");

		idea.setAlgorithms("IDEA");
		idea.setKeySize(keysizeidea);
		idea.setMode(modelidea);
		idea.setPadding(paddingidea);
		bouncycastleAlg.add(idea);


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
			return blowfish.readKeyFile(path);
		} else if (mode.equals("AES")) {
			return aes.readKeyFile(path);
		} else if (mode.equals("RC2")) {
			return rc2.readKeyFile(path);
		} else if (mode.equals("RC4")) {
			return rc4.readKeyFile(path);
		} else if (mode.equals("CAST5")) {
			return cast5.readKeyFile(path);
		} else if (mode.equals("CAST6")) {
			return cast6.readKeyFile(path);
		} else if (mode.equals("RC6")) {
			return rc6.readKeyFile(path);
		} else if (mode.equals("DSTU7624")) {
			return dstu7624.readKeyFile(path);
		} else if (mode.equals("IDEA")) {
			return idea.readKeyFile(path);
		}
		return null;
	}

	public List<Integer> getDataKeySizeByID(String mode, String lib) {

		List<Integer> list = new ArrayList<Integer>();
		List<AlgorithmsItem> condition = new ArrayList<AlgorithmsItem>();

		if (lib.equals("normal")) {
			condition = getAlgorithmsItems();
		} else if (lib.equals("Bouncy Castle")) {
			condition = getBouncycastleAlg();
		}

		for (AlgorithmsItem algorithmsItem : condition) {
			if (algorithmsItem.getAlgorithms().equals(mode)) {
				list.addAll(algorithmsItem.getKeySize());
				break;
			}
		}

		return list;
	}

	public List<String> getDataModeByID(String mode, String lib) {

		List<String> list = new ArrayList<String>();
		List<AlgorithmsItem> condition = new ArrayList<AlgorithmsItem>();

		if (lib.equals("normal")) {
			condition = getAlgorithmsItems();
		} else if (lib.equals("Bouncy Castle")) {
			condition = getBouncycastleAlg();
		}

		for (AlgorithmsItem algorithmsItem : condition) {
			if (algorithmsItem.getAlgorithms().equals(mode)) {
				list.addAll(algorithmsItem.getMode());
				break;
			}
		}

		return list;
	}

	public List<String> getDataPaddingByID(String mode, String lib) {

		List<String> list = new ArrayList<String>();

		List<AlgorithmsItem> condition = new ArrayList<AlgorithmsItem>();

		if (lib.equals("normal")) {
			condition = getAlgorithmsItems();
		} else if (lib.equals("Bouncy Castle")) {
			condition = getBouncycastleAlg();
		}

		for (AlgorithmsItem algorithmsItem : condition) {
			if (algorithmsItem.getAlgorithms().equals(mode)) {
				list.addAll(algorithmsItem.getPadding());
				break;
			}
		}

		return list;
	}

	// EncryptFile
	public void encryptFileDES(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		des.encryptFileDES(sFile, dFile, key, mode, padding);
	}

	public void encryptFileBlowfish(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		blowfish.encryptFileBlowfish(sFile, dFile, key, mode, padding);
	}

	public void encryptFileDeSede(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		deSede.encryptFileDESede(sFile, dFile, key, mode, padding);
	}

	public void encryptFileAES(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		aes.encryptFileAES(sFile, dFile, key, mode, padding);
	}

	public void encryptFileRC2(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		rc2.encryptFileRC2(sFile, dFile, key, mode, padding);
	}

	public void encryptFileRC4(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		rc4.encryptFileRC4(sFile, dFile, key, mode, padding);
	}
	public void encryptFileCAST5(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		cast5.encryptFileAES(sFile, dFile, key, mode, padding);
	}
	public void encryptFileCAST6(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		cast6.encryptFileAES(sFile, dFile, key, mode, padding);
	}
	public void encryptFileRC6(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		rc6.encryptFileAES(sFile, dFile, key, mode, padding);
	}
	public void encryptFileDSTU7624(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		dstu7624.encryptFileAES(sFile, dFile, key, mode, padding);
	}
	public void encryptFileIDEA(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		idea.encryptFileAES(sFile, dFile, key, mode, padding);
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

	public String encryptCAST5(String message, SecretKey key, String mode, String padding) throws Exception {
		return cast5.printBytetoString(cast5.encrypt(message, key, mode, padding));
	}

	public String encryptCAST6(String message, SecretKey key, String mode, String padding) throws Exception {
		return cast6.printBytetoString(cast6.encrypt(message, key, mode, padding));
	}

	public String encryptRC6(String message, SecretKey key, String mode, String padding) throws Exception {
		return rc6.printBytetoString(rc6.encrypt(message, key, mode, padding));
	}

	public String encryptDSTU7624(String message, SecretKey key, String mode, String padding) throws Exception {
		return dstu7624.printBytetoString(dstu7624.encrypt(message, key, mode, padding));
	}

	public String encryptIDEA(String message, SecretKey key, String mode, String padding) throws Exception {
		return idea.printBytetoString(idea.encrypt(message, key, mode, padding));
	}

	// DecryptFile
	public void decryptFileDES(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		des.decryptFileDES(sFile, dFile, key, mode, padding);
	}

	public void decryptFileBlowfish(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		blowfish.decryptFileBlowfish(sFile, dFile, key, mode, padding);
	}

	public void decryptFileDESede(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		deSede.decryptFileDESede(sFile, dFile, key, mode, padding);
	}

	public void decryptFileAES(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		aes.decryptFileAES(sFile, dFile, key, mode, padding);
	}

	public void decryptFileRC2(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		rc2.decryptFileRC2(sFile, dFile, key, mode, padding);
	}

	public void decryptFileRC4(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		rc4.decryptFileRC4(sFile, dFile, key, mode, padding);
	}
	public void decryptFileCAST5(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		cast5.decryptFileAES(sFile, dFile, key, mode, padding);
	}
	public void decryptFileCAST6(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		cast6.decryptFileAES(sFile, dFile, key, mode, padding);
	}
	public void decryptFileRC6(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		rc6.decryptFileAES(sFile, dFile, key, mode, padding);
	}
	public void decryptFileDSTU7624(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		dstu7624.decryptFileAES(sFile, dFile, key, mode, padding);
	}

	public void decryptFileIDEA(String sFile, String dFile, SecretKey key, String mode, String padding)
			throws Exception {
		idea.decryptFileAES(sFile, dFile, key, mode, padding);
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

	public String decryptCast5(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return cast5.decryptAES(cast5.printStringtoByte(encrypt), key, mode, padding);
	}

	public String decryptCast6(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return cast6.decryptAES(cast6.printStringtoByte(encrypt), key, mode, padding);
	}

	public String decryptRC6(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return rc6.decryptAES(rc6.printStringtoByte(encrypt), key, mode, padding);
	}

	public String decryptdstu7624(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return dstu7624.decryptAES(dstu7624.printStringtoByte(encrypt), key, mode, padding);
	}
	public String decryptIDEA(String encrypt, SecretKey key, String mode, String padding) throws Exception {
		return idea.decryptAES(idea.printStringtoByte(encrypt), key, mode, padding);
	}

	public void init() {
		algorithmsItems = new ArrayList<>();
		bouncycastleAlg = new ArrayList<AlgorithmsItem>();
		des = new DES();
		deSede = new DESede();
		blowfish = new Blowfish();
		aes = new AES();
		rc2 = new RC2();
		rc4 = new RC4();
		cast5 = new CAST5();
		cast6 = new CAST6();
		rc6 = new RC6();
		dstu7624 = new DSTU7624();
		idea = new IDEA();
	}

	public List<AlgorithmsItem> getAlgorithmsItems() {
		return algorithmsItems;
	}

	public List<AlgorithmsItem> getBouncycastleAlg() {
		return bouncycastleAlg;
	}

	public void setBouncycastleAlg(List<AlgorithmsItem> bouncycastleAlg) {
		this.bouncycastleAlg = bouncycastleAlg;
	}

	public void setAlgorithmsItems(List<AlgorithmsItem> algorithmsItems) {
		this.algorithmsItems = algorithmsItems;
	}

}
