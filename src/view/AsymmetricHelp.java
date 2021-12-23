package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AsymmetricHelp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	private Image image;
	private JLabel lblNewLabel_1;


	private Image img_lose = new ImageIcon(AsymmetricHelp.class.getResource("/asset/close.png")).getImage()
			.getScaledInstance(30, 30, Image.SCALE_SMOOTH);


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					
//					AsymmetricHelp frame = new AsymmetricHelp();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AsymmetricHelp(Image icon) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 350);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBackground(Color.WHITE);
		setUndecorated(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dispose();

			}
		});
		lblNewLabel.setIcon(new ImageIcon(img_lose));
		lblNewLabel.setBounds(620, 4, 35, 30);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("");
		//lblNewLabel_1.setIcon(new ImageIcon(img_main));
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setBounds(21, 8, 600, 342);
		contentPane.add(lblNewLabel_1);

		setImage(icon);
		
	}
	public void setImage(Image image) {
		lblNewLabel_1.setIcon(new ImageIcon(image));
	}
}
