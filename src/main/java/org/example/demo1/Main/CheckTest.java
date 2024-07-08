package org.example.demo1.Main;

import org.example.demo1.criteria.DataBase;

import java.sql.Connection;

public class CheckTest {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        Connection conn = dataBase.connect("Test", "AssessmentOfEducation", "root");

        CalculateTeacherExp calculateTeacherExp = new CalculateTeacherExp();


        CalculatePracticeANDFaculties calculatePracticeANDFaculties = new CalculatePracticeANDFaculties();


        float totalScore = (calculatePracticeANDFaculties.scorePracticeANDFaculties(conn) + calculateTeacherExp.scoreTeacherExp(conn))/60 * 5;
        System.out.println(totalScore);
    }
}
