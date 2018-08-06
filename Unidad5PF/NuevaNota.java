package Unidad5PF;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NuevaNota {

     private ArrayList<Notas> Notas;
     private int indice;

    @FXML
    private AnchorPane panel;

    @FXML
    private Pane contenedor;

    @FXML
    private JFXTextField titulo;

    @FXML
    private JFXTextArea descripcion;

    @FXML
    private CheckBox guardar;
    @FXML
    private Button crear;

    @FXML
    void cancelar(ActionEvent event) {
        Pane pane = (Pane) panel.getParent();
        pane.getChildren().remove(1);

    }

    @FXML
    void crear(ActionEvent event) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:notas.db");

        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "INSERT INTO apuntes ( titulo, descripcion) VALUES (" +
                "'" + titulo.getText() + "'," +
                "'" + descripcion.getText() +"'"+
                ")";
        statement.execute(sql);
        titulo.setText("");
        descripcion.setText("");

    }

    @FXML
    void guardar(ActionEvent event)  {

    }




}
