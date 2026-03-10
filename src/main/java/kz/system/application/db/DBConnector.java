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

    public static ArrayList<Request> getAllStudents(boolean handled){
        ArrayList<Request> requests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orda.requests WHERE handled = ?");
            statement.setBoolean(1, handled);
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

    public static Request getStudentById(int id) {
        Request request = new Request();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orda.requests WHERE id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                request.setId(resultSet.getInt("id"));
                request.setUserName(resultSet.getString("name"));
                request.setCourseName(resultSet.getString("course_name"));
                request.setCommentary(resultSet.getString("commentary"));
                request.setPhone(resultSet.getString("phone"));
                request.setHandled(resultSet.getBoolean("handled"));

            }

            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }


    public static void addStudent(Request request) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO orda.requests (name, course_name, commentary, phone, handled) VALUES (?, ?, ?, ?, ?)" );
            statement.setString(1, request.getUserName());
            statement.setString(2, request.getCourseName());
            statement.setString(3, request.getCommentary());
            statement.setString(4, request.getPhone());
            statement.setBoolean(5, request.isHandled());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM orda.requests WHERE id = ?");

            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(Request request) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE orda.requests SET name = ?, course_name=?, commentary =?, phone=?, handled=? WHERE id = ? ");
            statement.setString(1, request.getUserName());
            statement.setString(2, request.getCourseName());
            statement.setString(3, request.getCommentary());
            statement.setString(4, request.getPhone());
            statement.setBoolean(5, request.isHandled());
            statement.setInt(6, request.getId());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

