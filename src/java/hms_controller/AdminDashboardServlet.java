// DatabaseManager.java
// This class connects to a database using JDBC to fetch data.
// IMPORTANT: Replace placeholder database connection details and SQL queries
// with your actual database configuration (driver, URL, username, password)
// and table/column names.

package hms_controller; // Example package name

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDashboardServlet {

    // --- Database Connection Details ---
    // You MUST replace these with your actual database information.
    // Example for MySQL:
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // For MySQL 8.0+
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";   // Your database password

    // --- Static block to load the JDBC driver ---
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + JDBC_DRIVER);
            e.printStackTrace();
            // Exit or throw a runtime exception if the driver is critical and cannot be loaded
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Establishes a connection to the database.
     * @return A database Connection object.
     * @throws SQLException if a database access error occurs.
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, "root", "");
    }

    /**
     * Retrieves the total count of students from the database.
     * Assumes a 'Student' table with a 'studentID' column.
     * @return The total number of students.
     */
    public static int getTotalStudents() {
        int count = 0;
        // Ensure you have a 'Student' table with 'studentID' or similar
        String sql = "SELECT COUNT(studentID) AS student_count FROM Student";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count = rs.getInt("student_count");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total students: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Retrieves the total count of wardens from the database.
     * Assumes a 'Warden' table with a 'wardenID' column.
     * @return The total number of wardens.
     */
    public static int getTotalWardens() {
        int count = 0;
        // Ensure you have a 'Warden' table with 'wardenID' or similar
        String sql = "SELECT COUNT(wardenID) AS warden_count FROM Warden";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count = rs.getInt("warden_count");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total wardens: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Retrieves the total count of rooms from the database.
     * Assumes a 'Room' table with a 'roomID' column.
     * @return The total number of rooms.
     */
    public static int getTotalRooms() {
        int count = 0;
        // Ensure you have a 'Room' table with 'roomID' or similar
        String sql = "SELECT COUNT(roomID) AS room_count FROM Room";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count = rs.getInt("room_count");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total rooms: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Retrieves the total count of allocated rooms from the database.
     * Assumes an 'Allocation' table. You might need to adjust the WHERE clause
     * based on how 'allocated' status is represented (e.g., status = 'Allocated').
     * @return The total number of allocated rooms.
     */
    public static int getTotalAllocatedRooms() {
        int count = 0;
        // Assumes 'Allocation' table exists and 'status' column indicates allocation
        String sql = "SELECT COUNT(allocationID) AS allocated_count FROM Allocation WHERE status = 'Approved'"; // Adjust 'status' value as per your DB
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count = rs.getInt("allocated_count");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total allocated rooms: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Retrieves the total count of bills from the database.
     * Assumes a 'Bill' table with a 'billID' or similar.
     * @return The total number of bills.
     */
    public static int getTotalBills() {
        int count = 0;
        // Assumes 'Bill' table exists
        String sql = "SELECT COUNT(BID) AS bill_count FROM Bill"; // Using BID as per ERD
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count = rs.getInt("bill_count");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total bills: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Retrieves the total count of maintenance requests from the database.
     * Assumes a 'Maintenance' table with a 'requestID' or similar.
     * @return The total number of maintenance requests.
     */
    public static int getTotalMaintenanceRequests() {
        int count = 0;
        // Assumes 'Maintenance' table exists
        String sql = "SELECT COUNT(requestID) AS maintenance_count FROM Maintenance"; // Using requestID as per ERD
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count = rs.getInt("maintenance_count");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total maintenance requests: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Retrieves an overview of student data for the dashboard.
     * Adjust SQL query and column names based on your 'Student' table structure.
     * @return A list of maps, each map representing a student's overview data.
     */
    public static List<Map<String, String>> getStudentOverviewData() {
        List<Map<String, String>> studentOverviewList = new ArrayList<>();
        // Adjust column names (sID, sName, program, blockName) to match your 'Student' and 'Hostel_Block' tables
        String sql = "SELECT s.studentID, s.sName, s.program, b.blockName " +
                     "FROM Student s JOIN Allocation a ON s.studentID = a.studentID " +
                     "JOIN Room r ON a.roomID = r.roomID " +
                     "JOIN Hostel_Block b ON r.blockID = b.blockID LIMIT 5"; // Limit to a few for overview
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, String> student = new HashMap<>();
                student.put("id", rs.getString("studentID"));
                student.put("name", rs.getString("sName"));
                student.put("program", rs.getString("program"));
                student.put("block", rs.getString("blockName"));
                studentOverviewList.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching student overview data: " + e.getMessage());
            e.printStackTrace();
        }
        return studentOverviewList;
    }

    /**
     * Retrieves a list of pending maintenance requests.
     * Adjust SQL query and column names based on your 'Maintenance' table structure.
     * @return A list of maps, each map representing a pending maintenance request.
     */
    public static List<Map<String, String>> getPendingMaintenanceRequests() {
        List<Map<String, String>> pendingRequestsList = new ArrayList<>();
        // Adjust column names (requestID, ssus_type, status) and 'status' value to match your 'Maintenance' table
        String sql = "SELECT requestID, ssus_type, status FROM Maintenance WHERE status = 'Pending' LIMIT 5"; // Limit to a few for overview
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, String> request = new HashMap<>();
                request.put("reqId", rs.getString("requestID"));
                request.put("type", rs.getString("ssus_type")); // Using ssus_type as per ERD
                request.put("status", rs.getString("status"));
                pendingRequestsList.add(request);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching pending maintenance requests: " + e.getMessage());
            e.printStackTrace();
        }
        return pendingRequestsList;
    }
}
