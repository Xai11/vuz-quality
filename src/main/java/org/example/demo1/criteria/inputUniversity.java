//package org.example.demo1.criteria;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Scanner;
//
//public class inputUniversity extends DataBase {
//    public String readData() {
//        Scanner scanner = new Scanner(System.in);
//        String vuz = scanner.nextLine();
//        return vuz;
//    }
//    public void checkData(Connection conn, String nameTable) {
//        Statement statement;
//        ResultSet resultSet = null;
//        String readUniver = readData();
//        try {
//            String query = "SELECT COUNT (*) FROM " + nameTable + " WHERE name = '" + readUniver + "';";
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                int count = resultSet.getInt(1);
//                if (count > 0) {
//                    System.out.println("Данные найдены");
//                    continue;
//                } else {
//                    System.out.println("Данные не найдены");
//                    statement = conn.createStatement();
//                    statement.executeUpdate("INSERT INTO " + nameTable + " (name) VALUES ('" + readUniver + "');");
//                    System.out.println("Добавлена информация");
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//
//        }
//    }
//}