package org.example;
import java.io.IOException;
import java.util.*;
public class ExpenseManager {
    private List<Expense> expenses;

    private Map<Integer, Double> monthBudget = new HashMap<>();

    public ExpenseManager() throws IOException {
        this.expenses = FileHandler.readExpenses();
        this.monthBudget = FileHandler.readBudget();
    }

    public void addExpense(String description, double amount, String category) {
        int id = expenses.size() + 1;
        Expense.Category enumCategory = Expense.Category.valueOf(category);
        Expense newExpense = new Expense(id, description,amount, enumCategory);
        expenses.add(newExpense);

        try{
            FileHandler.writeExpenses(expenses);
            System.out.println("Expense added successfully" + newExpense);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void updateExpense(int id, Map<String,String>updates) throws IOException {
        Expense expense = getExpense(id);
        if(expense != null){
            expense.updateAttributes(updates);

            try {
                FileHandler.writeExpenses(expenses);
                System.out.println("Expense updated successfully" + expense);
            }catch(IOException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("Expense not found");
        }

    }
    public void removeExpense(int id) {
        Expense expense = getExpense(id);
        if(expense != null){
            expenses.remove(expense);
            try {
                FileHandler.writeExpenses(expenses);
                System.out.println("Expense removed successfully" + expense);
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Expense not found");
        }
    }

    public void listExpenses() {
        for(Expense expense : expenses){
            System.out.println(expense);
        }
    }

    public void sumaryAll() {
        double total = 0;
        for(Expense expense : expenses){
            total += expense.getAmount();
        }
        System.out.println("Total expenses: " + total + "€");
    }

    public void sumaryForMonth(int month) {
        double totalMonth = 0;
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        for(Expense expense : expenses){
            if(expense.getCreated().getMonthValue() == month){
                totalMonth += expense.getAmount();
            }

        }
        System.out.println("Total expenses for " + monthName[month-1] + ": " + totalMonth + ", Budget: " + monthBudget.get(month));
        checkBudget(month,totalMonth);
    }

    public Expense getExpense(int id) {
        return expenses.stream().filter(expense -> expense.getId() == id).findFirst().orElse(null);

    }

    public void exportExpenses(String fileName) throws IOException {
        FileHandler.exportExpenses(expenses,fileName);
    }

    public void setBudget(int month, double budget) throws IOException {
        monthBudget.put(month,budget);
        FileHandler.writeBudget(monthBudget);
        System.out.println("Budget set to month " + month + ": " + budget);
    }
    public void checkBudget(int month, double totalExpenses) {
        if(monthBudget.containsKey(month)){
            double budget = monthBudget.get(month);
            if(totalExpenses > budget){
                System.out.println("Warning! You have exceeded the budget for month " + month + " .Budget: " + budget
                        + "€ , Expenses: " + totalExpenses + "€.");
            }
        }
    }
}
