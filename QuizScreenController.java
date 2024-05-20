/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class QuizScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button subject1, subject2, subject3;
     ArrayList<Integer> quizIDs; // Declare quizIds at the class level

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    public void subjectClicked(ActionEvent e){
       Button selectedButton= (Button)e.getSource();
       String selectedSubject =selectedButton.getText();
       System.out.println(selectedSubject);
       quizIDs=fetchQuizzes(selectedSubject);
       
       Stage stage=(Stage)selectedButton.getScene().getWindow();
       stage.close();
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuizSelectionScreen.fxml"));
            Parent root = loader.load();

            QuizSelectionScreenController controller = loader.getController();
            controller.setQuizIds(quizIDs);

            Stage quizStage = new Stage();
            quizStage.setScene(new Scene(root));
            quizStage.setTitle("Select a Quiz");
            quizStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

     private ArrayList<Integer> fetchQuizzes(String selectedSubject) {
        ArrayList<Integer> quizIds = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/TimedQuizDB" ;
        String username = "root";
        String password = "zoinks!!12";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

            String query = "SELECT quiz_id FROM quizzes WHERE subject_id IN " +
                           "(SELECT subject_id FROM quizsubjects WHERE subject_name = '" + selectedSubject + "')";

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int currentID=rs.getInt("quiz_id");
                quizIds.add(currentID);
                System.out.println(currentID);
                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return quizIds;
    }
     


}