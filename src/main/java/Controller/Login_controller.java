package Controller;

import Models.DBConnect;
import Models.DBControl;
import Models.IdStudents;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class Login_controller {


    @FXML
    private TextField id ;

    @FXML
    private Button btn_enter ;


    @FXML
    public void enterToMainMenu(ActionEvent event){
        DBControl openDB = DBConnect.openDB();

        int count = 0;
        String name ;
        btn_enter = (Button) event.getSource();

        for (IdStudents s : openDB.readStudent()){
            if (id.getText().equals(s.getId())) {
                count += 1;
                Stage stage = (Stage) btn_enter.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../UI/Menu.fxml"));

                try {
                    stage.setScene(new Scene(loader.load(), 800, 600));
                    stage.setTitle("Menu Register");
                    stage.setResizable(false);

                    Menu_controller controller = (Menu_controller) loader.getController();
                    name = s.getFirstName()+ "\t"+s.getLastName();
                    controller.setID(id.getText(),name);


                    stage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }

        if (count != 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setContentText("Incorrect ID (404)  Please try again !!!");
            alert.show();

        }

    }
}
