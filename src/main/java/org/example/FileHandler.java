package org.example;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.nio.file.*;
import java.util.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandler {
    private static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter()).create();
    private static final String FILENAME = "expenses.json";
    private static final String BUDGET_FILE = "budget.json";

    public static List<Expense> readExpenses() throws IOException {
        if (!Files.exists(Paths.get(FILENAME))) {
            Files.createFile(Paths.get(FILENAME));
            return new ArrayList<>();
        }

        try{
            String json = new String(Files.readAllBytes(Paths.get(FILENAME)));

            if(json.trim().isEmpty()){
                return new ArrayList<>();
            }
            List<Expense> expenses = gson.fromJson(json, new TypeToken<List<Expense>>(){}.getType());

            return expenses != null ? expenses : new ArrayList<>();
        }catch (IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public static void writeExpenses(List<Expense> expenses) throws IOException {
        try (FileWriter writer = new FileWriter(FILENAME)) {
            gson.toJson(expenses, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void exportExpenses(List<Expense> expenses, String fileName) throws IOException {
        try(FileWriter writer = new FileWriter(fileName)) {
            writer.append("ID,Description,Amount,Category,Date\n");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (Expense expense : expenses) {
                writer.append(expense.getId()+","+expense.getDescription()+","+expense.getAmount()+","+expense.getCategory()+","+expense.getModified().format(formatter)+"\n");
            }
            System.out.println("Expenses exported to " + fileName);
        }catch (IOException e){
            System.out.println("Export error " + e.getMessage());
        }
    }
    public static void writeBudget(Map<Integer,Double> budget) throws IOException {
        try(FileWriter writer = new FileWriter(BUDGET_FILE)) {
            gson.toJson(budget, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Map<Integer,Double> readBudget() throws IOException {
        if (!Files.exists(Paths.get(BUDGET_FILE))) {
            Files.createFile(Paths.get(BUDGET_FILE));
            return new HashMap<>();
        }

        try{
            String json = new String(Files.readAllBytes(Paths.get(BUDGET_FILE)));

            if(json.trim().isEmpty()){
                return new HashMap<>();
            }
            HashMap<Integer,Double> budgets = gson.fromJson(json, new TypeToken<Map<Integer,Double>>(){}.getType());

            return budgets != null ? budgets : new HashMap<>();
        }catch (IOException e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    // formatea para que se vea yyyy-MM-dd'T'HH:mm:ss
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        //convierte un objeto LocalDateTime en su representaccion de texto para Json
        if (value == null) {//si el valor es null, escribe null
            out.nullValue();
        } else {//si no es null utiliza el formateador para convertir el objeto para poder escribir en JSON
            out.value(value.format(formatter)); // Serializa como cadena
        }
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        //convierte el JSON en un objeto LocalDateTime
        String json = in.nextString();
        return LocalDateTime.parse(json, formatter); // Deserializa desde cadena
    }
}