import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GradeAssignmentController {

    @FXML
    private GridPane grid;

    @FXML
    public void initialize() {
        ArrayList<Assignment> arr = LearningSystem.assignments;
        System.out.println(arr.size());
        for (int i = 0; i < arr.size(); i++) {
            final int ind = i;
            Label l = new Label();
            l.setText(String.format("%d", arr.get(i).getStudentId()));

            Label c = new Label();
            c.setText(String.format("%s", arr.get(i).getComments()));

            Button btn = new Button();
            btn.setText(String.format("Assignment - %d", arr.get(i).getStudentId()));
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String filePath = arr.get(ind).getFileAddress();
                    try {
                        File file = new File(filePath);
                        if (file.exists()) {
                            Desktop.getDesktop().open(file);
                        }
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
            });
            grid.add(l, 0, i + 1);
            grid.add(c, 1, i + 1);
            grid.add(btn, 2, i + 1);
        }
    }

    @FXML
    void homeAct(ActionEvent event) throws Exception {
        Stage stage = (Stage) grid.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("InstructorDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("LMS");
        stage.setScene(scene);
    }
}
