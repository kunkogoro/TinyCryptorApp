package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class MainView extends JFrame {


	private JPanel contentPane;
	private final JPanel panelMenu = new JPanel();

	private Image img_logo = new ImageIcon(MainView.class.getResource("/asset/iconSecurity.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

	private PanelSymmetric panelSymmetric;
	private PanelAsymmetric panelAsymmetric;
	private PanelSessionKey panelGenkeyPair;
	private PanelGenKeyPair panelSessionKey;
	private PanelHash panelHash;
	private PanelPBE panelPBE;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		//	setBounds(100, 100, 711, 418);
		setBounds(100, 100, 650, 350);
		//setLocation(deDimension.width/2-getWidth()/2,deDimension.height/2-getHeight()/2 );
		setLocationRelativeTo(null);
//		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panelMenu.setBounds(0, 0, 126, 361);
		panelMenu.setBackground(SystemColor.textInactiveText);
		panelMenu.setForeground(Color.CYAN);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		// Panel Symmetric
		panelSymmetric = new PanelSymmetric();
//		panelSymmetric.setBounds(0, 0, 420, 311);
		// panel Asymmetric
		panelAsymmetric = new PanelAsymmetric();
//		panelSymmetric.setBounds(0, 0, 408, 311);
		panelGenkeyPair = new PanelSessionKey();
		panelSessionKey = new PanelGenKeyPair();
		panelHash = new PanelHash();
		panelPBE = new PanelPBE();



		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(20, 11, 106, 78);
		lblIcon.setIcon(new ImageIcon(img_logo));
		panelMenu.add(lblIcon);

		// Jpanel Symmetric
		JPanel panelSymetric = new JPanel();
		panelSymetric.addMouseListener(new PanelButtonMouseAdapter(panelSymetric) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClick(panelSymmetric);

			}
		});
		panelSymetric.setBackground(SystemColor.controlDkShadow);
		panelSymetric.setBounds(10, 100, 106, 24);
		panelMenu.add(panelSymetric);
		panelSymetric.setLayout(null);



		JLabel lblNewLabel = new JLabel("Symmetric");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(20, 5, 66, 14);
		panelSymetric.add(lblNewLabel);

		// Jpanel Asymmetric
		JPanel panelAsymetric = new JPanel();
		panelAsymetric.addMouseListener(new PanelButtonMouseAdapter(panelAsymetric){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClick(panelAsymmetric);
			}
		});
		panelAsymetric.setBackground(SystemColor.controlDkShadow);
		panelAsymetric.setBounds(10, 135, 106, 24);
		panelMenu.add(panelAsymetric);

		JLabel lblAsymmetric = new JLabel("Asymmetric");
		lblAsymmetric.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsymmetric.setForeground(Color.WHITE);
		lblAsymmetric.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelAsymetric.add(lblAsymmetric);

		JPanel panGenkeyPair = new JPanel();
		panGenkeyPair.addMouseListener(new PanelButtonMouseAdapter(panGenkeyPair){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClick(panelGenkeyPair);
			}
		});
		panGenkeyPair.setBackground(SystemColor.controlDkShadow);
		panGenkeyPair.setBounds(10, 170, 106, 24);
		panelMenu.add(panGenkeyPair);

		JLabel lblNewLabel_3 = new JLabel("Symmetric Key");

		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		panGenkeyPair.add(lblNewLabel_3);

		JPanel panelSessonKey = new JPanel();

		panelSessonKey.addMouseListener(new PanelButtonMouseAdapter(panelSessonKey){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClick(panelSessionKey);
			}
		});
		panelSessonKey.setBackground(SystemColor.controlDkShadow);
		panelSessonKey.setBounds(10, 205, 106, 24);
		panelMenu.add(panelSessonKey);

		JLabel lblNewLabel_2 = new JLabel("Asymmetric Key");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSessonKey.add(lblNewLabel_2);

		JPanel paneHash = new JPanel();
		paneHash.addMouseListener(new PanelButtonMouseAdapter(paneHash){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClick(panelHash);
			}
		});
		paneHash.setBackground(SystemColor.controlDkShadow);
		paneHash.setBounds(10, 240, 106, 24);
		panelMenu.add(paneHash);

		JLabel lblNewLabel_2_1 = new JLabel("Hash");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		paneHash.add(lblNewLabel_2_1);

		JPanel panelSymetric_3_2 = new JPanel();
		panelSymetric_3_2.addMouseListener(new PanelButtonMouseAdapter(panelSymetric_3_2){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClick(panelPBE);
			}
		});
		panelSymetric_3_2.setBackground(SystemColor.controlDkShadow);
		panelSymetric_3_2.setBounds(10, 275, 106, 24);
		panelMenu.add(panelSymetric_3_2);

		JLabel lblNewLabel_2_2 = new JLabel("PBE");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSymetric_3_2.add(lblNewLabel_2_2);

		JPanel panelContent = new JPanel();
		panelContent.setBounds(127, 0, 507, 311);
		contentPane.add(panelContent);
		panelContent.setLayout(null);


		panelContent.add(panelSymmetric);
		panelContent.add(panelAsymmetric);
		panelContent.add(panelGenkeyPair);
		panelContent.add(panelSessionKey);
		panelContent.add(panelHash);
		panelContent.add(panelPBE);
		panelPBE.setVisible(false);
	}
	public void menuClick(JPanel jpanel) {
		panelAsymmetric.setVisible(false);
		panelSymmetric.setVisible(false);
		panelGenkeyPair.setVisible(false);
		panelSessionKey.setVisible(false);
		panelHash.setVisible(false);
		panelPBE.setVisible(false);
		jpanel.setVisible(true);

	}
	private class PanelButtonMouseAdapter extends MouseAdapter {

		JPanel jpanel;

		public PanelButtonMouseAdapter(JPanel jPanel) {
			// TODO Auto-generated constructor stub
			this.jpanel = jPanel;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			jpanel.setBackground(SystemColor.controlDkShadow);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			jpanel.setBackground(new Color(127, 255, 212));
		}
		@Override
		public void mousePressed(MouseEvent e) {
			jpanel.setBackground(new Color(0, 250, 154));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			jpanel.setBackground(new Color(0, 250, 154));
		}
	}

}

