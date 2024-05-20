import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SubmitAssignmentController {

    private String path;
    @FXML
    private Button browseButton;

    @FXML
    private TextArea comments;

    @FXML
    private Button submitButton;

    @FXML
    void submitAssignment(ActionEvent event) throws IOException {
        String commentsText = comments.getText();
        int sId = LearningSystem.activeUser.UserID;
        Assignment a1 = new Assignment(sId, commentsText, path);
        LearningSystem.assignments.add(a1);

        // Writing to File
        String arg = String.format("%d,%s,%s", sId, commentsText, path);
        try (FileWriter fw = new FileWriter("static/Assignments.csv", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(arg);
        } catch (IOException e) {
            System.out.println("Error Writing to file.");
        }

        Stage stage = (Stage) submitButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("LMS");
            stage.setScene(scene);
    }

    @FXML
    void uploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Upload");
        File selectedFile = fileChooser.showOpenDialog(null);
        path = selectedFile.getAbsolutePath();
    }

}
