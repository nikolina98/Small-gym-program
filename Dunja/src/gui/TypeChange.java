package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.Crud;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class TypeChange extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private User user;
	private Crud crud;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			TypeChange dialog = new TypeChange();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public TypeChange(User user, Crud crud) {
		this.user = user;
		this.crud = crud;
		setTitle("Promena tipa treninga");
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		{
			JLabel lblIzabreiTip = new JLabel("Izaberi tip:");
			lblIzabreiTip.setFont(new Font("Candara", Font.BOLD, 22));
			lblIzabreiTip.setBounds(12, 26, 136, 28);
			contentPanel.add(lblIzabreiTip);
		}
			List<String> trainings = getCrud().getTrainings();
			JComboBox<String> comboType = new JComboBox<>();
			comboType.setBounds(140, 26, 209, 28);
			contentPanel.add(comboType);
			comboType.addItem(null);
			trainings.forEach(t -> comboType.addItem(t));
		{
			JLabel lblDodatno = new JLabel("Dodatno:");
			lblDodatno.setFont(new Font("Candara", Font.BOLD, 22));
			lblDodatno.setBounds(12, 108, 114, 22);
			contentPanel.add(lblDodatno);
		}
		
			JComboBox<String> cmbMore = new JComboBox<>();
			cmbMore.setBounds(140, 105, 209, 28);
			contentPanel.add(cmbMore);
		
		comboType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cmbMore.removeAllItems();
				if(comboType.getSelectedIndex() == 1) {
					cmbMore.addItem("Mesecno");
					cmbMore.addItem("Dnevno");
				} else if(comboType.getSelectedIndex() > 1 && comboType.getSelectedIndex() < 5) {
					for(int i = 4; i < trainings.size(); i++) {
						cmbMore.addItem(trainings.get(i));
					}
				} else if(comboType.getSelectedIndex() >= 5) {
					cmbMore.addItem(" x 8");
					cmbMore.addItem(" x 12");
					cmbMore.addItem(" x 16");
				}
				
			}
		});
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(comboType.getSelectedIndex() == -1 || cmbMore.getSelectedIndex() == -1) {
							JOptionPane.showMessageDialog(contentPanel, "Popunite sva polja!");
							return;
						}
						String type = (String) comboType.getSelectedItem();
						String more = (String) cmbMore.getSelectedItem();
						
						getUser().setTrainingType(type + " - " + more);
						JOptionPane.showMessageDialog(contentPanel, "Uspesno izmenjeni podaci korisniku: " + getUser().getName() + " " + getUser().getSurname() + "!");
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.setBackground(new Color(255, 255, 255));
				okButton.setForeground(new Color(25, 25, 112));
				okButton.setFont(new Font("Candara", Font.BOLD, 18));
				okButton.setActionCommand("Potvrdi");
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.setForeground(new Color(25, 25, 112));
				cancelButton.setFont(new Font("Candara", Font.BOLD, 19));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setActionCommand("Otkazi");
			}
		}
	}

	private User getUser() {
		return this.user;
	}
	
	private Crud getCrud() {
		return crud;
	}
}
