package modelAsymmetric;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import modelSymmetric.AES;
import modelSymmetric.AlgorithmsItem;
import modelSymmetric.Blowfish;
import modelSymmetric.DES;
import modelSymmetric.DESede;
import modelSymmetric.RC2;

public class AlgorithmsAsyModel {
	private List<AlgorithmsItem> algorithmsItems;
	private RSA rsa;

	public AlgorithmsAsyModel() {
		init();
		loadData();

	}

	public void loadData() {
		// RSA
		AlgorithmsItem des = new AlgorithmsItem();

		List<Integer> keysizeDES = new ArrayList<Integer>();
		keysizeDES.add(1024);

		List<String> modes = new ArrayList<String>();
		modes.add("NONE");	
		modes.add("ECB");

		List<String> paddings = new ArrayList<String>();
		paddings.add("NoPadding");
		paddings.add("PKCS1Padding");
		paddings.add("OAEPWithSHA1AndMGF1Padding");
		paddings.add("OAEPWithSHA-256AndMGF1Padding");
		paddings.add("OAEPWithSHA-512AndMGF1Padding");
		

		des.setAlgorithms("RSA");
		des.setKeySize(keysizeDES);
		des.setMode(modes);
		des.setPadding(paddings);
		algorithmsItems.add(des);


	}
	
	public PublicKey readPublicKeyFile(String path) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		return rsa.readPublicKey(path);
	}
	public PrivateKey readPrivateKeyFile(String path) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		return rsa.readPrivateKey(path);
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
	
	

	public void init() {
		algorithmsItems = new ArrayList<>();
		rsa = new RSA();
	}

	public List<AlgorithmsItem> getAlgorithmsItems() {
		return algorithmsItems;
	}

	public void setAlgorithmsItems(List<AlgorithmsItem> algorithmsItems) {
		this.algorithmsItems = algorithmsItems;
	}

	public void encryptFileRSA(String sFile, String dFile, PublicKey publicKey, String mode, String padding) throws NoSuchAlgorithmException {
		rsa.encryptRSAWithAES(publicKey, sFile, dFile, mode, padding);
		
	}

	public void decryptFileRSA(String sFile, String dFile, PrivateKey privateKey, String mode, String padding) throws NoSuchAlgorithmException {
		rsa.decryptRSAWithAES(privateKey, sFile, dFile, mode, padding);
		
	}

}
