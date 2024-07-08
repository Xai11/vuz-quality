package org.example.demo1.criteria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBase implements IDataBase {
    public Connection connect(String dbname, String user, String pass ){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if(conn != null){

            } else{
                System.out.println("Нет соединения");
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
    public void createTable(Connection conn, String nameTable) {
        Statement statement;
        try{
            String query = "CREATE TABLE " + nameTable + "(id BIGSERIAL, name varchar(50));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Создана таблица");
        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void insertDataTable(Connection conn, String nameTable) {
        Statement statement;
        try{
            String arg = "Andrew";
            String queryInsert = "INSERT INTO " + nameTable + "(name) Values ('" + arg + "')";
            statement = conn.createStatement();
            statement.executeUpdate(queryInsert);
            System.out.println("Добавлена информация");
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void deleteTable(Connection conn, String nameTable){
        Statement statement;
        try{
            String queryDelete = "DROP TABLE " + nameTable;
            statement = conn.createStatement();
            statement.executeUpdate(queryDelete);
            System.out.println("Таблица удалена");
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

