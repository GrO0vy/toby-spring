package com.example.tobyspring3.db;

import java.sql.*;
import java.util.Map;

public class ConnectionChecker {
    Map<String, String > env = System.getenv();
    String dbHost = env.get("DB_HOST");
    String dbUser = env.get("DB_USER");
    String dbPassword = env.get("DB_PASSWORD");

    public void check() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,dbUser,dbPassword
        );

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SHOW DATABASES");
        rs = st.getResultSet();

        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add(String id, String name, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,dbUser,dbPassword
        );

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id,name,password) values(?, ?, ?)");
        pstmt.setString(1,id);
        pstmt.setString(2,name);
        pstmt.setString(3,password);
        pstmt.executeUpdate();
    }

    public void select() throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,dbUser,dbPassword
        );
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();
        while(rs.next()){
            String str = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);
            System.out.println(str);
            System.out.println(str2);
            System.out.println(str3);
        }
    }

    public void delete() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,dbUser,dbPassword
        );
        PreparedStatement pstmt = conn.prepareStatement("delete from users");
        pstmt.executeUpdate();
    }

    public void update(String prev, String fixed) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost,dbUser,dbPassword
        );

        PreparedStatement pstmt = conn.prepareStatement("update users set name = ? where name = ?");
        pstmt.setString(1, fixed);
        pstmt.setString(2, prev);
        pstmt.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker cc = new ConnectionChecker();
        //cc.check();
        //cc.delete();
        cc.add("1","minchegool","123311");
        cc.add("2","kimcheol","91283");
        cc.add("3","parkcheol","53241");
       /* cc.select();
        cc.delete();
        cc.select();*/
        //cc.update("mincheol","lee");
        cc.select();
    }
}
