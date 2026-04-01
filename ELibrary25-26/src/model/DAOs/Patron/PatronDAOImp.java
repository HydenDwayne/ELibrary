package model.DAOs.Patron;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatronDAOImp implements DAOShowAllPatron, DAOShowOnePatron {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    @Override
    public List<DAOPatron> getAllUsers() {

        List<DAOPatron> patrons = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); CallableStatement stmt = conn.prepareCall("{CALL showAllPatrons}")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                patrons.add(new DAOPatron(
                        rs.getString("Patron ID"),
                        rs.getString("Full Name"),
                        rs.getString("Email Address"),
                        rs.getString("Contact Number"),
                        rs.getString("Home Address"),
                        rs.getString("Campus"),
                        rs.getString("Patron Type")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patrons;
    }

	@Override
	public List<DAOPatron> getUserDetail() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String[] getCollegesPerCampus(String campusCode) {
        List<String> list = new ArrayList<>();
        

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("select CollegeCode from COLLEGE where CampCode = ?");) {

        	if (campusCode.equals("Main")) {
            	stmt.setString(1, "M");
            } else {
        		stmt.setString(1, "BC");
        	}
        	

            ResultSet rs = stmt.executeQuery();
        	
            while (rs.next()) {
                list.add(rs.getString("CollegeCode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert List<String> to String[]
        return list.toArray(new String[0]);
    }
	
	public String[] getPrograms(String collegeCode, String campusCode) {
	    List<String> list = new ArrayList<>();
	    
	    if (campusCode.equals("Main") || campusCode.equals("Bustos")) {
	    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	   	         PreparedStatement stmt = conn.prepareStatement(
	   	             "SELECT ProgramCode FROM PROGRAM WHERE CollCode = ? AND CampusCode = ?"
	   	         )) {

	   	        stmt.setString(1, collegeCode);

	   	        // DB uses single-letter codes, convert
	   	        if (campusCode.equalsIgnoreCase("Main")) {
	   	            stmt.setString(2, "M");
	   	        } else if (campusCode.equalsIgnoreCase("Bustos")) {
	   	            stmt.setString(2, "BC");
	   	        }

	   	        ResultSet rs = stmt.executeQuery();
	   	        while (rs.next()) {
	   	            list.add(rs.getString("ProgramCode"));
	   	        }

	   	    } catch (SQLException e) {
	   	        e.printStackTrace();
	   	    }
	    } else {
	    	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	   	         PreparedStatement stmt = conn.prepareStatement(
	   	             "SELECT ProgramCode FROM PROGRAM WHERE CampusCode = ?"
	   	         )) {

	   	        // DB uses single-letter codes, convert
	    		switch (campusCode.toLowerCase()) {
	            case "hagonoy": stmt.setString(1, "HC"); break;
	            case "meneses": stmt.setString(1, "MC"); break;
	            case "san rafael": stmt.setString(1, "SRC"); break;
	            case "sarmiento": stmt.setString(1, "SC"); break;
	            default: stmt.setString(1, campusCode);
	        }

	   	        ResultSet rs = stmt.executeQuery();
	   	        while (rs.next()) {
	   	            list.add(rs.getString("ProgramCode"));
	   	        }

	   	    } catch (SQLException e) {
	   	        e.printStackTrace();
	   	    }
	    }

	    

	    return list.toArray(new String[0]);
	}
	
	public String[] getProgramsByCampus(String campusCode) {
	    List<String> list = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement stmt = conn.prepareStatement(
	             "SELECT ProgramCode FROM PROGRAM WHERE CampusCode = ?"
	         )) {

	        // Convert user campus to DB code
	        switch (campusCode.toLowerCase()) {
	            case "main": stmt.setString(1, "M"); break;
	            case "bustos": stmt.setString(1, "BC"); break;
	            case "hagonoy": stmt.setString(1, "H"); break;
	            case "meneses": stmt.setString(1, "MC"); break;
	            case "san rafael": stmt.setString(1, "SR"); break;
	            case "sarmiento": stmt.setString(1, "SC"); break;
	            default: stmt.setString(1, campusCode);
	        }

	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            list.add(rs.getString("ProgramCode"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list.toArray(new String[0]);
	}
	
	public String[] getProgramsByCollegeOnly(String collegeCode) {
	    List<String> list = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement stmt = conn.prepareStatement(
	             "SELECT ProgramCode FROM PROGRAM WHERE CollCode = ?"
	         )) {

	        stmt.setString(1, collegeCode);

	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            list.add(rs.getString("ProgramCode"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list.toArray(new String[0]);
	}
	
}
