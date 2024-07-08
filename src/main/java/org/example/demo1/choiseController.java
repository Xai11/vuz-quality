package org.example.demo1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.example.demo1.criteria.DataBase;
import org.example.demo1.criteria.SingleDataBase;
import org.example.demo1.criteria.MoveUniversity;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class choiseController {
    int vuz_id = 0;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private Button submit_but;

    @FXML
    void initialize(){
        MoveUniversity moveVuz = new MoveUniversity();
        DataBase vuz = new DataBase();                      // only connect
        Connection conn = vuz.connect("Test", "AssessmentOfEducation", "root");
        ArrayList<String> totalVuz = moveVuz.getUniversity(conn);

        ObservableList<String> list = FXCollections.observableArrayList("<добавить свой ВУЗ>");
        comb.setValue("<добавить свой ВУЗ>");

        for(int i = 0; i < totalVuz.size(); i++){
            list.add(totalVuz.get(i));
        }
        comb.setItems(list);


        submit_but.setOnAction(actionEvent -> choise());

    }

    private void choise(){
        if (comb.getValue() == "<добавить свой ВУЗ>"){
            submit_but.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("addVuzWindow.fxml"));

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

            MoveUniversity moveVuz = new MoveUniversity();
            DataBase vuz = new DataBase();                      // only connect
            Connection conn = vuz.connect("Test", "AssessmentOfEducation", "root");
            moveVuz.setComb(comb.getValue());

            SingleDataBase singleDB = SingleDataBase.getInstance();
            singleDB.setVuz_id(moveVuz.getIDInputUniversity(conn));

//
//            System.out.println(vuz_id);
//            moveVuz.insertMoreToMore(conn, vuz_id);


            submit_but.getScene().getWindow().hide();
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
        }
    }

}