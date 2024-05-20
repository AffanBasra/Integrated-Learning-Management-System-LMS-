import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InstructorDashboardController {

    @FXML
    private Button homeButton;

    @FXML
    private Button addQuizButton;

    @FXML
    private Button attendanceButton;

    @FXML
    private Button dataButton;

    @FXML
    private Button gradeAssignmentButton;

    @FXML
    private Button gradeQuizButton;

    @FXML
    void homeAct(ActionEvent event) throws Exception {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LMS");
        stage.setScene(scene);
    }

    @FXML
    void addQuizAct(ActionEvent event) {

    }

    @FXML
    void attendanceAct(ActionEvent event) throws Exception {
        TakeAttendance takeAttendance = new TakeAttendance();
        VBox root = takeAttendance.getLayout();
        Stage stage = (Stage) attendanceButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    @FXML
    void dataAct(ActionEvent event) {

    }

    @FXML
    void gradeAssignmentAct(ActionEvent event) throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("GradeAssignment.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LMS");
        stage.setScene(scene);
    }

    @FXML
    void gradeQuizAct(ActionEvent event) {

    }

}
