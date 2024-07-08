package org.example.demo1.criteria.faculties;

import org.example.demo1.criteria.DataBase;
import org.example.demo1.criteria.MoveUniversity;
import org.example.demo1.criteria.SingleDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MoveFaculties {
    public void addFacultiesInPostgres(int countFaculties){
        Statement statement;
        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect("Test", "AssessmentOfEducation", "root");
        int intFaculties = selectDataBaseFaculties(conn) + countFaculties;
        System.out.println(intFaculties);
        SingleDataBase singleDataBase = SingleDataBase.getInstance();
        try {
            String queryInsert = "UPDATE university SET vuz_electives = " + intFaculties + " WHERE vuz_id = " + singleDataBase.getVuz_id() +";";
            statement = conn.createStatement();
            statement.executeUpdate(queryInsert);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public int selectDataBaseFaculties(Connection conn){
        Statement statement;
        int bufferFaculties = 0;
        ResultSet resultSet = null;
        try{
            SingleDataBase singleDataBase = SingleDataBase.getInstance();
            String querySelect = "SELECT vuz_electives FROM university WHERE vuz_id = " + singleDataBase.getVuz_id() + ";";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);

            while(resultSet.next()){
                bufferFaculties = resultSet.getInt("vuz_electives");
                break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return bufferFaculties;
    }

}
