/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pharmacy_management_system;

import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.Statement;
import java.io.File;
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
//import java.sql.PreparedStatement;
import java.sql.Statement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;



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
    @FXML
    private Label username;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Image image;
    
    @FXML
    
    public void addMedicinesAdd(){
        
    String sql = "INSERT INTO medicine (medicine_id, brand, productName, type, status, price, image, date)"
    + "VALUES(?,?,?,?,?,?,?,?)";
    
    connect = (Connection) Database.connectDb();
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
        
        //connect = database.connectDb();
        connect = (Connection) Database.connectDb();
        
            
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
        
        
        connect = (Connection) Database.connectDb();
        
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
    open.setTitle("Import Image File");
    open.getExtensionFilters().add(new ExtensionFilter("Image File", "*.jpg", "*.png"));
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
       
        connect = (Connection) Database.connectDb();
       
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
          addmedicine_co_price.setCellValueFactory(new PropertyValueFactory<>("status"));
          addmedicine_co_status.setCellValueFactory(new PropertyValueFactory<>("price"));
          addmedicine_co_date.setCellValueFactory(new PropertyValueFactory<>("date"));
          
          addMedicine_tableView.setItems(addMedicineList);
          
}
         
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
                }else if(predicateMedicineData.getPrice().toString().contains(searchKey)){
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
    
    @FXML
    public void switchForm( ActionEvent event ){
        if(event.getSource()== dashboard_btn){
           dashboard_form.setVisible(true);
           addmedicine_form.setVisible(false);
           purches_from.setVisible(false);
           
           dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
           addMed_btn.setStyle("-fx-background-color:transparent");
           purchase_btn.setStyle("-fx-background-color:transparent");
           
           
        }else if(event.getSource()== addMed_btn){
            
            dashboard_form.setVisible(false);
           addmedicine_form.setVisible(true);
           purches_from.setVisible(false);
           
           addMed_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
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
           
           purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
           dashboard_btn.setStyle("-fx-background-color:transparent");
           addMed_btn.setStyle("-fx-background-color:transparent");
        
        }
    
    }

    public void displayUsername(){
     
        String user = getData.username;
        
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
        
    }
    
    private double x = 0;
    private double y = 0;

    @FXML
    public void logout() {

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
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

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        
        addMedicineShowListData();
        addMedicineListStatus();
        addMedicineListType();

    }

}
   
