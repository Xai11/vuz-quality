package org.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo1.criteria.DataBase;
import org.example.demo1.criteria.SingleDataBase;
import org.example.demo1.criteria.MoveUniversity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class addVuzController {

    @FXML
    private TextField new_vuz;

    public TextField getNew_vuz() {
        return new_vuz;
    }

    @FXML
    private Button new_vuz_but;

    @FXML
    void initialize(){

        new_vuz_but.setOnAction(actionEvent -> {
            MoveUniversity moveVuz = new MoveUniversity();
            DataBase vuz = new DataBase();                      // only connect
            Connection conn = vuz.connect("Test", "AssessmentOfEducation", "root");
            addVuzInPostgres(conn);
            newDeportID(conn);
            new_vuz_but.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SpecializationVuzWindow.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

    }

    private void addVuzInPostgres(Connection conn){
        Statement statement;
        try {
            String queryInsert = "INSERT INTO university (vuz_name) VALUES ('" + new_vuz.getText() + "');";
            statement = conn.createStatement();
            statement.executeUpdate(queryInsert);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void newDeportID (Connection conn){
        MoveUniversity moveVuz = new MoveUniversity();
        SingleDataBase singleBD = SingleDataBase.getInstance();
        singleBD.setVuz_id(moveVuz.getIDInputUniversity(conn,new_vuz.getText()));
    }



}
