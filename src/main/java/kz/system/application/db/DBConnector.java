package kz.system.application.db;


import kz.system.application.entity.Request;

import java.sql.*;

import java.util.ArrayList;

public class DBConnector {
    private static Connection connection;
    private static String login = "postgres";
    private static String password = "postgres";
    private static String url = "jdbc:postgresql://localhost:5432/tech?currentSchema=orda";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Request> getAllStudents(){
        ArrayList<Request> requests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orda.requests");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Request request = new Request();
                request.setId(resultSet.getInt("id"));
                request.setUserName(resultSet.getString("name"));
                request.setCourseName(resultSet.getString("course_name"));
                request.setCommentary(resultSet.getString("commentary"));
                request.setPhone(resultSet.getString("phone"));
                request.setHandled(resultSet.getBoolean("handled"));

                requests.add(request);
            }
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }
}

