package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.Crud;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddingType extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Crud crud;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			AddingType dialog = new AddingType();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddingType(Crud crud) {
		this.crud = crud;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		{
			JLabel lblUnesiteImeTreninga = new JLabel("Unesite ime treninga:");
			lblUnesiteImeTreninga.setFont(new Font("Candara", Font.BOLD, 22));
			lblUnesiteImeTreninga.setBounds(12, 25, 248, 28);
			contentPanel.add(lblUnesiteImeTreninga);
		}
		{
			textField = new JTextField();
			textField.setBounds(12, 65, 388, 44);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String type = textField.getText();
						System.out.println(getCrud());
						boolean add = getCrud().addTrainingType(type);
						if(add) {
							JOptionPane.showMessageDialog(contentPanel, "Uspesno dodat novi tip treninga!");
							textField.setText("");
							return;
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Nesupelo dodavanje novog tipa treninga!");
							return;
						}
					}
				});
				okButton.setBackground(new Color(255, 255, 255));
				okButton.setForeground(new Color(25, 25, 112));
				okButton.setFont(new Font("Candara", Font.BOLD, 18));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setForeground(new Color(25, 25, 112));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setFont(new Font("Candara", Font.BOLD, 18));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private Crud getCrud() {
		return crud;
	}
}
