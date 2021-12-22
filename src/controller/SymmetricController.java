package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

import model.OpenDialogFile;
import modelSymmetric.AlgorithmsItem;
import modelSymmetric.AlgorithmsModel;
import view.MainView;
import view.PanelSymmetric;

public class SymmetricController {

	private PanelSymmetric mainView;
	private AlgorithmsModel model;
	private OpenDialogFile open;
	private SecretKey key;
	private String dFile;
	private String sFile;
	private String keyPath;

	public SymmetricController(PanelSymmetric mainView) {
		this.mainView = mainView;
		this.model = new AlgorithmsModel();
		open = OpenDialogFile.getInstantce();
	}

	public void loadDataAlgorithms(String mode) {
		if (mode.equals("normal")) {
			mainView.loadDataAlgorithms(model.getAlgorithmsItems());
		} else if (mode.equals("Bouncy Castle")) {
			mainView.loadDataAlgorithms(model.getBouncycastleAlg());
		}

	}

	public void loadDataAllOption(String algorithms, String mode) {
		loadDataKeySize(algorithms, mode);
		loadDataMode(algorithms, mode);
		loadDataPadding(algorithms, mode);
	}

	public void encryptFile(String alg, String mode, String padding,String lib) throws Exception {



		if (sFile == null) {
			System.out.println("file nguồn:" + open.filePath());
			mainView.setStatus("Chưa chọn file nguồn");
		} else if (dFile == null) {
			mainView.setStatus("Chưa chọn file đích");
		} else if (keyPath == null) {
			mainView.setStatus("Chưa chọn file key");
		} else {

			loadKeyFile(alg);

			switch (alg) {
				case "DES":
					this.model.encryptFileDES(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "DESede":
					this.model.encryptFileDeSede(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "Blowfish":
					this.model.encryptFileBlowfish(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "AES":
					this.model.encryptFileAES(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "RC2":
					this.model.encryptFileRC2(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "RC4":
					this.model.encryptFileRC4(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "CAST5":
					this.model.encryptFileCAST5(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "CAST6":
					this.model.encryptFileCAST6(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "RC6":
					this.model.encryptFileRC6(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "DSTU7624":
					this.model.encryptFileDSTU7624(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				case "IDEA":
					this.model.encryptFileIDEA(sFile, dFile, key, mode, padding);
					setDefaultDFile();
					mainView.setStatus("Mã hóa thành công!");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + alg);
			}
		}

	}

	public String encrypt(String alg, String message, String mode, String padding, String lib) throws Exception {

		if (keyPath == null) {
			return "Vui lòng nhập Key";
		} else if (message.equals("")) {
			return "Vui lòng nhập từ cần mã hóa";
		} else {

			if(loadKeyFile(alg) == null) {
				return "Vui lòng nhập Key";
			}

			if (lib.equals("normal")) {

				System.out.println("alg");

				switch (alg) {
					case "DES":
						return this.model.encryptDES(message, key, mode, padding);
					case "DESede":
						return this.model.encryptDESede(message, key, mode, padding);
					case "Blowfish":
						return this.model.encryptBlowfish(message, key, mode, padding);
					case "AES":
						return this.model.encryptAES(message, key, mode, padding);
					case "RC2":
						return this.model.encryptRC2(message, key, mode, padding);
					case "RC4":
						return this.model.encryptRC4(message, key, mode, padding);
					default:
						throw new IllegalArgumentException("Unexpected value: " + alg);
				}
			} else if (lib.equals("Bouncy Castle")) {

				System.out.println(alg);

				switch (alg) {
					case "CAST5":
						return this.model.encryptCAST5(message, key, mode, padding);
					case "CAST6":
						return this.model.encryptCAST6(message, key, mode, padding);
					case "RC6":
						return this.model.encryptRC6(message, key, mode, padding);
					case "DSTU7624":
						return this.model.encryptDSTU7624(message, key, mode, padding);
					case "IDEA":
						return this.model.encryptIDEA(message, key, mode, padding);
					default:
						throw new IllegalArgumentException("Unexpected value: " + alg);
				}
			}

		}
		return "Error";
	}

	public void decryptFile(String alg, String mode, String padding,String lib) throws Exception {



		if (dFile == null) {
			System.out.println(dFile);
			mainView.setStatus("Chưa chọn file nguồn");
		} else if (sFile == null) {
			mainView.setStatus("Chưa chọn file đích");
		} else if (keyPath == null) {
			mainView.setStatus("Chưa chọn file key");
		} else {

			loadKeyFile(alg);

			switch (alg) {
				case "DES":
					this.model.decryptFileDES(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "DESede":
					this.model.decryptFileDESede(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "Blowfish":
					this.model.decryptFileBlowfish(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "AES":
					this.model.decryptFileAES(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "RC2":
					this.model.decryptFileRC2(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "RC4":
					this.model.decryptFileRC4(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "CAST5":
					this.model.decryptFileCAST5(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "CAST6":
					this.model.decryptFileCAST6(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "RC6":
					this.model.decryptFileRC6(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "DSTU7624":
					this.model.decryptFileDSTU7624(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				case "IDEA":
					this.model.decryptFileIDEA(sFile, dFile, key, mode, padding);
					setDefaultFile();
					mainView.setStatus("Giải mã thành công!");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + alg);
			}
		}
	}

	public String decrypt(String alg, String message, String mode, String padding, String lib) throws Exception {

		if (keyPath == null) {
			return "Vui lòng nhập Key";
		} else if (message.equals("")) {
			return "Vui lòng nhập từ cần mã hóa";
		} else {

			if(loadKeyFile(alg) == null) {
				return "Vui lòng nhập Key";
			}

			if (lib.equals("normal")) {
				switch (alg) {
					case "DES":
						return this.model.decryptDES(message, key, mode, padding);
					case "DESede":
						return this.model.decryptDESede(message, key, mode, padding);
					case "Blowfish":
						return this.model.decryptBlowfish(message, key, mode, padding);
					case "AES":
						return this.model.decryptAES(message, key, mode, padding);
					case "RC2":
						return this.model.decryptRC2(message, key, mode, padding);
					case "RC4":
						return this.model.decryptRC4(message, key, mode, padding);
					default:
						throw new IllegalArgumentException("Unexpected value: " + alg);
				}
			} else if (lib.equals("Bouncy Castle")) {
				switch (alg) {
					case "CAST5":
						return this.model.decryptCast5(message, key, mode, padding);
					case "CAST6":
						return this.model.decryptCast6(message, key, mode, padding);
					case "RC6":
						return this.model.decryptRC6(message, key, mode, padding);
					case "DSTU7624":
						return this.model.decryptdstu7624(message, key, mode, padding);
					case "IDEA":
						return this.model.decryptIDEA(message, key, mode, padding);
					default:
						throw new IllegalArgumentException("Unexpected value: " + alg);
				}
			}

		}
		return "Error";
	}

	public void openDialogFile() {
//		open = OpenDialogFile.getInstantce();
		open.openDialog();
	}

	public void openDialogFile_1() {
		open.openDialog();
		this.sFile = open.filePath();

		if (sFile != "") {
			mainView.setStatus("File nguồn:" + sFile);
			mainView.setColorButtonOpen();
		} else {
			mainView.setStatus("Mở File nguồn thất bại");
		}
	}

	public void openDialogFile_2() {
		open.openDialog();
		dFile = open.filePath();
		if (dFile != "") {
			mainView.setStatus("File Lưu:" + dFile);
			mainView.setColorButtonSave();
		} else {
			mainView.setStatus("Mở File lưu thất bại");
		}
	}

	public void openDialogKey(String mode) {
		open.openDialog();
		keyPath = open.filePath();
		mainView.setStatus("Key File:" + keyPath);
	}

	public void saveDialogFile(String message) throws IOException {
		open.saveDialog();
		saveFile(message);
	}

	public String readFile() throws IOException {
		openDialogFile();
		return open.readFile(open.filePath());
	}

	public void saveFile(String message) throws IOException {
		if (open.saveFile(message, open.filePath()))
			openDialogError("Lưu file thành công");

	}

	public SecretKey loadKeyFile(String algorthims) {

		System.out.println("mode: " + algorthims);

		if (open == null) {
			return null;
		}

		if (keyPath == "") {
			return null;
		}
		try {

			mainView.setColorKeyButton();
			mainView.setColorKeyButtonFile();

			key = model.readKeyFile(keyPath, algorthims);

			return key;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void loadDataKeySize(String algorithms, String mode) {
		mainView.loadDataKeySize(model.getDataKeySizeByID(algorithms, mode));
	}

	public void openDialogError(String error) {
		JOptionPane.showMessageDialog(null, error);
	}

	public void loadDataMode(String algorithms, String mode) {
		mainView.loadDataMode(model.getDataModeByID(algorithms, mode));
	}

	public void loadDataPadding(String algorithms, String mode) {
		mainView.loadDataPadding(model.getDataPaddingByID(algorithms, mode));
	}

	public void setDefaultDFile() {
		dFile = null;
		mainView.setColorButtonSaveNor();
	}

	public void setDefaultFile() {
		sFile = null;
		dFile = null;
		mainView.setColorButtonOpenNor();
		mainView.setColorButtonSaveNor();
	}
}
