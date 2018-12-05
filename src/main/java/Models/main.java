package Models;

import Controller.Register_controller;

import java.sql.Connection;
import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();
        DBControl addDBControl = new DBControl(connection);


//        Register_controller register_controller=new Register_controller();
//        addDBControl.readRegisteredSubject("6010450512");
//        for(Course course:register_controller.getData4(register_controller.getData(),"6010450512")){
//            System.out.println(course.getId());
//            System.out.println(course.getId());
//            System.out.println(course.getId());
//        }



        //System.out.println(addDBControl.get());
        //System.out.println(addDBControl.getStudentiId());

        //        System.out.println(addDBControl.addStudent(new IdStudents("5810450733","Tanakrit", "Pon")));

//        for (IdStudents s : addDBControl.readStudent()){
//            System.out.println(s.getId());
//        }


//        System.out.println(addDBControl.addCourse(new Course("01418114","Introduction to Computer Science","4","1"
//                ,"1","2")));

//        for (Course s : addDBControl.readCourse()){
//            System.out.println(s);
//        }
    }
}
