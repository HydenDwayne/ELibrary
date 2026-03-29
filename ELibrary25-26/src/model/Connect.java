package model;

import java.sql.*;

public class Connect {

    private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=E_Library_PostBookFixV2;encrypt=true;trustServerCertificate=true";
    private final String USER = "Pia";
    private final String PASSWORD = "passwordPia";

    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public Connection connectToDB() {
        try (Connection con = DriverManager.getConnection(getURL(), getUSER(), getPASSWORD())) {
            System.out.println("✅ Connected successfully to E_Library!");
            return con;
        } catch (Exception e) {
            System.out.println("❌ Connection failed!");
            // e.printStackTrace();
            return null;
        }
    }

    public String[] execStoredProcedure(String procedureName, int iteration) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
            Statement stmt = con.createStatement(); 
            ResultSet rs = stmt.executeQuery(procedureName);) {

                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                String[] rowValue = new String[columnCount];

                
                while (iteration >= 0) {
                    rs.next();
                    iteration--;
                }

                if (!rs.next()) {return null;}

                for (int i = 1; i < columnCount; i++) {
                    rowValue[i] = rs.getString((i));
                }

                // while (rs.next()) {
                    
                // }
            return rowValue;

        } catch (SQLException e) {
            System.out.println("❌ Invalid query!");
            e.printStackTrace();
        }
        return null;
    }

    public int returnRowCount(String procedureName) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); 
            Statement stmt = con.createStatement(); 
            ResultSet rs = stmt.executeQuery(procedureName);) {

            int rowCount =0;
            while(rs.next()) {
                rowCount++;
            }

            return rowCount;

        } catch (SQLException e) {
            System.out.println("❌ Invalid query!");
            // e.printStackTrace();
        }
        return -1;
    }

}
