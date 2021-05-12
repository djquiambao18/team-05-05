package BudgetTracker.SaveFile;

import BudgetTracker.Budget.Budget;
import BudgetTracker.ExpensesPkg.Expenses;
import javafx.scene.control.Alert;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveFile {
    private static File expenses_SaveFile = new File("userExpenses.csv");   //assigned within the save methods.
    private static File setDate_SaveFile = new File("setDate.txt");
    private static File setBudget_SaveFile = new File("budget.txt");
    /*
    * Expenses save data method
    * */
    public static boolean saveExpenses(List<Expenses> expenses) throws IOException {

        if(expenses_SaveFile.exists()){
            File fileOut = new File("userExpenses.csv");
            PrintWriter writer = new PrintWriter(fileOut);
            for (Expenses exp: expenses) {
                writer.print(exp.getItemName() +"," + exp.getItemCost() + "," +exp.getItemCategory() +"\n");
            }
            writer.close();
            return true;
        }
        else{
            File fileOut = new File("userExpenses.csv");
            return fileOut.createNewFile();
        }
    }

    /*
    * Expenses load data method
    * */
    public static ArrayList<Expenses> loadExpenses() throws IOException {
        ArrayList<Expenses> tempArrayList = new ArrayList<>();
        try {
            if(expenses_SaveFile.exists()) {
                Scanner reader = new Scanner(expenses_SaveFile);
                reader.useDelimiter("[,\\n]+");
                while (reader.hasNext()) {
                    Expenses expenses = new Expenses(reader.next(), Double.parseDouble(reader.next()), reader.next());
                    tempArrayList.add(expenses);
                }
            }
            else
            {
                Alert save_existsAlert = new Alert(Alert.AlertType.INFORMATION);
                save_existsAlert.setContentText("No data Found...");
                save_existsAlert.showAndWait();    //waits for user input to press OK to continue
            }
        }
        catch (IOException | NumberFormatException e)
        {
            Alert save_existsAlert = new Alert(Alert.AlertType.INFORMATION);
            save_existsAlert.setContentText("No readable data Found...");
            save_existsAlert.showAndWait();    //waits for user input to press OK to continue
            e.getStackTrace();
        }
        return tempArrayList;
    }

    public static boolean setBudget_save(Budget budgetAmount) {
        try {
            PrintWriter writer = new PrintWriter(setBudget_SaveFile.getAbsolutePath());
            writer.println("Budget=" + budgetAmount.getBudget() + "," + LocalDate.now() +"\n");
            writer.close();
            return true;
        } catch (IOException | NumberFormatException e)
        {
            e.getStackTrace();
        }
        return false;
    }

    public static double setBudget_load(){
        Budget budgetParsed = new Budget();
        try{
            Scanner reader = new Scanner(setBudget_SaveFile);
            reader.useDelimiter("[=,\\n]+");
            while(reader.hasNext()){
                if(reader.next().equals("Budget")){
                    budgetParsed.setBudget(Double.parseDouble(reader.next()));
                }
            }
        }
        catch(IOException | NumberFormatException e)
        {
            e.getStackTrace();
        }
        return budgetParsed.getBudget();
    }
/*
    public static boolean setdate_save(SetDate date){
        try {
                PrintWriter writer = new PrintWriter(income_SaveFile.getAbsolutePath());
                writer.println(income.getTotalIncomeBalance() + "," + income.getWageEarnings() +"\n");
                writer.close();
                return true;

        }catch (IOException | NumberFormatException | NullPointerException e)
        {
            e.getStackTrace();
        }
        return false;
    }

    public static Income income_load(){
        Income income = new Income();
        try{

            Scanner reader = new Scanner(income_SaveFile);
            reader.useDelimiter("[,\\n]+");
            while(reader.hasNext()){
                //set income vars
                income.setTotalIncomeBalance(Double.parseDouble(reader.next()));
                income.setWageEarnings(Double.parseDouble(reader.next()));
            }
        }
        catch(IOException | NumberFormatException e)
        {
            e.getStackTrace();
        }
        return income;
    }*/

    //Checks if file already exists.
    public static boolean fileExists(){
        return expenses_SaveFile.exists() || setDate_SaveFile.exists() || setBudget_SaveFile.exists();
    }
}
