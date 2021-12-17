package modelKey;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import modelSymmetric.AlgorithmsItem;
import view.PanelSessionKey;

public class SessionKeyModel {
	
	private List<AlgorithmsItem> algorithmsItems;
	
	public SessionKeyModel() {
		init();
		loadData();
	}
	
	
	private void init() {
		
		algorithmsItems= new ArrayList<AlgorithmsItem>();
		
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

	}
	

	public SecretKey createAutoKey(String algorthms,int keySize) {
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
		//System.out.println(model.printBytetoString(model.createAutoKey("AES",128).getEncoded()));
		

	}

}
