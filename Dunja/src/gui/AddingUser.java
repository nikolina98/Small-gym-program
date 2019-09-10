package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.Crud;

import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AddingUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField txtValue;
	private Crud crud;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			AddingUser dialog = new AddingUser();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public AddingUser(Crud crud) {
		this.crud = crud;
		setResizable(false);
		setModal(true);
		setTitle("Dodavanje novog clana");
		setBounds(100, 100, 646, 417);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setFont(new Font("Candara", Font.BOLD, 22));
		lblIme.setBounds(12, 28, 74, 22);
		contentPanel.add(lblIme);
		
		nameField = new JTextField();
		nameField.setBounds(141, 22, 201, 35);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Prezime:");
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 22));
		lblNewLabel.setBounds(12, 82, 110, 22);
		contentPanel.add(lblNewLabel);
		
		surnameField = new JTextField();
		surnameField.setBounds(141, 76, 201, 35);
		contentPanel.add(surnameField);
		surnameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tip treninga:");
		lblNewLabel_1.setFont(new Font("Candara", Font.BOLD, 22));
		lblNewLabel_1.setBounds(12, 143, 119, 22);
		contentPanel.add(lblNewLabel_1);
		
		JComboBox<String> comboType = new JComboBox<>();
		comboType.setBounds(143, 143, 201, 22);
		contentPanel.add(comboType);
		List<String> trainings = getCrud().getTrainings();
		comboType.addItem("");
		trainings.forEach(s -> comboType.addItem(s));
		
		JLabel lblNewLabel_2 = new JLabel("Placeno:");
		lblNewLabel_2.setFont(new Font("Candara", Font.BOLD, 22));
		lblNewLabel_2.setBounds(376, 214, 125, 22);
		contentPanel.add(lblNewLabel_2);
		
		JComboBox<String> comboPay = new JComboBox<>();
		comboPay.setBounds(376, 249, 216, 22);
		contentPanel.add(comboPay);
		comboPay.addItem("Ne");
		comboPay.addItem("Da");
		
		JLabel lblNewLabel_3 = new JLabel("Datum polaska:");
		lblNewLabel_3.setFont(new Font("Candara", Font.BOLD, 22));
		lblNewLabel_3.setBounds(376, 22, 216, 28);
		contentPanel.add(lblNewLabel_3);
		
		JComboBox<Integer> cmb1 = new JComboBox<>();
		cmb1.setBounds(376, 76, 50, 22);
		contentPanel.add(cmb1);
		cmb1.addItem(null);
		
		JComboBox<String> cmb2 = new JComboBox<>();
		cmb2.setBounds(438, 76, 85, 22);
		contentPanel.add(cmb2);
		cmb2.addItem("");
		
		JComboBox<Integer> cmb3 = new JComboBox<>();
		cmb3.setBounds(535, 76, 72, 22);
		contentPanel.add(cmb3);
		cmb3.addItem(null);
		
		JLabel lblDatumIstekaClanarine = new JLabel("Datum isteka clanarine:");
		lblDatumIstekaClanarine.setFont(new Font("Candara", Font.BOLD, 22));
		lblDatumIstekaClanarine.setBounds(376, 123, 252, 28);
		contentPanel.add(lblDatumIstekaClanarine);
		
		JComboBox<Integer> cmb4 = new JComboBox<>();
		cmb4.setBounds(376, 164, 50, 22);
		contentPanel.add(cmb4);
		cmb4.addItem(null);
		
		IntStream.range(1, 32).forEach(s -> {
			cmb1.addItem(s);
			cmb4.addItem(s);
		});
		
		JComboBox<String> cmb5 = new JComboBox<>();
		cmb5.setBounds(438, 164, 85, 22);
		contentPanel.add(cmb5);
		cmb5.addItem("");
		
		String[] months = new DateFormatSymbols().getMonths();
        for (String month : months) {
            cmb2.addItem(month);
            cmb5.addItem(month);
        }
		
		JComboBox<Integer> cmb6 = new JComboBox<>();
		cmb6.setBounds(535, 164, 72, 22);
		contentPanel.add(cmb6);
		cmb6.addItem(null);
		
		IntStream.range(2015, 2050).forEach(s -> {
			cmb3.addItem(s);
			cmb6.addItem(s);
		});
		
		JLabel lblNewLabel_4 = new JLabel("Iznos:");
		lblNewLabel_4.setFont(new Font("Candara", Font.BOLD, 22));
		lblNewLabel_4.setBounds(12, 259, 63, 22);
		contentPanel.add(lblNewLabel_4);
		
		txtValue = new JTextField();
		txtValue.setBounds(141, 253, 201, 35);
		contentPanel.add(txtValue);
		txtValue.setColumns(10);
		
		JLabel lblDodatno = new JLabel("Dodatno:");
		lblDodatno.setFont(new Font("Candara", Font.BOLD, 22));
		lblDodatno.setBounds(12, 199, 110, 22);
		contentPanel.add(lblDodatno);
		
		JComboBox<String> cmbMore = new JComboBox<>();
		cmbMore.setBounds(141, 199, 201, 22);
		contentPanel.add(cmbMore);
		comboType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cmbMore.removeAllItems();
				if(comboType.getSelectedIndex() == 1) {
					cmbMore.addItem("Mesecno");
					cmbMore.addItem("Dnevno");
				} else if(comboType.getSelectedIndex() > 1 && comboType.getSelectedIndex() < 5) {
					for(int i = 5; i < trainings.size(); i++) {
						cmbMore.addItem(trainings.get(i));
					}
				} else if(comboType.getSelectedIndex() >= 5) {
					cmbMore.addItem("...");
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
				JButton okButton = new JButton("UNESI");
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setBackground(new Color(25, 25, 112));
				okButton.setFont(new Font("Candara", Font.BOLD, 17));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(cmb1.getSelectedIndex());
						if(nameField.getText().equals("") || surnameField.getText().equals("") || comboType.getSelectedIndex() == -1 || comboPay.getSelectedIndex() == -1 || comboType.getSelectedIndex() == -1
								|| cmbMore.getSelectedIndex() == -1 || cmb1.getSelectedIndex() == -1 || cmb2.getSelectedIndex() == -1 || cmb3.getSelectedIndex() == -1 || cmb4.getSelectedIndex() == -1
								|| cmb5.getSelectedIndex() == -1 || cmb6.getSelectedIndex() == -1 || txtValue.getText().equals("")) {
							JOptionPane.showMessageDialog(contentPanel, "Popunite sva polja!");
							return;
						}
						
						String name = nameField.getText();
						String surname = surnameField.getText();
						String type = (String) comboType.getSelectedItem();
						String more = (String) cmbMore.getSelectedItem();
						boolean pay = true;
						if(comboPay.getSelectedIndex() == 0)
							pay = false;
						int startDay = (int) cmb1.getSelectedItem();
						int startMonth = getIntMonth((String) cmb2.getSelectedItem());
						int startYear = (int) cmb3.getSelectedItem();
					
						
						LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
						
						
						int endDay = (int) cmb4.getSelectedItem();
						int endMonth = getIntMonth((String) cmb5.getSelectedItem());
						int endYear = (int) cmb6.getSelectedItem();
						LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
						
						int value; 
						try {
							value = Integer.parseInt(txtValue.getText());
						} catch (NumberFormatException em) {
							JOptionPane.showMessageDialog(contentPanel, "Niste uneli iznos u dobrom formatu!");
							txtValue.setText("");
							return;
						}
						boolean add = getCrud().add(name, surname, type + " - " + more, pay, startDate, endDate, value);
						if(add) {
							JOptionPane.showMessageDialog(contentPanel, "Korisnik je uspesno dodat");
							nameField.setText("");
							surnameField.setText("");
							txtValue.setText("");
							comboType.setSelectedIndex(0);
							comboPay.setSelectedIndex(0);
							cmbMore.removeAllItems();
							cmb1.setSelectedIndex(0);
							cmb2.setSelectedIndex(0);
							cmb3.setSelectedIndex(0);
							cmb4.setSelectedIndex(0);
							cmb5.setSelectedIndex(0);
							cmb6.setSelectedIndex(0);
							return;
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("OTKAZI");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setBackground(new Color(25, 25, 112));
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.setFont(new Font("Candara", Font.BOLD, 17));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private int getIntMonth(String month) {
		String[] meseci = new DateFormatSymbols().getMonths();
        for (int i = 0; i < meseci.length; i++) {
           if(month.equals(meseci[i]))
        	   return i + 1;
        }
        return -1;
	}
	
	private Crud getCrud() {
		return crud;
	}
}
