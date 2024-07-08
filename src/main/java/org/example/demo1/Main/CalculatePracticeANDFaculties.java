package org.example.demo1.Main;

import org.example.demo1.criteria.SingleDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CalculatePracticeANDFaculties {
    public float scorePracticeANDFaculties(Connection conn){
        SingleDataBase singleDataBase = SingleDataBase.getInstance();
        Statement statement;
        ResultSet resultSet = null;
        String querySelect = "SELECT vuz_id, vuz_practice, vuz_electives FROM university WHERE vuz_id = " + singleDataBase.getVuz_id() + ";";

        int intPractice = 0;
        float scorePractice = 0;

        float scoreElectives = 0;
        int intElectives = 0;

        float totalScore = 0;
        try{

            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while(resultSet.next()){
                intPractice = resultSet.getInt("vuz_practice");
                intElectives = resultSet.getInt("vuz_electives");
                if(intPractice > 40){
                    scorePractice = 20.0f;
                } else {
                    scorePractice = intPractice * 0.5f;
                }
                if(intElectives > 50){
                    scoreElectives = 10.0f;
                } else {
                    scoreElectives = intElectives * 0.2f;
                }
            }
            totalScore += scorePractice + scoreElectives;

        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println(totalScore);
        return totalScore;
    }
}
