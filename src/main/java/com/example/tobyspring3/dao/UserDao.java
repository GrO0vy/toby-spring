package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class UserDao {
    Map<String, String> env = System.getenv();
    String dbHost = env.get("DB_HOST");
    String dbUser = env.get("DB_USER");
    String dbPassword = env.get("DB_PASSWORD");

    public void add() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost,dbUser,dbPassword);

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
        pstmt.setString(1,"1");
        pstmt.setString(2,"mincheol");
        pstmt.setString(3,"12341234");

        pstmt.executeUpdate();
    }

    public User get(){
        return new User();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();
        dao.add();
    }
}
