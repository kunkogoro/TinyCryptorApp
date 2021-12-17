package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

import model.OpenDialogFile;
import modelKey.SessionKeyModel;
import view.PanelGenKeyPair;
import view.PanelSessionKey;

public class SessionKeyController {
	private PanelSessionKey view;
	private SessionKeyModel model;
	private SecretKey key;
	private OpenDialogFile open;

	public SessionKeyController(PanelSessionKey view) {
		this.view = view;
		init();
	}

	public void init() {

		model = new SessionKeyModel();
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

	public void saveDialogFile() throws IOException {
		open.saveDialog();
		saveFile();
	}

	public void saveFile() throws IOException {
		if (model.saveKeyFile(open.filePath(), key.getEncoded())) {
			openDialogError("Lưu key thành công");
		}else {
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
