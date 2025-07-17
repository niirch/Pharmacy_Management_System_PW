/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pharmacy_management_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Label userName;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button addMed_btn;
    @FXML
    private Button purchase_btn;
    @FXML
    private AnchorPane addmedicine_form;
    @FXML
    private TextField addmedicine_medicineID;
    @FXML
    private TextField addmedicine_brandName;
    @FXML
    private TextField addmedicine_productName;
    @FXML
    private ComboBox<?> addmedicine_type;
    @FXML
    private ComboBox<?> addmedicine_status;
    @FXML
    private TextField addmedicine_price;
    @FXML
    private ImageView addmedicine_imageView;
    @FXML
    private Button addmedicine_importBtn;
    @FXML
    private Button addmedicine_addBtn;
    @FXML
    private Button addmedicine_updateBtn;
    @FXML
    private Button addmedicine_clearBtn;
    @FXML
    private Button addmedicine_deletBtn;
    @FXML
    private TextField addmedicine_search;
    @FXML
    private TableColumn<?, ?> addmedicine_co_medicineID;
    @FXML
    private TableColumn<?, ?> addmedicine_co_brandName;
    @FXML
    private TableColumn<?, ?> addmedicine_co_productsName;
    @FXML
    private TableColumn<?, ?> addmedicine_co_type;
    @FXML
    private TableColumn<?, ?> addmedicine_co_price;
    @FXML
    private TableColumn<?, ?> addmedicine_co_status;
    @FXML
    private TableColumn<?, ?> addmedicine_co_date;
    @FXML
    private AnchorPane purches_from;
    @FXML
    private ComboBox<?> purches_type;
    @FXML
    private ComboBox<?> purches_medicineID;
    @FXML
    private ComboBox<?> purches_brand;
    @FXML
    private ComboBox<?> purches_productName;
    @FXML
    private Button purches_addbtn;
    @FXML
    private Label purches_total;
    @FXML
    private TextField purches_amount;
    @FXML
    private Label purches_balance;
    @FXML
    private Button purches_paybtn;
    @FXML
    private TableView<?> purches_tableView;
    @FXML
    private TableColumn<?, ?> purches_co_medicineID;
    @FXML
    private TableColumn<?, ?> purches_co_brandName;
    @FXML
    private TableColumn<?, ?> purches_co_productName;
    @FXML
    private TableColumn<?, ?> purches_co_type;
    @FXML
    private TableColumn<?, ?> purches_co_quentity;
    @FXML
    private TableColumn<?, ?> purches_co_price;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private AreaChart<?, ?> dashboard_chart;
    @FXML
    private Label dashboard_availableMedicine;
    @FXML
    private Label dashboard_totalIncome;
    @FXML
    private Label dashboard_totalCustomer;
    @FXML
    private Button logout_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(ActionEvent event) {
    }

    @FXML
    private void minimize(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
}
