package Controller;

import Models.Course;
import Models.DBConnect;
import Models.DBControl;
import Models.IdStudents;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AllCourse_controller implements Initializable {

    private String id = "";

    @FXML
    private Button back , btn_all, btn_4_2 , btn_4_1 , btn_3_2 , btn_3_1 , btn_2_2 ,btn_2_1 , btn_1_2 , btn_1_1;

    @FXML
    private TableView<Course> table;

    @FXML
    private TableColumn<Course,String> c_id ;
    @FXML
    private TableColumn<Course,String>  c_name;
    @FXML
    private TableColumn<Course,String> c_credit ;
    @FXML
    private TableColumn<Course,String>  c_year;
    @FXML
    private TableColumn<Course,String>  c_term;
    @FXML
    private TableColumn<Course,String> c_diff;
    @FXML
    private TableColumn<Course,String> c_prerequisite;



    ObservableList<Course> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateColor(c_diff);
        pullData();
        addData();

    }


    @FXML
    public void all(ActionEvent event){
        btn_all = (Button) event.getSource();
        pullData();
        addData();
    }


    @FXML
    public void enterFourBtnTermTwo(ActionEvent event){
        btn_4_2 = (Button) event.getSource();
        table.getItems().removeAll(oblist);

        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();
        DBControl addDBControl = new DBControl(connection);

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Course WHERE Year='4' and Term='2'");
            while (rs.next()){
                oblist.add(new Course(rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Credit"),rs.getString("Year"),rs.getString("Term"),
                        rs.getString("Difficulty"),rs.getString("Prerequisite")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addData();


    }

    @FXML
    public void enterFourBtnTermOne(ActionEvent event){
        btn_4_1 = (Button) event.getSource();
        table.getItems().removeAll(oblist);

        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Course WHERE YEAR='4' and Term='1'");
            while (rs.next()){
                oblist.add(new Course(rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Credit"),rs.getString("Year"),rs.getString("Term"),
                        rs.getString("Difficulty"),rs.getString("Prerequisite")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addData();
    }

    @FXML
    public void enterThirdBtnTermTwo(ActionEvent event){
        btn_3_2 = (Button) event.getSource();
        table.getItems().removeAll(oblist);

        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Course WHERE YEAR='3' and Term='2'");
            while (rs.next()){
                oblist.add(new Course(rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Credit"),rs.getString("Year"),rs.getString("Term"),
                        rs.getString("Difficulty"),rs.getString("Prerequisite")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addData();
    }


    @FXML
    public void enterThirdBtnTermOne(ActionEvent event){
        btn_3_1 = (Button) event.getSource();
        table.getItems().removeAll(oblist);

        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Course WHERE YEAR='3' and Term='1'");
            while (rs.next()){
                oblist.add(new Course(rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Credit"),rs.getString("Year"),rs.getString("Term"),
                        rs.getString("Difficulty"),rs.getString("Prerequisite")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addData();
    }

    @FXML
    public void enterSecondBtnTermTwo(ActionEvent event){
        btn_2_2 = (Button) event.getSource();
        table.getItems().removeAll(oblist);

        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Course WHERE YEAR='2' and Term='2'");
            while (rs.next()){
                oblist.add(new Course(rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Credit"),rs.getString("Year"),rs.getString("Term"),
                        rs.getString("Difficulty"),rs.getString("Prerequisite")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addData();
    }

    @FXML
    public void enterSecondBtnTermOne(ActionEvent event){
        btn_2_1 = (Button) event.getSource();
        table.getItems().removeAll(oblist);

        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Course WHERE YEAR='2' and Term='1'");
            while (rs.next()){
                oblist.add(new Course(rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Credit"),rs.getString("Year"),rs.getString("Term"),
                        rs.getString("Difficulty"),rs.getString("Prerequisite")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addData();
    }

    @FXML
    public void enterFirstBtnTermTwo(ActionEvent event){
        btn_1_2 = (Button) event.getSource();
        table.getItems().removeAll(oblist);

        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Course WHERE YEAR='1' and Term='2'");
            while (rs.next()){
                oblist.add(new Course(rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Credit"),rs.getString("Year"),rs.getString("Term"),
                        rs.getString("Difficulty"),rs.getString("Prerequisite")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addData();
    }
    @FXML
    public void enterFirstBtnTermOne(ActionEvent event){
        btn_1_1 = (Button) event.getSource();
        table.getItems().removeAll(oblist);

        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Course WHERE YEAR='1' and Term='1'");
            while (rs.next()){
                oblist.add(new Course(rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Credit"),rs.getString("Year"),rs.getString("Term"),
                        rs.getString("Difficulty"),rs.getString("Prerequisite")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addData();
    }




    public void pullData(){
        DBConnect db = new DBConnect();
        Connection connection = db.openDatabase();
        DBControl addDBControl = new DBControl(connection);


        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Course order by Year");
            while (rs.next()){
                oblist.add(new Course(rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Credit"),rs.getString("Year"),rs.getString("Term"),
                        rs.getString("Difficulty"),rs.getString("Prerequisite")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addData(){

        c_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        c_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        c_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        c_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        c_term.setCellValueFactory(new PropertyValueFactory<>("term"));
        c_diff.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        c_prerequisite.setCellValueFactory(new PropertyValueFactory<>("prerequisite"));


        table.setItems(oblist);
    }



    public void getID(String idFromMenu){
        id = idFromMenu;
        System.out.println(id);
    }


    @FXML
    public void gotoMenu(ActionEvent event){
        DBControl openDB = DBConnect.openDB();

        String name ;
        back = (Button) event.getSource();

        for (IdStudents s : openDB.readStudent()){
            if (id.equals(s.getId())){
                Stage stage = (Stage) back.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/Menu.fxml"));

                try {
                    stage.setScene(new Scene(loader.load(), 800, 600));
                    stage.setTitle("CS");
                    stage.setResizable(false);

                    Menu_controller controller = (Menu_controller) loader.getController();
                    name = s.getFirstName()+"\t"+s.getLastName();
                    controller.setID(id,name);


                    stage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }


    @FXML
    public void updateColor(TableColumn nowColumn) {
        nowColumn.setCellFactory(column -> {
            return new TableCell<Course, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory
                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty\
                        setText(item); //Put the String data in the cell
                        Course auxPerson = getTableView().getItems().get(getIndex());
                        if (auxPerson.getDifficulty().equals("3")) {
                            setText("Hard");
                            setStyle("-fx-font-weight: bold; -fx-background-color: rgba(223,0,11,0.45)");
                        } else if (auxPerson.getDifficulty().equals("2")) {
                            setText("Medium");
                            setStyle("-fx-font-weight: bold; -fx-background-color: rgba(15,86,223,0.45)");
                        } else if (auxPerson.getDifficulty().equals("1")) {
                            setText("Easy");
                            setStyle("-fx-font-weight: bold; -fx-background-color: rgba(0,202,24,0.45)");
                        }
                    }
                }
            };
        });
    }
}
