import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TakeAttendance{

    private VBox layout = new VBox();
    GridPane grid = new GridPane();

    public TakeAttendance() throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene(layout, 600, 400);
        ArrayList<Student> arr = LearningSystem.students;
        stage.setTitle("LMS");

        Label titleLabel = new Label();
        titleLabel.setText("Attendance");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(20);

        Label label1 = new Label();
        label1.setText("CMS");

        Label label2 = new Label();
        label2.setText("Name");

        Label label3 = new Label();
        label3.setText("Present");

        grid.add(label1, 1, 0);
        grid.add(label2, 2, 0);
        grid.add(label3, 3, 0);
        for (int i = 0; i < arr.size(); i++) {
            Label label = new Label();
            label.setText(String.format("%d", arr.get(i).getStudentId()));

            Label labelName = new Label();
            labelName.setText(String.format("%s", arr.get(i).getStudentName()));

            CheckBox cBox = new CheckBox();
            cBox.setId(String.format("%d", i));

            grid.add(label, 1, i + 1);
            grid.add(labelName, 2, i + 1);
            grid.add(cBox, 3, i + 1);
        }
        grid.setAlignment(Pos.CENTER);

        Button submitButton = new Button();
        submitButton.setText("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < arr.size(); i++) {
                    CheckBox cBox = (CheckBox) scene.lookup(String.format("#%d", i));
                    if (cBox.isSelected()){
                        arr.get(i).markPresent();
                    }
                    else {
                        arr.get(i).markAbsent();
                    }
                }
                try {
                    Stage stage = (Stage) layout.getScene().getWindow();
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("InstructorDashboard.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("LMS");
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });;

        layout.getChildren().add(titleLabel);
        layout.getChildren().add(grid);
        layout.getChildren().add(submitButton);
        layout.setAlignment(Pos.CENTER);

        stage.setScene(scene);
        stage.show();
        for (int i = 0; i < arr.size(); i++) {
            System.out.printf("%s's presents are: %d", arr.get(i).getStudentId(), arr.get(i).getNoOfPresents());
            System.out.printf("%s's absents are: %d", arr.get(i).getStudentId(), arr.get(i).getNoOfAbsents());
        }
    }

    public VBox getLayout() {
        return layout;
    }

    
}
