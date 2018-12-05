package Controller;

import Models.Course;
import Models.DBConnect;
import Models.DBControl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class ConfirmPopUp_controller {
    private String id,courseId,courseName,credit;
    private Course course;
    private ObservableList<Course> allsubs;


    public void sendValue(String id, String courseId, String courseName, String credit, Course newCourse, ObservableList<Course> allsubs){
        this.id=id;
        this.courseId=courseId;
        this.courseName=courseName;
        this.credit=credit;
        this.course=newCourse;
        this.allsubs=allsubs;
    }

//    @FXML
//    public void backButton(ActionEvent event){
//        Stage stage=
//    }

    @FXML
    public void okButton(ActionEvent e){
//        DBConnect db = new DBConnect();
//        Connection connection = db.openDatabase();
//        DBControl addDBControl = new DBControl(connection);
//        Register_controller register_controller=new Register_controller();
//        register_controller.oblist.add(course);
//        addDBControl.addRegisteredSucject(id,courseId,courseName,credit);
//        register_controller.table2.setItems(allsubs);

    }
}
