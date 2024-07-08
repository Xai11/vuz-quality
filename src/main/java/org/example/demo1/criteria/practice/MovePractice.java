package org.example.demo1.criteria.practice;

import org.example.demo1.criteria.DataBase;
import org.example.demo1.criteria.IDataBase;
import org.example.demo1.criteria.SingleDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MovePractice {
    public void addPracticeInPostgres(int countPractice){
        Statement statement;
        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect("Test", "AssessmentOfEducation", "root");
        int intPractice = selectDataBasePractice(conn) + countPractice;
        System.out.println(intPractice);
        SingleDataBase singleDataBase = SingleDataBase.getInstance();
        try {
            String queryInsert = "UPDATE university SET vuz_practice = " + intPractice + " WHERE vuz_id = " + singleDataBase.getVuz_id() +";";
            statement = conn.createStatement();
            statement.executeUpdate(queryInsert);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public int selectDataBasePractice(Connection conn){
        Statement statement;
        int bufferPractice = 0;
        ResultSet resultSet = null;
        try{
            SingleDataBase singleDataBase = SingleDataBase.getInstance();
            String querySelect = "SELECT vuz_practice FROM university WHERE vuz_id = " + singleDataBase.getVuz_id() + ";";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);

            while(resultSet.next()){
                bufferPractice = resultSet.getInt("vuz_practice");
                break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return bufferPractice;
    }
}
