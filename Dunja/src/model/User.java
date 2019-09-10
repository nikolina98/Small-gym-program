package model;

import java.time.LocalDate;

public class User implements Comparable<User>{

	private static int counter = 0;
	private int id;
	private String name;
	private String surname;
	private String trainingType;
	private boolean memberStatus;
	private boolean payStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	private int paidValue;
	
	public User(String name, String surname, String trainingType, boolean memberStatus, boolean payStatus,
			LocalDate startDate, LocalDate endDate, int value) {
		super();
		this.id = counter++;
		this.name = name;
		this.surname = surname;
		this.trainingType = trainingType;
		this.memberStatus = memberStatus;
		this.payStatus = payStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paidValue = value;
	}

	public int getPaidValue() {
		return paidValue;
	}

	public void setPaidValue(int paidValue) {
		this.paidValue = paidValue;
	}

	public int getId() {
		return id;
	}

	public static void setCounter(int counter) {
		User.counter = counter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public boolean isMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(boolean memberStatus) {
		this.memberStatus = memberStatus;
	}

	public boolean isPayStatus() {
		return payStatus;
	}

	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result + (memberStatus ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (payStatus ? 1231 : 1237);
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((trainingType == null) ? 0 : trainingType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (memberStatus != other.memberStatus)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (payStatus != other.payStatus)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (trainingType == null) {
			if (other.trainingType != null)
				return false;
		} else if (!trainingType.equals(other.trainingType))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name + ";" + surname + ";" + trainingType + ";" + Boolean.toString(payStatus) + ";" + 
				startDate.toString() + ";" + endDate.toString() + ";" + paidValue;
	}

	@Override
	public int compareTo(User o) {
		int cmp = name.compareTo(o.getName());
		if(cmp == 0)
			cmp = surname.compareTo(o.getSurname());
		return cmp;
	}
}
