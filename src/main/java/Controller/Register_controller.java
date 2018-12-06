package Controller;

import Models.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Register_controller {



    private String id;

    @FXML
    private TextField idSubject;

    @FXML
    private Button btn_submit , btn_cancle;

    @FXML
    private TableView<Course> allSubjecttable ;
    @FXML
    private TableView<Course> table2 ;
    @FXML
    private TableColumn<Course,String> tb2_id ;
    @FXML
    private TableColumn<Course,String>  tb2_name;
    @FXML
    private TableColumn<Course,String> tb2_credit ;

    @FXML
    private TableColumn<Course,String> tb_id ;
    @FXML
    private TableColumn<Course,String>  tb_name;
    @FXML
    private TableColumn<Course,String> tb_credit ;

    @FXML
    private TableColumn<Course,String> tb_prerequisite;


    private ArrayList<String> dataCouse = new ArrayList<>();
    private ArrayList<RegisteredSubject> dataRegister;

    private ArrayList<Course> addCourseToTableView;


    ObservableList<Course> oblist = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
//
////        tb_id.setCellValueFactory(new PropertyValueFactory<Course,String>("id"));
////        tb_name.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
////        tb_credit.setCellValueFactory(new PropertyValueFactory<Course,String>("credit"));
////        tb_prerequisite.setCellValueFactory(new PropertyValueFactory<Course,String>("prerequisite"));
////

        updateColor(tb2_id);
        colorCourse(tb_id);

        tb2_id.setCellValueFactory(new PropertyValueFactory<Course,String>("id"));
        tb2_name.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        tb2_credit.setCellValueFactory(new PropertyValueFactory<Course,String>("credit"));


        table2.setItems(getData3(getData2()));

//
//
    }

    @FXML
    public void setDataToTableView() {
        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();
        DBControl addDBControl = new DBControl(connection);
        dataRegister = addDBControl.readRegisteredSubject(id);

        for (Course course : addDBControl.readCourse()){
            dataCouse.add(course.getId());
        }

        for (int i=0 ; i<dataCouse.size() ; i++){
            for (RegisteredSubject registeredSubject : dataRegister){
                if (dataCouse.get(i).equals(registeredSubject.getCourseId())){
                    System.out.println("--------- E -------");
                    dataCouse.remove(i);
                }
            }
        }


        addCourseToTableView = new ArrayList<>();

        for (Course course : addDBControl.readCourse()){
            for (String s : dataCouse){
                if (s.equals(course.getId())){
                    addCourseToTableView.add(course);
                }
            }
        }

        tb_id.setCellValueFactory(new PropertyValueFactory<Course,String>("id"));
        tb_name.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        tb_credit.setCellValueFactory(new PropertyValueFactory<Course,String>("credit"));
        tb_prerequisite.setCellValueFactory(new PropertyValueFactory<Course,String>("prerequisite"));

        for (Course c : addCourseToTableView){
            System.out.println(c);
        }

    }




    public ObservableList<Course> getData(){
        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();
        DBControl addDBControl = new DBControl(connection);

        ObservableList<Course> data=FXCollections.observableArrayList();

        for(Course course: addCourseToTableView){

            data.add(new Course(course.getId(),course.getName(), course.getCredit(), course.getYear(),course.getTerm(),course.getDifficulty(),course.getPrerequisite()));
        }
        return data;

    }

    public ObservableList<RegisteredSubject> getData2(){
        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();
        DBControl addDBControl = new DBControl(connection);

        ObservableList<RegisteredSubject> data=FXCollections.observableArrayList();
        for(RegisteredSubject registeredSubject:addDBControl.readRegisteredSubject(id)){
            data.add(new RegisteredSubject(registeredSubject.getStudentId(),registeredSubject.getCourseId(), registeredSubject.getCourseName(), registeredSubject.getCredit()));
        }
        return data;

    }
    public ObservableList<Course> getData3(ObservableList<RegisteredSubject> allSubs){
        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();
        DBControl addDBControl = new DBControl(connection);
        ArrayList<Course> courses =new ArrayList<>();
        for(RegisteredSubject registeredSubject:allSubs){
            courses.add(new Course(registeredSubject.getStudentId(),registeredSubject.getCourseId(),registeredSubject.getCourseName(),registeredSubject.getCredit()));
        }

        ObservableList<Course> data=FXCollections.observableArrayList();
        for(Course course:courses){
            data.add(new Course(course.getS_id(),course.getId(), course.getName(), course.getCredit()));
        }
        return data;

    }
    public void getID(String idFromMenu){
        System.out.println("!!!!!!!!!!!!!!!!!");
        id = idFromMenu;
        System.out.println(id);
        table2.setItems(getData3(getData2()));

        checkUpdateSubject();

        setDataToTableView();
        allSubjecttable.setItems(getData());
        addCourseToTableView.clear();
        dataCouse.clear();
        dataRegister.clear();
        System.out.println(addCourseToTableView);

    }


    @FXML
    public void addSubject(ActionEvent event) {


        showTable();
        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();
        DBControl addDBControl = new DBControl(connection);

        btn_submit = (Button) event.getSource();

        addDBControl.searchStudent(id);
        addDBControl.searchCource(idSubject.getText());
        ArrayList<RegisteredSubject> allRegisterSubjects = addDBControl.readRegisteredSubject(id);

        Course newCourse = new Course(addDBControl.getCourseID(),
                addDBControl.getCourseName(),
                addDBControl.getCourseCredit(),
                addDBControl.getCourseYear(),
                addDBControl.getCourseTerm(),
                addDBControl.getCourseDiff(),
                addDBControl.getCoursePrerequisite());

        if (addDBControl.getCourseID() != null) { //มีวิชาในดีบี
            boolean mustPrereq = true; //ต้องการตัวต่อ
            int round = 0;
            boolean dupInput = false;//ใส่วิชาซ้ำ
            for (RegisteredSubject registeredSubject : allRegisterSubjects) {
                if (newCourse.getId().equals(registeredSubject.getCourseId())) { //ใส่วิชาที่เคยลงทะเบียนไปแล้ว
                    dupInput = true;

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error message");
                    alert.setContentText("รหัสวิชานี้ได้ทำการลงทะเบียนเรียนเรียบร้อยแล้ว");
                    alert.show();
                    break;

                }
            }


            if (Integer.parseInt(addDBControl.getCourseYear()) <= Integer.parseInt(addDBControl.getStudentYear()) && dupInput == false) { //ชั้นปีที่เราอยู่ต้องมากกว่าหรือเท่ากับปีของวิชาที่เปิด และไม่ได้ใส่ซ้ำ
                if (addDBControl.getCoursePrerequisite() == null) {//ไม่มีวิชาก่อนหน้า

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to add this subject?",ButtonType.YES,ButtonType.NO);
                    alert.setHeaderText("");
                    Optional<ButtonType> optional = alert.showAndWait();
                    if (optional.get() == ButtonType.YES){
                        System.out.println("--------");
                        oblist.add(newCourse);  //ส่งขึ้นตาราง
                        addDBControl.addRegisteredSucject(id, addDBControl.getCourseID(), addDBControl.getCourseName(), addDBControl.getCourseCredit());
                        idSubject.clear();
                        table2.setItems(getData3(getData2()));

                        setDataToTableView();
                        allSubjecttable.setItems(getData());
                        addCourseToTableView.clear();
                        dataCouse.clear();
                        dataRegister.clear();
                    }



                } else if (addDBControl.getCoursePrerequisite() != null) {//มีวิชาก่อนหน้า
                    for (RegisteredSubject registeredSubject : allRegisterSubjects) { //กรณีนิสิตคนนี้เคยลงทะเบียนไปบ้างแล้วในดีบี
                        if (registeredSubject.getCourseId().contains(addDBControl.getCoursePrerequisite())) {//ลงตัวก่อนหน้าไปแล้ว ก็ลงทะเบียนตัวต่อไปได้
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to add this subject?",ButtonType.YES,ButtonType.NO);
                            alert.setHeaderText("");
                            Optional<ButtonType> optional = alert.showAndWait();
                            if (optional.get()==ButtonType.YES){
                                System.out.println("--------");
                                oblist.add(newCourse);  //ส่งขึ้นตาราง
                                addDBControl.addRegisteredSucject(id, addDBControl.getCourseID(), addDBControl.getCourseName(), addDBControl.getCourseCredit());
                                idSubject.clear();
                                mustPrereq = false;
                                table2.setItems(getData3(getData2()));
                                setDataToTableView();
                                allSubjecttable.setItems(getData());
                                addCourseToTableView.clear();
                                dataCouse.clear();
                                dataRegister.clear();
                                break;
                            }

                        } else if (mustPrereq == true && round == allRegisterSubjects.size() - 1) { //ไม่ลงตัวก่อนหน้า
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error message");
                            alert.setContentText("Just pass subject id : "+addDBControl.getCoursePrerequisite());
                            alert.show();
                        }

                        round++;
                    }
                    if (allRegisterSubjects.size() == 0) {//กรณียนิสิตคนนั้นังไม่เคยลงทะเบียนซักวิชาในดาต้าเบสเลย

                        if (addDBControl.getCoursePrerequisite() == null) {//ไม่มีวิชาก่อนหน้า   add data3
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to add this subject?",ButtonType.YES,ButtonType.NO);
                            alert.setHeaderText("");
                            Optional<ButtonType> optional = alert.showAndWait();
                            if (optional.get()==ButtonType.YES){
                                oblist.add(newCourse);  //ส่งขึ้นตาราง
                                addDBControl.addRegisteredSucject(id, addDBControl.getCourseID(), addDBControl.getCourseName(), addDBControl.getCourseCredit());
                                idSubject.clear();
                                table2.setItems(getData3(getData2()));
                                setDataToTableView();
                                allSubjecttable.setItems(getData());
                                addCourseToTableView.clear();
                                dataCouse.clear();
                                dataRegister.clear();
                            }

                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error message");
                            alert.setContentText("Just pass subject id : "+addDBControl.getCoursePrerequisite());
                            alert.show();
                        }
                    }
                }

            } else if (dupInput == false && Integer.parseInt(addDBControl.getCourseYear()) > Integer.parseInt(addDBControl.getStudentYear())) {//ลงวิชารุ่นพี่ไม่ได้
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setContentText("ชั้นปีของท่านยังไม่เปิดให้ลงทะเบียนรายวิชานี้");
                alert.show();
//
            }

            } else {//หาวิชาไม่เจอ
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error message");
                alert.setContentText("Incorrect Subject ID (404)  Please try again !!!");
                alert.show();
            }
        }


        public void showTable () {

            tb2_id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
            tb2_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            tb2_credit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCredit()));
            table2.setItems(getData3(getData2()));
        }
        public void showTable2 () {

            tb_id.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
            tb_name.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
            tb_credit.setCellValueFactory(new PropertyValueFactory<Course, String>("credit"));
            tb_prerequisite.setCellValueFactory(new PropertyValueFactory<Course, String>("prerequisite"));

        }


        @FXML
        public void gotoBack (ActionEvent event){
            DBControl openDB = DBConnect.openDB();

            String name;
            btn_cancle = (Button) event.getSource();

            for (IdStudents s : openDB.readStudent()) {
                if (id.equals(s.getId())) {
                    Stage stage = (Stage) btn_cancle.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/Menu.fxml"));

                    try {
                        stage.setScene(new Scene(loader.load(), 800, 600));
                        stage.setTitle("Menu Register");
                        stage.setResizable(false);

                        Menu_controller controller = (Menu_controller) loader.getController();
                        name = s.getFirstName() + "\t" + s.getLastName();
                        controller.setID(id, name);


                        stage.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }


    @FXML
    public void updateColor(TableColumn nowColumn) {
        DBControl openDB = DBConnect.openDB();
        nowColumn.setCellFactory(column -> {
            return new TableCell<RegisteredSubject, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory
                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty\
                        setText("PASS"); //Put the String data in the cell
//                        RegisteredSubject auxPerson = getTableView().getItems().get(getIndex());
                        setStyle("-fx-font-weight: bold; -fx-background-color: rgba(0,202,24,0.45)");
                    }
                }
            };
        });
    }

    @FXML
    public void colorCourse(TableColumn nowColumn) {
        nowColumn.setCellFactory(column -> {
            return new TableCell<RegisteredSubject, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory
                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty\
                        setText(item); //Put the String data in the cell
//                        RegisteredSubject auxPerson = getTableView().getItems().get(getIndex());
                        setStyle("-fx-font-weight: bold;" + "-fx-background-color: rgba(223,0,11,0.45)");
                    }
                }
            };
        });
    }

    @FXML
    public void checkUpdateSubject(){


        DBControl openDB = DBConnect.openDB();
        for (Course course : openDB.readCourse()){
            for (RegisteredSubject registeredSubject : openDB.readRegisteredSubject(id)){
                if (registeredSubject.getCourseName().equals(course.getId())){
//                    System.out.println("---------- EQUILT ---------");
                    updateColor(tb2_id);
                }
            }
        }

    }

}
