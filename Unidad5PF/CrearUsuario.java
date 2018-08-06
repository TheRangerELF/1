package Unidad5PF;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CrearUsuario {
    private ArrayList<Usuarios> Usuarios;
    private int inddice;

    @FXML
    private AnchorPane Panel;

    @FXML
    private Pane contenedor;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField clave;

    @FXML
    void bCancelar(ActionEvent event) throws IOException {
        Parent Layout = FXMLLoader.load(getClass().getResource("LoginNotas.fxml"));
        contenedor.getChildren().add(Layout);
    }

    @FXML
    void bCrear(ActionEvent event) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:notas.db");

        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

        String sql = "INSERT INTO Usuario (" + "usuario, clave) VALUES (" +
                "'" + usuario.getText() + "'," +
                "'" + clave.getText() +"'"+
                ")";
        statement.execute(sql);
        usuario.setText("");
        clave.setText("");

    }

}
