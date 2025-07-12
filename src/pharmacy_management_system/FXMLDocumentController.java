/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package pharmacy_management_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button close;

    @FXML
    private Button loginbtn;


    @FXML
    private TextField password;

    @FXML
    private TextField username;
    
    private PreparedStatement prepare;
    private Connection connect;
    private ResultSet result;
    
    @FXML
    public void loginAdmin(){
        
        String sql = "SELECT * FROM admin WHERE username = ? and password = ? ";
        
        connect = Database.connectDb();
     try{
     prepare = connect.prepareStatement(sql);
     prepare.setString(1, username.getText());
     prepare.setString(2, password.getText());
     
     result = prepare.executeQuery();
     
     Alert alert;
     
     if (username.getText().isEmpty() || password.getText().isEmpty()){
       alert = new Alert (AlertType.ERROR);
       alert.setTitle("Error Message");
       alert.setHeaderText(null);
       alert.setContentText("Please Fill All Blank Fields");
       alert.showAndWait();
   
     }else{
     
     if(result.next()){
         
         alert = new Alert (AlertType.INFORMATION);
       alert.setTitle("Information Message");
       alert.setHeaderText(null);
       alert.setContentText("Sucessfully Login");
       alert.showAndWait();
       
       loginbtn.getScene().getWindow().hide();
       
       
       
       Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
       Stage stage = new Stage();
       Scene scene = new Scene(root); 
       
       stage.setScene(scene);
       stage.show();
     
     }else{
         alert = new Alert (AlertType.ERROR);
       alert.setTitle("Error Message");
       alert.setHeaderText(null);
       alert.setContentText("Wrong username/password");
       alert.showAndWait();
     
     }
     }
     }catch(Exception e){e.printStackTrace();}
    }
    
    @FXML
    public void close(){
    System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
