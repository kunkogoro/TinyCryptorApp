package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.swing.JOptionPane;

import model.OpenDialogFile;
import modelAsymmetric.AlgorithmsAsyModel;
import modelHash.MD5;
import modelHash.SHA;
import view.PanelAsymmetric;
import view.PanelHash;

public class HashController {
	private PanelHash view;
	private AlgorithmsAsyModel model;
	private OpenDialogFile open;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private String dFile;
	private String sFile;
	private String dFileSHA;
	private String sFileSHA;
	private MD5 md5;
	private SHA sha;

	public HashController(final PanelHash mainView) {
		this.view = mainView;
		this.model = new AlgorithmsAsyModel();
		this.open = OpenDialogFile.getInstantce();
		md5 = new MD5();
		sha = new SHA();
	}

	public String hashStringMD5(String message) {
		return md5.hashString(message);
	}

	public String hashFileMD5() throws NoSuchAlgorithmException, IOException {
		return md5.hashFile(sFile);
	}

	public String hashMD5(String message) throws NoSuchAlgorithmException, IOException {

		if (sFile != null && !message.equals("")) {

			int op = JOptionPane.showConfirmDialog(view, "Nhấn YES để hash file!", "Bạn muốn hash file?",
					JOptionPane.OK_OPTION, JOptionPane.CANCEL_OPTION);

			if (op == JOptionPane.OK_OPTION) {
				return hashFileMD5();
			} else if (op == JOptionPane.CANCEL_OPTION) {
				return hashStringMD5(message);
			}

		} else if (!message.equals("")) {
			return hashStringMD5(message);
		} else if (sFile != null) {
			return hashFileMD5();
		}
		return "error";

	}

	public String hashSHA(String message, String mode) throws NoSuchAlgorithmException, IOException {
		if (sFileSHA != null && !message.equals("")) {

			int op = JOptionPane.showConfirmDialog(view, "Nhấn YES để hash file!", "Bạn muốn hash file?",
					JOptionPane.OK_OPTION, JOptionPane.CANCEL_OPTION);

			if (op == JOptionPane.OK_OPTION) {
				return sha.hashFile(sFileSHA, mode);
			} else if (op == JOptionPane.CANCEL_OPTION) {
				return sha.hashString(message,mode);
			}

		} else if (!message.equals("")) {
			return sha.hashString(message,mode);
		} else if (sFileSHA != null) {
			return sha.hashFile(sFileSHA, mode);
		}
		return "error";
	}

	public void saveDialogFile(String message) throws IOException {
		open.saveDialog();
		saveFile(message);
	}

	public void saveFile(String message) throws IOException {
		if (open.saveFile(message, open.filePath()))
			openDialogError("Lưu file thành công");
	}

	public void openDialogFile_1() {
		this.open.openDialog();
		this.sFile = this.open.filePath();
		if (this.sFile != "") {
			this.view.setStatus("File nguồn:" + this.sFile);
		} else {
			this.view.setStatus("Mở file nguồn thất bại");
		}
	}

	public void openDialogFile_2() {
		this.open.openDialog();
		this.sFileSHA = this.open.filePath();
		if (this.sFileSHA != "") {
			this.view.setStatus("File nguồn:" + this.sFileSHA);
		} else {
			this.view.setStatus("Mở file nguồn thất bại");
		}
	}

	public void openDialogFile() {
		this.open.openDialog();
	}

	public void openDialogError(final String error) {
		JOptionPane.showMessageDialog(null, error);
	}

	public void loadMode() {
		view.loadMode(sha.getListmode());

	}

}
