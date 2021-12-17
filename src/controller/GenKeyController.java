package controller;

import java.io.IOException;
import java.security.KeyPair;

import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

import model.OpenDialogFile;
import modelKey.GenKeyPairModel;
import modelKey.SessionKeyModel;
import view.PanelGenKeyPair;
import view.PanelSessionKey;

public class GenKeyController {
	private PanelGenKeyPair view;
	private GenKeyPairModel model;
	private KeyPair key;
	private OpenDialogFile open;
	private String publicPath;
	private String privatePath;

	public GenKeyController(PanelGenKeyPair view) {
		this.view = view;
		init();
	}

	public void init() {

		model = new GenKeyPairModel();
		open = OpenDialogFile.getInstantce();
	}

	public void loadDataKeySize(String algorithms) {
		view.loadDataKeySize(model.getDataKeySizeByID(algorithms));
	}

	public void loadDataAllOption(String algorithms) {
		loadDataKeySize(algorithms);
	}

	public void loadDataAlgorithms() {
		view.loadDataAlgorithms(model.getAlgorithmsItems());
	}

	public void saveDialogFile(String mode) throws IOException {
		open.saveDialog();
		publicPath = open.filePath();
		saveFile(mode);
	}
	public void saveDialogFile_1(String mode) throws IOException {
		open.saveDialog();
		privatePath = open.filePath();
		saveFile(mode);
	}

	public void saveFile(String mode) throws IOException {
		try {
			if(mode.equals("Public Key")) {
				model.saveKeyFile(publicPath, key.getPublic().getEncoded());
				openDialogError("Lưu key thành công");
			}else if(mode.equals("Private Key")) {
				model.saveKeyFile(privatePath, key.getPrivate().getEncoded());
				openDialogError("Lưu key thành công");
			}
				
		} catch (Exception e) {
			openDialogError("Lưu key thất bại");
		}
		
	}
	public void openDialogError(String error) {
		JOptionPane.showMessageDialog(null, error);
	}


	public void createGenKeyPair(String algorithms, String keySize) {

		key = model.createAutoKey(algorithms, Integer.parseInt(keySize));
		if (key!=null) {
			view.setColorKeyButtonSuccess();
		}else {
			view.setColorKeyButtonFail();
		}
		

	}
}
