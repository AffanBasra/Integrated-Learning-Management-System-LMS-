import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

public class InstructorRegisterController {

    @FXML
    private TextField emailField;

    @FXML
    private Label errorField;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    void submitForm(ActionEvent event) throws Exception{
        if (passwordField.getText().length() < 8)
        {
            // Check if password is strong
            errorField.setText("Password must be greater than 8 characters.");
        }
        else
        {
            register();

            // Back to Index
            Stage stage = (Stage) emailField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("LMS");
            stage.setScene(scene);
        }
    }

    void register() {
        try {
            // Password hashing
            MessageDigest encoder = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder();
            encoder.update(passwordField.getText().getBytes());
            byte[] br = encoder.digest();
            for (byte b: br){
                sb.append(String.format("%02x", b));
            }

            // Reading Fields
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String password = sb.toString();

            Instructor I1 = new Instructor(id, name, email, password);
            LearningSystem.instructors.add(I1);

            // Writing to File
            String arg = String.format("%s,%s,%s,%s", id, name, email, password);
            try (FileWriter fw = new FileWriter("static/Instructors.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                out.println(arg);
            } catch (IOException e) {
                System.out.println("Error Writing to file.");
            }
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("Error while hashing password.");
        }
    }
}
