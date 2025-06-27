package com.hexaware.insurancemanagementsystem.dao;

import com.hexaware.insurancemanagementsystem.entity.Policy;
import com.hexaware.insurancemanagementsystem.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyDaoImpl implements IPolicyDao {

    
	@Override
	public boolean createPolicy(Policy policy) {
	    String query = "INSERT INTO Policy (policyId, policyName, policyType, coverageAmount, premium) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement pstmt = null;
	    Connection conn = null;

	    try {
	        conn = DBConnUtil.getConnection();
	        if (conn == null) {
	            System.err.println("Connection is null. Check your DBConnUtil or db.properties.");
	            return false;
	        }

	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, policy.getPolicyId());
	        pstmt.setString(2, policy.getPolicyName());
	        pstmt.setString(3, policy.getPolicyType());
	        pstmt.setDouble(4, policy.getCoverageAmount());
	        pstmt.setDouble(5, policy.getPremium());

	        return pstmt.executeUpdate() > 0;

	    } catch (SQLException e) {
	        System.err.println("SQL Error inserting policy: " + e.getMessage());
	        return false;
	    } finally {
	        DBConnUtil.closePreparedStatement(pstmt);
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            System.err.println("Error closing connection: " + e.getMessage());
	        }
	    }
	}


   

    // -------------------- SELECT BY ID --------------------
	@Override
	public Policy getPolicy(int policyId) {
	    String query = "SELECT * FROM Policy WHERE policyId = ?";
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Connection conn = null;

	    try {
	        conn = DBConnUtil.getConnection();
	        if (conn == null) {
	            System.err.println("Database connection failed.");
	            return null;
	        }

	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, policyId);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return new Policy(
	                rs.getInt("policyId"),
	                rs.getString("policyName"),
	                rs.getString("policyType"),
	                rs.getDouble("coverageAmount"),
	                rs.getDouble("premium")
	            );
	        }
	    } catch (SQLException e) {
	        System.err.println("SQL Error in getPolicy: " + e.getMessage());
	        return null;
	    } finally {
	        DBConnUtil.closeResultSet(rs);
	        DBConnUtil.closePreparedStatement(pstmt);
	        try {
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            System.err.println("Error closing connection: " + e.getMessage());
	        }
	    }
	    return null;
	}


    // -------------------- SELECT ALL --------------------
    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        String query = "SELECT * FROM Policy";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBConnUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Policy policy = new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getString("policyType"),
                    rs.getDouble("coverageAmount"),
                    rs.getDouble("premium")
                );
                policies.add(policy);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in getAllPolicies: " + e.getMessage());
            return policies;
        } finally {
            DBConnUtil.closeResultSet(rs);
            DBConnUtil.closePreparedStatement(pstmt);
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return policies;
    }


    // -------------------- UPDATE --------------------
    @Override
    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE Policy SET policyName = ?, policyType = ?, coverageAmount = ?, premium = ? WHERE policyId = ?";
        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = DBConnUtil.getConnection();
            if (conn == null) {
                System.err.println("Connection failed.");
                return false;
            }

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, policy.getPolicyName());
            pstmt.setString(2, policy.getPolicyType());
            pstmt.setDouble(3, policy.getCoverageAmount());
            pstmt.setDouble(4, policy.getPremium());
            pstmt.setInt(5, policy.getPolicyId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error in updatePolicy: " + e.getMessage());
            return false;
        } finally {
            DBConnUtil.closePreparedStatement(pstmt);
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }


    // -------------------- DELETE --------------------
    @Override
    public boolean deletePolicy(int policyId) {
        String query = "DELETE FROM Policy WHERE policyId = ?";
        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = DBConnUtil.getConnection();
            if (conn == null) {
                System.err.println("Connection is null, failed to delete.");
                return false;
            }

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, policyId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error in deletePolicy: " + e.getMessage());
            return false;
        } finally {
            DBConnUtil.closePreparedStatement(pstmt);
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

}
