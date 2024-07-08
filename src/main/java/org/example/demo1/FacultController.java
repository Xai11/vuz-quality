package org.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.demo1.criteria.DataBase;
import org.example.demo1.criteria.SingleDataBase;
import org.example.demo1.criteria.MoveDataBase;
import org.example.demo1.criteria.faculties.MoveFaculties;

import java.io.IOException;
import java.sql.Connection;

public class FacultController {

    @FXML
    private TextField Tname;

    @FXML
    private Button end_but;

    @FXML
    private Button back_but;

    @FXML
    private Button insert_but;
    private int countFaculties = 0;

    @FXML
    private TableColumn<Facults, String> name;

    @FXML
    private TableView<Facults> table;

//    ObservableList<Facults> initialData(){
//        Facults fac1 = new Facults("Факультет хуйни ебаной");
//        return FXCollections.<Facults> observableArrayList(fac1);}

    @FXML
    void initialize(){
        name.setCellValueFactory(new PropertyValueFactory<Facults,String>("name"));

//        table.setItems(initialData());

        insert_but.setOnAction(actionEvent -> but());

        end_but.setOnAction(actionEvent -> next_wind());
        back_but.setOnAction(actionEvent -> back());

        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect("Test", "AssessmentOfEducation", "root");
        MoveDataBase moveDB = new MoveDataBase();

        SingleDataBase singleDB = SingleDataBase.getInstance();
        System.out.println("+" + singleDB.getVuz_id());
        System.out.println("+" + singleDB.getSpec_id());
        for(int i = 0; i < singleDB.getTeacher_id().size(); i++){
            System.out.println(singleDB.getTeacher_id().get(i));
        }

    }

    private void but(){
        if (!Tname.getText().isEmpty()){
            Facults nfac = new Facults(Tname.getText());
            countFaculties++;

            table.getItems().add(nfac);
            Tname.clear();
        }
    }

    private void back(){
        back_but.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CompanyWindow.fxml"));

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

    private void next_wind(){
        MoveFaculties moveFaculties = new MoveFaculties();
        moveFaculties.addFacultiesInPostgres(countFaculties);
        MoveDataBase moveDataBase = new MoveDataBase();
        moveDataBase.insertMoreToMore();
        end_but.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MyVuzWindow.fxml"));

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
