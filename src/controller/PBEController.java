package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

import model.OpenDialogFile;
import modelPBE.PBE;
import modelSymmetric.AlgorithmsModel;
import view.PanelPBE;
import view.PanelSymmetric;

public class PBEController {

	private PanelPBE mainView;
	private PBE model;
	private OpenDialogFile open;
	private SecretKey key;
	private String dFile;
	private String sFile;
	private String keyPath;

	public PBEController(PanelPBE mainView) {
		this.mainView = mainView;
		this.model = new PBE();
		open = OpenDialogFile.getInstantce();
	}
	
	public void loadDataAlgorithms() {
		mainView.loadDataAlgorithms(model.getAlgorithmsItems());
	}
	
	
	public String encryptPBE(String property,String password,String mode ) throws UnsupportedEncodingException, GeneralSecurityException {
		
		if (property.equals("")) {
			return "Vui lòng nhập input";
		}
		if(password.equals("")) {
			return "Vui lòng nhập password";
		}
		
		return model.encrypt(property, password, mode);
	}
	public String decryptPBE(String property,String password,String mode ) throws GeneralSecurityException, IOException {
		if (property.equals("")) {
			return "Vui lòng nhập input";
		}
		if(password.equals("")) {
			return "Vui lòng nhập password";
		}
		return model.decrypt(property, password, mode);
	}

	public void saveDialogFile(String message) throws IOException {
		open.saveDialog();
		saveFile(message);
	}
	public void saveFile(String message) throws IOException {
		if (open.saveFile(message, open.filePath()))
			openDialogError("Lưu file thành công");
	}
	public void openDialogError(String error) {
		JOptionPane.showMessageDialog(null, error);
	}
}
