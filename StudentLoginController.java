import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentLoginController {

    @FXML
    private Label errorLabel;
    
    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField userIdField;

    @FXML
    void loginAct(ActionEvent event) throws Exception{
        boolean validLogin = false;
        Student s1 = null;
        int enteredUser = Integer.parseInt(userIdField.getText());
        String enteredPassword = "";

        try {
            // Password hashing
            MessageDigest encoder = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder();
            encoder.update(passwordField.getText().getBytes());
            byte[] br = encoder.digest();
            for (byte b: br){
                sb.append(String.format("%02x", b));
            }
            enteredPassword = sb.toString();
        }
        catch(NoSuchAlgorithmException e){
            System.out.println("Error while hashing password.");
        }

        for(Student s: LearningSystem.students){
            if (s.getStudentId() == enteredUser)
            {
                s1 = s;
                break;
            }
        }
        if (s1 == null) {
            errorLabel.setText("Invalid User!");
            return;
        }
        else {
            if(enteredPassword.equals(s1.getHashedPassword())){
                validLogin = true;
            }
            else {
                errorLabel.setText("Invalid Password!");
                return;
            }
        }

        if (validLogin){
            LearningSystem.activeUser = s1;
            Stage stage = (Stage) userIdField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("StudentDashboard.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("LMS");
            stage.setScene(scene);
        }
    }

    @FXML
    void registerAct(ActionEvent event) throws Exception{
        Stage stage = (Stage) userIdField.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("StudentRegister.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LMS");
        stage.setScene(scene);
    }

}
