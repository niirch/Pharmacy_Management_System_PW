/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbproject/project.properties to edit this template
 */
package pharmacy_management_system;

import java.sql.Connection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
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
import java.sql.ResultSet;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.sql.Statement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import java.awt.Desktop;

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
    private TableView<medicineData> addMedicine_tableView;
    @FXML
    private TableColumn<medicineData, String> addmedicine_co_medicineID;
    @FXML
    private TableColumn<medicineData, String> addmedicine_co_brandName;
    @FXML
    private TableColumn<medicineData, String> addmedicine_co_productsName;
    @FXML
    private TableColumn<medicineData, String> addmedicine_co_type;
    @FXML
    private TableColumn<medicineData, String> addmedicine_co_price;
    @FXML
    private TableColumn<medicineData, String> addmedicine_co_status;
    @FXML
    private TableColumn<medicineData, String> addmedicine_co_date;
    @FXML
    private Spinner<Integer> purchase_quantity;
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
    private TableView<customerData> purches_tableView;
    @FXML
    private TableColumn<customerData, String> purches_co_medicineID;
    @FXML
    private TableColumn<customerData, String> purches_co_brandName;
    @FXML
    private TableColumn<customerData, String> purches_co_productName;
    @FXML
    private TableColumn<customerData, String> purches_co_type;
    @FXML
    private TableColumn<customerData, String> purches_co_quentity;
    @FXML
    private TableColumn<customerData, String> purches_co_price;
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
    @FXML
    private Label username;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Image image;
    
    public void homeChart(){
        dashboard_chart.getData().clear();
        
        String sql = "SELECT date, SUM(total) FROM customer_info"
                + " GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 9";
        
        connect = Database.connectDb();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            
            dashboard_chart.getData().add(chart);
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void homeAM(){
        String sql = "SELECT COUNT(id) FROM medicine WHERE status = 'Available'";
        
        connect = Database.connectDb();
        int countAM = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countAM = result.getInt("COUNT(id)");
            }
            
            dashboard_availableMedicine.setText(String.valueOf(countAM));
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void homeTI(){
        String sql = "SELECT SUM(total) FROM customer_info";
        
        connect = Database.connectDb();
        double totalDisplay = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                totalDisplay = result.getDouble("SUM(total)");
            }
            
            dashboard_totalIncome.setText("$" + String.valueOf(totalDisplay));
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void homeTC(){
        String sql = "SELECT COUNT(id) FROM customer_info";
        
        connect = Database.connectDb();
        int countTC = 0;
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countTC = result.getInt("COUNT(id)");
            }
            
            dashboard_totalCustomer.setText(String.valueOf(countTC));
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    @FXML
    public void addMedicinesAdd(){
        String sql = "INSERT INTO medicine (medicine_id, brand, productName, type, status, price, image, date)"
                + " VALUES(?,?,?,?,?,?,?,?)";
        
        connect = Database.connectDb();
        try {
            Alert alert;
            
            if(addmedicine_medicineID.getText().isEmpty()
                    || addmedicine_brandName.getText().isEmpty()
                    || addmedicine_productName.getText().isEmpty()
                    || addmedicine_type.getSelectionModel().getSelectedItem()== null
                    || addmedicine_status.getSelectionModel().getSelectedItem()== null
                    || addmedicine_price.getText().isEmpty()
                    || getData.path == null || getData.path == "" ){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                String checkData = "SELECT medicine_id FROM medicine WHERE medicine_id = '" +addmedicine_medicineID.getText()+"'";
                
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);
                
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Medicine ID: " + addmedicine_medicineID.getText() + " was already exist!");
                    alert.showAndWait();
                }else{
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addmedicine_medicineID.getText());
                    prepare.setString(2, addmedicine_brandName.getText());
                    prepare.setString(3, addmedicine_productName.getText());
                    prepare.setString(4, (String)addmedicine_type.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String)addmedicine_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, addmedicine_price.getText());
                    
                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    
                    prepare.setString(7, uri);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    
                    prepare.setString(8, String.valueOf(sqlDate));
                    
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    
                    addMedicineShowListData();
                    addMedicineReset();
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }
    
    @FXML
    public void addMedicineUpdate(){
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        
        String sql = "UPDATE medicine SET brand = '"
                +addmedicine_brandName.getText()+"', productName = '"
                +addmedicine_productName.getText()+"', type = '"
                +addmedicine_type.getSelectionModel().getSelectedItem()+"', status = '"
                +addmedicine_status.getSelectionModel().getSelectedItem()+"', price = '"
                +addmedicine_price.getText()+"', image = '"+uri+"' WHERE medicine_id = '"
                +addmedicine_medicineID.getText()+"'";
        
        connect = Database.connectDb();
        
        try{
            Alert alert;
            
            if(addmedicine_medicineID.getText().isEmpty()
                    || addmedicine_brandName.getText().isEmpty()
                    || addmedicine_productName.getText().isEmpty()
                    || addmedicine_type.getSelectionModel().getSelectedItem() == null
                    || addmedicine_status.getSelectionModel().getSelectedItem() == null
                    || addmedicine_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Medicine ID:" + addmedicine_medicineID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    
                    addMedicineShowListData();
                    addMedicineReset();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    @FXML
    public void addMedicineDelete(){
        String sql = "DELETE FROM medicine WHERE medicine_id = '"+addmedicine_medicineID.getText()+"'";
        
        connect = Database.connectDb();
        
        try{
            Alert alert;
            
            if(addmedicine_medicineID.getText().isEmpty()
                    || addmedicine_brandName.getText().isEmpty()
                    || addmedicine_productName.getText().isEmpty()
                    || addmedicine_type.getSelectionModel().getSelectedItem() == null
                    || addmedicine_status.getSelectionModel().getSelectedItem() == null
                    || addmedicine_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Medicine ID:" + addmedicine_medicineID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    
                    addMedicineShowListData();
                    addMedicineReset();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void addMedicineReset(){
        addmedicine_medicineID.setText("");
        addmedicine_brandName.setText("");
        addmedicine_productName.setText("");
        addmedicine_price.setText("");
        addmedicine_type.getSelectionModel().clearSelection();
        addmedicine_status.getSelectionModel().clearSelection();
        
        addmedicine_imageView.setImage(null);
        getData.path = "";
    }
    
    private String[] addMedicineListT = {"Hydrocodone", "Antibiotics", "Metformin", "Losartan", "Albuterol"};
    
    @FXML
    public void addMedicineListType() {
        List<String> listT = new ArrayList<>();
        for (String data : addMedicineListT) {
            listT.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listT);
        addmedicine_type.setItems(listData);
    }
    
    private String[] addMedicineStatus = {"Available", "Not Available"};
    
    @FXML
    public void addMedicineListStatus() {
        List<String> listS = new ArrayList<>();
        for (String data : addMedicineStatus) {
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        addmedicine_status.setItems(listData);
    }
    
    @FXML
    public void addMedicineImportImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Choose an Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File file = open.showOpenDialog(main_form.getScene().getWindow());
        
        if(file != null) {
            image = new Image(file.toURI().toString(), 113, 153, false, true);
            addmedicine_imageView.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }
    
    public ObservableList<medicineData> addMedicinesListData() {
        String sql = "SELECT * FROM medicine";
        
        ObservableList<medicineData> listData = FXCollections.observableArrayList();
        
        connect = Database.connectDb();
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            medicineData medData;
            while (result.next()) {
                medData = new medicineData(result.getInt("medicine_id"), result.getString("brand")
                        ,result.getString("productName"), result.getString("type")
                        ,result.getString("status"), result.getDouble("price")
                        ,result.getString("image"), result.getDate("date"));
                listData.add(medData);
            }
        }catch (Exception e) {e.printStackTrace(); }
        return listData;
    }
    
    private ObservableList<medicineData> addMedicineList;
    
    public void addMedicineShowListData() {
        addMedicineList = addMedicinesListData();
        
        addmedicine_co_medicineID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        addmedicine_co_brandName.setCellValueFactory(new PropertyValueFactory<>("brand"));
        addmedicine_co_productsName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addmedicine_co_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        addmedicine_co_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addmedicine_co_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addmedicine_co_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        addMedicine_tableView.setItems(addMedicineList);
    }
    
    @FXML
    public void addMedicineSearch(){
        FilteredList<medicineData> filter = new FilteredList<>(addMedicineList, e-> true);
        
        addmedicine_search.textProperty().addListener((Observable, oldValue, newValue) ->{
            filter.setPredicate(predicateMedicineData ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();
                
                if(predicateMedicineData.getMedicineId().toString().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getBrand().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getProductName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getType().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getStatus().toLowerCase().contains(searchKey)){
                    return true;
                }else if(String.valueOf(predicateMedicineData.getPrice()).contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getDate().toString().contains(searchKey)){
                    return true;
                }else return false;
            });
        });
        
        SortedList<medicineData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addMedicine_tableView.comparatorProperty());
        addMedicine_tableView.setItems(sortList);
    }
    
    @FXML
    public void addMedicineSelect(){
        medicineData medData = addMedicine_tableView.getSelectionModel().getSelectedItem();
        int num = addMedicine_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {return;}
        
        addmedicine_medicineID.setText(String.valueOf(medData.getMedicineId()));
        addmedicine_brandName.setText(medData.getBrand());
        addmedicine_productName.setText(medData.getProductName());
        addmedicine_price.setText(String.valueOf(medData.getPrice()));
        
        String uri = "file:" + medData.getImage();
        image = new Image(uri, 113, 153, false, true);
        addmedicine_imageView.setImage(image);
        
        getData.path = medData.getImage();
    }
    
    private double totalP;
    
    @FXML
    public void purchaseAdd(){
        purchaseCustomerId();
        
        String sql = "INSERT INTO customer (customer_id,type,medicine_id,brand,productName,quantity,price,date)"
                + " VALUES(?,?,?,?,?,?,?,?)";
        
        connect = Database.connectDb();
        
        try{
            Alert alert;
            
            if(purches_type.getSelectionModel().getSelectedItem() == null
                    || purches_medicineID.getSelectionModel().getSelectedItem() == null
                    || purches_brand.getSelectionModel().getSelectedItem() == null
                    || purches_productName.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, String.valueOf(customerId));
                prepare.setString(2, (String)purches_type.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String)purches_medicineID.getSelectionModel().getSelectedItem());
                prepare.setString(4, (String)purches_brand.getSelectionModel().getSelectedItem());
                prepare.setString(5, (String)purches_productName.getSelectionModel().getSelectedItem());
                prepare.setString(6, String.valueOf(qty));
                
                String checkData = "SELECT price FROM medicine WHERE medicine_id = '"
                        +purches_medicineID.getSelectionModel().getSelectedItem()+"'";
                
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);
                double priceD = 0;
                if(result.next()){
                    priceD = result.getDouble("price"); 
                }
                
                totalP = (priceD * qty);
                
                prepare.setString(7, String.valueOf(totalP));
                
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(8, String.valueOf(sqlDate));
                
                prepare.executeUpdate();
                
                purchaseShowListData();
                purchaseDisplayTotal();
            }
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    private double totalPriceD;
    
    public void purchaseDisplayTotal(){
        String sql = "SELECT SUM(price) FROM customer WHERE customer_id = '"+customerId+"'";
        
        connect = Database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if(result.next()){
                totalPriceD = result.getDouble("SUM(price)");
            }
            purches_total.setText("$" + String.valueOf(totalPriceD));
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    private double balance;
    private double amount;
    
    @FXML
    public void purchaseAmount(){
        if(purches_amount.getText().isEmpty() || totalPriceD == 0){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        }else{
            amount = Double.parseDouble(purches_amount.getText());
            if(totalPriceD > amount){
                purches_amount.setText("");
            }else{
                balance = (amount - totalPriceD);
                purches_balance.setText("$"+String.valueOf(balance));
            }
        }
    } 
    
    @FXML
    public void purchasePay(){
        purchaseCustomerId();
        String sql = "INSERT INTO customer_info (customer_id, total, date) "
                + "VALUES(?,?,?)";
        
        connect = Database.connectDb();
        
        try{
            Alert alert;
            
            if(totalPriceD == 0){
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, String.valueOf(totalPriceD));
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(3, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    
                    String receiptContent = generateReceipt();
                    File receiptFile = new File("receipt_" + customerId + "_" + sqlDate + ".txt");
                    try (FileWriter writer = new FileWriter(receiptFile)) {
                        writer.write(receiptContent);
                    } catch (IOException e) {
                        e.printStackTrace();
                        alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to generate receipt file!");
                        alert.showAndWait();
                    }
                    
                    if (receiptFile.exists()) {
                        try {
                            Desktop.getDesktop().open(receiptFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                            alert = new Alert(AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setContentText("Failed to open receipt file!");
                            alert.showAndWait();
                        }
                    }
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successful! Receipt generated.");
                    alert.showAndWait();
                    
                    purches_amount.setText("");
                    purches_balance.setText("$0.0");
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    private String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("===== Pharmacy Receipt =====\n");
        receipt.append("Customer ID: ").append(customerId).append("\n");
        receipt.append("Date: ").append(new Date()).append("\n");
        receipt.append("----------------------------\n");
        receipt.append("Items Purchased:\n");
        
        String sql = "SELECT * FROM customer WHERE customer_id = '" + customerId + "'";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                receipt.append("Medicine ID: ").append(result.getString("medicine_id")).append("\n");
                receipt.append("Type: ").append(result.getString("type")).append("\n");
                receipt.append("Brand: ").append(result.getString("brand")).append("\n");
                receipt.append("Product Name: ").append(result.getString("productName")).append("\n");
                receipt.append("Quantity: ").append(result.getInt("quantity")).append("\n");
                receipt.append("Price: $").append(result.getDouble("price")).append("\n");
                receipt.append("----------------------------\n");
            }
        } catch (Exception e) { e.printStackTrace(); }
        
        receipt.append("Total: $").append(totalPriceD).append("\n");
        receipt.append("Amount Paid: $").append(amount).append("\n");
        receipt.append("Balance: $").append(balance).append("\n");
        receipt.append("===== Thank You! =====");
        return receipt.toString();
    }
    
    private SpinnerValueFactory<Integer> spinner;
    
    public void purchaseShowValue(){
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
        purchase_quantity.setValueFactory(spinner);
    }
    
    private int qty;
    
    @FXML
    public void purchaseQuantity(){
        qty = purchase_quantity.getValue();
    }
    
    public ObservableList<customerData> purchaseListData(){
        purchaseCustomerId();
        
        String sql = "SELECT * FROM customer WHERE customer_id = '"+customerId+"'";
        
        ObservableList<customerData> listData = FXCollections.observableArrayList();
        
        connect = Database.connectDb();
        
        try{
            customerData customerD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                customerD = new customerData(result.getInt("customer_id")
                        , result.getString("type"), result.getInt("medicine_id")
                        , result.getString("brand"), result.getString("productName")
                        , result.getInt("quantity"), result.getDouble("price")
                        , result.getDate("date"));
                listData.add(customerD); 
            }
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }
    
    private ObservableList<customerData> purchaseList;
    
    public void purchaseShowListData(){
        purchaseList = purchaseListData();
        
        purches_co_medicineID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        purches_co_brandName.setCellValueFactory(new PropertyValueFactory<>("brand"));
        purches_co_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        purches_co_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        purches_co_quentity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purches_co_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        purches_tableView.setItems(purchaseList); 
    } 
    
    private int customerId;
    
    public void purchaseCustomerId(){
        String sql = "SELECT customer_id FROM customer";
        
        connect = Database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                customerId = result.getInt("customer_id");
            }
            int cID = 0;
            String checkData = "SELECT customer_id FROM customer_info";
            
            statement = connect.createStatement();
            result = statement.executeQuery(checkData);
            
            while(result.next()){
                cID = result.getInt("customer_id");  
            }
            
            if(customerId == 0){
                customerId+=1;
            }else if(cID == customerId){
                customerId = cID+1;
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    @FXML
    public void purchaseType(){
        String sql = "SELECT type FROM medicine WHERE status = 'Available'";
        
        connect = Database.connectDb();
        
        try{
            ObservableList listData = FXCollections.observableArrayList();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                listData.add(result.getString("type"));
            }
            purches_type.setItems(listData);
            
            purchaseMedicineId();
        }catch(Exception e){e.printStackTrace();}
    }
    
    @FXML
    public void purchaseMedicineId(){
        String sql = "SELECT * FROM medicine WHERE type = '"
                +purches_type.getSelectionModel().getSelectedItem()+"'";
        
        connect = Database.connectDb();
        
        try{
            ObservableList listData = FXCollections.observableArrayList();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                listData.add(result.getString("medicine_id"));
            }
            purches_medicineID.setItems(listData);
            
            purchaseBrand();
        }catch(Exception e){e.printStackTrace();}
    }
    
    @FXML
    public void purchaseBrand(){
        String sql = "SELECT * FROM medicine WHERE medicine_id = '"
                +purches_medicineID.getSelectionModel().getSelectedItem()+"'";
        
        connect = Database.connectDb(); 
        
        try{
            ObservableList listData = FXCollections.observableArrayList();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                listData.add(result.getString("brand"));
            }
            purches_brand.setItems(listData);
            
            purchaseProductName();
        }catch(Exception e){e.printStackTrace();}
    }
    
    @FXML
    public void purchaseProductName(){
        String sql = "SELECT * FROM medicine WHERE brand = '"
                +purches_brand.getSelectionModel().getSelectedItem()+"'";
        
        connect = Database.connectDb();
        
        try{
            ObservableList listData = FXCollections.observableArrayList();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                listData.add(result.getString("productName"));
            }
            purches_productName.setItems(listData);
        }catch(Exception e){e.printStackTrace();}
    }
    
    @FXML
    public void switchForm( ActionEvent event ){
        if(event.getSource()== dashboard_btn){
            dashboard_form.setVisible(true);
            addmedicine_form.setVisible(false);
            purches_from.setVisible(false);
            
            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            addMed_btn.setStyle("-fx-background-color:transparent");
            purchase_btn.setStyle("-fx-background-color:transparent");
            
            homeChart();
            homeAM();
            homeTI();
            homeTC();
        }else if(event.getSource()== addMed_btn){
            dashboard_form.setVisible(false);
            addmedicine_form.setVisible(true);
            purches_from.setVisible(false);
            
            addMed_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a41c);");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            purchase_btn.setStyle("-fx-background-color:transparent");
            
            addMedicineShowListData();
            addMedicineListStatus();
            addMedicineListType();
            addMedicineSearch();
        }else if(event.getSource()== purchase_btn){
            dashboard_form.setVisible(false);
            addmedicine_form.setVisible(false);
            purches_from.setVisible(true);
            
            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a41c);");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            addMed_btn.setStyle("-fx-background-color:transparent");
            
            purchaseType();
            purchaseMedicineId();
            purchaseBrand();
            purchaseProductName();
            purchaseShowListData();
            purchaseShowValue();
            purchaseDisplayTotal();
        }
    }
    
    public void displayUsername(){
        String user = getData.username;
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }
    
    private double x = 0;
    private double y = 0;
    
    @FXML
    public void logout(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if (option.get().equals(ButtonType.OK)) {
                logout_btn.getScene().getWindow().hide();
                
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
        } catch (Exception e) {e.printStackTrace();}
    }
    
    @FXML
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    public void close() {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername(); 
        
        homeChart();
        homeAM();
        homeTI();
        homeTC();
        
        addMedicineShowListData();
        addMedicineListStatus();
        addMedicineListType();
        
        purchaseType();
        purchaseMedicineId();
        purchaseBrand();
        purchaseProductName();
        purchaseShowListData();
        purchaseShowValue();
        purchaseDisplayTotal();
    }

 @FXML
private void searchBTN(ActionEvent event) {
    String searchKey = addmedicine_search.getText().trim();
    ObservableList<medicineData> searchResults = FXCollections.observableArrayList();
    
    // If search field is empty, show all medicines
    String sql = searchKey.isEmpty() 
        ? "SELECT * FROM medicine"
        : "SELECT * FROM medicine WHERE productName LIKE ?";
    
    connect = Database.connectDb();
    
    try {
        prepare = connect.prepareStatement(sql);
        
        // If searchKey is not empty, set the LIKE parameter
        if (!searchKey.isEmpty()) {
            prepare.setString(1, "%" + searchKey + "%"); // Use LIKE for partial matches
        }
        
        result = prepare.executeQuery();
        
        medicineData medData;
        while (result.next()) {
            medData = new medicineData(
                result.getInt("medicine_id"),
                result.getString("brand"),
                result.getString("productName"),
                result.getString("type"),
                result.getString("status"),
                result.getDouble("price"),
                result.getString("image"),
                result.getDate("date")
            );
            searchResults.add(medData);
        }
        
        // Update table with search results
        addmedicine_co_medicineID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        addmedicine_co_brandName.setCellValueFactory(new PropertyValueFactory<>("brand"));
        addmedicine_co_productsName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addmedicine_co_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        addmedicine_co_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addmedicine_co_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addmedicine_co_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        addMedicine_tableView.setItems(searchResults);
        
        // Show alert if no results are found and searchKey is not empty
        if (searchResults.isEmpty() && !searchKey.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Search Result");
            alert.setHeaderText(null);
            alert.setContentText("No medicine found with name: " + searchKey);
            alert.showAndWait();
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Error occurred while searching. Please try again.");
        alert.showAndWait();
    } finally {
        // Close database resources
        try {
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}