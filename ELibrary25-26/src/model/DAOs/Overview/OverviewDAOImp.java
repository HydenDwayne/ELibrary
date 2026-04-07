package model.DAOs.Overview;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.DAOs.FunctionHall.DAOFuncHall;

public class OverviewDAOImp {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";


	public List<DAOOverview> getAllOverview() {

		List<DAOOverview> ims = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM getOverviewData")) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ims.add(new DAOOverview(rs.getInt("NoOfBooks"), rs.getInt("NoOfBorrowedBooks"),
						rs.getInt("NoOfOverdueBooks"), rs.getInt("NoOfPatron")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ims;
	}
	
	public List<DAOPatronFootTraffic> getTraffic() {
		String sql = "SELECT * FROM showPatronFootTraffic";

		
		

        List<DAOPatronFootTraffic> analytics = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                	analytics.add(new DAOPatronFootTraffic(
                            rs.getTimestamp("HourStart"),
                            rs.getInt("PatronCount")
                    ));
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return analytics;
    }
	
	public List<DAOBookTraffic> getBookTraffic() {
        String sql = "SELECT * FROM showBookTraffic";

        List<DAOBookTraffic> analytics = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String dayName = rs.getString(2);
                int count = rs.getInt(3);

                analytics.add(new DAOBookTraffic(dayName, count));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return analytics;
    }
    
    
    
}
