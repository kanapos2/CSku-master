package Models;

import java.sql.*;
import java.util.ArrayList;

public class DBControl {
    private Connection connection;
    private ResultSet resultSet = null;
    private Statement stmt = null;
    private String studentiId,studentFirstName,studentLastName,studentYear;
    private String courseID,courseName,courseCredit,courseYear,courseTerm,courseDiff,coursePrerequisite;

    public DBControl(Connection connection) {
        this.connection = connection;
    }

    public boolean addStudent(IdStudents student) {
        boolean addResult = false;
        try {
            IdStudents newStudent = student;
            String sqlText = "INSERT INTO Students VALUES (?,?,?)";
            PreparedStatement prepare = connection.prepareStatement(sqlText);
            prepare.setString(1, newStudent.getId());
            prepare.setString(2, newStudent.getFirstName());
            prepare.setString(3, newStudent.getLastName());


            if (prepare.executeUpdate() == 1) {
                addResult = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnect.closeAllConfigure(resultSet, stmt, connection);
        }
        return addResult;
    }

    public void searchStudent(String id)  {
       for(IdStudents student : readStudent()){
           if(id.equals(student.getId())){
               this.studentiId=student.getId();
               this.studentFirstName=student.getFirstName();
               this.studentLastName=student.getLastName();
               this.studentYear=student.getYear();


           }
       }
    }

    public void searchCource(String id)  {
        for(Course course : readCourse()){
            if(id.equals(course.getId())){
                this.courseID=course.getId();
                this.courseName=course.getName();
                this.courseCredit=course.getCredit();
                this.courseYear=course.getYear();
                this.coursePrerequisite=course.getPrerequisite();
                this.courseTerm=course.getTerm();
                this.courseDiff=course.getDifficulty();

            }
        }

    }

    public void addRegisteredSucject(String studentId,String courseId,String courseName,String courseCredit){
        try {

            String sqlText = "INSERT INTO Register(student_id,course_id,course_name,credit) VALUES (?,?,?,?)";
            PreparedStatement prepare = connection.prepareStatement(sqlText);
            prepare.setString(1, studentId);
            prepare.setString(2,courseId);
            prepare.setString(3,courseName);
            prepare.setString(4, courseCredit);
            prepare.executeUpdate();
            prepare.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<RegisteredSubject> readRegisteredSubject(String studentiId){ // ใส่รหัสนักเรียนลงไป เพื่อเอาข้อมูลที่นักเรียนคนนั้นลงทะเบียนวิชาไรไปบ้าง
        ArrayList<RegisteredSubject> incomeArray = new ArrayList<>();
        RegisteredSubject inFlow = null;
        try {
            stmt = connection.createStatement();
            String query = "SELECT student_id,course_id,course_name,credit FROM Register where student_id="+studentiId;
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                inFlow = new RegisteredSubject(resultSet.getString(1), resultSet.getString(2) , resultSet.getString(3),resultSet.getString(4));

                incomeArray.add(inFlow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeArray;
    }


    public ArrayList<IdStudents> readStudent(){ // Review  //
        ArrayList<IdStudents> incomeArray = new ArrayList<>();
        IdStudents inFlow = null;
        try {
            stmt = connection.createStatement();
            String query = "SELECT * FROM Students";
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                inFlow = new IdStudents(resultSet.getString(1), resultSet.getString(2) , resultSet.getString(3),resultSet.getString(4));
                inFlow.setPath(resultSet.getString(5));

                incomeArray.add(inFlow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeArray;
    }


    public boolean addCourse(Course course) {
        boolean addResult = false;
        try {
            Course newCourse = course;
            String sqlText = "INSERT INTO Course VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement prepare = connection.prepareStatement(sqlText);
            prepare.setString(1, newCourse.getId());
            prepare.setString(2, newCourse.getName());
            prepare.setString(3, newCourse.getCredit());
            prepare.setString(4, newCourse.getYear());
            prepare.setString(5, newCourse.getTerm());
            prepare.setString(6, newCourse.getDifficulty());



            if (prepare.executeUpdate() == 1) {
                addResult = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAllConfigure(resultSet, stmt, connection);
        }
        return addResult;
    }

    public ArrayList<Course> readCourse(){ // Review  //
        ArrayList<Course> incomeArray = new ArrayList<>();
        Course inFlow = null;
        try {
            stmt = connection.createStatement();
            String query = "SELECT * FROM Course order by Year";
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                inFlow = new Course(resultSet.getString(1)
                        , resultSet.getString(2) ,
                        resultSet.getString(3) ,
                        resultSet.getString(4) ,
                        resultSet.getString(5) ,
                        resultSet.getString(6),
                        resultSet.getString(7));

                incomeArray.add(inFlow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeArray;
    }



    public String getStudentYear() {
        return studentYear;
    }

    public String getCoursePrerequisite() {
        return coursePrerequisite;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public String getCourseYear() {
        return courseYear;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

    public String getCourseDiff() {
        return courseDiff;
    }

}
