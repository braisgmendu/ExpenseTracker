package org.example;
import java.util.Map;
import java.time.LocalDateTime;
public class Expense {
    public enum Category {FIXED, VARIABLE, OTHER}
    private int id;
    private String description;
    private double amount;
    private Category category;
    private LocalDateTime created;
    private LocalDateTime modified;
    public Expense(int id, String description, double amount, Category category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    //GETTERS
    public int getId() {
        return this.id;
    }
    public String getDescription() {
        return this.description;
    }
    public double getAmount() {
        return this.amount;
    }
    public Category getCategory() {
        return this.category;
    }
    public LocalDateTime getCreated() {
        return this.created;
    }
    public LocalDateTime getModified() {
        return this.modified;
    }
    //SETTERS
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void updateTimestamp(){
        this.modified = LocalDateTime.now();
    }

    public void updateAttributes(Map<String, String> updates) {
        if (updates.containsKey("description")) this.description = updates.get("description");
        if (updates.containsKey("amount")) this.amount = Double.parseDouble(updates.get("amount"));
        if (updates.containsKey("category")) this.category = Category.valueOf(updates.get("category"));
        this.updateTimestamp();
    }

    //toString
    public String toString() {
        return "Expense [description=" + description + ", amount=" + amount + ", category=" + category.toString() + "]";
    }
}
