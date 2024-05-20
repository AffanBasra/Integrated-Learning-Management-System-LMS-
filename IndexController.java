import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class IndexController {

    @FXML
    private Button InstuctorButton;

    @FXML
    private Button StudentButton;

    @FXML
    private Label WelcomLabel;
    
    @FXML
    void openStudentPage(ActionEvent event) throws Exception{
        Stage stage = (Stage) InstuctorButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LMS");
        stage.setScene(scene);
    }

    @FXML
    void openInstructorPage(ActionEvent event) throws Exception{
        Stage stage = (Stage) InstuctorButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("InstructorLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
    }
}
