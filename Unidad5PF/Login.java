package Unidad5PF;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class Login extends Application {

    @FXML
    private AnchorPane contenedor;

    @FXML
    private Pane Panel;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField clave;

    @FXML
    void bConectar(ActionEvent event) throws IOException {

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:notas.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String usr = usuario.getText();
            String clv = clave.getText();

            String consulta = "SELECT * FROM Usuario " + "WHERE usuario='"+usr+"' AND clave='"+clv+"'";

            ResultSet resultSet = statement.executeQuery(consulta);
            if (resultSet.next()){
                System.out.println("Usuario Correcto "+ resultSet.getString("usuario"));
                Parent Layout = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                contenedor.getChildren().add(Layout);
            }
            else {
                System.out.println("Usuario Incorrecto");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void bcontraseña(ActionEvent event) throws IOException {
        Parent Layout = FXMLLoader.load(getClass().getResource("CrearUsuario.fxml"));
        contenedor.getChildren().add(Layout);


    }

    @FXML
    void bpregunta(ActionEvent event) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent Layout = FXMLLoader.load(getClass().getResource("LoginNotas.fxml"));

      Scene Escena = new Scene(Layout);
      primaryStage.setScene(Escena);
      primaryStage.show();

    }
}
