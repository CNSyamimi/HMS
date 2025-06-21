package com.hostel.dao;

import com.hostel.model.Bill;
import com.hostel.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class BillDAO {

    /**
     * Adds a new bill to the database.
     * @param bill The Bill object to add.
     * @return True if the bill was added successfully, false otherwise.
     */
    public boolean addBill(Bill bill) {
        String sql = "INSERT INTO bill (billID, billType, amount, dueDate, status, studentID, allocationID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bill.getBillID());
            ps.setString(2, bill.getBillType());
            ps.setDouble(3, bill.getAmount());
            ps.setDate(4, bill.getDueDate());
            ps.setString(5, bill.getStatus());
            if (bill.getStudentID() != null) {
                ps.setInt(6, bill.getStudentID());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            ps.setInt(7, bill.getAllocationID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding bill: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowAffected;
    }

    /**
     * Retrieves a bill by its ID.
     * @param billID The ID of the bill to retrieve.
     * @return A Bill object if found, null otherwise.
     */
    public Bill getBillById(String billID) {
        String sql = "SELECT * FROM bill WHERE billID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Bill bill = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, billID);
            rs = ps.executeQuery();

            if (rs.next()) {
                bill = new Bill();
                bill.setBillID(rs.getString("billID"));
                bill.setBillType(rs.getString("billType"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setDueDate(rs.getDate("dueDate"));
                bill.setStatus(rs.getString("status"));
                int studentID = rs.getInt("studentID");
                if (rs.wasNull()) {
                    bill.setStudentID(null);
                } else {
                    bill.setStudentID(studentID);
                }
                bill.setAllocationID(rs.getInt("allocationID"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting bill by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bill;
    }

    /**
     * Updates an existing bill's information.
     * @param bill The Bill object with updated information.
     * @return True if the bill was updated successfully, false otherwise.
     */
    public boolean updateBill(Bill bill) {
        String sql = "UPDATE bill SET billType = ?, amount = ?, dueDate = ?, status = ?, studentID = ?, allocationID = ? WHERE billID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bill.getBillType());
            ps.setDouble(2, bill.getAmount());
            ps.setDate(3, bill.getDueDate());
            ps.setString(4, bill.getStatus());
            if (bill.getStudentID() != null) {
                ps.setInt(5, bill.getStudentID());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setInt(6, bill.getAllocationID());
            ps.setString(7, bill.getBillID());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating bill: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowAffected;
    }

    /**
     * Deletes a bill from the database by ID.
     * @param billID The ID of the bill to delete.
     * @return True if the bill was deleted successfully, false otherwise.
     */
    public boolean deleteBill(String billID) {
        String sql = "DELETE FROM bill WHERE billID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean rowAffected = false;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, billID);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rowAffected = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting bill: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowAffected;
    }

    /**
     * Retrieves all bills from the database with pagination.
     * @param page The current page number.
     * @param recordsPerPage Number of records per page.
     * @return A list of Bill objects for the specified page.
     */
    public List<Bill> getAllBills(int page, int recordsPerPage) {
        List<Bill> billList = new ArrayList<>();
        String sql = "SELECT * FROM bill ORDER BY billID LIMIT ?, ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (page - 1) * recordsPerPage);
            ps.setInt(2, recordsPerPage);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillID(rs.getString("billID"));
                bill.setBillType(rs.getString("billType"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setDueDate(rs.getDate("dueDate"));
                bill.setStatus(rs.getString("status"));
                int studentID = rs.getInt("studentID");
                if (rs.wasNull()) {
                    bill.setStudentID(null);
                } else {
                    bill.setStudentID(studentID);
                }
                bill.setAllocationID(rs.getInt("allocationID"));
                billList.add(bill);
            }
        } catch (SQLException e) {
            System.err.println("Error getting paginated bills: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return billList;
    }

    /**
     * Retrieves all bills from the database (without pagination).
     * @return A list of all Bill objects.
     */
    public List<Bill> getAllBills() {
        return getAllBills(1, Integer.MAX_VALUE); // Fallback to get all records
    }

    /**
     * Gets the total count of bills in the database.
     * @return Total number of bills.
     */
    public int getBillCount() {
        String sql = "SELECT COUNT(*) FROM bill";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error getting bill count: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * Searches bills with pagination.
     * @param search The search term.
     * @param page The current page number.
     * @param recordsPerPage Number of records per page.
     * @return A list of matching Bill objects.
     */
    public List<Bill> searchBills(String search, int page, int recordsPerPage) {
        List<Bill> billList = new ArrayList<>();
        String sql = "SELECT * FROM bill WHERE billID LIKE ? OR billType LIKE ? OR status LIKE ? OR " +
                     "studentID LIKE ? OR allocationID LIKE ? ORDER BY billID LIMIT ?, ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            String searchParam = "%" + search + "%";
            ps.setString(1, searchParam);
            ps.setString(2, searchParam);
            ps.setString(3, searchParam);
            ps.setString(4, searchParam);
            ps.setString(5, searchParam);
            ps.setInt(6, (page - 1) * recordsPerPage);
            ps.setInt(7, recordsPerPage);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillID(rs.getString("billID"));
                bill.setBillType(rs.getString("billType"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setDueDate(rs.getDate("dueDate"));
                bill.setStatus(rs.getString("status"));
                int studentID = rs.getInt("studentID");
                if (rs.wasNull()) {
                    bill.setStudentID(null);
                } else {
                    bill.setStudentID(studentID);
                }
                bill.setAllocationID(rs.getInt("allocationID"));
                billList.add(bill);
            }
        } catch (SQLException e) {
            System.err.println("Error searching bills: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return billList;
    }

    /**
     * Gets the count of bills matching a search term.
     * @param search The search term.
     * @return Number of matching bills.
     */
    public int getSearchBillCount(String search) {
        String sql = "SELECT COUNT(*) FROM bill WHERE billID LIKE ? OR billType LIKE ? OR status LIKE ? OR " +
                     "studentID LIKE ? OR allocationID LIKE ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            String searchParam = "%" + search + "%";
            ps.setString(1, searchParam);
            ps.setString(2, searchParam);
            ps.setString(3, searchParam);
            ps.setString(4, searchParam);
            ps.setString(5, searchParam);
            rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error getting search bill count: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * Retrieves bills for a specific student.
     * @param studentID The ID of the student.
     * @return A list of Bill objects for the student.
     */
    public List<Bill> getBillsByStudentID(int studentID) {
        List<Bill> billList = new ArrayList<>();
        String sql = "SELECT * FROM bill WHERE studentID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillID(rs.getString("billID"));
                bill.setBillType(rs.getString("billType"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setDueDate(rs.getDate("dueDate"));
                bill.setStatus(rs.getString("status"));
                bill.setStudentID(rs.getInt("studentID"));
                bill.setAllocationID(rs.getInt("allocationID"));
                billList.add(bill);
            }
        } catch (SQLException e) {
            System.err.println("Error getting bills by student ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return billList;
    }
}