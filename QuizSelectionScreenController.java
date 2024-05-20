package quizapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class QuizSelectionScreenController {

    @FXML
    private VBox quizVBox;
    

    public void initialize() {
        // This method will be called after the FXML is loaded
    }

    public void setQuizIds(List<Integer> quizIds) {
        for (Integer quizId : quizIds) {
            Button quizButton = new Button("Quiz " + quizId);
            quizButton.setStyle("-fx-background-color: #74B9FF; -fx-background-radius: 32px; -fx-border-radius: 32px;");
            quizButton.setTextFill(javafx.scene.paint.Color.WHITE);
            quizButton.setFont(new javafx.scene.text.Font("Arial Rounded MT Bold", 14));
            quizButton.setOnAction(event -> {
               // System.out.println("Selected Quiz ID: " + quizId);
                try{
            Stage thisStage=(Stage) quizButton.getScene().getWindow();
            thisStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuizContentScreen.fxml"));
            Scene scene = new Scene(loader.load());
            QuizContentScreenController controller = loader.getController();
            controller.initializeQuiz(quizId);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                // Handle quiz selection
            });
            VBox.setMargin(quizButton, new Insets(5.0));
            quizVBox.getChildren().add(quizButton);
        }
    }
}
