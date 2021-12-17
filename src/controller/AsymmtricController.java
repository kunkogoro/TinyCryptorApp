package controller;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.swing.JOptionPane;

import model.OpenDialogFile;
import modelAsymmetric.AlgorithmsAsyModel;
import modelSymmetric.AlgorithmsModel;
import view.PanelAsymmetric;

public class AsymmtricController {
	
	 private PanelAsymmetric view;
	    private AlgorithmsAsyModel model;
	    private OpenDialogFile open;
	    private PublicKey publicKey;
	    private PrivateKey privateKey;
	    private String dFile;
	    private String sFile;
	    private String publicKeyPath;
	    private String privateKeyPath;
	    
	    public AsymmtricController(final PanelAsymmetric mainView) {
	        this.view = mainView;
	        this.model = new AlgorithmsAsyModel();
	        this.open = OpenDialogFile.getInstantce();
	    }
	    
	    public void loadDataAlgorithms() {
	        this.view.loadDataAlgorithms(this.model.getAlgorithmsItems());
	    }
	    
	    public void encryptFile(final String alg, final String mode, final String padding) throws Exception {
	        if (this.publicKeyPath == null) {
	            this.view.setStatus("chưa chọn file public key");
	        }
	        else {
	            this.loadPublicKeyFile(alg);
	        }
	        if (this.sFile == null) {
	            System.out.println("file ngu\u1ed3n:" + this.open.filePath());
	            this.view.setStatus("Chưa chọn file nguồn");
	        }
	        else if (this.dFile == null) {
	            this.view.setStatus("Chưa chọn file đích");
	        }
	        else {
	            if (this.publicKey != null) {
	                switch (alg) {
	                    case "RSA": {
	                        this.model.encryptFileRSA(this.sFile, this.dFile, this.publicKey, mode, padding);
	                        this.setDefaultDFile();
	                        this.view.setStatus("Mã hóa thành công!");
	                        return;
	                    }
	                    default:
	                        break;
	                }
	                throw new IllegalArgumentException("Unexpected value: " + alg);
	            }
	            this.view.setStatus("Chưa chọn file key");
	        }
	    }
	    
	    public void decryptFile(final String alg, final String mode, final String padding) throws Exception {
	        if (this.privateKeyPath == null) {
	            this.view.setStatus("Chưa chọn file private key");
	        }
	        else {
	            this.loadPrivateKeyFile(alg);
	        }
	        if (this.sFile == null) {
	            System.out.println(this.dFile);
	            this.view.setStatus("Chưa chọn file nguồn");
	        }
	        else if (this.dFile == null) {
	            this.view.setStatus("Chưa chọn file đích");
	        }
	        else {
	            if (this.privateKey != null) {
	                switch (alg) {
	                    case "RSA": {
	                    	try {
	                    		this.model.decryptFileRSA(this.sFile, this.dFile, this.privateKey, mode, padding);
		                        this.setDefaultFile();
		                        this.view.setStatus("Giải mã thành công!");
		                        return;
							} catch (Exception e) {
								this.view.setStatus("Mode không phù hợp!");
							}
	                        
	                    }
	                    default:
	                        break;
	                }
	                throw new IllegalArgumentException("Unexpected value: " + alg);
	            }
	            System.out.println("do day");
	            this.view.setStatus("Ch\u01b0a ch\u1ecdn file key");
	        }
	    }
	    
	    public void openDialogFile_1() {
	        this.open.openDialog();
	        this.sFile = this.open.filePath();
	        if (this.sFile != "") {
	            this.view.setStatus("File nguồn:" + this.sFile);
	            this.view.setColorButtonOpen();
	        }
	        else {
	            this.view.setStatus("Mở file nguồn thất bại");
	        }
	    }
	    
	    public void openDialogFile_2() {
	        this.open.openDialog();
	        this.dFile = this.open.filePath();
	        if (this.dFile != "") {
	            this.view.setStatus("File L\u01b0u:" + this.dFile);
	            this.view.setColorButtonSave();
	        }
	        else {
	            this.view.setStatus("Mở file đích thất bại");
	        }
	    }
	    
	    public void openDialogFile() {
	        this.open.openDialog();
	    }
	    
	    public void openDialogKey(final String mode) {
	        this.open.openDialog();
	        if (mode.equals("Public Key")) {
	            this.publicKeyPath = this.open.filePath();
	            this.view.setStatus("Public Key File:" + this.publicKeyPath);
	        }
	        else if (mode.equals("Private Key")) {
	            this.privateKeyPath = this.open.filePath();
	            this.view.setStatus("Public Key File:" + this.privateKeyPath);
	        }
	    }
	    
	    public PublicKey loadPublicKeyFile(final String algorthims) throws Exception {
	        System.out.println("mode: " + algorthims);
	        if (this.open == null) {
	            return null;
	        }
	        if (this.publicKeyPath == "") {
	            return null;
	        }
	        try {
	            this.view.setColorKeyButtonFile();
	            return this.publicKey = this.model.readPublicKeyFile(this.publicKeyPath);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    public PrivateKey loadPrivateKeyFile(final String algorthims) throws Exception {
	        System.out.println("mode: " + algorthims);
	        if (this.open == null) {
	            return null;
	        }
	        if (this.privateKeyPath == "") {
	            return null;
	        }
	        try {
	            this.view.setColorKeyButtonFile();
	            System.out.println("dô gòi");
	            return this.privateKey = this.model.readPrivateKeyFile(this.privateKeyPath);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    public void loadDataAllOption(final String algorithms) {
	        this.loadDataKeySize(algorithms);
	        this.loadDataMode(algorithms);
	        this.loadDataPadding(algorithms);
	    }
	    
	    public void loadDataKeySize(final String algorithms) {
	        this.view.loadDataKeySize(this.model.getDataKeySizeByID(algorithms));
	    }
	    
	    public void openDialogError(final String error) {
	        JOptionPane.showMessageDialog(null, error);
	    }
	    
	    public void loadDataMode(final String algorithms) {
	        this.view.loadDataMode(this.model.getDataModeByID(algorithms));
	    }
	    
	    public void loadDataPadding(final String algorithms) {
	        this.view.loadDataPadding(this.model.getDataPaddingByID(algorithms));
	    }
	    
	    public void setDefaultDFile() {
	        this.dFile = null;
	        this.view.setColorButtonSaveNor();
	    }
	    
	    public void setDefaultFile() {
	        this.sFile = null;
	        this.dFile = null;
	        this.view.setColorButtonOpenNor();
	        this.view.setColorButtonSaveNor();
	    }
}
