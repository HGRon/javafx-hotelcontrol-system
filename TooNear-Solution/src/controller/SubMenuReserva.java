package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SubMenuReserva  {

    @FXML
    private Pane buttonAgendar;

    @FXML
    private Pane buttonCheckIn;

    @FXML
    private Pane buttonCheckOut;

    @FXML
    private Pane rootPane;

    @FXML
    public void abrirTelaAgendar() throws IOException {
        buttonAgendar.setId("paneSubmenuRegistroToActive");
        buttonCheckIn.setId("paneSubmenuRegistroTo");
        buttonCheckOut.setId("paneSubmenuRegistroTo");
        Pane testePane = FXMLLoader.load(getClass().getResource("../fxml/TelaReserva.fxml"));
        rootPane.getChildren().setAll(testePane);
    }

    @FXML
    public void abrirTelaCheckIn() throws IOException {
        buttonAgendar.setId("paneSubmenuRegistroTo");
        buttonCheckIn.setId("paneSubmenuRegistroToActive");
        buttonCheckOut.setId("paneSubmenuRegistroTo");
        Pane testePane = FXMLLoader.load(getClass().getResource("../fxml/TelaReserva.fxml"));
        rootPane.getChildren().setAll(testePane);
    }

    @FXML
    public void abrirTelaCheckOut() throws IOException {
        buttonAgendar.setId("paneSubmenuRegistroTo");
        buttonCheckIn.setId("paneSubmenuRegistroTo");
        buttonCheckOut.setId("paneSubmenuRegistroToActive");
        Pane testePane = FXMLLoader.load(getClass().getResource("../fxml/TelaReserva.fxml"));
        rootPane.getChildren().setAll(testePane);
    }


}
