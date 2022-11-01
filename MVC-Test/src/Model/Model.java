/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pwn233 (Anuwatr Treecharoenrat 63050206)
 */
public class Model {
    /*Initialize parameters
    (also use Netbeans generate getter/setter methods from parameters).*/
    private String fname, lname, email, comment;
    /*create state for using.*/
    private String[] state = {"OPEN", "CLOSE", "ESCALATE"};

    /* from line 26 - 57 is usual getter setter methods*/
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    /*this one will return state Open for new comment that coming in GUI.*/
    private String setFirstState() {
        return state[0];
    }
    
    /*getTimpestamp Method use DateTimeFormatter, LocalDateTime of Java by import
    and gain date and time (in variable 'now') to be timestamp in String pattern 
    by convert them with dtf.format() (dtf can be set in String pattern which you
    want).*/
    private String getTimestamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String date = dtf.format(now);
        return date;
    }
    
    
    /* use the same method as getTimestamp but we add an function of .plusDays()
    to get weeks life of state.*/
    public String getExpired() {
        int weeklifes = 7;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now().plusDays(weeklifes);  
        String date = dtf.format(now);
        return date;
    }
    
    /*This method will query insert our data that processed and store it in our database,
    also contain status that will return to user in case our program unable to complete
    this action.*/
    public String insertDatabase() {
        String query = "";
        String status;
        //query valid check in phpMyAdmin (SQL Execution) already.
        query = String.format("INSERT INTO user_data (REF_ID, FNAME, LNAME, EMAIL, COMMENT, TIMESTAMP, EXPIRED, STATE)VALUES (NULL, '%1$s', '%2$s', '%3$s', '%4$s', '%5$s', '%6$s', '%7$s')", getFname(), getLname(), getEmail(), getComment(),getTimestamp(), getExpired(), setFirstState());
            try {
                /*Prefer to create DatabaseConnect Class everytime we wish to use it. 
                (Because overall methods in Model Class don't use much DatabaseConnect Class.*/
                DatabaseConnect cn = new DatabaseConnect();
                boolean result = cn.execute(query);
                if (result) {
                    status = "Complete";
                } else {
                    status = "Fail";
                }
                cn.close();
            } catch (Exception ex) {
                status = "Error Update: " + ex;
            }
        return status;
    }
    
    /* update state button on Table Class will perform query and find which
    ref_id is match and then update it STATE to ESCALATE*/
    public String updateDatabase(int id){
        String query = "",st = "";
        String status;
        int rfid = 0;
        //query valid check in phpMyAdmin (SQL Execution) already.
        query = String.format("SELECT REF_ID FROM user_data WHERE REF_ID = '%1$d'", id);
        //checking that id contain state close or not
        DatabaseConnect cn = new DatabaseConnect();
        ResultSet rs = cn.getResult(query);
        try {
            while(rs.next()) {
            rfid = rs.getInt("REF_ID"); 
        }
        } catch(Exception ex) {

        }
        cn.close();
        if(rfid == 0) {
            status = "Fail : No REF_ID in Database.";
        } else {
                query = String.format("SELECT STATE FROM user_data WHERE REF_ID = '%1$d'", id);
                cn = new DatabaseConnect();
                rs = cn.getResult(query);
                try {
                    while(rs.next()) {
                        st = rs.getString("STATE"); 
                    }
                } catch(Exception ex) {

                }
                cn.close();
                if(st.equals("CLOSE")) {
                    status = "Fail : Can not change CLOSE state.";
                } else if(st.equals("ESCALATE")) {
                    status = "Fail : Status is already ESCALATE.";
                } else {    
                    //query valid check in phpMyAdmin (SQL Execution) already.
                    query = String.format("UPDATE user_data SET STATE = 'ESCALATE' WHERE REF_ID = '%1$d'", id);
                    try {
                        /*Prefer to create DatabaseConnect Class everytime we wish to use it. 
                        (Because overall methods in Model Class don't use much DatabaseConnect Class.*/
                        cn = new DatabaseConnect();
                        boolean result = cn.execute(query);
                        if (result) {
                            status = "Update State Complete";
                        } else {
                            status = "Update State Fail";
                        }
                        cn.close();
                    } catch (Exception ex) {
                        status = "Error Update: " + ex;
                }
            }
        }
        
        return status;
    }
    
    /* close state button on Table Class will perform query and find which
    ref_id is match and then update it STATE to CLOSE*/
    public String updateCloseDatabase(int id) {
        String query = "",st = "";
        String status;
        int rfid = 0;
        query = String.format("SELECT REF_ID FROM user_data WHERE REF_ID = '%1$d'", id);
        //checking that id contain state close or not
        DatabaseConnect cn = new DatabaseConnect();
        ResultSet rs = cn.getResult(query);
        try {
            while(rs.next()) {
                rfid = rs.getInt("REF_ID"); 
            }
        } catch(Exception ex) {
            
        }
        cn.close();
        if(rfid == 0) {
            status = "Fail : No REF_ID in Database.";
        } else {
            query = String.format("SELECT STATE FROM user_data WHERE REF_ID = '%1$d'", id);
            cn = new DatabaseConnect();
            rs = cn.getResult(query);
            try {
                while(rs.next()) {
                   st = rs.getString("STATE"); 
                }
            } catch(Exception ex) {

            }
            cn.close();
            if(st.equals("CLOSE")) {
                status = "Fail : Can not change CLOSE state.";
            } else {
            //query valid check in phpMyAdmin (SQL Execution) already.
            query = String.format("UPDATE user_data SET STATE = 'CLOSE' WHERE REF_ID = '%1$d'", id);
                try {
                    /*Prefer to create DatabaseConnect Class everytime we wish to use it. 
                    (Because overall methods in Model Class don't use much DatabaseConnect Class.*/
                    cn = new DatabaseConnect();
                    boolean result = cn.execute(query);
                    if (result) {
                        status = "Close State Complete";
                    } else {
                        status = "Close State Fail";
                    }
                    cn.close();
                } catch (Exception ex) {
                    status = "Error Update: " + ex;
                }
            }
        }
        return status;
    }
    
    /*This method will query data with or without [condition], and get data by row.
    Then add rows of data to the model when we first create.
    (at Table.java that we create black table with columns).*/
    public DefaultTableModel showData(DefaultTableModel model) {
        String query = "";
        try {
            DatabaseConnect cn = new DatabaseConnect();
            ResultSet rs;
            /*query in order to get from ESCALATE -> OPEN -> CLOSE by order (Don't recomment, brute force way :P)*/
            query = String.format("SELECT * FROM user_data WHERE STATE = 'ESCALATE'");
            rs = cn.getResult(query);
            while (rs.next()) {
                String ri = rs.getString("REF_ID");
                String fn = rs.getString("FNAME");
                String ln = rs.getString("LNAME");
                String em = rs.getString("EMAIL");
                String cm = rs.getString("COMMENT");
                String ts = rs.getString("TIMESTAMP");
                String exp = rs.getString("EXPIRED");
                String st = rs.getString("STATE");
                String[] row = {ri, fn, ln, em, cm, ts, exp, st};
                model.addRow(row);
            }
            query = String.format("SELECT * FROM user_data WHERE STATE = 'OPEN'");
            rs = cn.getResult(query);
            while (rs.next()) {
                String ri = rs.getString("REF_ID");
                String fn = rs.getString("FNAME");
                String ln = rs.getString("LNAME");
                String em = rs.getString("EMAIL");
                String cm = rs.getString("COMMENT");
                String ts = rs.getString("TIMESTAMP");
                String exp = rs.getString("EXPIRED");
                String st = rs.getString("STATE");
                String[] row = {ri, fn, ln, em, cm, ts, exp, st};
                model.addRow(row);
            }
            query = String.format("SELECT * FROM user_data WHERE STATE = 'CLOSE'");
            rs = cn.getResult(query);
            while (rs.next()) {
                String ri = rs.getString("REF_ID");
                String fn = rs.getString("FNAME");
                String ln = rs.getString("LNAME");
                String em = rs.getString("EMAIL");
                String cm = rs.getString("COMMENT");
                String ts = rs.getString("TIMESTAMP");
                String exp = rs.getString("EXPIRED");
                String st = rs.getString("STATE");
                String[] row = {ri, fn, ln, em, cm, ts, exp, st};
                model.addRow(row);
            }
            cn.close();
        } catch (Exception ex) {
        }
        return model;
    }
    public DefaultTableModel showDataClose(DefaultTableModel model) {
        String query = "";
        try {
            DatabaseConnect cn = new DatabaseConnect();
            query = String.format("SELECT * FROM user_data WHERE STATE = 'CLOSE'");
            ResultSet rs = cn.getResult(query);
            while (rs.next()) {
                String ri = rs.getString("REF_ID");
                String fn = rs.getString("FNAME");
                String ln = rs.getString("LNAME");
                String em = rs.getString("EMAIL");
                String cm = rs.getString("COMMENT");
                String ts = rs.getString("TIMESTAMP");
                String exp = rs.getString("EXPIRED");
                String st = rs.getString("STATE");
                String[] row = {ri, fn, ln, em, cm, ts, exp, st};
                model.addRow(row);
            }
            cn.close();
        } catch (Exception ex) {
        }
        return model;
    }
}
