package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class BarraDeMenu implements Initializable {

    @FXML
    private Pane rootPane;

    public Pane Btn_Registro;
    public Pane Btn_Reserva;
    public Pane Btn_Controle;
    public int MenuIndice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void ControllRegistro() {
        MenuIndice = 1;
        CheckForm();
        try {
            loadRegistroUi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ControllReserva() {
        MenuIndice = 2;
        CheckForm();
        try {
            loadReservaUi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ControllControle() {
        MenuIndice = 3;
        CheckForm();
        try {
            loadControleUi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CheckForm() {

        if (MenuIndice == 1) {
            Btn_Registro.setId("paneToActive");
        } else {
            Btn_Registro.setId("paneTo");
        }

        if (MenuIndice == 2) {
            Btn_Reserva.setId("paneToActive");
        } else {
            Btn_Reserva.setId("paneTo");
        }

        if (MenuIndice == 3) {
            Btn_Controle.setId("paneToActive");
        } else {
            Btn_Controle.setId("paneTo");
        }

    }

    @FXML
    private void loadRegistroUi() throws IOException {
        Pane testePane = FXMLLoader.load(getClass().getResource("../fxml/SubmenuRegistro.fxml"));
        rootPane.getChildren().setAll(testePane);

    }

    @FXML
    private void loadReservaUi() throws IOException {
        Pane testePane = FXMLLoader.load(getClass().getResource("../fxml/SubmenuReserva.fxml"));
        rootPane.getChildren().setAll(testePane);
    }

    @FXML
    private void loadControleUi() throws IOException {
        Pane testePane = FXMLLoader.load(getClass().getResource("../fxml/TelaControle.fxml"));
        rootPane.getChildren().setAll(testePane);
    }
}

