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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.example.demo1.criteria.SingleDataBase;
import org.example.demo1.criteria.specialization.MovePerson;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class TeacherController {

    @FXML
    private ComboBox<String> Tdegree;

    @FXML
    private TextField Texp;

    @FXML
    private TextField Tname;


    @FXML
    private ComboBox<String> Tprof;

    @FXML
    private TextField Tprogress;

    @FXML
    private TextField Tprohours;

    @FXML
    private ComboBox<String> Trank;

    @FXML
    private TextField Tsubj;

    @FXML
    private TableColumn<Person, Integer> degree;

    @FXML
    private TableColumn<Person, Integer> exp;

    @FXML
    private Button insert_but;

    @FXML
    private Button end_but;

    @FXML
    private TableColumn<Person, String> name;

    @FXML
    private TableColumn<Person, Integer> prof;

    @FXML
    private TableColumn<Person, String> progress;

    @FXML
    private TableColumn<Person, Integer> prohours;

    @FXML
    private TableColumn<Person, Integer> rank;

    @FXML
    private TableColumn<Person, String> subj;
    private MovePerson movePerson = new MovePerson();
    private SingleDataBase singleDB = SingleDataBase.getInstance();
    private Connection conn = movePerson.connect("Test", "AssessmentOfEducation", "root");

//    ObservableList<Person> initialData(){
//
////        Person p1 = new Person("Мартын","c++","2 - ебал вас в рот",2,"Пидор","Яйцо",280,"Соснул хуйца, пошел на ВМ");
////        return FXCollections.<Person> observableArrayList(p1);
//    }
    @FXML
    private void but(){

        if (!Tname.getText().isEmpty() || !Tsubj.getText().isEmpty()  || !Texp.getText().isEmpty() || !Tprohours.getText().isEmpty() || !Tprogress.getText().isEmpty()){
            Person npers = new Person(Tname.getText(),Tsubj.getText(),Tprof.getValue(),Integer.parseInt(Texp.getText()), Tdegree.getValue(), Trank.getValue(), Integer.parseInt(Tprohours.getText()) ,Tprogress.getText());
            table.getItems().add(npers);
            singleDB.setTeacher_id(movePerson.getIDInputPerson(conn, npers));
            //Tprof.clear();
            //Tdegree.clear();
            Texp.clear();
            Tprohours.clear();
            //Trank.clear();
            Tname.clear();
            Tprogress.clear();
            Tsubj.clear();

        }
    }

    private void next_wind(){
        end_but.getScene().getWindow().hide();

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

    @FXML
    private TableView<Person> table;

    @FXML
    void initialize() {
        ObservableList<String> listDegree = FXCollections.observableArrayList("нет", "кандидат наук", "доктор наук");
        Tdegree.setItems(listDegree);

        ObservableList<String> listRank = FXCollections.observableArrayList("нет", "доцент", "профессор");
        Trank.setItems(listRank);

        ObservableList<String> listProf = FXCollections.observableArrayList("непрофильный", "частично", "профильный");
        Tprof.setItems(listProf);

        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        subj.setCellValueFactory(new PropertyValueFactory<Person, String>("subject"));
        prof.setCellValueFactory(new PropertyValueFactory<Person, Integer>("prof"));

        exp.setCellValueFactory(new PropertyValueFactory<Person, Integer>("exp"));
        degree.setCellValueFactory(new PropertyValueFactory<Person, Integer>("degr"));
        rank.setCellValueFactory(new PropertyValueFactory<Person, Integer>("rank"));
        prohours.setCellValueFactory(new PropertyValueFactory<Person, Integer>("hours"));
        progress.setCellValueFactory(new PropertyValueFactory<Person, String>("progr"));

//        table.setItems(initialData());

        insert_but.setOnAction(actionEvent -> but());


        end_but.setOnAction(actionEvent -> next_wind());

    }




}
