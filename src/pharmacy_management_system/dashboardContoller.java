/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharmacy_management_system;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class dashboardContoller implements Initializable {

    @FXML
    private Button addMed_btn;

    @FXML
    private Button addmedicine_addBtn;

    @FXML
    private TextField addmedicine_brandName;

    @FXML
    private Button addmedicine_clearBtn;

    @FXML
    private TableColumn<?, ?> addmedicine_co_brandName;

    @FXML
    private TableColumn<?, ?> addmedicine_co_date;

    @FXML
    private TableColumn<?, ?> addmedicine_co_medicineID;

    @FXML
    private TableColumn<?, ?> addmedicine_co_price;

    @FXML
    private TableColumn<?, ?> addmedicine_co_productsName;

    @FXML
    private TableColumn<?, ?> addmedicine_co_status;

    @FXML
    private TableColumn<?, ?> addmedicine_co_type;

    @FXML
    private Button addmedicine_deletBtn;

    @FXML
    private AnchorPane addmedicine_form;

    @FXML
    private ImageView addmedicine_imageView;

    @FXML
    private Button addmedicine_importBtn;

    @FXML
    private TextField addmedicine_medicineID;

    @FXML
    private TextField addmedicine_price;

    @FXML
    private TextField addmedicine_productName;

    @FXML
    private TextField addmedicine_search;

    @FXML
    private ComboBox<?> addmedicine_status;

    @FXML
    private ComboBox<?> addmedicine_type;

    @FXML
    private Button addmedicine_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Label dashboard_availableMedicine;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AreaChart<?, ?> dashboard_chart;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalCustomer;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button purchase_btn;

    @FXML
    private Button purches_addbtn;

    @FXML
    private TextField purches_amount;

    @FXML
    private Label purches_balance;

    @FXML
    private ComboBox<?> purches_brand;

    @FXML
    private TableColumn<?, ?> purches_co_brandName;

    @FXML
    private TableColumn<?, ?> purches_co_medicineID;

    @FXML
    private TableColumn<?, ?> purches_co_price;

    @FXML
    private TableColumn<?, ?> purches_co_productName;

    @FXML
    private TableColumn<?, ?> purches_co_quentity;

    @FXML
    private TableColumn<?, ?> purches_co_type;

    @FXML
    private AnchorPane purches_from;

    @FXML
    private ComboBox<?> purches_medicineID;

    @FXML
    private Button purches_paybtn;

    @FXML
    private ComboBox<?> purches_productName;

    @FXML
    private TableView<?> purches_tableView;

    @FXML
    private Label purches_total;

    @FXML
    private ComboBox<?> purches_type;

    @FXML
    private Label userName;

    private double x = 0;
    private double y = 0;

    public void logout() {

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                
                logout.getScene().getWindow().hide();
                
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

                
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getScreenY();

                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {

                    stage.setOpacity(1);
                });
                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
