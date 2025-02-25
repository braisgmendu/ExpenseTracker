package org.example;
import  java.io.*;
import java.util.Locale;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ExpenseCLI {
    public static void main(String[] args) throws IOException {
        ExpenseManager expenseManager = new ExpenseManager();
        Scanner sc = new Scanner(System.in);

        if (args.length == 0) {
            System.out.println("No command provided. Exiting.");
            return;
        }

        String command = args[0].toLowerCase();
        boolean flag;
        switch (command) {
            case "add":
                flag = false;
                System.out.println("Enter amount to be added: ");
                double amount = sc.nextDouble();
                sc.nextLine(); // Limpiar el buffer
                System.out.println("Enter description: ");
                String description = sc.nextLine();
                Expense.Category category = null;

                while (!flag) {
                    System.out.println("New category (fixed[1], variable[2], other[3]):");
                    int choice = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer
                    switch (choice) {
                        case 1:
                            category = Expense.Category.FIXED;
                            flag = true;
                            break;
                        case 2:
                            category = Expense.Category.VARIABLE;
                            flag = true;
                            break;
                        case 3:
                            category = Expense.Category.OTHER;
                            flag = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                }
                expenseManager.addExpense(description, amount, category.toString());
                break;

            case "remove":
                System.out.println("Enter ID to be removed: ");
                int id = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
                expenseManager.removeExpense(id);
                break;

            case "update":
                flag = false;
                System.out.println("Enter ID to be updated: ");
                id = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
                Map<String, String> updates = new HashMap<>();
                System.out.println("Enter attributes to update (leave blank to skip):");
                System.out.println("New description:");
                String newDescription = sc.nextLine();
                if (!newDescription.isEmpty()) updates.put("description", newDescription);

                Expense.Category newCategory = null;
                while (!flag) {
                    System.out.println("New category (fixed[1], variable[2], other[3]):");
                    int choice = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer
                    switch (choice) {
                        case 1:
                            newCategory = Expense.Category.FIXED;
                            flag = true;
                            break;
                        case 2:
                            newCategory = Expense.Category.VARIABLE;
                            flag = true;
                            break;
                        case 3:
                            newCategory = Expense.Category.OTHER;
                            flag = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                }
                if (newCategory != null) updates.put("category", newCategory.toString());
                expenseManager.updateExpense(id, updates);
                break;

            case "list":
                expenseManager.listExpenses();
                break;

            case "summary":
                expenseManager.sumaryAll();
                break;

            case "summary-month":
                System.out.println("Enter number of months to summarize: ");
                int month = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
                expenseManager.sumaryForMonth(month);
                break;

            default:
                System.out.println("Unexpected command: " + command);
                break;
        }
        sc.close();
    }
}