package view;

import java.util.Iterator;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.util.List;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JTabbedPane;
import java.awt.LayoutManager;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import controller.HashController;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;

public class PanelHash extends JPanel {
	private JTextArea tarResult;
	private JTextArea tarResult_1;
	private HashController controller;
	private JComboBox cbMode;

	public PanelHash() {
		 this.controller = new HashController(this);
		this.setBackground(UIManager.getColor("Button.disabledShadow"));
		this.setBounds(0, 0, 507, 311);
		this.setLayout(null);
		final JTabbedPane tabbedPane = new JTabbedPane(1);
		tabbedPane.setBounds(0, 11, 507, 300);
		this.add(tabbedPane);
		final JPanel panel = new JPanel();
		tabbedPane.addTab("MD5", null, panel, null);
		panel.setLayout(null);
		final JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(1, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Input", 4, 2, null, new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 22, 223, 132);
		panel.add(panel_2);
		panel_2.setLayout(null);
		final JTextArea taInput = new JTextArea();
		taInput.setWrapStyleWord(true);
		taInput.setBounds(10, 24, 203, 97);
		panel_2.add(taInput);
		final JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Hash String", 4, 2, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(0, 0, 246, 168);
		panel.add(panel_3);
		panel_3.setLayout(null);
		final JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(null, "Hash File", 4, 2, null, null));
		panel_4.setBounds(247, 0, 255, 168);
		panel.add(panel_4);
		panel_4.setLayout(null);
		final JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openDialogFile_1();
			}
		});
		// btnOpen.addActionListener((ActionListener)new PanelHash.PanelHash$1(this));
		btnOpen.setBounds(26, 41, 89, 23);
		panel_4.add(btnOpen);
		final JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = tarResult.getText().toString().trim();
				try {
					controller.saveDialogFile(result);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					controller.openDialogError("Lỗi lưu file");
				}
			}
		});
		// btnNewButton_1.addActionListener((ActionListener)new
		// PanelHash.PanelHash$2(this, taInput));
		btnNewButton_1.setBounds(156, 41, 89, 23);
		panel_4.add(btnNewButton_1);
		final JButton btnNewButton_2 = new JButton("Start");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = taInput.getText().toString().trim();
				try {
					tarResult.setText(controller.hashMD5(message));
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					tarResult.setText("error");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					tarResult.setText("error");
					e1.printStackTrace();
				}
				
			}
		});
		// btnNewButton_2.addActionListener((ActionListener)new
		// PanelHash.PanelHash$3(this));
		btnNewButton_2.setBounds(88, 104, 89, 23);
		panel_4.add(btnNewButton_2);
		final JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Result", 4, 2, null, null));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(0, 165, 499, 107);
		panel.add(panel_5);
		panel_5.setLayout(null);
		this.tarResult = new JTextArea();
		//tarResult.setDisabledTextColor(UIManager.getColor("Button.shadow"));
		this.tarResult.setEditable(false);
		this.tarResult.setBackground(SystemColor.textInactiveText);
		this.tarResult.setBounds(10, 21, 479, 75);
		panel_5.add(this.tarResult);
		final JPanel panel_6 = new JPanel();
		tabbedPane.addTab("SHA", null, panel_6, null);
		panel_6.setLayout(null);
		final JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new TitledBorder(null, "Hash String", 4, 2, null, null));
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setBounds(0, 0, 246, 168);
		panel_6.add(panel_3_1);
		final JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(new EtchedBorder(1, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Input", 4, 2, null, new Color(0, 0, 0)));
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(10, 22, 223, 132);
		panel_3_1.add(panel_2_1);
		final JTextArea taInput_1 = new JTextArea();
		taInput_1.setWrapStyleWord(true);
		taInput_1.setBounds(10, 24, 203, 97);
		panel_2_1.add(taInput_1);
		final JPanel panel_5_1 = new JPanel();
		panel_5_1.setLayout(null);
		panel_5_1.setBorder(new TitledBorder(null, "Hash File", 4, 2, null, null));
		panel_5_1.setBackground(Color.WHITE);
		panel_5_1.setBounds(247, 0, 255, 168);
		panel_6.add(panel_5_1);
		final JButton btnOpen_1 = new JButton("Open");
		btnOpen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.openDialogFile_2();
				
			}
		});
		// btnOpen_1.addActionListener((ActionListener)new PanelHash.PanelHash$4(this));
		btnOpen_1.setBounds(26, 41, 89, 23);
		panel_5_1.add(btnOpen_1);
		final JButton btnNewButton_1_1 = new JButton("Save");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = tarResult_1.getText().toString().trim();
				try {
					controller.saveDialogFile(message);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					controller.openDialogError("Lỗi lưu file");
				}
				
			}
		});
		// btnNewButton_1_1.addActionListener((ActionListener)new
		// PanelHash.PanelHash$5(this));
		btnNewButton_1_1.setBounds(156, 41, 89, 23);
		panel_5_1.add(btnNewButton_1_1);
		final JButton btnNewButton_2_1 = new JButton("Start");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String message = taInput.getText().toString().trim();
				
				String mode = cbMode.getSelectedItem().toString().trim();
				
				try {
					tarResult_1.setText(controller.hashSHA(message,mode));
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					tarResult_1.setText("error");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					tarResult_1.setText("error");
					e1.printStackTrace();
				}
				
			}
		});
		// btnNewButton_2_1.addActionListener((ActionListener)new
		// PanelHash.PanelHash$6(this, taInput_1));
		btnNewButton_2_1.setBounds(88, 104, 89, 23);
		panel_5_1.add(btnNewButton_2_1);
		final JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBorder(new TitledBorder(null, "Result", 4, 2, null, null));
		panel_4_1.setBackground(Color.WHITE);
		panel_4_1.setBounds(247, 165, 255, 107);
		panel_6.add(panel_4_1);
		(this.tarResult_1 = new JTextArea()).setLineWrap(true);
		this.tarResult_1.setEditable(false);
		this.tarResult_1.setDisabledTextColor(Color.WHITE);
		this.tarResult_1.setBackground(SystemColor.textInactiveText);
		this.tarResult_1.setBounds(10, 21, 235, 75);
		panel_4_1.add(this.tarResult_1);
		final JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Option", 4, 2, null, null));
		panel_7.setBackground(Color.WHITE);
		panel_7.setBounds(0, 165, 246, 107);
		panel_6.add(panel_7);
		panel_7.setLayout(null);
		this.cbMode = new JComboBox();
		cbMode.setBounds(109, 41, 110, 22);
		panel_7.add(this.cbMode);
		final JLabel lblNewLabel = new JLabel("Algorithms");
		lblNewLabel.setBounds(32, 45, 67, 14);
		panel_7.add(lblNewLabel);
		this.controller.loadMode();
	}

	public void loadMode(final List<String> list) {
		final DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (final String algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem);
		}
		this.cbMode.setModel(defaultComboBoxModel);
	}

	public void setStatus(final String message) {
		this.tarResult.setText(message);
	}

	public void setStatus1(final String message) {
		this.tarResult_1.setText(message);
	}
}