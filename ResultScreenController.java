package quizapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class ResultScreenController implements Initializable {

    @FXML 
    private Label MarksObt;
    
    @FXML 
    private Label MarksLabel;
    
    @FXML
    private Label CorrectAnswers;
    
    @FXML
    private Label WrongAnswers;
    
    @FXML
    private ProgressIndicator CorrectProgressIndicator;
    
    @FXML
    private ProgressIndicator IncorrectProgressIndicator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code if needed
    }
    
    public void setResults(int quizID, int correct_answers, int wrong_answers, int total_questions, int counter) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TimedQuizDB", "root", "zoinks!!12");
            String query = "INSERT INTO results (quiz_id, total_questions, attempted_questions, correct_answers, wrong_answers) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, quizID);
            pstmt.setInt(2, total_questions);
            pstmt.setInt(3, counter);
            pstmt.setInt(4, correct_answers);
            pstmt.setInt(5, wrong_answers);
            pstmt.executeUpdate();
            
            MarksObt.setText("Marks Obtained: " + correct_answers + "/" + total_questions);
            CorrectAnswers.setText("Correct Answer(s): " + correct_answers);
            WrongAnswers.setText("Wrong Answer(s): " + wrong_answers);
            MarksLabel.setText(correct_answers + "/" + total_questions);

            // Setting progress indicators
            float correctProgress = (float) correct_answers / total_questions;
            CorrectProgressIndicator.setProgress(correctProgress);

            float wrongProgress = (float) wrong_answers / total_questions;
            IncorrectProgressIndicator.setProgress(wrongProgress);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
