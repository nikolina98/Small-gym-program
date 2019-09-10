package gui;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import crud.Crud;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frmFitnessCentarDunja;
	private Crud crud;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWindow window = new MainWindow();
//					window.frmFitnessCentarDunja.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public MainWindow(Crud crud) {
		this.crud = crud;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		if(!crud.getOk()) {
			JOptionPane.showMessageDialog(frmFitnessCentarDunja, "Greska pri ucitavanju");
			System.exit(0);
		}
		frmFitnessCentarDunja = new JFrame();
		frmFitnessCentarDunja.setResizable(false);
		frmFitnessCentarDunja.setTitle("Fitness centar Dunja");
		frmFitnessCentarDunja.setBounds(100, 100, 954, 666);
		frmFitnessCentarDunja.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFitnessCentarDunja.getContentPane().setLayout(null);
		URL url = getClass().getResource("/fit.jpg");
		frmFitnessCentarDunja.setContentPane(new JLabel(new ImageIcon(url)));
		frmFitnessCentarDunja.setResizable(false);
		
		JButton btnNewButton = new JButton("Dodaj novog clana");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddingUser add = new AddingUser(crud);
				add.setModal(true);
				add.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(25, 25, 112));
		btnNewButton.setFont(new Font("Candara", Font.BOLD, 20));
		btnNewButton.setBounds(39, 32, 276, 53);
		frmFitnessCentarDunja.getContentPane().add(btnNewButton);
		
		JButton btnActive = new JButton("Spisak aktivnih clanarina");
		btnActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListUsers lu = new ListUsers(crud);
				lu.setModal(false);
				lu.setVisible(true);
			}
		});
		btnActive.setBackground(new Color(255,255,255));
		btnActive.setForeground(new Color(25, 25, 112));
		btnActive.setFont(new Font("Candara", Font.BOLD, 20));
		btnActive.setBounds(39, 102, 276, 53);
		frmFitnessCentarDunja.getContentPane().add(btnActive);
		
		JLabel dunja = new JLabel("Fitness centar Dunja");
		dunja.setForeground(new Color(255, 255, 255));
		dunja.setFont(new Font("Lucida Calligraphy", Font.BOLD, 62));
		dunja.setBounds(39, 200, 800, 200);
		frmFitnessCentarDunja.getContentPane().add(dunja);
		
		
		JButton buttonNotActive = new JButton("Spisak isteklih clanarina");
		buttonNotActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListUsers2 lu2 = new ListUsers2(crud);
				lu2.setModal(true);
				lu2.setVisible(true);
			}
		});
		buttonNotActive.setBackground(new Color(255,255,255));
		buttonNotActive.setForeground(new Color(25, 25, 112));
		buttonNotActive.setFont(new Font("Candara", Font.BOLD, 20));
		buttonNotActive.setBounds(39, 452, 276, 53);
		frmFitnessCentarDunja.getContentPane().add(buttonNotActive);
		
		JButton btnAddType = new JButton("Dodaj novi tip treninga");
		btnAddType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddingType at = new AddingType(crud);
				at.setModal(true);
				at.setVisible(true);
			}
		});
		btnAddType.setBackground(new Color(255,255,255));
		btnAddType.setForeground(new Color(25, 25, 112));
		btnAddType.setFont(new Font("Candara", Font.BOLD, 20));
		btnAddType.setBounds(39, 522, 276, 53);
		frmFitnessCentarDunja.getContentPane().add(btnAddType);
		
		frmFitnessCentarDunja.addWindowListener(new WindowAdapter() {
			 @Override
	            public void windowClosing(WindowEvent e)
	            {
	                boolean exit = crud.onExit();
	                if(!exit) {
	                	JOptionPane.showMessageDialog(frmFitnessCentarDunja, "Neuspelo snimanje podataka");
	                	return;
	                } else
	                	e.getWindow().dispose();
	            }
	            });
	}
	
	public void setVisible1(boolean vis) {
		frmFitnessCentarDunja.setVisible(vis);
	}
}
