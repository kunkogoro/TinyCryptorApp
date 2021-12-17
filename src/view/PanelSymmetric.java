package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import controller.SymmetricController;
import model.FileTypeFilter;
import modelSymmetric.AlgorithmsItem;
import modelSymmetric.DES;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Image;
import java.util.Enumeration;
import java.util.List;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import java.awt.Component;

public class PanelSymmetric extends JPanel {
	
	private final ButtonGroup btnGroupModel = new ButtonGroup();
	private SymmetricController controller = new SymmetricController(this);
	private JButton btnKey;
	private JButton btnKeyFile;
	private JButton btnOpenFile;
	private JButton btnSaveFile;
	private String mode = "";
	private String modeFile = "";

	/**
	 * Create the panel.
	 */

	private JComboBox cbxAlgorithm;
	private JComboBox cbxKeySize;
	private JComboBox cbxMode;
	private JComboBox cbxPadding;

	private JComboBox cbxAlgorithmFile;
	private JComboBox cbxKeySizeFile;
	private JComboBox cbxModeFile;
	private JComboBox cbxPaddingFile;

	private JTextArea taResult;
	private JTextArea taMessage;
	private JTextArea taMessageStatus;
	private final ButtonGroup buttonGroupModelFile = new ButtonGroup();
	private JPanel panel_3_2;

	public PanelSymmetric() {

		setBounds(0, 0, 507, 311);
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 507, 311);
		add(tabbedPane);

		JPanel panelString = new JPanel();
		panelString.setBackground(new Color(255, 255, 255));
		panelString.setBounds(10, 11, 59, 21);
		tabbedPane.addTab("String", panelString);
		panelString.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(
				new TitledBorder(null, "Mode", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		panel.setBounds(10, 11, 225, 49);
		panelString.add(panel);
		panel.setLayout(null);

		JRadioButton rdoDecrypt = new JRadioButton("Decrypt");
		rdoDecrypt.setBounds(113, 14, 79, 23);
		panel.add(rdoDecrypt);
		btnGroupModel.add(rdoDecrypt);
		rdoDecrypt.setBackground(new Color(255, 255, 255));

		JRadioButton rdoEncript = new JRadioButton("Encrypt");
		rdoEncript.setBounds(34, 14, 68, 23);
		panel.add(rdoEncript);
		rdoEncript.setSelected(true);
		btnGroupModel.add(rdoEncript);
		rdoEncript.setBackground(new Color(255, 255, 255));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Property", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		panel_1.setBounds(10, 74, 225, 87);
		panel_1.setBackground(new Color(255, 255, 255));
		panelString.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Algorithms");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 23, 71, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Key size");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 56, 46, 14);
		panel_1.add(lblNewLabel_1);

