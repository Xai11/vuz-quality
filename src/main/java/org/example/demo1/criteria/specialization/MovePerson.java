package org.example.demo1.criteria.specialization;

import org.example.demo1.Person;
import org.example.demo1.criteria.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MovePerson extends DataBase {
    private String comb;
    public void setComb(String comb){
        this.comb = comb;
    }
    public String getComb(){
        return comb;
    }
    public ArrayList<String> getPerson(Connection conn){
        Statement statement;
        ResultSet resultSet = null;
        ArrayList<String> bufferPerson = new ArrayList<String>();
        try{
            String querySelect = "SELECT spec_name FROM specialization;";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while(resultSet.next()) {
                bufferPerson.add(resultSet.getString("spec_name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return bufferPerson;
    }
    public int getIDInputPerson(Connection conn, Person person){
        Statement statement;
        ResultSet resultSet = null;
        int idPerson = 0;
        try{
            addPersonInPostgres(conn, person);
            String querySelect = "SELECT teacher_id FROM teaching_staff WHERE teacher_name = '" + person.getName()
                    + "' AND teacher_subject = '" + person.getSubject() + "' AND teacher_hours = '" + person.getHours() + "';";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while (resultSet.next()){
                idPerson = resultSet.getInt("teacher_id");
                break;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return idPerson;
    }
    private void addPersonInPostgres(Connection conn, Person person){
        Statement statement;
        try {
            String queryInsert = "INSERT INTO teaching_staff (teacher_name, teacher_exp," +
                    " teacher_profile_subject, teacher_subject, teacher_degree, teacher_rank, teacher_achievements," +
                    " teacher_hours ) VALUES ('" + person.getName() + "','" + person.getExp() + "','" + scoreProfile(person) + "','"
                    + person.getSubject() + "','" + scoreDegree(person) + "','" + scoreRank(person) + "','"
                    + countAchievements(person) + "','" + person.getHours() + "');";
            statement = conn.createStatement();
            statement.executeUpdate(queryInsert);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private int countAchievements(Person person){
        int count = 0;
        if(person.getProgr().isEmpty()){
            return count;
        } else {
            for(int i = 0; i < person.getProgr().length(); i++){
                if(person.getProgr().charAt(i) == ','){
                    count++;
                }
            }
            count++;
            return count;

        }
    }
    public int scoreDegree(Person person){
        if(person.getDegr().equals("нет")){
            return 0;
        } else if(person.getDegr().equals("кандидат наук")){
            return 1;
        } else {
            return 2;
        }
    }
    public int scoreRank(Person person){
        if(person.getRank().equals("нет")){
            return 0;
        } else if(person.getRank().equals("доцент")){
            return 1;
        } else {
            return 2;
        }
    }
    public int scoreProfile(Person person){
        if(person.getProf() == "профильный"){
            System.out.println(2);
            return 2;

        }
        if(person.getProf() == "частично"){
            System.out.println(1);
            return 1;
        } else {
            System.out.println(0);
            return 0;
        }
    }
}

