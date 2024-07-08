package org.example.demo1.Main;

import org.example.demo1.criteria.SingleDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CalculateTeacherExp {
    private SingleDataBase singleDataBase = SingleDataBase.getInstance();
    public int countSelectDataBase(Connection conn){
        Statement statement;
        ResultSet resultSet = null;
        int count = 0;
        try {
            String query = "SELECT COUNT (*) FROM university_specialization_teaching_staff WHERE university_id = 13;"; //+ singleDataBase.getVuz_id();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return count;
    }
    public float scoreTeacherExp(Connection conn){
        float score = 0;
        Statement statement;
        ResultSet resultSet = null;
        int tExp = 0;
        int tDegree = 0;
        int tRank = 0;
        int tAchiv = 0;
        int tProfile = 0;
        int tHours = 0;

        int tTotalHours = 0;
        int tTotalProfileHours = 0;

        float tHoursScore = 0;
        float tExpScore = 0;
        float tAchivScore = 0;

        String querySelect = "SELECT teaching_staff.teacher_exp, teaching_staff.teacher_degree, teaching_staff.teacher_rank, teaching_staff.teacher_achievements, teaching_staff.teacher_profile_subject, teaching_staff.teacher_hours FROM university_specialization_teaching_staff " +
                "JOIN university ON university_specialization_teaching_staff.university_id = university.vuz_id " +
                "JOIN specialization ON university_specialization_teaching_staff.specialization_id = specialization.spec_id " +
                "JOIN teaching_staff ON university_specialization_teaching_staff.teaching_staff_id = teaching_staff.teacher_id " +
                "WHERE vuz_id = " + singleDataBase.getVuz_id() + ";";
        try{
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while(resultSet.next()){
                tExp = resultSet.getInt("teacher_exp");
                tDegree = resultSet.getInt("teacher_degree");
                tRank = resultSet.getInt("teacher_rank");
                tAchiv = resultSet.getInt("teacher_achievements");
                tProfile = resultSet.getInt("teacher_profile_subject");
                tHours = resultSet.getInt("teacher_hours");


                if(tExp > 20){
                    tExpScore = 10;
                } else {
                    tExpScore = tExp * 0.5f;
                }
                if(tAchiv > 20){
                    tAchivScore = 10;
                } else {
                    tAchivScore = tAchiv * 0.5f;
                }
                if(tProfile == 2){
                    tTotalProfileHours += tHours;
                }
                tTotalHours += tHours;

                score += (tExpScore + tAchivScore + 5*tRank + 5*tDegree) * tProfile / 4;
            }
        } catch (Exception e){
            System.out.println(e);
        }

        tHoursScore = (tTotalProfileHours * 1.0f / tTotalHours)*100.0f;

        int count = countSelectDataBase(conn);

        score /= count;
        score += tHoursScore*0.1f;
        System.out.println(score);
        return score;

    }


}
