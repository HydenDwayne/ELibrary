package model.DAOs.Patron;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatronDAOImp {

	private final String URL = "jdbc:sqlserver://26.91.144.197:1433;databaseName=bsu_elibrary;encrypt=true;trustServerCertificate=true";
	private final String USER = "Pia";
	private final String PASSWORD = "passwordPia";

	public List<DAOPatron> getAllUsers(String searchQuery, String campuses, String types) {

		List<DAOPatron> patrons = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement stmt = conn.prepareCall("{CALL searchPatron(?,?,?)}")) {

			if (searchQuery == null) {
				stmt.setString(1, "");
			} else {
				stmt.setString(1, searchQuery);
			}

			if (campuses == null) {
				stmt.setString(2, "");
			} else {
				stmt.setString(2, campuses);
			}

			if (types == null) {
				stmt.setString(3, "");
			} else {
				stmt.setString(3, types);
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				patrons.add(new DAOPatron(rs.getString("Patron ID"), rs.getString("Full Name"),
						rs.getString("Email Address"), rs.getString("Contact Number"), rs.getString("Home Address"),
						rs.getString("Campus"), rs.getString("Patron Type")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patrons;
	}

	public List<DAOPatron> getUserDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getCollegesPerCampus(String campusCode) {
		List<String> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("SELECT CollegeCode FROM COLLEGE WHERE CampCode = ?")) {

			stmt.setString(1, campusCode); // DB code

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("CollegeCode"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list.toArray(new String[0]);
	}

	public String[] getPrograms(String collegeCode, String campusCode) {
		List<String> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT ProgramCode FROM PROGRAM WHERE CollCode = ? AND CampusCode = ?")) {

			stmt.setString(1, collegeCode);
			stmt.setString(2, campusCode); // ← already DB code

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("ProgramCode"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list.toArray(new String[0]);
	}

	public String[] getProgramsByCampus(String campusCode) {
		List<String> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT ProgramCode FROM PROGRAM WHERE CampusCode = ?")) {

			// Convert user campus to DB code
			switch (campusCode.toLowerCase()) {
			case "main":
				stmt.setString(1, "M");
				break;
			case "bustos":
				stmt.setString(1, "BC");
				break;
			case "hagonoy":
				stmt.setString(1, "H");
				break;
			case "meneses":
				stmt.setString(1, "MC");
				break;
			case "san rafael":
				stmt.setString(1, "SR");
				break;
			case "sarmiento":
				stmt.setString(1, "SC");
				break;
			default:
				stmt.setString(1, campusCode);
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
				PreparedStatement stmt = conn.prepareStatement("SELECT ProgramCode FROM PROGRAM WHERE CollCode = ?")) {

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

	public boolean insertStudentRecord(String patronID, String firstName, String middleInitial, String lastName,
			String emailAddress, String contactNumber, String homeAddress, String campCode,

			java.sql.Date yearEnrolled, String studentType,

			String yearLevel, String colCode, String programCode, String campusCode,

			String thesisTitle, java.sql.Date yearGraduated, String degree,

			Integer gradeLevel) {

		String sql = "{CALL addNewRecord_Patron_Student(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement cs = conn.prepareCall(sql)) {

			// ── Patron
			cs.setString(1, patronID);
			cs.setString(2, firstName);
			cs.setString(3, middleInitial);
			cs.setString(4, lastName);
			cs.setString(5, emailAddress);
			cs.setString(6, contactNumber);
			cs.setString(7, homeAddress);

			switch (campCode.toLowerCase()) {
			case "main":
				cs.setString(8, "M");
				break;
			case "bustos":
				cs.setString(8, "BC");
				break;
			case "hagonoy":
				cs.setString(8, "H");
				break;
			case "meneses":
				cs.setString(8, "MC");
				break;
			case "san rafael":
				cs.setString(8, "SR");
				break;
			case "sarmiento":
				cs.setString(8, "SC");
				break;
			default:
				cs.setString(8, campCode);
			}

			// ── Student
			cs.setDate(9, yearEnrolled);
			cs.setString(10, studentType);

			// ── Undergraduate
			if (yearLevel != null)
				cs.setString(11, yearLevel);
			else
				cs.setNull(11, Types.VARCHAR);

			if (colCode != null)
				cs.setString(12, colCode);
			else
				cs.setNull(12, Types.VARCHAR);

			if (programCode != null)
				cs.setString(13, programCode);
			else
				cs.setNull(13, Types.VARCHAR);

			if (campusCode != null)
				cs.setString(14, campusCode);
			else
				cs.setNull(14, Types.VARCHAR);

			// ── Graduate / Alumni
			if (thesisTitle != null)
				cs.setString(15, thesisTitle);
			else
				cs.setNull(15, Types.VARCHAR);

			if (yearGraduated != null)
				cs.setDate(16, yearGraduated);
			else
				cs.setNull(16, Types.DATE);

			if (degree != null)
				cs.setString(17, degree);
			else
				cs.setNull(17, Types.VARCHAR);

			// ── LabHigh
			if (gradeLevel != null)
				cs.setInt(18, gradeLevel);
			else
				cs.setNull(18, Types.INTEGER);

			// SQL Server may return update count AFTER execute
			cs.execute();

			while (cs.getMoreResults() || cs.getUpdateCount() != -1) {
				int count = cs.getUpdateCount();
				if (count > 0)
					return true;
			}

			return false;

		} catch (SQLException e) {
//			e.printStackTrace();
			return false;
		}
	}

	public boolean insertEmployeeRecord(String patronID, String firstName, String middleInitial, String lastName,
			String emailAddress, String contactNumber, String homeAddress, String campCode,

			boolean administrator, boolean libraryStaff, boolean faculty, java.sql.Date dateHired,

			String adminPosition, String assignmentCode, String staffPosition,

			String facultyRank, String colCode) {

		String sql = "{CALL addNewRecord_Patron_Employee(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement cs = conn.prepareCall(sql)) {

			// ── Patron
			cs.setString(1, patronID);
			cs.setString(2, firstName);
			cs.setString(3, middleInitial);
			cs.setString(4, lastName);
			cs.setString(5, emailAddress);
			cs.setString(6, contactNumber);
			cs.setString(7, homeAddress);
			cs.setString(8, campCode); // ✅ already normalized

			// ── Employee flags
			cs.setBoolean(9, administrator);
			cs.setBoolean(10, libraryStaff);
			cs.setBoolean(11, faculty);
			cs.setDate(12, dateHired);

			// ── Administrator
			if (adminPosition != null)
				cs.setString(13, adminPosition);
			else
				cs.setNull(13, Types.VARCHAR);

			// ── Library Staff
			if (assignmentCode != null)
				cs.setString(14, assignmentCode);
			else
				cs.setNull(14, Types.VARCHAR);

			if (staffPosition != null)
				cs.setString(15, staffPosition);
			else
				cs.setNull(15, Types.VARCHAR);

			// ── Faculty
			if (facultyRank != null)
				cs.setString(16, facultyRank);
			else
				cs.setNull(16, Types.VARCHAR);

			if (colCode != null)
				cs.setString(17, colCode);
			else
				cs.setNull(17, Types.VARCHAR);

			// ✅ EXECUTE ONCE
			cs.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkPatronExists(String patronID) {
	    String sql = "SELECT COUNT(*) FROM PATRON WHERE PatronID = ?";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, patronID);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;  // If count > 0, record exists
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public boolean checkPatronBorrowLimit(String patronID) {

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         CallableStatement stmt = conn.prepareCall("{CALL checkPatronBorrowLimit(?)}")) {

	    	stmt.setString(1, patronID);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;  // If count > 0, record exists
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
}
