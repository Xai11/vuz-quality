package org.example.demo1.criteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MoveUniversity extends DataBase{
    private String comb;

    public void setComb(String comb) {
        this.comb = comb;
    }
    public String getComb(){
        return comb;
    }

    public ArrayList<String> getUniversity(Connection conn){
        Statement statement;
        ResultSet resultSet = null;
        ArrayList<String> bufferVuz = new ArrayList<String>();
        try{
            String querySelect = "SELECT vuz_name FROM university;";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while(resultSet.next()) {
                 bufferVuz.add(resultSet.getString("vuz_name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return bufferVuz;
    }
    public int getIDInputUniversity(Connection conn){
        Statement statement;
        ResultSet resultSet = null;
        int idVuz = 0;
        try{
            String querySelect = "SELECT vuz_id FROM university WHERE vuz_name = '" + getComb() + "';";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while (resultSet.next()){
                idVuz = resultSet.getInt("vuz_id");
                break;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return idVuz;
    }
    public int getIDInputUniversity(Connection conn, String vuz_name){
        Statement statement;
        ResultSet resultSet = null;
        int idVuz = 0;
        try{
            String querySelect = "SELECT vuz_id FROM university WHERE vuz_name = '" + vuz_name + "';";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while (resultSet.next()){
                idVuz = resultSet.getInt("vuz_id");
                break;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return idVuz;
    }

}
