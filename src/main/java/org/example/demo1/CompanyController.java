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
import org.example.demo1.criteria.faculties.MoveFaculties;
import org.example.demo1.criteria.practice.MovePractice;

import java.io.IOException;

public class CompanyController {

    @FXML
    private TextField Tname;

    @FXML
    private Button end_but;

    @FXML
    private Button back_but;

    @FXML
    private Button insert_but;

    @FXML
    private TableColumn<Company, String> name;

    @FXML
    private TableView<Company> table;
    private int countPractice = 0;

//    ObservableList<Company> initialData(){
//        Company comp1 = new Company("Мартынов СИПИ");
//        return FXCollections.<Company> observableArrayList(comp1);}

    @FXML
    void initialize(){
        name.setCellValueFactory(new PropertyValueFactory<Company,String>("name"));

//        table.setItems(initialData());

        insert_but.setOnAction(actionEvent -> but());

        end_but.setOnAction(actionEvent -> next_wind());

        back_but.setOnAction(actionEvent -> back());

    }

    private void but(){
        if (!Tname.getText().isEmpty()){
            Company ncomp = new Company(Tname.getText());

            countPractice++;

            table.getItems().add(ncomp);
            Tname.clear();
        }
    }
    private void back(){
        back_but.getScene().getWindow().hide();
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

    private void next_wind(){
        MovePractice movePractice = new MovePractice();
        movePractice.addPracticeInPostgres(countPractice);
        end_but.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FacultWindow.fxml"));

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
