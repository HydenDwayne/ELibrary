package model.DAOs.IMS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class IMSDAOImp implements DAOInterface {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    @Override
    public List<DAOIMS> getAllUsers() {

        List<DAOIMS> ims = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM showAllIMS")) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ims.add(new DAOIMS(
                        rs.getString("FacilityCode"),
                        rs.getString("SerialNumber"),
                        rs.getString("EquipmentName")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ims;
    }
    
}
