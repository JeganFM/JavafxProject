package presentation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    BorderPane userBorderPane;

    @FXML
    TextField pinTextField;

    @FXML
    Button btnGoToSuperHomepage;



    public void setBtnGoToSuperHomepage(ActionEvent event){
        //TODO for Discretionary Access Control.
        System.out.println("Btn pressed");

    }


    public void btnBackAction(ActionEvent event) {
        System.out.println("User Back pressed");
        goToLoginPage();
        closeWindow();
    }




    public void goToLoginPage(){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();

        } catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }

    }
    public void closeWindow(){
        Stage stage = (Stage) userBorderPane.getScene().getWindow();
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }


}
