package model.DAOs.FacilityLogin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacilityLoginDAOImp implements DAOInterfaceFacilityLogin {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    @Override
    public List<DAOFacilityLogin> getLoggedIn(String facilityCode) {

        List<DAOFacilityLogin> cards = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); CallableStatement stmt = conn.prepareCall("{CALL showLoggedIn_FacilityLogin(?)}")) {

        	stmt.setString(1, facilityCode);
        	
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cards.add(new DAOFacilityLogin(
                        rs.getString("FacilityCode"),
                        rs.getString("PatronTimeIn"),
                        rs.getString("PatronTimeOut"),
                        rs.getString("PatronID"),
                        rs.getInt("CardNo")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }
}
