package Control;

import Model.Model;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pwn233 (Anuwatr Treecharoenrat 63050206)
 */
public class Control {
    /*initialize Model Class as Global because every Model's methods that will 
    be called in Control's method will be called without duplicate or multiple 
    initialize of Model Class.*/
    /*need to be private for safety purpose*/
    private Model m = new Model();
    
    
    /*returnStatus will call insertDatabase method from model and get status 
    from it and return the status back to View(GUI) to let user acknowledge 
    that it whether complete or fail.*/
    public String returnStatus() {
        return m.insertDatabase();
    }

    /*showData method and showClose method on Control Class works as a bridge 
    to bring showData method on Model Class and use it.*/
    public DefaultTableModel showData(DefaultTableModel model) {
        m.showData(model);
        return model;
    }

    public DefaultTableModel showClose(DefaultTableModel model) {
        m.showDataClose(model);
        return model;
    }
    /*black field checking logic in case user didn't input every field*/
    public boolean checkData(String fname, String lname, String email, String comment) {
        if(fname.equals("") || lname.equals("") || email.equals("") || comment.equals("") )return false;
        else return true;
    }
    
    /*set data from view to model as usual in control*/
    public void setData(String fname, String lname, String email, String comment) {
        m.setFname(fname);
        m.setLname(lname);
        m.setEmail(email);
        m.setComment(comment);
    }
    
    /*2 update state of ESCALATE and CLOSE*/
    public String changeStatus(int id) {
        return m.updateDatabase(id);
    }

    public String closeDown(int id) {
        return m.updateCloseDatabase(id);
    }
}
