package gui;

import java.time.LocalDate;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.User;

public class Model extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] attributes = {"Ime","Prezime","Tip treninga","Aktivna clanarina","Placena clanarina",
			"Iznos", "Datum pocetka clanarina","Datum isteka clanarine"
	};
	private List<User> users;
	
	public Model(List<User> u) {
		users = u;
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		User u = users.get(arg0);
		switch(arg1) {
		case 0:
			return u.getName();
		case 1:
			return u.getSurname();
		case 2:
			return u.getTrainingType();
		case 3:
			return getBoolean(u.isMemberStatus());
		case 4:
			return getBoolean(u.isPayStatus());
		case 5:
			return String.valueOf(u.getPaidValue());
		case 6:
			return getDate(u.getStartDate());
		case 7:
			return getDate(u.getEndDate());
		}
		
		return null;
	}
	
	public User get(int index) {
		return users.get(index);
	}
	
	@Override
	public String getColumnName(int col) {
		return attributes[col];
	}

	public void removeFromList(int index) {
		users.remove(index);
	}

	private String getBoolean(boolean b) {
		if(b)
			return "Da";
		return "Ne";
	}
	
	private String getDate(LocalDate ld) {
		int day = ld.getDayOfMonth();
		int month = ld.getMonthValue();
		int year = ld.getYear();
		return day + "." + month + "." + year;
	}
}
