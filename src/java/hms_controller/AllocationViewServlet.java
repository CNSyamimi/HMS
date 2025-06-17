package hms_controller;

import hms_model.Allocation;
import hms_model.Room;
import hms_model.Student;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AllocationViewServlet")
public class AllocationViewServlet extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Add your database password here

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer adminId = (Integer) session.getAttribute("admin_id");
        
        // Check if admin is logged in
        if (adminId == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);
            
            // Get all allocations with student and room details
            List<AllocationView> allocationViews = getAllAllocationViews();
            
            // Set attributes for JSP
            request.setAttribute("allocations", allocationViews);
            request.getRequestDispatcher("allocationView.jsp").forward(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving allocation data: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private List<AllocationView> getAllAllocationViews() throws SQLException {
        List<AllocationView> allocationViews = new ArrayList<>();
        
        String sql = "SELECT a.allocationID, a.date_from, a.date_to, a.status, " +
                     "s.studentID, s.sName, s.program, s.semester, " +
                     "r.roomID, r.blockID, r.capacity, r.curOcc " +
                     "FROM allocation a " +
                     "JOIN student s ON a.studentID = s.studentID " +
                     "JOIN room r ON a.roomID = r.roomID AND a.blockID = r.blockID " +
                     "ORDER BY a.date_from DESC";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                AllocationView view = new AllocationView();
                
                // Set allocation data
                Allocation allocation = new Allocation();
                allocation.setAllocationID(rs.getInt("allocationID"));
                allocation.setDate_from(rs.getDate("date_from"));
                allocation.setDate_to(rs.getDate("date_to"));
                allocation.setStatus(rs.getString("status"));
                view.setAllocation(allocation);
                
                // Set student data
                Student student = new Student();
                student.setStudentID(rs.getInt("studentID"));
                student.setSName(rs.getString("sName"));
                student.setProgram(rs.getString("program"));
                student.setSemester(rs.getInt("semester"));
                view.setStudent(student);
                
                // Set room data
                Room room = new Room();
                room.setRoomID(rs.getString("roomID"));
                room.setBlockID(rs.getString("blockID"));
                room.setCapacity(rs.getInt("capacity"));
                room.setCurOcc(rs.getInt("curOcc"));
                view.setRoom(room);
                
                allocationViews.add(view);
            }
        }
        
        return allocationViews;
    }
}

// Helper class to combine allocation, student and room data
class AllocationView {
    private Allocation allocation;
    private Student student;
    private Room room;

    public Allocation getAllocation() {
        return allocation;
    }

    public void setAllocation(Allocation allocation) {
        this.allocation = allocation;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}