import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CheckAttendanceController {

    @FXML
    private Label absent;

    @FXML
    private Label percentage;

    @FXML
    private Label present;

    @FXML
    private Label total;

    @FXML
    public void initialize() {
        Student s1 = (Student) LearningSystem.activeUser;
        int nPresent, nAbsent, nTotal;
        double nPercentage = 0;
        nPresent = s1.getNoOfPresents();
        nAbsent = s1.getNoOfAbsents();
        nTotal = nAbsent + nPresent;
        try {
            nPercentage = nPresent / nTotal * 100;
        } catch (ArithmeticException e) {
            System.err.println("Division by zero.");
        }

        absent.setText(String.format("%d", nAbsent));
        present.setText(String.format("%d", nPresent));
        total.setText(String.format("%d", nTotal));
        percentage.setText(String.format("%.2f", nPercentage));
    }

    @FXML
    void homeAct(ActionEvent event) throws Exception {
        Stage stage = (Stage) present.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("StudentDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LMS");
        stage.setScene(scene);
    }
}
