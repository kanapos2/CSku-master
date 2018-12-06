package Controller;

import Models.DBConnect;
import Models.DBControl;
import Models.IdStudents;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu_controller {

    private String getIDtonextPage = "";

    @FXML
    private ImageView img;

    @FXML
    private Label id , name;

    @FXML
    private Button btn_all , btn_regis , btn_logOut;

    @FXML
    public void setID(String idStu , String flname){
        id.setText(idStu);
        name.setText(flname);

        getIDtonextPage = idStu;

        DBControl openDB = DBConnect.openDB();

        for (IdStudents s : openDB.readStudent()){
            if (idStu.equals(s.getId())){
                if (s.getPath()==null){
                    img.setImage(new Image("person/default.png"));
                }
                else {
                    img.setImage(new Image("person/"+s.getPath()));
                }
            }
        }

    }

    @FXML
    public void allCourse (ActionEvent event){
        btn_all = (Button) event.getSource();

        Stage stage = (Stage) btn_all.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/Allcourse.fxml"));

        try {
            stage.setScene(new Scene(loader.load(), 1135, 613));
            stage.setTitle("All course for CS");
            stage.setX(150);
            stage.setResizable(false);
            AllCourse_controller controller = (AllCourse_controller) loader.getController();
            controller.getID(getIDtonextPage);



            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @FXML
    public void register(ActionEvent event){
        btn_regis = (Button) event.getSource();


        Stage stage = (Stage) btn_regis.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/Register.fxml"));

        try {
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.setTitle("Register CS");
            stage.setResizable(false);

            Register_controller controller = (Register_controller) loader.getController();
            controller.getID(getIDtonextPage);



            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @FXML
    public void logOut(ActionEvent event){
        btn_logOut = (Button) event.getSource();

        Stage stage = (Stage) btn_logOut.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/Login.fxml"));

        try {
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.setTitle("CS");
            stage.setResizable(false);

            Login_controller controller = (Login_controller) loader.getController();



            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }



}
