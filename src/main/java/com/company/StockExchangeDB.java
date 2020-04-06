package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class StockExchangeDB {
    // Блок объявления констант

    static Properties properties = new Properties();


    public static final String DB_URL = properties.getProperty("URL");
    public static final String DB_Driver = properties.getProperty("Driver");

    public static void main(String[] args) {
        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            Connection connection = DriverManager.getConnection(DB_URL);//соединениесБД
            System.out.println("Соединение с СУБД выполнено.");
            connection.close();       // отключение от БД
            System.out.println("Отключение от СУБД выполнено.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }

        // Запрос в БД, например создание таблицы
        try{
            String url = properties.getProperty("URL");
            String username = properties.getProperty("root");
            String password = properties.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            // команда создания таблицы
            String sqlCommand = "CREATE TABLE products (Id INT PRIMARY KEY AUTO_INCREMENT, ProductName VARCHAR(20), Price INT)";

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                // создание таблицы
                statement.executeUpdate(sqlCommand);

                System.out.println("Database has been created!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}