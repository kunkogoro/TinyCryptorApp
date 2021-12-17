package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import controller.PBEController;
import modelSymmetric.AlgorithmsItem;

import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.awt.event.ActionEvent;

public class PanelPBE extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	//private JPasswordField passwordField;
	private JComboBox cbxAlgorithm;
	private PBEController controller;
	private JTextArea taResult;

	/**
	 * Create the panel.
	 */
	public PanelPBE() {

		controller = new PBEController(this);

		setBounds(0, 0, 507, 311);
		setLayout(null);

		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(
				new TitledBorder(null, "Mode", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(15, 39, 224, 212);
		add(panel);

		JRadioButton rdoDecrypt = new JRadioButton("Decrypt");
		buttonGroup.add(rdoDecrypt);
		rdoDecrypt.setBackground(Color.WHITE);
		rdoDecrypt.setBounds(113, 33, 79, 23);
		panel.add(rdoDecrypt);

		JRadioButton rdoEncript = new JRadioButton("Encrypt");
		buttonGroup.add(rdoEncript);
		rdoEncript.setSelected(true);
		rdoEncript.setBackground(Color.WHITE);
		rdoEncript.setBounds(32, 33, 68, 23);
		panel.add(rdoEncript);

		JLabel lblAlgorithm = new JLabel("Algorithm");
		lblAlgorithm.setBounds(21, 69, 71, 16);
		panel.add(lblAlgorithm);
		lblAlgorithm.setFont(new Font("Tahoma", Font.PLAIN, 13));

		cbxAlgorithm = new JComboBox();
		cbxAlgorithm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxAlgorithm.setBounds(21, 90, 177, 22);
		panel.add(cbxAlgorithm);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(21, 129, 71, 16);
		panel.add(lblPassword);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(21, 150, 177, 20);
		passwordField.setEchoChar('*');
		panel.add(passwordField);

		JCheckBox chckbxNewCheckBox = new JCheckBox("show password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxNewCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}

			}
		});
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(20, 175, 160, 23);
		panel.add(chckbxNewCheckBox);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new TitledBorder(

				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Output",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(186, 85, 211)));
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setBounds(254, 151, 233, 100);
		add(panel_3_1);

		 taResult = new JTextArea();
		taResult.setLineWrap(true);
		taResult.setEditable(false);
		taResult.setBackground(Color.LIGHT_GRAY);
		taResult.setBounds(10, 21, 213, 68);
		panel_3_1.add(taResult);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(
				new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(186, 85, 211)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(254, 39, 233, 101);
		add(panel_3);

		JTextArea taMessage = new JTextArea();
		taMessage.setRows(10);
		taMessage.setLineWrap(true);
		taMessage.setBounds(10, 21, 213, 69);
		panel_3.add(taMessage);

		JButton btnStart = new JButton("Run");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String opti = "";

				try {

					Enumeration<AbstractButton> btngrMode = buttonGroup.getElements();
					String algorithms = cbxAlgorithm.getSelectedItem().toString().trim();
					String password = passwordField.getText().toString().trim();
					String message =  taMessage.getText().toString().trim();
					while (btngrMode.hasMoreElements()) {
						JRadioButton button = (JRadioButton) btngrMode.nextElement();
						if (button.isSelected())
							opti = button.getText();
					}
					if (opti == "Encrypt") {
						taResult.setText(controller.encryptPBE(message, password, algorithms));
					} else if (opti == "Decrypt") {
						taResult.setText(controller.decryptPBE(message, password, algorithms));
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					taResult.setText("Vui lòng chọn mode phù hợp");
				}

			}
		});
		btnStart.setBounds(158, 262, 81, 23);
		add(btnStart);
		
		JButton btnSaveFile = new JButton("Save");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = taResult.getText().toString().trim();
				
				try {
					controller.saveDialogFile(result);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					taResult.setText("Lưu file thất bại");
				}
			}
		});
		btnSaveFile.setBounds(254, 262, 76, 23);
		add(btnSaveFile);

		controller.loadDataAlgorithms();

	}
	
	public void setResult(String mess) {
		taResult.setText(mess);
	}

	public void loadDataAlgorithms(final List<String> list) {
		final DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (final String algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem);
		}
		this.cbxAlgorithm.setModel(defaultComboBoxModel);
	}
}
