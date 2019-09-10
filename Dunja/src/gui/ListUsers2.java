package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import crud.Crud;
import model.User;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ListUsers2 extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Crud crud;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ListUsers dialog = new ListUsers();
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
	/**
	 * Create the dialog.
	 */
	public ListUsers2(Crud crud) {
		this.crud = crud;
		setTitle("Spisak isteklih clanarina");
		setBounds(100, 100, 1296, 600);
		getContentPane().setLayout(null);
		URL url = getClass().getResource("/bck.jpg");
		setContentPane(new JLabel(new ImageIcon(url)));
		setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 1255, 370);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		List<User> users = getCrud().getInactiveUsers();
		Model tm = new Model(users);
		table.setModel(tm);
		
		TableRowSorter<Model> row = new TableRowSorter<Model>(tm);
		JLabel labelName = new JLabel("Ukucaj rec za pretragu: ");
		labelName.setFont(new Font("Candara", Font.BOLD, 20));
		labelName.setForeground(Color.WHITE);
		labelName.setBounds(12, 400, 210, 22);
		table.setRowSorter(row);
		getContentPane().add(labelName);
		
		JTextField nameField = new JTextField();
		nameField.setBounds(225, 390, 201, 35);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		nameField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = nameField.getText();

                if (text.trim().length() == 0) {
                    row.setRowFilter(null);
                } else {
                    row.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = nameField.getText();

                if (text.trim().length() == 0) {
                    row.setRowFilter(null);
                } else {
                    row.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
		
		ListSelectionModel lsm = table.getSelectionModel();
		
		JButton btnMore = new JButton("Obnovi clanarinu");
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int maxSel = lsm.getMaxSelectionIndex();
				if(maxSel == -1)
					return;
				User user = tm.get(maxSel);
				ChangeWindow cw = new ChangeWindow(user);
				cw.setModal(true);
				cw.setVisible(true);
				LocalDate now = LocalDate.now();
				if(now.compareTo(user.getEndDate()) <= 0 && now.compareTo(user.getStartDate()) >= 0) {
					user.setMemberStatus(true);
					getCrud().switchUser(user);
				}
					
				tm.fireTableDataChanged();
			}
		});
		btnMore.setBounds(12, 450, 210, 50);
		btnMore.setForeground(new Color(25, 25, 112));
		btnMore.setBackground(Color.WHITE);
		btnMore.setFont(new Font("Candara", Font.BOLD, 20));
		getContentPane().add(btnMore);
		
		JButton btnType = new JButton("Promeni tip treninga");
		btnType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int maxSel = lsm.getMaxSelectionIndex();
				if(maxSel == -1)
					return;
				User user = tm.get(maxSel);
				TypeChange cw = new TypeChange(user, crud);
				cw.setModal(true);
				cw.setVisible(true);
				tm.fireTableDataChanged();
			}
		});
		btnType.setBounds(250, 450, 230, 50);
		btnType.setForeground(new Color(25, 25, 112));
		btnType.setBackground(Color.WHITE);
		btnType.setFont(new Font("Candara", Font.BOLD, 20));
		getContentPane().add(btnType);
		
		JButton delete = new JButton("Izbrisi clana");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int max = lsm.getMaxSelectionIndex();
				if(max == -1)
					return;
				tm.removeFromList(max);
				tm.fireTableDataChanged();
				JOptionPane.showMessageDialog(getContentPane(), "Uspesno izbrisan clan!");
			}
		});
		delete.setBounds(515, 450, 230, 50);
		delete.setForeground(new Color(25, 25, 112));
		delete.setBackground(Color.WHITE);
		delete.setFont(new Font("Candara", Font.BOLD, 20));
		getContentPane().add(delete);
	}
	
	private Crud getCrud() {
		return crud;
	}
}
