package org.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import org.example.demo1.Main.CalculatePracticeANDFaculties;
import org.example.demo1.Main.CalculateTeacherExp;
import org.example.demo1.criteria.DataBase;
import org.example.demo1.criteria.SingleDataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyVuzController {

    @FXML
    private Button back_but;

    @FXML
    private TableColumn<MyVuz, String> name;
    @FXML
    private TableColumn<MyVuz, Float> rank;

    @FXML
    private TableView<MyVuz> table;


    @FXML
    private Button next_but;
//    ObservableList<Company> initialData(){
//        Company comp1 = new Company("Мартынов СИПИ");
//        return FXCollections.<Company> observableArrayList(comp1);}


    ObservableList<MyVuz> initialData(){
        SingleDataBase singleDataBase = SingleDataBase.getInstance();
        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect("Test", "AssessmentOfEducation", "root");
        Statement statement;
        ResultSet resultSet = null;
        String vuz_name = "";
        try{
            String query = "SELECT vuz_name FROM university WHERE vuz_id = " + singleDataBase.getVuz_id() + ";";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                vuz_name = resultSet.getString("vuz_name");
            }

        } catch (Exception e){
            System.out.println(e);

        }
        MyVuz vuz1 = new MyVuz(vuz_name);

        CalculateTeacherExp calculateTeacherExp = new CalculateTeacherExp();
        CalculatePracticeANDFaculties calculatePracticeANDFaculties = new CalculatePracticeANDFaculties();
        float totalScore = (calculatePracticeANDFaculties.scorePracticeANDFaculties(conn) + calculateTeacherExp.scoreTeacherExp(conn))/60 * 5;
        float temp2 = Math.round(totalScore * 100.0f)/100.0f;
        vuz1.setRank(temp2);    //устанавливаем наш ранг через перемеренную
        return FXCollections.<MyVuz> observableArrayList(vuz1);}

    @FXML
    void initialize(){


        name.setCellValueFactory(new PropertyValueFactory<MyVuz,String>("name"));
        rank.setCellValueFactory(new PropertyValueFactory<MyVuz,Float>("rank"));
//
        table.setItems(initialData());
//
//        insert_but.setOnAction(actionEvent -> but());

        next_but.setOnAction(actionEvent -> next_wind());

        back_but.setOnAction(actionEvent -> back());

    }



//    private void but(){
//        if (!Tname.getText().isEmpty()){
//            Company ncomp = new Company(Tname.getText());
//
//            table.getItems().add(ncomp);
//            Tname.clear();
//        }
//    }
    private void back(){
        back_but.getScene().getWindow().hide();
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

    private void next_wind(){

        next_but.getScene().getWindow().hide();

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


