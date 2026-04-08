package model.DAOs.Patron;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Connect;

public class PatronDAOImp {

	Connect connect = new Connect();
    private final String URL = connect.getURL();
    private final String USER = connect.getUSER();
    private final String PASSWORD = connect.getPASSWORD();

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
		
		return null;
	}

	public String[] getCollegesPerCampus(String campusCode) {
		List<String> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("SELECT CollegeCode FROM COLLEGE WHERE CampCode = ?")) {

			stmt.setString(1, campusCode); 

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
			stmt.setString(2, campusCode); 

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

			
			cs.setDate(9, yearEnrolled);
			cs.setString(10, studentType);

			
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

			
			if (gradeLevel != null)
				cs.setInt(18, gradeLevel);
			else
				cs.setNull(18, Types.INTEGER);

			
			cs.execute();

			while (cs.getMoreResults() || cs.getUpdateCount() != -1) {
				int count = cs.getUpdateCount();
				if (count > 0)
					return true;
			}

			return false;

		} catch (SQLException e) {

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

			
			cs.setString(1, patronID);
			cs.setString(2, firstName);
			cs.setString(3, middleInitial);
			cs.setString(4, lastName);
			cs.setString(5, emailAddress);
			cs.setString(6, contactNumber);
			cs.setString(7, homeAddress);
			cs.setString(8, campCode); 

			
			cs.setBoolean(9, administrator);
			cs.setBoolean(10, libraryStaff);
			cs.setBoolean(11, faculty);
			cs.setDate(12, dateHired);

			
			if (adminPosition != null)
				cs.setString(13, adminPosition);
			else
				cs.setNull(13, Types.VARCHAR);

			
			if (assignmentCode != null)
				cs.setString(14, assignmentCode);
			else
				cs.setNull(14, Types.VARCHAR);

			if (staffPosition != null)
				cs.setString(15, staffPosition);
			else
				cs.setNull(15, Types.VARCHAR);

			
			if (facultyRank != null)
				cs.setString(16, facultyRank);
			else
				cs.setNull(16, Types.VARCHAR);

			if (colCode != null)
				cs.setString(17, colCode);
			else
				cs.setNull(17, Types.VARCHAR);

			
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
					return rs.getInt(1) > 0; 
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean checkLibrarianExists(String patronID) {
		String sql = "SELECT COUNT(*) FROM LIBRARY_STAFF WHERE EmployeeID = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, patronID);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0; 
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
					return rs.getInt(1) > 0; 
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	
	public String[] getStudentDetails(String patronID) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement stmt = conn.prepareCall("{CALL GetPatronWithType_IF(?)}")) {

			stmt.setString(1, patronID);

			ResultSet rs = stmt.executeQuery();
			rs.next();
			String[] studentDetails = { checkIfColNull(rs, "PatronID"), checkIfColNull(rs, "FirstName"),
					checkIfColNull(rs, "MiddleInitial"), checkIfColNull(rs, "LastName"),
					checkIfColNull(rs, "EmailAddress"), checkIfColNull(rs, "ContactNumber"),
					checkIfColNull(rs, "HomeAddress"), checkIfColNull(rs, "StudentType"),
					checkIfColNull(rs, "CampusName"),

					checkIfColNull(rs, "YearLevel"), checkIfColNull(rs, "ColCode"), checkIfColNull(rs, "ProgramCode"),

					checkIfColNull(rs, "ThesisTitle"), checkIfColNull(rs, "Degree"),

					checkIfColNull(rs, "GradeLevel"),

					checkIfColNull(rs, "YearGraduated"), };

			return studentDetails;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String checkIfColNull(ResultSet rs, String column) {
		try {
			rs.findColumn(column); 
			String val = rs.getString(column);
			if (val == null) {
				val = "";
			}
			return val;
		} catch (SQLException e) {
			return ""; 
		}
	}

	public String[] getColleges(String campusCode) {

		List<String> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("select * from COLLEGE where CampCode = ?")) {

			stmt.setString(1, campusCode);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("CollegeCode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list.toArray(new String[0]);
	}

	public String[] getProgramsPerCollege(String collegeCode, String campusCode) {

		List<String> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn
						.prepareStatement("select * from PROGRAM where CollCode = ? and CampusCode = ?")) {

			stmt.setString(1, collegeCode);
			stmt.setString(2, campusCode);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("ProgramCode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list.toArray(new String[0]);
	}

	public String[] getProgramsPerCampus(String campusCode) {

		List<String> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("select * from PROGRAM where CampusCode = ?")) {

			stmt.setString(1, campusCode);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("ProgramCode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list.toArray(new String[0]);
	}

	
	public String[] getEmployeeDetails(String patronID) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				CallableStatement stmt = conn.prepareCall("{CALL GetPatronEmployeeDetails(?)}")) {

			stmt.setString(1, patronID);

			ResultSet rs = stmt.executeQuery();
			rs.next();
			String[] studentDetails = { checkIfColNull(rs, "PatronID"), checkIfColNull(rs, "FirstName"),
					checkIfColNull(rs, "MiddleInitial"), checkIfColNull(rs, "LastName"),
					checkIfColNull(rs, "EmailAddress"), checkIfColNull(rs, "ContactNumber"),
					checkIfColNull(rs, "HomeAddress"),

					checkIfColNull(rs, "AdminPosition"),

					checkIfColNull(rs, "AssignmentCode"), checkIfColNull(rs, "LibraryPosition"),

					checkIfColNull(rs, "FacultyRank"), checkIfColNull(rs, "ColCode"), checkIfColNull(rs, "CampusName"),

					checkIfColNull(rs, "ADMINISTRATOR"), checkIfColNull(rs, "LIBRARY_STAFF"),
					checkIfColNull(rs, "FACULTY") };

			return studentDetails;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean updatePatronStudent(String[] values) {

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         CallableStatement stmt =
	             conn.prepareCall(
	                 "{call updateRecord_Patron_Student(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"
	             )) {

	        
	        stmt.setString(1, values[0]);

	        
	        stmt.setString(2, values[1]);

	        
	        if (isBlank(values[2]))
	            stmt.setNull(3, Types.VARCHAR);
	        else
	            stmt.setString(3, values[2]);

	        
	        stmt.setString(4, values[3]);

	        
	        if (isBlank(values[4]))
	            stmt.setNull(5, Types.VARCHAR);
	        else
	            stmt.setString(5, values[4]);

	        
	        if (isBlank(values[5]))
	            stmt.setNull(6, Types.VARCHAR);
	        else
	            stmt.setString(6, values[5]);

	        
	        if (isBlank(values[6]))
	            stmt.setNull(7, Types.VARCHAR);
	        else
	            stmt.setString(7, values[6]);

	        
	        stmt.setString(8, normalizeCampus(values[7]));

	        stmt.setNull(9, Types.DATE); 

	        
	        stmt.setString(10, values[9]);

	        
	        if (isBlank(values[10]))
	            stmt.setNull(11, Types.VARCHAR);
	        else
	            stmt.setString(11, values[10]);

	        
	        if (isBlank(values[11]))
	            stmt.setNull(12, Types.VARCHAR);
	        else
	            stmt.setString(12, values[11]);

	        
	        if (isBlank(values[12]))
	            stmt.setNull(13, Types.VARCHAR);
	        else
	            stmt.setString(13, values[12]);

	        
	        if (isBlank(values[13]))
	            stmt.setNull(14, Types.VARCHAR);
	        else
	            stmt.setString(14, normalizeCampus(values[13]));

	        
	        if (isBlank(values[14]))
	            stmt.setNull(15, Types.VARCHAR);
	        else
	            stmt.setString(15, values[14]);

	     
	        java.sql.Date yearGraduated = parseYearOrNull(values[15]);

	        if (yearGraduated != null)
	            stmt.setDate(16, yearGraduated);
	        else
	            stmt.setNull(16, Types.DATE);
	        

	        
	        if (isBlank(values[16]))
	            stmt.setNull(17, Types.VARCHAR);
	        else
	            stmt.setString(17, values[16]);

	        
	        if (isBlank(values[17]))
	            stmt.setNull(18, Types.VARCHAR);
	        else
	            stmt.setString(18, values[17]);

	        stmt.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	/** Treat empty / whitespace as NULL */
	private boolean isBlank(String s) {
	    return s == null || s.trim().isEmpty();
	}
	
	private java.sql.Date parseYearOrNull(String year) {
	    if (year == null) return null;

	    year = year.trim();

	    
	    if (!year.matches("\\d{4}")) return null;

	    return java.sql.Date.valueOf(year + "-01-01");
	}
	
	private String normalizeCampus(String campus) {
	    if (campus == null) return null;
	    return switch (campus) {
	        case "Main" -> "M";
	        case "Bustos" -> "BC";
	        case "Hagonoy" -> "HC";
	        case "Meneses" -> "MC";
	        case "Sarmiento" -> "SC";
	        case "San Rafael" -> "SRC";
	        default -> campus;
	    };
	}
	
	public boolean updatePatronEmployee(String[] patronDetails) {
        /*
        0  = PatronID
        1  = FirstName
        2  = MiddleInitial
        3  = LastName
        4  = EmailAddress
        5  = ContactNumber
        6  = HomeAddress
        7  = CampCode

        8  = IsAdmin
        9  = IsLibraryStaff
        10 = IsFaculty

        11 = AdminPosition
        12 = AssignmentCode
        13 = StaffPosition

        14 = FacultyRank
        15 = ColCode
        */
		
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
   	         CallableStatement stmt = conn.prepareCall("{CALL updateRecord_Patron_Employee(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {


        	
        	stmt.setString(1, patronDetails[0]); 
        	stmt.setString(2, patronDetails[1]); 

        	if (isBlank(patronDetails[2]))
        	    stmt.setNull(3, Types.VARCHAR);
        	else
        	    stmt.setString(3, patronDetails[2]);

        	stmt.setString(4, patronDetails[3]); 

        	if (isBlank(patronDetails[4]))
        	    stmt.setNull(5, Types.VARCHAR);
        	else
        	    stmt.setString(5, patronDetails[4]);

        	if (isBlank(patronDetails[5]))
        	    stmt.setNull(6, Types.VARCHAR);
        	else
        	    stmt.setString(6, patronDetails[5]);

        	if (isBlank(patronDetails[6]))
        	    stmt.setNull(7, Types.VARCHAR);
        	else
        	    stmt.setString(7, patronDetails[6]);

        	stmt.setString(8, normalizeCampus(patronDetails[7]));

        	stmt.setBoolean(9, "1".equals(patronDetails[8]) || "true".equalsIgnoreCase(patronDetails[8]));
        	stmt.setBoolean(10, "1".equals(patronDetails[9]) || "true".equalsIgnoreCase(patronDetails[9]));
        	stmt.setBoolean(11, "1".equals(patronDetails[10]) || "true".equalsIgnoreCase(patronDetails[10]));

            
        	if (isBlank(patronDetails[11])) {
        	    stmt.setNull(12, Types.VARCHAR);
        	} else {
        	    stmt.setString(12, patronDetails[11]);
        	}

        	if (isBlank(patronDetails[12])) {
        	    stmt.setNull(13, Types.VARCHAR);
        	} else {
        	    stmt.setString(13, patronDetails[12]);
        	}

        	if (isBlank(patronDetails[13])) {
        	    stmt.setNull(14, Types.VARCHAR);
        	} else {
        	    stmt.setString(14, patronDetails[13]);
        	}

        	if (isBlank(patronDetails[14])) {
        	    stmt.setNull(15, Types.VARCHAR);
        	} else {
        	    stmt.setString(15, patronDetails[14]);
        	}

        	if (isBlank(patronDetails[15])) {
        	    stmt.setNull(16, Types.VARCHAR);
        	} else {
        	    stmt.setString(16, patronDetails[15]);
        	}
            

        

            stmt.execute();
            return true;
        } catch (SQLException e) {
        	e.printStackTrace();
        	return false;
        }
        
	}
}
