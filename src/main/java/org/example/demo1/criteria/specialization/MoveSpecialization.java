package org.example.demo1.criteria.specialization;

import org.example.demo1.criteria.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class MoveSpecialization extends DataBase {
    private String comb;
    public void setComb(String comb){
        this.comb = comb;
    }
    public String getComb(){
        return comb;
    }
    public ArrayList<String> getSpecialization(Connection conn){
        Statement statement;
        ResultSet resultSet = null;
        ArrayList<String> bufferSpec = new ArrayList<String>();
        try{
            String querySelect = "SELECT spec_name FROM specialization;";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while(resultSet.next()) {
                bufferSpec.add(resultSet.getString("spec_name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return bufferSpec;
    }
    public int getIDInputSpecialization(Connection conn){
        Statement statement;
        ResultSet resultSet = null;
        int idSpec = 0;
        try{
            String querySelect = "SELECT spec_id FROM specialization WHERE spec_name = '" + getComb() + "';";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while (resultSet.next()){
                idSpec = resultSet.getInt("spec_id");
                break;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return idSpec;
    }
    public int getIDInputSpecialization(Connection conn, String spec_name){
        Statement statement;
        ResultSet resultSet = null;
        int idSpec = 0;
        try{
            String querySelect = "SELECT spec_id FROM specialization WHERE spec_name = '" + spec_name + "';";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while (resultSet.next()){
                idSpec = resultSet.getInt("spec_id");
                break;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return idSpec;
    }
}
