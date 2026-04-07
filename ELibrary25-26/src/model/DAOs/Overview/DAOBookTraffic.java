package model.DAOs.Overview;

public class DAOBookTraffic {

    private String dayName; // Monday, Tuesday, ...
    private int count;

    public DAOBookTraffic(String dayName, int count) {
        this.dayName = dayName;
        this.count = count;
    }

    public String getDayName() {
        return dayName;
    }

    public int getCount() {
        return count;
    }
}