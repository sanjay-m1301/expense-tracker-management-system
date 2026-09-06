package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Transaction;
import util.DBconnection;
public class TransactionDAO {
	 public boolean addTransaction(Transaction transaction) {

	        String sql = "INSERT INTO transactions "
	                   + "(user_id, type, category, amount, description, transaction_date) "
	                   + "VALUES (?, ?, ?, ?, ?, ?)";

	        try (Connection con = DBconnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, transaction.getUserId());
	            ps.setString(2, transaction.getType());
	            ps.setString(3, transaction.getCategory());
	            ps.setDouble(4, transaction.getAmount());
	            ps.setString(5, transaction.getDescription());
	            ps.setDate(6, java.sql.Date.valueOf(transaction.getTransactionDate()));

	            int rows = ps.executeUpdate();

	            return rows > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	// View all transactions
	 public void viewTransactions(int userId) {

	     String sql = "SELECT * FROM transactions WHERE user_id=?";

	     try (Connection con = DBconnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1,userId);

	            ResultSet rs = ps.executeQuery();

	            System.out.println("\n========== TRANSACTIONS ==========");

	            boolean found = false;

	            while (rs.next()) {

	                found = true;

	                System.out.println("ID          : "
	                        + rs.getInt("transaction_id"));

	                System.out.println("Type        : "
	                        + rs.getString("type"));

	                System.out.println("Category    : "
	                        + rs.getString("category"));

	                System.out.println("Amount      : ₹"
	                        + rs.getDouble("amount"));

	                System.out.println("Description : "
	                        + rs.getString("description"));

	                System.out.println("Date        : "
	                        + rs.getDate("transaction_date"));

	                System.out.println("----------------------------------");
	            }

	            if (!found) {
	                System.out.println("No transactions found.");
	            }

	            rs.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
	// Search transactions by category
	 public void searchByCategory(int userId, String category) {

	        String sql = "SELECT * FROM transactions "
	                   + "WHERE user_id = ? AND category = ?";

	        try (Connection con = DBconnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, userId);
	            ps.setString(2, category);

	            ResultSet rs = ps.executeQuery();

	            System.out.println("\n========== SEARCH RESULTS ==========");

	            boolean found = false;

	            while (rs.next()) {

	                found = true;

	                System.out.println("ID          : "
	                        + rs.getInt("transaction_id"));

	                System.out.println("Type        : "
	                        + rs.getString("type"));

	                System.out.println("Category    : "
	                        + rs.getString("category"));

	                System.out.println("Amount      : ₹"
	                        + rs.getDouble("amount"));

	                System.out.println("Description : "
	                        + rs.getString("description"));

	                System.out.println("Date        : "
	                        + rs.getDate("transaction_date"));

	                System.out.println("------------------------------------");
	            }

	            if (!found) {
	                System.out.println(
	                        "No transactions found for: " + category);
	            }

	            rs.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	// Calculate balance
	  public void showBalance(int userId) {

	        String sql = "SELECT "
	                   + "SUM(CASE WHEN type = 'Income' THEN amount ELSE 0 END) "
	                   + "AS total_income, "
	                   + "SUM(CASE WHEN type = 'Expense' THEN amount ELSE 0 END) "
	                   + "AS total_expense "
	                   + "FROM transactions WHERE user_id = ?";

	        try (Connection con = DBconnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, userId);

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {

	                double income = rs.getDouble("total_income");
	                double expense = rs.getDouble("total_expense");

	                double balance = income - expense;

	                System.out.println("\n========== BALANCE ==========");

	                System.out.println(
	                        "Total Income  : ₹" + income);

	                System.out.println(
	                        "Total Expense : ₹" + expense);

	                System.out.println("-----------------------------");

	                System.out.println(
	                        "Current Balance: ₹" + balance);
	            }

	            rs.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	// Delete a transaction
	  public boolean deleteTransaction(
	            int transactionId, int userId) {

	        String sql = "DELETE FROM transactions "
	                   + "WHERE transaction_id = ? AND user_id = ?";

	        try (Connection con = DBconnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, transactionId);
	            ps.setInt(2, userId);

	            int rows = ps.executeUpdate();

	            return rows > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	// Update a transaction
	  public boolean updateTransaction(
	            Transaction transaction, int userId) {

	        String sql = "UPDATE transactions SET "
	                   + "type = ?, "
	                   + "category = ?, "
	                   + "amount = ?, "
	                   + "description = ? "
	                   + "WHERE transaction_id = ? "
	                   + "AND user_id = ?";

	        try (Connection con = DBconnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setString(1, transaction.getType());
	            ps.setString(2, transaction.getCategory());
	            ps.setDouble(3, transaction.getAmount());
	            ps.setString(4, transaction.getDescription());
	            ps.setInt(5, transaction.getTransactionId());
	            ps.setInt(6, userId);

	            int rows = ps.executeUpdate();

	            return rows > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
