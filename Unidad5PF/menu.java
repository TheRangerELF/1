package Unidad5PF;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class menu  implements  Initializable{

    private ArrayList<Notas> Notas;
    private ArrayList<Usuarios> Usuarios;

    @FXML
    private AnchorPane contenedor;

    @FXML
    private TilePane listaActividades;


    @FXML
    void acercaDe(ActionEvent event) {

    }

    @FXML
    void crearNota(ActionEvent event) throws IOException {
        Parent Layout = FXMLLoader.load(getClass().getResource("NuevaNota.fxml"));
        contenedor.getChildren().add(Layout);


    }

    @FXML
    void editar(ActionEvent event) throws IOException {
        Parent Layout = FXMLLoader.load(getClass().getResource("EditarNota.fxml"));
        contenedor.getChildren().add(Layout);

    }

    @FXML
    void salir(ActionEvent event) {

        Platform.exit();

    }
    private GridPane crearNota(String titulo, String descripcion) {
        GridPane gridPane = new GridPane();

        gridPane.setMaxWidth(400);
        gridPane.setMinWidth(200);

        gridPane.setVgap(15);
        gridPane.setHgap(15);
        gridPane.setPadding(new Insets(5));


        Label Titulo = new Label(titulo);
        Reflection r = new Reflection();
        r.setFraction(0.5f);
        Titulo.setEffect(r);

        gridPane.add(Titulo, 0, 0);

        gridPane.add(new Label(descripcion), 0, 1);

        return gridPane;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listaActividades.setHgap(10);
        listaActividades.setVgap(10);

        try {

            Connection connection = DriverManager.getConnection("jdbc:sqlite:notas.db");

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(60);
            String sql = "SELECT * FROM apuntes";
            ResultSet resultSet = statement.executeQuery(sql);
            Notas = new ArrayList<Notas>();
            while(resultSet.next()){
               Notas.add(new Notas(
                       resultSet.getInt("idNota"),
                       resultSet.getString("titulo"),
                       resultSet.getString("descripcion")
               ));
               listaActividades.getChildren().add(crearNota(
                       resultSet.getString("titulo"),
                       resultSet.getString("descripcion")
               ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
