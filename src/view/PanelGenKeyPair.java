package view;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import modelSymmetric.AlgorithmsItem;
import java.util.List;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.LayoutManager;
import java.awt.Color;

import controller.GenKeyController;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;

public class PanelGenKeyPair extends JPanel {
	private JComboBox cbxKeySize;
	private JComboBox cbxAlgorithm;
	private String mode;
	private JButton btnStart;
	private JButton btnSavePrivate;
	private JButton btnSavePublic;
	private GenKeyController controller;

	public PanelGenKeyPair() {
		this.controller = new GenKeyController(this);
		this.setBounds(0, 0, 507, 311);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Property", 4, 2, null, new Color(65, 105, 225)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(101, 45, 315, 92);
		this.add(panel_1);
		final JLabel lblNewLabel = new JLabel("Algorithms");
		lblNewLabel.setFont(new Font("Tahoma", 0, 13));
		lblNewLabel.setBounds(10, 23, 71, 14);
		panel_1.add(lblNewLabel);
		final JLabel lblNewLabel_1 = new JLabel("Key size");
		lblNewLabel_1.setFont(new Font("Tahoma", 0, 13));
		lblNewLabel_1.setBounds(10, 56, 46, 14);
		panel_1.add(lblNewLabel_1);
		this.cbxAlgorithm = new JComboBox();
		cbxAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mode = String.valueOf(cbxAlgorithm.getSelectedItem());
				controller.loadDataAllOption(mode);
				
			}
		});
		this.cbxAlgorithm.setBounds(107, 19, 198, 22);
		panel_1.add(this.cbxAlgorithm);
		this.cbxKeySize = new JComboBox();
		cbxKeySize.setBounds(107, 52, 198, 22);
		panel_1.add(this.cbxKeySize);
		this.btnStart = new JButton("Run");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String algorthims = (String) cbxAlgorithm.getSelectedItem();
				String keySize = (String) cbxKeySize.getSelectedItem();
				controller.createGenKeyPair(algorthims, keySize);
				
			}
		});
		this.btnStart.setBounds(46, 195, 89, 23);
		this.add(this.btnStart);
		this.btnSavePublic = new JButton("Public Key");
		btnSavePublic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mode = btnSavePublic.getText();
				try {
					controller.saveDialogFile(mode);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					controller.openDialogError("Lỗi mở file");
				}
				
			}
		});
		this.btnSavePublic.setBounds(169, 195, 111, 23);
		this.add(this.btnSavePublic);
		this.btnSavePrivate = new JButton("Private Key");
		btnSavePrivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mode = btnSavePrivate.getText();
				try {
					controller.saveDialogFile_1(mode);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					controller.openDialogError("Lỗi mở file");
				}
			}
		});
		this.btnSavePrivate.setBounds(314, 195, 111, 23);
		this.add(this.btnSavePrivate);
		this.controller.loadDataAlgorithms();
	}

	public void setColorKeyButtonSuccess() {
		this.btnStart.setBackground(Color.GREEN);
	}

	public void setColorKeyButtonFail() {
		this.btnStart.setBackground(Color.RED);
	}

	public void loadDataAlgorithms(final List<AlgorithmsItem> list) {
		final DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (final AlgorithmsItem algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem.getAlgorithms());
		}
		this.cbxAlgorithm.setModel(defaultComboBoxModel);
		this.mode = list.get(0).getAlgorithms();
		this.loadDataKeySize(list.get(0).getKeySize());
	}

	public void loadDataKeySize(final List<Integer> list) {
		final DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (final Integer algorithmsItem : list) {
			defaultComboBoxModel.addElement(String.valueOf(algorithmsItem));
		}
		this.cbxKeySize.setModel(defaultComboBoxModel);
	}
}