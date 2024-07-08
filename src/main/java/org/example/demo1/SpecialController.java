package org.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.example.demo1.criteria.DataBase;
import org.example.demo1.criteria.SingleDataBase;
import org.example.demo1.criteria.specialization.MoveSpecialization;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class SpecialController {
    int spec_id = 0;
    @FXML
    private ComboBox<String> comb;

    @FXML
    private Button submit_but;

    @FXML
    void initialize(){
        MoveSpecialization moveSpec = new MoveSpecialization();
        DataBase spec = new DataBase();                      // only connect
        Connection conn = spec.connect("Test", "AssessmentOfEducation", "root");
        ArrayList<String> totalSpec = moveSpec.getSpecialization(conn);

        ObservableList<String> list = FXCollections.observableArrayList("<добавить специальность>");
        comb.setValue("<добавить специальность>");

        for(int i = 0; i < totalSpec.size(); i++){
            list.add(totalSpec.get(i));
        }
        comb.setItems(list);

        submit_but.setOnAction(actionEvent -> choise());
    }

    private void choise(){
        if (comb.getValue() == "<добавить специальность>"){
            submit_but.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddSpecWindow.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            MoveSpecialization moveSpec = new MoveSpecialization();
            DataBase spec = new DataBase();                      // only connect
            Connection conn = spec.connect("Test", "AssessmentOfEducation", "root");

            moveSpec.setComb(comb.getValue());

            SingleDataBase singleDB = SingleDataBase.getInstance();
            singleDB.setSpec_id(moveSpec.getIDInputSpecialization(conn));

            submit_but.getScene().getWindow().hide();
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
        }
    }
}
