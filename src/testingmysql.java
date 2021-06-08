import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class testingmysql {
//public static void main(String[] args) throws ClassNotFoundException, SQLException
//{
////load and register JDBC Driver
//Class.forName("com.mysql.cj.jdbc.Driver");
//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poo_db","root","");
//Statement stmt = con.createStatement();
//ResultSet rs = stmt.executeQuery("select login, pwd from users");
//
///*
//open a connection to database
//getConnection(url,username,password);
//*/
//
////create statement object
//
////execute statement object and return result to ResultSet
//while(rs.next()){
//System.out.println("Email : "+rs.getString(1));
//System.out.println("Password : "+rs.getString(2));
//}
//
////close the connection
//rs.close();
//stmt.close();
//con.close();
//}
//}