import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StudentDashboardController {

    @FXML
    private Button assignmentButton;

    @FXML
    private Button attendanceButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button quizButton;

    @FXML
    void assignmentAct(ActionEvent event) throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SubmitAssignment.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LMS");
        stage.setScene(scene);
    }

    @FXML
    void attendanceAct(ActionEvent event) throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("CheckAttendance.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LMS");
        stage.setScene(scene);
    }

    @FXML
    void homeAct(ActionEvent event) throws Exception{
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LMS");
        stage.setScene(scene);
    }

    @FXML
    void quizAct(ActionEvent event) {

    }

}
