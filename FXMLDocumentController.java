package quizapp;

import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Button EnterButton;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code here
    }    

    @FXML
    private void handleEnterButton(ActionEvent event) {
        try {
            if (Username.getText().equals("Admin123") && Password.getText().equals("Seecs@123")) {
                Stage thisStage = (Stage) EnterButton.getScene().getWindow();
                thisStage.close();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("QuizScreen.fxml")));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Error Message");
                errorAlert.setHeaderText(null); // Optional: remove header
                errorAlert.setContentText("Error! Invalid Username or Password.");
                errorAlert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