		cbxAlgorithm = new JComboBox();
		cbxAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mode = String.valueOf(cbxAlgorithm.getSelectedItem());
				controller.loadDataAllOption(mode);
			}
		});
		cbxAlgorithm.setBounds(107, 19, 108, 22);
		panel_1.add(cbxAlgorithm);

		cbxKeySize = new JComboBox();
		cbxKeySize.setBounds(107, 52, 108, 22);
		panel_1.add(cbxKeySize);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(186, 85, 211)));
		panel_2.setBounds(10, 170, 225, 87);
		panelString.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblMode = new JLabel("Mode");
		lblMode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMode.setBounds(10, 24, 71, 14);
		panel_2.add(lblMode);

		JLabel lblNewLabel_1_1 = new JLabel("Padding");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 57, 46, 14);
		panel_2.add(lblNewLabel_1_1);

		cbxMode = new JComboBox();
		cbxMode.setBounds(107, 16, 108, 22);
		panel_2.add(cbxMode);

		cbxPadding = new JComboBox();
		cbxPadding.setBounds(107, 49, 108, 22);
		panel_2.add(cbxPadding);

		JButton btnStart = new JButton("Run");
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String opti = "";
				String algorithms = cbxAlgorithm.getSelectedItem().toString().trim();
				String keySize = cbxKeySize.getSelectedItem().toString().trim();
				String mode = cbxMode.getSelectedItem().toString().trim();
				String padding = cbxPadding.getSelectedItem().toString().trim();
				String message = taMessage.getText().toString().trim();

				try {

					Enumeration<AbstractButton> btngrMode = btnGroupModel.getElements();

					while (btngrMode.hasMoreElements()) {
						JRadioButton button = (JRadioButton) btngrMode.nextElement();
						if (button.isSelected())
							opti = button.getText();
					}
					if (opti == "Encrypt") {
						String encrypt = controller.encrypt(algorithms, message, mode, padding);
						taResult.setText(encrypt);
					} else if (opti == "Decrypt") {
						String decrypt = controller.decrypt(algorithms, message, mode, padding);
						taResult.setText(decrypt);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					taResult.setText("Vui lòng chọn mode phù hợp");
				}

			}
		});
		btnStart.setBounds(204, 258, 89, 23);
		panelString.add(btnStart);
	//	btnStart.setIcon(new ImageIcon("D:\\OneDrive\\Desktop\\start.png"));
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(
				new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(186, 85, 211)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(259, 74, 233, 87);
		panelString.add(panel_3);
		panel_3.setLayout(null);

		taMessage = new JTextArea();
		taMessage.setRows(10);
		taMessage.setLineWrap(true);
		taMessage.setBounds(10, 21, 213, 57);
		panel_3.add(taMessage);

//		JScrollPane scr = new JScrollPane(taMessage,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		panel_3.add(scr);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Output",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(186, 85, 211)));
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setBounds(259, 170, 233, 87);
		panelString.add(panel_3_1);

		taResult = new JTextArea();
		taResult.setLineWrap(true);
		taResult.setBackground(new Color(192, 192, 192));
		taResult.setEditable(false);
		taResult.setBounds(10, 21, 213, 57);
		panel_3_1.add(taResult);

//		JScrollPane scr = new JScrollPane(taResult,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		panel_3_1.add(scr);

		btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mode = cbxAlgorithm.getSelectedItem().toString().trim();
				controller.openDialogKey(mode);

			}
		});
		btnKey.setBounds(259, 22, 76, 23);
		panelString.add(btnKey);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String result = taResult.getText().toString().trim();

				try {
					controller.saveDialogFile(result);
//					controller.openDialogError("Lưu file thành công!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					controller.openDialogError("Lưu file thất bại");
				}

			}
		});
		btnSave.setBounds(416, 22, 76, 23);
		panelString.add(btnSave);

		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String message = controller.readFile();

					taMessage.setText(message);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					controller.openDialogError("Mở file thất bại");

				}

			}
		});
		btnOpen.setBounds(338, 22, 76, 23);
		panelString.add(btnOpen);

		JPanel panelFile = new JPanel();
		panelFile.setBounds(67, 11, 59, 21);
		panelFile.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("File", panelFile);
		panelFile.setLayout(null);

		JPanel panelModeFile = new JPanel();
		panelModeFile.setLayout(null);
		panelModeFile.setBorder(
				new TitledBorder(null, "Mode", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		panelModeFile.setBackground(Color.WHITE);
		panelModeFile.setBounds(10, 11, 225, 49);
		panelFile.add(panelModeFile);

		JRadioButton rdoDecrypt_1 = new JRadioButton("Decrypt");
		buttonGroupModelFile.add(rdoDecrypt_1);
		rdoDecrypt_1.setBackground(Color.WHITE);
		rdoDecrypt_1.setBounds(113, 14, 79, 23);
		panelModeFile.add(rdoDecrypt_1);

		JRadioButton rdoEncript_1 = new JRadioButton("Encrypt");
		buttonGroupModelFile.add(rdoEncript_1);
		rdoEncript_1.setSelected(true);
		rdoEncript_1.setBackground(Color.WHITE);
		rdoEncript_1.setBounds(34, 14, 68, 23);
		panelModeFile.add(rdoEncript_1);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "Property", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(10, 74, 225, 87);
		panelFile.add(panel_1_1);

		JLabel lblNewLabel_2 = new JLabel("Algorithms");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 23, 71, 14);
		panel_1_1.add(lblNewLabel_2);

		JLabel lblNewLabel_1_2 = new JLabel("Key size");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(10, 56, 46, 14);
		panel_1_1.add(lblNewLabel_1_2);

		cbxAlgorithmFile = new JComboBox();
		cbxAlgorithmFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modeFile = String.valueOf(cbxAlgorithmFile.getSelectedItem());
				controller.loadDataAllOption(modeFile);

			}
		});
		cbxAlgorithmFile.setBounds(107, 19, 108, 22);
		panel_1_1.add(cbxAlgorithmFile);

		cbxKeySizeFile = new JComboBox();
		cbxKeySizeFile.setBounds(107, 52, 108, 22);
		panel_1_1.add(cbxKeySizeFile);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(186, 85, 211)));
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(10, 170, 225, 87);
		panelFile.add(panel_2_1);

		JLabel lblMode_1 = new JLabel("Mode");
		lblMode_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMode_1.setBounds(10, 24, 71, 14);
		panel_2_1.add(lblMode_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Padding");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(10, 57, 46, 14);
		panel_2_1.add(lblNewLabel_1_1_1);

		cbxModeFile = new JComboBox();
		cbxModeFile.setBounds(107, 16, 108, 22);
		panel_2_1.add(cbxModeFile);

		cbxPaddingFile = new JComboBox();
		cbxPaddingFile.setBounds(107, 49, 108, 22);
		panel_2_1.add(cbxPaddingFile);

		panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Status", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		panel_3_2.setBackground(Color.WHITE);
		panel_3_2.setBounds(259, 74, 233, 87);
		panelFile.add(panel_3_2);

		taMessageStatus = new JTextArea();
		taMessageStatus.setDisabledTextColor(new Color(0, 0, 0));
		taMessageStatus.setEditable(false);
		taMessageStatus.setBackground(new Color(192, 192, 192));
		taMessageStatus.setEnabled(false);
		taMessageStatus.setFont(new Font("Monospaced", Font.PLAIN, 13));
		taMessageStatus.setForeground(new Color(0, 0, 0));
		taMessageStatus.setRows(10);
		taMessageStatus.setLineWrap(true);
		taMessageStatus.setBounds(10, 21, 213, 57);
		panel_3_2.add(taMessageStatus);

		btnKeyFile = new JButton("Key");
		btnKeyFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String modeFile = cbxAlgorithmFile.getSelectedItem().toString().trim();
				controller.openDialogKey(modeFile);
			}
		});
		btnKeyFile.setBounds(259, 22, 76, 23);
		panelFile.add(btnKeyFile);

		btnOpenFile = new JButton("Open");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.openDialogFile_1();

			}
		});
		btnOpenFile.setBounds(338, 22, 76, 23);
		panelFile.add(btnOpenFile);

		btnSaveFile = new JButton("Save");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openDialogFile_2();
			}
		});
		btnSaveFile.setBounds(416, 22, 76, 23);
		panelFile.add(btnSaveFile);

		JButton btnStart_1 = new JButton("Run");
		btnStart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String opti = "";
				String algorithms = cbxAlgorithmFile.getSelectedItem().toString().trim();
				String keySize = cbxKeySizeFile.getSelectedItem().toString().trim();
				String mode = cbxModeFile.getSelectedItem().toString().trim();
				String padding = cbxPaddingFile.getSelectedItem().toString().trim();

				try {

					Enumeration<AbstractButton> btngrMode = buttonGroupModelFile.getElements();

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
		btnStart_1.setBounds(204, 258, 89, 23);
		panelFile.add(btnStart_1);

		controller.loadDataAlgorithms();
	}

	public void setColorKeyButton() {

		btnKey.setBackground(Color.GREEN);

	}

	public void setColorKeyButtonFile() {

		btnKeyFile.setBackground(Color.GREEN);

	}
	public void setColorButtonOpen() {
		btnOpenFile.setBackground(Color.GREEN);
	}
	public void setColorButtonSave() {
		btnSaveFile.setBackground(Color.GREEN);
	}
	public void setColorButtonOpenNor() {
		btnOpenFile.setBackground(new Color(240,240,240));
	}
	public void setColorButtonSaveNor() {
		btnSaveFile.setBackground(new Color(240,240,240));
	}

	public void setStatus(String message) {
		taMessageStatus.setText(message);
	}

	public void loadDataAlgorithms(List<AlgorithmsItem> list) {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();

		for (AlgorithmsItem algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem.getAlgorithms());

		}
		cbxAlgorithm.setModel(defaultComboBoxModel);
		cbxAlgorithmFile.setModel(defaultComboBoxModel);
		this.mode = list.get(0).getAlgorithms();
		this.modeFile = list.get(0).getAlgorithms();
		loadDataKeySize(list.get(0).getKeySize());
		loadDataMode(list.get(0).getMode());
		loadDataPadding(list.get(0).getPadding());
	}

	public void loadDataKeySize(List<Integer> list) {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();

		for (Integer algorithmsItem : list) {
			defaultComboBoxModel.addElement(String.valueOf(algorithmsItem));

		}
		cbxKeySize.setModel(defaultComboBoxModel);
		cbxKeySizeFile.setModel(defaultComboBoxModel);
	}

	public void loadDataMode(List<String> list) {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();

		for (String algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem);

		}
		cbxMode.setModel(defaultComboBoxModel);
		cbxModeFile.setModel(defaultComboBoxModel);
	}

	public void loadDataPadding(List<String> list) {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();

		for (String algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem);

		}
		cbxPadding.setModel(defaultComboBoxModel);
		cbxPaddingFile.setModel(defaultComboBoxModel);
	}
}
