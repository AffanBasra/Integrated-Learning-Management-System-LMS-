package quizapp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class QuizContentScreenController implements Initializable {
    int quizID;
    @FXML 
    public Label timerLabel;
    @FXML
    public Label Question;
    @FXML
    public Button Opt1, Opt2, Opt3, Opt4;
    
    int counter = 0;
    int quizID2=0;
    int correct_answers = 0;
    int wrong_answers = 0;
    int total_questions = 0; // Make total_questions static for access in the result screen
    private ResultSet rs;
    private ResultSet rs2;
    
    private Timeline timeline;
    
    public QuizContentScreenController() {
        // Default constructor required for FXML
    }
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code if needed
    }
    
    public void initializeQuiz(int quizID) {
        quizID2=quizID;
        System.out.println("The selected QUIZ ID: " + quizID);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TimedQuizDB", "root", "zoinks!!12");
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
           
            rs = stmt1.executeQuery("select * from quiz_content where quiz_id=" + quizID);
            rs2 = stmt2.executeQuery("select count(*) from quiz_content where quiz_id=" + quizID);
            if (rs2.next()) {
                total_questions = rs2.getInt(1);
            }
            
            if (rs.next()) {
                startTimer();
                loadQuestions();
            } else {
                System.out.println("No questions found for the given quiz ID.");
                Stage thisStage = (Stage) timerLabel.getScene().getWindow();
                thisStage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML 
    public void OptionSelected(ActionEvent e) {
        try {
            Button selectedButton = (Button) e.getSource();
            String user_answer = selectedButton.getText();
            String correct_answer = rs.getString("Correct_Option");
            if (user_answer.equalsIgnoreCase(correct_answer)) {
                correct_answers++;
            } else {
                wrong_answers++;
            }
            counter++;
            if (rs.next()) {
                loadQuestions();
            } else {
                System.out.println("Total Questions in quiz: " + total_questions);
                System.out.println("Total Questions Attempted: " + counter);
                System.out.println("Correct Answers: " + correct_answers);
                System.out.println("Wrong Answers: " + wrong_answers);
                stopTimer();  // Stop timer and close the stage
                openResultScreen();  // Open the result screen
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public void loadQuestions() throws SQLException {
        String current_mcq = rs.getString("mcq_text"); // Replace with actual column name
        Question.setText(current_mcq);
        Opt1.setText(rs.getString("Option_A")); // Replace with actual column names
        Opt2.setText(rs.getString("Option_B"));
        Opt3.setText(rs.getString("Option_C"));
        Opt4.setText(rs.getString("Option_D"));
    }
    
    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int timeRemaining = Integer.parseInt(timerLabel.getText());
            if (timeRemaining > 0) {
                timerLabel.setText(String.valueOf(timeRemaining - 1));
            } else {
                stopTimer();
                System.out.println("Time's up! Quiz finished.");
                System.out.println("Total Questions in quiz: " + total_questions);
                System.out.println("Total Questions Attempted: " + counter);
                System.out.println("Correct Answers: " + correct_answers);
                System.out.println("Wrong Answers: " + wrong_answers);
                openResultScreen();  // Open the result screen
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
            Stage thisStage = (Stage) timerLabel.getScene().getWindow();
            thisStage.close();
        }
    }
    
    private void openResultScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultScreen.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            // Pass data to ResultScreenController
            ResultScreenController controller = loader.getController();
            controller.setResults(quizID2,correct_answers, wrong_answers, total_questions,counter);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
