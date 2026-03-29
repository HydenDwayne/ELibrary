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
}
