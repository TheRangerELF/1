package Unidad5PF;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditarNota  implements Initializable {
    private  int indice;

    @FXML
    private AnchorPane contenedor;

    @FXML
    private JFXTextField titulo;

    @FXML
    private JFXTextArea descripcion;

    @FXML
    private JFXButton actualizar;

    @FXML
    private CheckBox guardar;

    @FXML
    private JFXComboBox<String> nota;
    private ArrayList<Notas> Nota;

    @FXML
    void actualizar(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:notas.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);

            String sql = "UPDATE apuntes SET " +
                    "titulo='" + titulo.getText() + "', " +
                    "descripcion='" + descripcion.getText()+"' "+
                    "WHERE idNota=" + Nota.get(indice).getIdNota();

            statement.execute(sql);
            Nota.get(indice).setTitulo(titulo.getText());
            Nota.get(indice).setDescripcion(descripcion.getText());

    }

    @FXML
    void cancelar(ActionEvent event) {
        Pane pane =(Pane) contenedor.getParent();
        pane.getChildren().remove(1);

    }

    @FXML
    void eliminar(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:notas.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(60);
        String sql = "DELETE FROM apuntes WHERE idNota=" + Nota.get(indice).getIdNota();

        statement.execute(sql);
        statement.close();
        connection.close();
        nota.getItems().remove(indice);
        Nota.remove(indice);
        titulo.setText("");
        descripcion.setText("");


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:notas.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(60);

            String sql = "SELECT * FROM apuntes";
            ResultSet resultSet = statement.executeQuery(sql);
            Nota = new ArrayList<Notas>();
            while (resultSet.next()){
                Nota.add(new Notas(
                        resultSet.getInt("idNota"),
                        resultSet.getString("titulo"),
                        resultSet.getString("descripcion")
                ));
                nota.getItems().add(resultSet.getString("titulo"));
            }
            nota.setOnAction(event -> {
                indice = nota.getSelectionModel().getSelectedIndex();
                titulo.setText(Nota.get(indice).getTitulo());
                descripcion.setText(Nota.get(indice).getDescripcion());
            });
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
