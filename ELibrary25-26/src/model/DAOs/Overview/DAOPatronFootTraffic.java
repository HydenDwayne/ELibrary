package model.DAOs.Overview;

import java.sql.Timestamp;

public class DAOPatronFootTraffic {

	private Timestamp time;
	private int count;
	
	public Timestamp getTime() {
		return time;
	}
	public int getCount() {
		return count;
	}
	
	public DAOPatronFootTraffic(Timestamp time, int count) {
		this.count = count;
		this.time = time;
	}
	
}
