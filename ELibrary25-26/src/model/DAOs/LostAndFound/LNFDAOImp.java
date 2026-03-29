package model.DAOs.LostAndFound;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LNFDAOImp implements DAOInterface {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    @Override
    public List<DAOLNF> getAllUsers() {

        List<DAOLNF> ims = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM LOST_AND_FOUND")) {
            ResultSet rs = stmt.executeQuery();

            
            while (rs.next()) {
                ims.add(new DAOLNF(
                        rs.getString("ItemLost"),
                        rs.getString("NameOfOwner"),
                        rs.getString("ItemDescription"),
                        rs.getString("FloorLevel"),
                        rs.getString("Status"),
                        rs.getString("LastSeen")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ims;
    }
    
}
