package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.SessionKeyController;
import modelSymmetric.AlgorithmsItem;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelSessionKey extends JPanel {

	private JComboBox cbxKeySize;
	private JComboBox cbxAlgorithm;
	private String mode;
	private SessionKeyController controller = new SessionKeyController(this);
	private JButton btnStart;
	private JButton btnSave;
	private JLabel lblNewLabel_2;
	private Image img_help = new ImageIcon(MainView.class.getResource("/asset/help.jpg")).getImage()
			.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	/**
	 * Create the panel.
	 */
	public PanelSessionKey() {
		setBounds(0, 0, 507, 311);
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Property", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(94, 60, 315, 92);
		add(panel_1);

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
				btnSave.setBounds(320, 198, 89, 23);

			}
		});
		cbxAlgorithm.setBounds(107, 19, 198, 22);
		panel_1.add(cbxAlgorithm);

		cbxKeySize = new JComboBox();
		cbxKeySize.setBounds(107, 52, 198, 22);
		panel_1.add(cbxKeySize);

		btnStart = new JButton("Run");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String algorthims = (String) cbxAlgorithm.getSelectedItem();
				String keySize = (String) cbxKeySize.getSelectedItem();
				controller.createGenKeyPair(algorthims, keySize);
			}
		});
		btnStart.setBounds(94, 198, 89, 23);
		add(btnStart);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					controller.saveDialogFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					controller.openDialogError("Lưu key thất bại");
				}
			}
		});
		btnSave.setBounds(320, 198, 89, 23);
		add(btnSave);

		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Image img_main = new ImageIcon(MainView.class.getResource("/asset/4.4.png")).getImage()
						.getScaledInstance(600, 328, Image.SCALE_SMOOTH);

				AsymmetricHelp asmm = new AsymmetricHelp(img_main);
				asmm.setVisible(true);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(img_help));
		lblNewLabel_2.setBounds(477, 280, 20, 20);
		add(lblNewLabel_2);

		controller.loadDataAlgorithms();

	}

	public void setColorKeyButtonSuccess() {
		btnStart.setBackground(Color.GREEN);
	}

	public void setColorKeyButtonFail() {
		btnStart.setBackground(Color.RED);
	}

	public void loadDataAlgorithms(List<AlgorithmsItem> list) {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();

		for (AlgorithmsItem algorithmsItem : list) {
			defaultComboBoxModel.addElement(algorithmsItem.getAlgorithms());

		}
		cbxAlgorithm.setModel(defaultComboBoxModel);
		this.mode = list.get(0).getAlgorithms();
		loadDataKeySize(list.get(0).getKeySize());
	}

	public void loadDataKeySize(List<Integer> list) {
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();

		for (Integer algorithmsItem : list) {
			defaultComboBoxModel.addElement(String.valueOf(algorithmsItem));
		}
		cbxKeySize.setModel(defaultComboBoxModel);
	}
}
