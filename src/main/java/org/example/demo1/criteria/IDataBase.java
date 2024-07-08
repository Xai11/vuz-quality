package org.example.demo1.criteria;

import java.sql.Connection;

public interface IDataBase {
    public Connection connect(String dbname, String user, String pass);
    public void createTable(Connection conn, String nameTable);
    public void insertDataTable(Connection conn, String nameTable);
}
