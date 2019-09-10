package crud;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.User;

public class Crud {

	private List<User> usersIn;
	private List<User> usersOut;
	private List<String> trainings;
	private boolean initOk;
	
	public Crud() {
		initialize();
	}
	
	public void initialize() {
		usersIn = new ArrayList<>();
		usersOut = new ArrayList<>();
		trainings = new ArrayList<>();
		InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream("/users.txt"));
		try(BufferedReader bf = new BufferedReader(isr)){
			String line = null;
			while((line = bf.readLine()) != null) {
				String[] data = line.split(";");
				String name = data[0];
				String surname = data[1];
				String trnType = data[2];
				boolean payStatus = Boolean.parseBoolean(data[3]);
				String[] dateS = data[4].split("-");
				LocalDate startDate = LocalDate.of(Integer.parseInt(dateS[0]),Integer.parseInt(dateS[1]),Integer.parseInt(dateS[2]));
				String[] dataE = data[5].split("-");
				LocalDate endDate = LocalDate.of(Integer.parseInt(dataE[0]), Integer.parseInt(dataE[1]), Integer.parseInt(dataE[2]));
				int value = Integer.parseInt(data[6]);
				
				LocalDate now = LocalDate.now();
				boolean memStatus = startDate.compareTo(now) <= 0 && endDate.compareTo(now) >= 0;
				User user = new User(name, surname, trnType, memStatus, payStatus, startDate, endDate, value);
				if(memStatus) {
					usersIn.add(user);
				} else {
					usersOut.add(user);
				}
			}
			
			InputStreamReader isr2 = new InputStreamReader(getClass().getResourceAsStream("/trainings.txt"));
			try(BufferedReader bf1 = new BufferedReader(isr2)){
				String line1 = null;
				while((line1 = bf1.readLine()) != null) {
					trainings.add(line1);
				}
			} catch (Exception e) {
				initOk = false;
			}
			
			initOk = true;
		}catch(IOException e){
			initOk = false;
		}
	}
	
	public boolean getOk() {
		return initOk;
	}
	
	public boolean add(String name, String surname, String type, boolean pay, LocalDate start, LocalDate end, int value) {
		try {
			LocalDate now = LocalDate.now();
			boolean memStatus = start.compareTo(now) <= 0 && end.compareTo(now) >= 0;
			User user = new User(name, surname, type, memStatus, pay, start, end, value);
			if(memStatus) {
				usersIn.add(user);
			} else {
				usersOut.add(user);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void switchUser(User u) {
		usersOut.remove(u);
		usersIn.add(u);
	}
	
	public void switchUserReverse(User u) {
		usersIn.remove(u);
		usersOut.add(u);
	}
	
	public boolean onExit() {
		try(PrintWriter pw = new PrintWriter(new FileOutputStream("users.txt"))){
			for(User u: usersIn)
				pw.write(u.toString() + "\n");
			
			for(User us: usersOut)
				pw.write(us.toString() + "\n");
			
			try(PrintWriter pw1 = new PrintWriter(new FileOutputStream("trainings.txt"))){

				for(String t: trainings)
					pw1.write(t + "\n");
			} catch (Exception e) {
				return false;
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<User> getActiveUsers(){
		Collections.sort(usersIn);
		return usersIn;
	}
	
	public List<User> getInactiveUsers(){
		Collections.sort(usersOut);
		return usersOut;
	}
	
	public List<String> getTrainings(){
		return trainings;
	}
	
	public boolean addTrainingType(String type) {
		try {
			trainings.add(type);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
