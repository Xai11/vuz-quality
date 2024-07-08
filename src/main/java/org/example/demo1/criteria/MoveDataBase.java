package org.example.demo1.criteria;

import java.sql.Connection;
import java.sql.Statement;

public class MoveDataBase {
    public void insertMoreToMore(){
        Statement statement;
        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect("Test", "AssessmentOfEducation", "root");
        SingleDataBase singleDataBase = SingleDataBase.getInstance();

        try{
            for(int i = 0; i < singleDataBase.getTeacher_id().size(); i++){
                String queryInsert = "INSERT INTO university_specialization_teaching_staff VALUES (" + singleDataBase.getVuz_id() + "," + singleDataBase.getSpec_id() + "," + singleDataBase.getTeacher_id().get(i) + ");";
                statement = conn.createStatement();
                statement.executeUpdate(queryInsert);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
