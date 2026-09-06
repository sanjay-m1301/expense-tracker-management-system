package main;
import java.time.LocalDate;
import java.util.Scanner;

import dao.TransactionDAO;
import model.Transaction;
import dao.userDAO;
import model.user;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);
		 
	        userDAO userDAO = new userDAO();
	        TransactionDAO transactionDAO = new TransactionDAO();

	        while (true) {

	            System.out.println("\n================================");
	            System.out.println("       EXPENSE TRACKER");
	            System.out.println("================================");
	            System.out.println("1. Register");
	            System.out.println("2. Login");
	            System.out.println("3. Exit");
	            System.out.print("Enter your choice: ");

	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {

	                case 1:

	                    System.out.println("\n========== REGISTER ==========");

	                    System.out.print("Enter name: ");
	                    String name = scanner.nextLine();

	                    System.out.print("Enter email: ");
	                    String email = scanner.nextLine();

	                    System.out.print("Enter password: ");
	                    String password = scanner.nextLine();

	                    user newUser = new user(name, email, password);

	                    boolean registered = userDAO.registerUser(newUser);

	                    if (registered) {
	                        System.out.println("Registration successful!");
	                    } else {
	                        System.out.println("Registration failed.");
	                    }

	                    break;

	                case 2:

	                    System.out.println("\n============ LOGIN ============");

	                    System.out.print("Enter email: ");
	                    String loginEmail = scanner.nextLine();

	                    System.out.print("Enter password: ");
	                    String loginPassword = scanner.nextLine();

	                    user loggedInUser =
	                            userDAO.loginUser(loginEmail, loginPassword);

	                    if (loggedInUser != null) {

	                        System.out.println(
	                                "\nWelcome, " + loggedInUser.getName() + "!");

	                        showTransactionMenu(
	                                scanner,
	                                transactionDAO,
	                                loggedInUser.getUserId()
	                        );

	                    } else {

	                        System.out.println(
	                                "Invalid email or password.");
	                    }

	                    break;

	                case 3:

	                    System.out.println(
	                            "Thank you for using Expense Tracker!");

	                    scanner.close();
	                    return;

	                default:

	                    System.out.println(
	                            "Invalid choice. Please try again.");
	            }
	        }
	}
	public static void showTransactionMenu(
            Scanner scanner,
            TransactionDAO transactionDAO,
            int userId) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("          MAIN MENU");
            System.out.println("================================");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. Search by Category");
            System.out.println("4. View Balance");
            System.out.println("5. Delete Transaction");
            System.out.println("6. Update Transaction");
            System.out.println("7. Logout");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    String type;

                    while (true) {

                        System.out.print(
                                "Enter type (Income/Expense): ");

                        type = scanner.nextLine();

                        if (type.equalsIgnoreCase("Income")
                                || type.equalsIgnoreCase("Expense")) {

                            break;
                        }

                        System.out.println(
                                "Invalid type! Please enter Income or Expense.");
                    }

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    double amount;

                    while (true) {

                        System.out.print("Enter amount: ");

                        amount = scanner.nextDouble();
                        scanner.nextLine();

                        if (amount > 0) {
                            break;
                        }

                        System.out.println(
                                "Amount must be greater than 0.");
                    }

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    Transaction transaction =
                            new Transaction(
                                    userId,
                                    type,
                                    category,
                                    amount,
                                    description,
                                    LocalDate.now()
                            );

                    boolean success =
                            transactionDAO.addTransaction(transaction);

                    if (success) {

                        System.out.println(
                                "Transaction added successfully!");

                    } else {

                        System.out.println(
                                "Failed to add transaction.");
                    }

                    break;


                case 2:

                    transactionDAO.viewTransactions(userId);

                    break;


                case 3:

                    System.out.print(
                            "Enter category to search: ");

                    String searchCategory =
                            scanner.nextLine();

                    transactionDAO.searchByCategory(
                            userId,
                            searchCategory);

                    break;


                case 4:

                    transactionDAO.showBalance(userId);

                    break;


                case 5:

                    System.out.print(
                            "Enter transaction ID to delete: ");

                    int transactionId =
                            scanner.nextInt();

                    scanner.nextLine();

                    boolean deleted =
                            transactionDAO.deleteTransaction(
                                    transactionId,
                                    userId);

                    if (deleted) {

                        System.out.println(
                                "Transaction deleted successfully!");

                    } else {

                        System.out.println(
                                "Transaction not found.");
                    }

                    break;


                case 6:

                    System.out.print(
                            "Enter transaction ID to update: ");

                    int updateId =
                            scanner.nextInt();

                    scanner.nextLine();

                    System.out.print(
                            "Enter new type (Income/Expense): ");

                    String updateType =
                            scanner.nextLine();

                    System.out.print(
                            "Enter new category: ");

                    String updateCategory =
                            scanner.nextLine();

                    System.out.print(
                            "Enter new amount: ");

                    double updateAmount =
                            scanner.nextDouble();

                    scanner.nextLine();

                    System.out.print(
                            "Enter new description: ");

                    String updateDescription =
                            scanner.nextLine();

                    Transaction updatedTransaction =
                            new Transaction(
                                    userId,
                                    updateType,
                                    updateCategory,
                                    updateAmount,
                                    updateDescription,
                                    LocalDate.now()
                            );

                    updatedTransaction.setTransactionId(updateId);

                    boolean updated =
                            transactionDAO.updateTransaction(
                                    updatedTransaction,
                                    userId);

                    if (updated) {

                        System.out.println(
                                "Transaction updated successfully!");

                    } else {

                        System.out.println(
                                "Transaction not found.");
                    }

                    break;


                case 7:

                    System.out.println(
                            "Logged out successfully.");

                    return;


                default:

                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }
	}

}
