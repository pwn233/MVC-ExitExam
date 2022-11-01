package Model;

import java.sql.*;

/**
 *
 * @author pwn233 (Anuwatr Treecharoenrat 63050206)
 */
public class DatabaseConnect {
    /*Set up parameter that we need to connect to database. (as usual)
    (in real work, I prefer not to use user = "root" and password = ""
    but in this MVC-Test Project, I prefer to use it for easily setup 
    on checking)*/
    /*using XAMPP for localhost and default port on 3306.
    I also provide mysql connector/j in my project.
    (connector between database and programming language, need to be 
    added in Libraries by right click and Add JAR/Folder)*/
    private Connection conn;
    private Statement st;
    private String url = "jdbc:mysql://localhost:3306/mvc-test";
    private String user = "root";
    private String password = "";
    
    /*getResult method will create statement and execute query (with a url,
    username, password) and retreieve the query execution statement(as rs) back*/
    public ResultSet getResult(String query) {
        ResultSet rs;
        try{
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            rs = st.executeQuery(query);
        }catch(Exception ex){
            /*in case scoobydoo dabydabydoo case happen. (joking)*/
            rs = null;
        }
        return rs ;
    }
    
    /* getResult method will create statement and execute query (with a url,
    username, password) and retreieve the ResultSet STATE
    (whether complete or not by true or false) back*/
    public boolean execute(String query)
    {
        boolean rs;
        try{
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            st.execute(query);
            rs=true;
        }catch(Exception ex){
            rs=false;
        }
        return rs;
    }
    /*close method for close the connection.*/
    public void close()
    {
        try{
            conn.close();
        }catch(Exception ex){
            
        }
    }
}
