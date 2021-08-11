package reservation;

import java.sql.Timestamp;

public class reservationDto {
	private int id;
	private String email;
	private String name;
	private int time;
	private Timestamp created;
	private Timestamp rDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getrDate() {
		return rDate;
	}
	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

}
