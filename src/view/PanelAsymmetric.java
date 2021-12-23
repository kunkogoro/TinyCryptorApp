package view;

import java.util.Enumeration;
import java.util.Iterator;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import modelSymmetric.AlgorithmsItem;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.LayoutManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import controller.AsymmtricController;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelAsymmetric extends JPanel {
	private final ButtonGroup btnGrModel;
	private AsymmtricController controller;
	private JComboBox cbxAlgorithmFile;
	private JComboBox cbxKeySizeFile;
	private JComboBox cbxModeFile;
	private JComboBox cbxPaddingFile;
	private JTextArea taResult;
	private JTextArea taMessage;
	private JTextArea taMessageStatus;
	private JButton btnOpenFile;
	private JButton btnSaveFile;
	private JButton btnPublicKey;
	private String mode;

	private Image img_help = new ImageIcon(MainView.class.getResource("/asset/help.jpg")).getImage()
			.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	public PanelAsymmetric() {
		this.btnGrModel = new ButtonGroup();
		this.controller = new AsymmtricController(this);
		this.mode = "";
		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, 507, 311);
		this.setLayout(null);

		final JPanel panelModeFile = new JPanel();
		panelModeFile.setLayout(null);
		panelModeFile.setBorder(new TitledBorder(null, "Mode", 4, 2, null, new Color(65, 105, 225)));
		panelModeFile.setBackground(Color.WHITE);
		panelModeFile.setBounds(10, 30, 225, 49);
		this.add(panelModeFile);
		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "Property", 4, 2, null, new Color(65, 105, 225)));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(10, 93, 225, 87);
		this.add(panel_1_1);
		final JLabel lblNewLabel_2 = new JLabel("Algorithms");
		lblNewLabel_2.setFont(new Font("Tahoma", 0, 13));
		lblNewLabel_2.setBounds(10, 23, 71, 14);
		panel_1_1.add(lblNewLabel_2);
		final JLabel lblNewLabel_1_2 = new JLabel("Key size");
		lblNewLabel_1_2.setFont(new Font("Tahoma", 0, 13));
		lblNewLabel_1_2.setBounds(10, 56, 46, 14);
		panel_1_1.add(lblNewLabel_1_2);
		this.cbxAlgorithmFile = new JComboBox();
		cbxAlgorithmFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.cbxAlgorithmFile.setBounds(107, 19, 108, 22);
		panel_1_1.add(this.cbxAlgorithmFile);
		(this.cbxKeySizeFile = new JComboBox()).setBounds(107, 52, 108, 22);
		panel_1_1.add(this.cbxKeySizeFile);
		final JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "Options", 4, 2, null, new Color(186, 85, 211)));
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(10, 191, 225, 87);
		this.add(panel_2_1);
		final JLabel lblMode_1 = new JLabel("Mode");
		lblMode_1.setFont(new Font("Tahoma", 0, 13));
		lblMode_1.setBounds(10, 24, 71, 14);
		panel_2_1.add(lblMode_1);
		final JLabel lblNewLabel_1_1_1 = new JLabel("Padding");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", 0, 13));
		lblNewLabel_1_1_1.setBounds(10, 57, 46, 14);
		panel_2_1.add(lblNewLabel_1_1_1);
		this.cbxModeFile = new JComboBox();
		cbxModeFile.setBounds(107, 16, 108, 22);
		panel_2_1.add(this.cbxModeFile);
		this.cbxPaddingFile = new JComboBox();
		cbxPaddingFile.setBounds(107, 49, 108, 22);
		panel_2_1.add(this.cbxPaddingFile);
		final JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBorder(new TitledBorder(new EtchedBorder(1, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Status", 4, 2, null, new Color(65, 105, 225)));
		panel_3_2.setBackground(Color.WHITE);
		panel_3_2.setBounds(259, 93, 233, 87);
		this.add(panel_3_2);
		(this.taMessageStatus = new JTextArea()).setRows(10);
		this.taMessageStatus.setLineWrap(true);
		this.taMessageStatus.setForeground(Color.BLACK);
		this.taMessageStatus.setFont(new Font("Monospaced", 0, 13));
		this.taMessageStatus.setEnabled(false);
		this.taMessageStatus.setEditable(false);
		this.taMessageStatus.setDisabledTextColor(Color.BLACK);
		this.taMessageStatus.setBackground(Color.LIGHT_GRAY);
		this.taMessageStatus.setBounds(10, 21, 213, 57);
		panel_3_2.add(this.taMessageStatus);
		final JButton btnStart_1 = new JButton("Run");
		btnStart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String opti = "";

				try {

					Enumeration<AbstractButton> btngrMode = btnGrModel.getElements();
					String algorithms = cbxAlgorithmFile.getSelectedItem().toString().trim();
					String padding  = cbxPaddingFile.getSelectedItem().toString().trim();

					while (btngrMode.hasMoreElements()) {
						JRadioButton button = (JRadioButton) btngrMode.nextElement();
						if (button.isSelected())
							opti = button.getText();
					}
					if (opti == "Encrypt") {
						controller.encryptFile(algorithms, mode, padding);
					} else if (opti == "Decrypt") {
						controller.decryptFile(algorithms, mode, padding);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					taMessageStatus.setText("Vui lòng chọn mode phù hợp");
				}


			}
		});

		btnStart_1.setBounds(204, 279, 89, 23);
		this.add(btnStart_1);
		this.btnPublicKey = new JButton("Public Key");
		btnPublicKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String modekey = btnPublicKey.getText().toString().trim();

				controller.openDialogKey(modekey);
			}
		});
		this.btnPublicKey.setBounds(243, 41, 98, 23);
		this.add(this.btnPublicKey);
		this.btnOpenFile = new JButton("Open");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openDialogFile_1();
			}
		});
		this.btnOpenFile.setBounds(345, 41, 76, 23);
		this.add(this.btnOpenFile);
		this.btnSaveFile = new JButton("Save");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openDialogFile_2();
			}
		});
		final JRadioButton rdoEncript_1 = new JRadioButton("Encrypt");
		rdoEncript_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPublicKey.setText("Public Key");
			}
		});

		// rdoEncript_1.addActionListener((ActionListener)new
		// PanelAsymmetric.PanelAsymmetric$6(this));
		this.btnGrModel.add(rdoEncript_1);
		rdoEncript_1.setSelected(true);
		rdoEncript_1.setBackground(Color.WHITE);
		rdoEncript_1.setBounds(34, 14, 68, 23);
		panelModeFile.add(rdoEncript_1);
		final JRadioButton rdoDecrypt_1 = new JRadioButton("Decrypt");
		rdoDecrypt_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnPublicKey.setText("Private Key");

			}
		});
		this.btnGrModel.add(rdoDecrypt_1);
		// rdoDecrypt_1.addActionListener((ActionListener)new
		// PanelAsymmetric.PanelAsymmetric$7(this));
		rdoDecrypt_1.setBackground(Color.WHITE);
		rdoDecrypt_1.setBounds(113, 14, 79, 23);
		panelModeFile.add(rdoDecrypt_1);
		this.btnSaveFile.setBounds(424, 41, 76, 23);
		this.add(this.btnSaveFile);

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


				Image img_main = new ImageIcon(MainView.class.getResource("/asset/3.3.png")).getImage()
						.getScaledInstance(600, 328, Image.SCALE_SMOOTH);

				AsymmetricHelp asmm = new AsymmetricHelp(img_main);
				asmm.setVisible(true);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon(img_help));
		lblNewLabel_3.setBounds(472, 283, 20, 20);
		add(lblNewLabel_3);
		this.controller.loadDataAlgorithms();
	}

	public void loadDataAlgorithms(final List<AlgorithmsItem> list) {
		final DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (final AlgorithmsItem algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem.getAlgorithms());
		}
		this.cbxAlgorithmFile.setModel(defaultComboBoxModel);
		this.mode = list.get(0).getAlgorithms();
		this.loadDataKeySize(list.get(0).getKeySize());
		this.loadDataMode(list.get(0).getMode());
		this.loadDataPadding(list.get(0).getPadding());
	}

	public void loadDataKeySize(final List<Integer> list) {
		final DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (final Integer algorithmsItem : list) {
			defaultComboBoxModel.addElement(String.valueOf(algorithmsItem));
		}
		this.cbxKeySizeFile.setModel(defaultComboBoxModel);
	}

	public void loadDataMode(final List<String> list) {
		final DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (final String algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem);
		}
		this.cbxModeFile.setModel(defaultComboBoxModel);
	}

	public void loadDataPadding(final List<String> list) {
		final DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		for (final String algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem);
		}
		this.cbxPaddingFile.setModel(defaultComboBoxModel);
	}

	public void setStatus(final String message) {
		this.taMessageStatus.setText(message);
	}

	public void setColorButtonOpen() {
		this.btnOpenFile.setBackground(Color.GREEN);
	}

	public void setColorKeyButtonFile() {
		this.btnPublicKey.setBackground(Color.GREEN);
	}

	public void setColorButtonSave() {
		this.btnSaveFile.setBackground(Color.GREEN);
	}

	public void setColorButtonOpenNor() {
		this.btnOpenFile.setBackground(new Color(240, 240, 240));
	}

	public void setColorButtonSaveNor() {
		this.btnSaveFile.setBackground(new Color(240, 240, 240));
	}
}