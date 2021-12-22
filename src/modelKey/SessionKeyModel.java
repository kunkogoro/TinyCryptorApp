package modelKey;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import modelSymmetric.AlgorithmsItem;
import view.PanelSessionKey;

public class SessionKeyModel {

	private List<AlgorithmsItem> algorithmsItems;

	public SessionKeyModel() {
		init();
		loadData();
	}

	private void init() {

		algorithmsItems = new ArrayList<AlgorithmsItem>();

	}

	public void loadData() {
		// DES
		AlgorithmsItem des = new AlgorithmsItem();

		List<Integer> keysizeDES = new ArrayList<Integer>();
		keysizeDES.add(56);

		des.setAlgorithms("DES");
		des.setKeySize(keysizeDES);
		algorithmsItems.add(des);

		// DEsede
		AlgorithmsItem desede = new AlgorithmsItem();

		List<Integer> keysizeDESede = new ArrayList<Integer>();
		keysizeDESede.add(112);
		keysizeDESede.add(168);

		desede.setAlgorithms("DESede");
		desede.setKeySize(keysizeDESede);
		algorithmsItems.add(desede);

		// blowfish
		AlgorithmsItem blowfish = new AlgorithmsItem();

		List<Integer> keysizeBlowfish = new ArrayList<Integer>();
		keysizeBlowfish.add(32);
		keysizeBlowfish.add(64);
		keysizeBlowfish.add(128);
		keysizeBlowfish.add(264);

		blowfish.setAlgorithms("Blowfish");
		blowfish.setKeySize(keysizeBlowfish);

		algorithmsItems.add(blowfish);

		// aes
		AlgorithmsItem aes = new AlgorithmsItem();

		List<Integer> keysizeaes = new ArrayList<Integer>();
		keysizeaes.add(128);
		keysizeaes.add(192);
		keysizeaes.add(256);

		aes.setAlgorithms("AES");
		aes.setKeySize(keysizeaes);

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

		rc2.setAlgorithms("RC2");
		rc2.setKeySize(keysizerc2);

		algorithmsItems.add(rc2);

		// rc4
		AlgorithmsItem rc4 = new AlgorithmsItem();

		List<Integer> keysizerc4 = new ArrayList<Integer>();
		keysizerc4.add(56);
		keysizerc4.add(64);
		keysizerc4.add(128);
		keysizerc4.add(256);
		keysizerc4.add(512);
		keysizerc4.add(1024);

		rc4.setAlgorithms("RC4");
		rc4.setKeySize(keysizerc4);

		algorithmsItems.add(rc4);

		// cast5
		AlgorithmsItem cast5 = new AlgorithmsItem();

		List<Integer> keysizecast5 = new ArrayList<Integer>();
		keysizecast5.add(32);
		keysizecast5.add(45);
		keysizecast5.add(56);
		keysizecast5.add(64);
		keysizecast5.add(128);

		cast5.setAlgorithms("CAST5");
		cast5.setKeySize(keysizecast5);

		algorithmsItems.add(cast5);

		// cast6
		AlgorithmsItem cast6 = new AlgorithmsItem();

		List<Integer> keysizecast6 = new ArrayList<Integer>();
		keysizecast6.add(32);
		keysizecast6.add(45);
		keysizecast6.add(56);
		keysizecast6.add(64);
		keysizecast6.add(128);
		keysizecast6.add(156);
		keysizecast6.add(256);

		cast6.setAlgorithms("CAST6");
		cast6.setKeySize(keysizecast6);

		algorithmsItems.add(cast6);

		// rc6
		AlgorithmsItem rc6 = new AlgorithmsItem();

		List<Integer> keysizerc6 = new ArrayList<Integer>();
		keysizerc6.add(32);
		keysizerc6.add(45);
		keysizerc6.add(56);
		keysizerc6.add(64);
		keysizerc6.add(128);
		keysizerc6.add(156);
		keysizerc6.add(256);

		rc6.setAlgorithms("RC6");
		rc6.setKeySize(keysizerc6);

		algorithmsItems.add(rc6);

		// rc6
		AlgorithmsItem dstu7624 = new AlgorithmsItem();

		List<Integer> keysizedstu7624 = new ArrayList<Integer>();
		keysizedstu7624.add(128);
		keysizedstu7624.add(256);
		keysizedstu7624.add(512);

		dstu7624.setAlgorithms("DSTU7624");
		dstu7624.setKeySize(keysizedstu7624);

		algorithmsItems.add(dstu7624);

		// IDEA
		AlgorithmsItem dstuidea = new AlgorithmsItem();

		List<Integer> keysizeidea = new ArrayList<Integer>();
		keysizeidea.add(32);
		keysizeidea.add(45);
		keysizeidea.add(56);
		keysizeidea.add(64);
		keysizeidea.add(128);
		keysizeidea.add(256);
		keysizeidea.add(512);

		dstuidea.setAlgorithms("IDEA");
		dstuidea.setKeySize(keysizeidea);

		algorithmsItems.add(dstuidea);

	}

	public SecretKey createAutoKey(String algorthms, int keySize) {

		Security.addProvider(new BouncyCastleProvider());

		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance(algorthms);
			keyGenerator.init(keySize);
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public boolean saveKeyFile(String filePath, byte[] data) throws IOException {

		File file = new File(filePath);

		try {
			BufferedOutputStream bufferedInputStream = new BufferedOutputStream(new FileOutputStream(file));

			bufferedInputStream.write(data, 0, data.length);

			bufferedInputStream.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

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

	public String printBytetoString(byte[] key) {
		return Base64.getEncoder().encodeToString(key);
	}

	public byte[] printStringtoByte(String key) {
		return Base64.getDecoder().decode(key);
	}

	public SessionKeyModel(List<AlgorithmsItem> algorithmsItems) {
		super();
		this.algorithmsItems = algorithmsItems;
	}

	public List<AlgorithmsItem> getAlgorithmsItems() {
		return algorithmsItems;
	}

	public void setAlgorithmsItems(List<AlgorithmsItem> algorithmsItems) {
		this.algorithmsItems = algorithmsItems;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {

		SessionKeyModel model = new SessionKeyModel();
		System.out.println(model.printBytetoString(model.createAutoKey("RC6", 128).getEncoded()));

	}

}
