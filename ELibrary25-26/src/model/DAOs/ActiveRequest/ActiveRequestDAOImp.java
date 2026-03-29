package model.DAOs.ActiveRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiveRequestDAOImp implements DAOInterfaceActiveRequest {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    @Override
    public List<DAOActiveRequest> getAllRequests() {

        List<DAOActiveRequest> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM getActiveRequest")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                books.add(new DAOActiveRequest(
                        rs.getString("loanID"),
                        rs.getString("SerialNumber"),
                        rs.getString("PatronName"),
                        rs.getString("Venue"),
                        rs.getString("BorrowDate"),
                        rs.getString("EquipmentName")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}
