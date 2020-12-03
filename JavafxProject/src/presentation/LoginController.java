package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

import static persistence.StaffDAO.*;


public class LoginController implements Initializable {
    @FXML
    public AnchorPane loginAnchorPane;

    @FXML
    public TextField usernameTextfield;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Label messageLabel;


    @FXML
    public Button btnCancel;
    public void btnCancelAction (ActionEvent event){
        showMessage("");
        closeWindow();

    }

    @FXML
    public Button  btnLogin;


    public void btnLoginAction(ActionEvent event){
        System.out.println("Login presssed");
        showMessage("Trying To Login");

        if(usernameTextfield.getText().equals("") || passwordTextField.getText().equals("") ){
            showMessage("Enter valid Username and or Password");
            return;
        }


        if(validateUser(usernameTextfield.getText(), passwordTextField.getText())){
            if(isLockAccount(usernameTextfield.getText())){//if its locked
                showMessage("Account Blocked, Please Contact admin");
                System.out.println("the if is running");
            }
            else{
                showMessage("Login successful");
                String role = getRole(usernameTextfield.getText(), passwordTextField.getText());
                goToHomePage(role);
            }
        }// if the user exists and the password, and if the account is locked
        else if(checkIfExists(usernameTextfield.getText())){

            showMessage(updateAttempts(usernameTextfield.getText()));
            System.out.println(" login attempt");
        }
        else{
            showMessage("Invalid Credentials");

        }
        //TODO Alternate flow 4b
    }

    @FXML
    public Button btnRegister;

    public void btnRegisterAction(ActionEvent event){

        showMessage("");
        System.out.println("Register Pressed");
        createAccount();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    public static void transferMessage(String message) {
        //Display the message
        System.out.println(message);

    }

    public void createAccount(){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();

        } catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }

    }
    public void goToHomePage(String page){

        try{
            Parent root = FXMLLoader.load(getClass().getResource(page+".fxml"));
            System.out.println("page "+page+".fxml");
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();
            closeWindow();

        } catch(Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }

    }
    public boolean validateUser(String name, String pwd){

        boolean result = persistence.StaffDAO.validateUser(name, pwd);
        return result;

    }
    public void reset(){
        usernameTextfield.setText("");
        passwordTextField.setText("");
    }

    public void showMessage(String msg){
        messageLabel.setText(msg);
    }

    public void closeWindow(){
        Stage stage = (Stage) loginAnchorPane.getScene().getWindow();
        stage.close();
    }

//    public void tranferData(String data){
//        System.out.println("Data from "+data);
//        showMessage(data);
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login init");


    }
}
