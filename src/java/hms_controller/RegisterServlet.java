package hms_controller;

import hms_model.Student;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final double MINIMUM_MERIT = 3.0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve form parameters
        String sName = request.getParameter("fullName");
        String studentID = request.getParameter("matricNumber");
        String sEmail = request.getParameter("email");
        String sPass = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String program = request.getParameter("program");
        String semesterStr = request.getParameter("semester");
        String sPho = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String meritStr = request.getParameter("merit");
        String terms = request.getParameter("terms");

        // Validate required fields
        if (sName == null || sName.isEmpty() ||
            studentID == null || studentID.isEmpty() ||
            sEmail == null || sEmail.isEmpty() ||
            sPass == null || sPass.isEmpty() ||
            confirmPassword == null || confirmPassword.isEmpty() ||
            program == null || program.isEmpty() ||
            semesterStr == null || semesterStr.isEmpty() ||
            sPho == null || sPho.isEmpty() ||
            gender == null || gender.isEmpty() ||
            meritStr == null || meritStr.isEmpty() ||
            terms == null) {
            
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Validate password match
        if (!sPass.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Convert semester and merit to numbers
        int semester;
        double merit;
        try {
            semester = Integer.parseInt(semesterStr);
            merit = Double.parseDouble(meritStr);
            
            if (semester < 1 || semester > 8) {
                throw new NumberFormatException("Invalid semester");
            }
            if (merit < MINIMUM_MERIT) {
                request.setAttribute("error", "Minimum merit requirement is " + MINIMUM_MERIT);
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid semester or merit value.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Validate gender
        if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
            request.setAttribute("error", "Invalid gender. Must be M or F.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Create Student object
        Student student = new Student();
        student.setSName(sName);
        student.setStudentID(Integer.parseInt(studentID));
        student.setSEmail(sEmail);
        student.setSPass(sPass);
        student.setProgram(program);
        student.setSemester(semester);
        student.setSPho(sPho);
        student.setAdmin_id(0);
        student.setGender(gender.toUpperCase());
        student.setMerit(merit);

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            conn.setAutoCommit(false); // Start transaction

            // 1. Insert student record
            String studentSql = "INSERT INTO student (sName, studentID, sEmail, sPass, program, semester, sPho, admin_id, gender, merit) " +
                              "VALUES (?, ?, ?, ?, ?, ?, ?, NULL, ?, ?)";
            
            try (PreparedStatement studentStmt = conn.prepareStatement(studentSql, Statement.RETURN_GENERATED_KEYS)) {
                studentStmt.setString(1, student.getSName());
                studentStmt.setInt(2, student.getStudentID());
                studentStmt.setString(3, student.getSEmail());
                studentStmt.setString(4, student.getSPass());
                studentStmt.setString(5, student.getProgram());
                studentStmt.setInt(6, student.getSemester());
                studentStmt.setString(7, student.getSPho());
                studentStmt.setString(8, student.getGender());
                studentStmt.setDouble(9, student.getMerit());

                int rowsAffected = studentStmt.executeUpdate();
                
                if (rowsAffected == 0) {
                    throw new SQLException("Student registration failed.");
                }

                // 2. Automatically allocate room
String roomPrefix = student.getGender().equals("F") ? "F" : "M";
String allocationSql = "INSERT INTO allocation (studentID, roomID, blockID, date_from, status) " +
                     "SELECT ?, r.roomID, r.blockID, CURDATE(), 'Active' FROM room r " +
                     "WHERE r.roomID LIKE ? AND r.curOcc < r.capacity " +
                     "ORDER BY RAND() LIMIT 1";

try (PreparedStatement allocStmt = conn.prepareStatement(allocationSql)) {
    allocStmt.setInt(1, student.getStudentID());
    allocStmt.setString(2, roomPrefix + "%");
    
    int allocRows = allocStmt.executeUpdate();
    
    if (allocRows == 0) {
        throw new SQLException("No available rooms for this gender.");
    }
}

                // 3. Update room occupancy
                String updateRoomSql = "UPDATE room SET curOcc = curOcc + 1 " +
                                      "WHERE roomID = (SELECT roomID FROM allocation WHERE studentID = ?)";
                
                try (PreparedStatement updateStmt = conn.prepareStatement(updateRoomSql)) {
                    updateStmt.setInt(1, student.getStudentID());
                    updateStmt.executeUpdate();
                }

                conn.commit(); // Commit transaction
                request.setAttribute("success", "Registration and room allocation successful!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            rollback(conn);
            request.setAttribute("error", "Database driver error.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } catch (SQLIntegrityConstraintViolationException e) {
            rollback(conn);
            if (e.getMessage().contains("studentID")) {
                request.setAttribute("error", "Matric number already exists.");
            } else if (e.getMessage().contains("sEmail")) {
                request.setAttribute("error", "Email already registered.");
            } else {
                request.setAttribute("error", "Database error: " + e.getMessage());
            }
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } catch (SQLException e) {
            rollback(conn);
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } finally {
            closeConnection(conn);
        }
    }

    private void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.setAutoCommit(true); // Reset auto-commit
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/register.jsp");
    }
}