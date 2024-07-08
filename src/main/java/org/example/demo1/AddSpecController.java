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
import org.example.demo1.criteria.specialization.MoveSpecialization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class AddSpecController {

    @FXML
    private TextField new_spec;

    @FXML
    private Button new_spec_but;

    @FXML
    void initialize(){

        new_spec_but.setOnAction(actionEvent -> {
            MoveSpecialization moveSpec = new MoveSpecialization();
            DataBase spec = new DataBase();                      // only connect
            Connection conn = spec.connect("Test", "AssessmentOfEducation", "root");
            addSpecInPostgres(conn);
            newDeportID(conn);

            new_spec_but.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PrepodsWindow.fxml"));

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

    private void addSpecInPostgres(Connection conn){
        Statement statement;
        try {
            String queryInsert = "INSERT INTO specialization (spec_name) VALUES ('" + new_spec.getText() + "');";
            statement = conn.createStatement();
            statement.executeUpdate(queryInsert);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void newDeportID (Connection conn){
        MoveSpecialization moveSpec = new MoveSpecialization();
        SingleDataBase singleBD = SingleDataBase.getInstance();
        singleBD.setSpec_id(moveSpec.getIDInputSpecialization(conn,new_spec.getText()));
    }

}



