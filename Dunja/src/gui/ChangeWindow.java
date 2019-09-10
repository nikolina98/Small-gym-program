package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private User user;
	private JTextField txtValue;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			ChangeWindow dialog = new ChangeWindow();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ChangeWindow(User user) {
		setTitle("Produzivanje clanarine");
		this.user = user;
		setBounds(100, 100, 557, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel_3 = new JLabel("Datum polaska:");
		lblNewLabel_3.setFont(new Font("Candara", Font.BOLD, 22));
		lblNewLabel_3.setBounds(12, 10, 146, 28);
		contentPanel.add(lblNewLabel_3);
		
		JComboBox<Integer> cmb1 = new JComboBox<>();
		cmb1.setBounds(170, 13, 46, 22);
		contentPanel.add(cmb1);
		cmb1.addItem(null);
		
		JComboBox<String> cmb2 = new JComboBox<>();
		cmb2.setBounds(225, 13, 81, 22);
		contentPanel.add(cmb2);
		cmb2.addItem("");
		
		JComboBox<Integer> cmb3 = new JComboBox<>();
		cmb3.setBounds(318, 13, 61, 22);
		contentPanel.add(cmb3);
		cmb3.addItem(null);
		
		JComboBox<Integer> cmb4 = new JComboBox<>();
		cmb4.setBounds(246, 85, 46, 22);
		contentPanel.add(cmb4);
		cmb4.addItem(null);
		
		IntStream.range(1, 32).forEach(s -> {
			cmb1.addItem(s);
			cmb4.addItem(s);
		});
		
		JLabel lblDatumIstekaClanarine = new JLabel("Datum isteka clanarine:");
		lblDatumIstekaClanarine.setFont(new Font("Candara", Font.BOLD, 22));
		lblDatumIstekaClanarine.setBounds(12, 82, 220, 28);
		contentPanel.add(lblDatumIstekaClanarine);
		
		JComboBox<String> cmb5 = new JComboBox<>();
		cmb5.setBounds(306, 85, 73, 22);
		contentPanel.add(cmb5);
		cmb5.addItem("");
		
		String[] months = new DateFormatSymbols().getMonths();
        for (String month : months) {
            cmb2.addItem(month);
            cmb5.addItem(month);
        }
		
		JComboBox<Integer> cmb6 = new JComboBox<>();
		cmb6.setBounds(391, 85, 61, 22);
		contentPanel.add(cmb6);
		
		JLabel lblNewLabel = new JLabel("Placeno:");
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 22));
		lblNewLabel.setBounds(12, 144, 146, 28);
		contentPanel.add(lblNewLabel);
		
		JComboBox<String> cmbPaid = new JComboBox<>();
		cmbPaid.setBounds(122, 147, 170, 25);
		contentPanel.add(cmbPaid);
		cmbPaid.addItem("Ne");
		cmbPaid.addItem("Da");
		
		JLabel lblIznos = new JLabel("Iznos:");
		lblIznos.setFont(new Font("Candara", Font.BOLD, 22));
		lblIznos.setBounds(12, 206, 81, 28);
		contentPanel.add(lblIznos);
		
		txtValue = new JTextField();
		txtValue.setBounds(116, 199, 190, 35);
		contentPanel.add(txtValue);
		txtValue.setColumns(10);
		cmb6.addItem(null);
		
		IntStream.range(2015, 2050).forEach(s -> {
			cmb3.addItem(s);
			cmb6.addItem(s);
		});
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Potvrdi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cmbPaid.getSelectedIndex() == -1 || cmb1.getSelectedIndex() == -1 || cmb2.getSelectedIndex() == -1 || cmb3.getSelectedIndex() == -1 || cmb4.getSelectedIndex() == -1
								|| cmb5.getSelectedIndex() == -1 || cmb6.getSelectedIndex() == -1 || txtValue.getText().equals("")) {
							JOptionPane.showMessageDialog(contentPanel, "Popunite sva polja!");
							return;
						}
						int startDay = (int) cmb1.getSelectedItem();
						int startMonth = getIntMonth((String) cmb2.getSelectedItem());
						int startYear = (int) cmb3.getSelectedItem();
					
						LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
						
						int endDay = (int) cmb4.getSelectedItem();
						int endMonth = getIntMonth((String) cmb5.getSelectedItem());
						int endYear = (int) cmb6.getSelectedItem();
						LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
						
						boolean pay = true;
						if(cmbPaid.getSelectedIndex() == 0)
							pay = false;
						
						int value = Integer.parseInt(txtValue.getText());
						
						user.setStartDate(startDate);
						user.setEndDate(endDate);
						user.setPayStatus(pay);
						user.setPaidValue(value);
						
						JOptionPane.showMessageDialog(contentPanel, "Uspesno izmenjeni podaci korisniku: " + getUser().getName() + " " + getUser().getSurname() + "!");
						setVisible(false);
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
				JButton cancelButton = new JButton("Otkazi");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setForeground(new Color(25, 25, 112));
				cancelButton.setFont(new Font("Candara", Font.BOLD, 19));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private User getUser() {
		return user;
	}
	
	private int getIntMonth(String month) {
		String[] meseci = new DateFormatSymbols().getMonths();
        for (int i = 0; i < meseci.length; i++) {
           if(month.equals(meseci[i]))
        	   return i + 1;
        }
        return -1;
	}
}
