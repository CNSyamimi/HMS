package hms_controller;

import hms_model.Bill;
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

@WebServlet("/BillManagementServlet")
public class BillManagementServlet extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
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
            
            // Get all bills with student details
            List<BillView> billViews = getAllBillViews();
            
            // Calculate summary statistics
            double totalPaid = calculateTotalPaid(billViews);
            double totalUnpaid = calculateTotalUnpaid(billViews);
            int paidCount = countByStatus(billViews, "Paid");
            int unpaidCount = countByStatus(billViews, "Unpaid");
            
            // Set attributes for JSP
            request.setAttribute("bills", billViews);
            request.setAttribute("totalPaid", totalPaid);
            request.setAttribute("totalUnpaid", totalUnpaid);
            request.setAttribute("paidCount", paidCount);
            request.setAttribute("unpaidCount", unpaidCount);
            
            request.getRequestDispatcher("billManagement.jsp").forward(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving bill data: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private List<BillView> getAllBillViews() throws SQLException {
        List<BillView> billViews = new ArrayList<>();
        
        String sql = "SELECT b.billID, b.billType, b.amount, b.dueDate, b.status, " +
                     "s.studentID, s.sName, s.program, s.semester " +
                     "FROM bill b " +
                     "JOIN student s ON b.studentID = s.studentID " +
                     "ORDER BY b.dueDate DESC";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                BillView view = new BillView();
                
                // Set bill data
                Bill bill = new Bill();
                bill.setBillID(rs.getInt("billID"));
                bill.setBillType(rs.getString("billType"));
                bill.setAmount(rs.getBigDecimal("amount"));
                bill.setDueDate(rs.getDate("dueDate"));
                bill.setStatus(rs.getString("status"));
                view.setBill(bill);
                
                // Set student data
                Student student = new Student();
                student.setStudentID(rs.getInt("studentID"));
                student.setSName(rs.getString("sName"));
                student.setProgram(rs.getString("program"));
                student.setSemester(rs.getInt("semester"));
                view.setStudent(student);
                
                billViews.add(view);
            }
        }
        
        return billViews;
    }

    private double calculateTotalPaid(List<BillView> bills) {
        return bills.stream()
                .filter(b -> "Paid".equals(b.getBill().getStatus()))
                .mapToDouble(b -> b.getBill().getAmount().doubleValue())
                .sum();
    }

    private double calculateTotalUnpaid(List<BillView> bills) {
        return bills.stream()
                .filter(b -> "Unpaid".equals(b.getBill().getStatus()))
                .mapToDouble(b -> b.getBill().getAmount().doubleValue())
                .sum();
    }

    private int countByStatus(List<BillView> bills, String status) {
        return (int) bills.stream()
                .filter(b -> status.equals(b.getBill().getStatus()))
                .count();
    }
}

// Helper class to combine bill and student data
class BillView {
    private Bill bill;
    private Student student;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}