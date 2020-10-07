package org.example.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @ClassName SqlServerTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/6/14 10:47
 * @Version 1.0
 */
public class SqlServerTest {

    public static void main(String[] args) {
        //数据库连接参数
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=scdb";
        String username = "sa";
        String password = "1234";
        //连接工具
        Connection dbConn;
        //sql
        String selectSql = "select * from dbo.student";

        try

        {

            Class.forName(driverName);

            dbConn = DriverManager.getConnection(dbUrl, username, password);

            PreparedStatement preparedStatement = dbConn.prepareStatement(selectSql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String sno = resultSet.getString("sno");
                String sname = resultSet.getString("sname");
                String gender = resultSet.getString("ssex");
                int sage = resultSet.getInt("sage");
                String sdept = resultSet.getString("sdept");
                System.out.println(sno + ":" + sname + ":" + sage + ":" + gender + ":"+ sdept);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("连接失败");
        }
    }

}
