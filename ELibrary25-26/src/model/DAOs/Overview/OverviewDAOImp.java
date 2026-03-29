package model.DAOs.Overview;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OverviewDAOImp implements DAOInterface {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    @Override
    public List<DAOOverview> getAllUsers() {

        List<DAOOverview> ims = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM getOverviewData")) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ims.add(new DAOOverview(
                        rs.getInt("NoOfBooks"),
                        rs.getInt("NoOfBorrowedBooks"),
                        rs.getInt("NoOfOverdueBooks"),
                        rs.getInt("NoOfPatron")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ims;
    }
    
}
