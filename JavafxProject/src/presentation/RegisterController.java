package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import persistence.StaffDAO;
import register.Password;
import register.PasswordFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private Stage stage;

    @FXML
    private AnchorPane registerAnchorPane;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private Button btnRegistration;

    @FXML
    TextField userNameTextfield;

    @FXML
    TextField pinTextfield;

    @FXML
    TextField emailTextfield;

    @FXML
    PasswordField passwordTextfield;

    @FXML
    PasswordField confirmPasswordTextfield;

    @FXML
    Label messageLabel;


    public void btnRegistrationAction(ActionEvent event) {
        System.out.println("Registration Pressed");
        String userName = userNameTextfield.getText();
        String pass1 = passwordTextfield.getText();
        String pass2 = confirmPasswordTextfield.getText();
        String pin = pinTextfield.getText();
        String UserEmail = emailTextfield.getText();
        showMessage("");
        confirmPassword(userName,pass1, pass2, pin, UserEmail);
//            persistence.StaffDAO.AddUser(userName, pass1, pin, UserEmail);
//        }
    }

    private void confirmPassword(String UserName,String pass1, String pass2, String pin, String UserEmail){
        //
        //
        //        // PWDSAME PWDLENGTH PWDDIGIT PWDCOMPROMISED
        //        //use of factory pattern
        PasswordFactory pf = new PasswordFactory();
        Password pwd1 = pf.getTest("PWDSAME");
        Password pwd2 = pf.getTest("PWDLENGTH");
        Password pwd3 = pf.getTest("PWDCOMPROMISED");
        Password pwd4 = pf.getTest("PWDDIGIT");
        List<Password> list = new ArrayList<Password>();
        list.add(pwd1);
        list.add(pwd2);
        list.add(pwd4);
        String message = "";

        for(Password p :list){
            if(p.checkPassword(pass1,pass2)){
                message =p.getMessage();
                System.out.println(" message "+message);
                showMessage(message);
                return;
            }
//          if (pwd3.checkPassword (pass1, pass2 )==true){
//                StaffDAO.AddUser(UserName, pass1, pin, UserEmail );
//                System.out.println("ran");
//            }
            message = "ok";
            showMessage(message);
        }
        if(!pwd3.checkPassword(pass1,pass2)){
            StaffDAO.AddUser(UserName,pass1,pin,UserEmail);
            System.out.println("should be added");
        }
    }

    @FXML
    private Button btntnCancelRegistration;


    public void btntnCancelRegistrationAction(ActionEvent event) {
        goToLoginPage();
        closeWindow();
    }


    public void returnToLogin() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();

            loginController.transferMessage("Hello World ?");

            //Show Login in new window
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.show();


        } catch (Exception ex) {

        }

    }

    public void goToLoginPage() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }

    }

    public void showMessage(String msg) {
        messageLabel.setText(msg);
    }

    public void resetPassword() {
        passwordTextfield.setText("");
        confirmPasswordTextfield.setText("");

    }

    public void closeWindow() {
        Stage stage = (Stage) registerAnchorPane.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Init Resister ");


    }
}
