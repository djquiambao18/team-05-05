package BudgetTracker.Summary;

import BudgetTracker.ExpensesPkg.Expenses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SummaryController implements Initializable {
    @FXML private PieChart pieChart;
    @FXML private Button changeScreenHomeBtn;
    @FXML private Button changeScreenSummaryBtn;
    double tempValue = 0.0;
    @FXML
    static ObservableList<PieChart.Data> pieChart_data;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(Expenses.getExpensesTable() != null){
            pieChart_data = FXCollections.observableArrayList();

            if (Expenses.getTotalGrocery() > 0) {
                pieChart_data.add(new PieChart.Data("Groceries", Expenses.getTotalGrocery()));
            }

            if (Expenses.getTotalUtil_Rent() + Expenses.getUtilitiesExpense() > 0) {
                pieChart_data.add(new PieChart.Data("Rent and Utilities",
                        Expenses.getTotalUtil_Rent() + Expenses.getUtilitiesExpense()));
            }

            if (Expenses.getSubscriptionExpense() > 0) {
                pieChart_data.add(new PieChart.Data("Subscriptions", Expenses.getSubscriptionExpense()));
            }

            if (Expenses.getTotalRestaurants() > 0) {
                pieChart_data.add(new PieChart.Data("Restaurants", Expenses.getTotalRestaurants()));
            }

            if (Expenses.getTotalMerchandise() > 0) {
                pieChart_data.add(new PieChart.Data("Merchandise", Expenses.getTotalMerchandise()));
            }

            if (Expenses.getTotalTransportation() > 0) {
                pieChart_data.add(new PieChart.Data("Transportation", Expenses.getTotalTransportation()));
            }

            if (Expenses.getTotalOthers() > 0) {
                pieChart_data.add(new PieChart.Data("Other", Expenses.getTotalOthers()));
            }

            pieChart.setVisible(true);
        } else
        {
            pieChart_data = FXCollections.observableArrayList(
                    new PieChart.Data("Groceries", tempValue),
                    new PieChart.Data("Rent and Utilities", tempValue),
                    new PieChart.Data("Subscriptions", tempValue),
                    new PieChart.Data("Restaurants", tempValue),
                    new PieChart.Data("Merchandise", tempValue),
                    new PieChart.Data("Transportation", tempValue),
                    new PieChart.Data("Other", tempValue)
            );
            pieChart.setVisible(false);
        }
        pieChart.setData(pieChart_data);

    }

    // when this method is called, it will change the scene to home page
    public void changeScreenToHome(ActionEvent event) throws IOException
    {
        Parent setHomeParent = FXMLLoader.load(getClass().getResource("../home.fxml"));
        Scene setHomeScene = new Scene (setHomeParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(setHomeScene);
        window.show();
    }
}