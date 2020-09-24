package controller;

import dataBase.QuartoDataBaseControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import object.Quarto;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuRegistro implements Initializable {

    public List<Quarto> quartos = new ArrayList<>();

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private TableColumn tabelaValor;

    @FXML
    private TableView tabelaPrincipal;

    @FXML
    private TableColumn tabelaNumero;

    @FXML
    private TableColumn tabelaAndar;

    @FXML
    private TextField coteudoTextFild;

    @FXML
    private Button buttonEditarQuarto;

    @FXML
    public void abrirTelaDeCadastro() throws SQLException {
        CadastroQuarto novoTelaCadastro = new CadastroQuarto();
        novoTelaCadastro.abrirTelaDeCadastro(false,-1);
    }

    @FXML
    public void abrirTelaDeEdicao() throws SQLException {
        if(tabelaPrincipal.getSelectionModel().getSelectedItem() != null) {
            Quarto quarto = (Quarto) tabelaPrincipal.getSelectionModel().getSelectedItem();
            CadastroQuarto novoTelaCadastro = new CadastroQuarto();
            novoTelaCadastro.abrirTelaDeCadastro(true, quarto.getId_quarto());
        }
    }

    public void filtrarQuartos() throws SQLException {

        final ObservableList<Quarto> quartos = FXCollections.observableArrayList( );

        int tamLista = 0;
        String conteudoChoiceBox = (String) choiceBox.getSelectionModel().getSelectedItem();
        String conteudoTextFildText = coteudoTextFild.getText();

        if (conteudoChoiceBox != null && coteudoTextFild.getText().isEmpty() == false) {
            QuartoDataBaseControl filtraQuartos = new QuartoDataBaseControl();
            for (Quarto quarto : (filtraQuartos.filtrar(conteudoChoiceBox, conteudoTextFildText))) {
                tamLista++;
                quartos.add(quarto);
            }
        } else {
            QuartoDataBaseControl todosQuartos = new QuartoDataBaseControl();
            for (Quarto quarto : (todosQuartos.listarTudo())) {
                tamLista++;
                quartos.add(quarto);
            }
        }

        if(tamLista <= 9) {
            tabelaPrincipal.setId("table-view-disable");
        } else {
            tabelaPrincipal.setId("table-view");
        }

        double tamToTabela = tamLista * 48;

        if (tamToTabela <= 464) {
            tabelaPrincipal.setPrefHeight(tamToTabela);
            tabelaPrincipal.setMinHeight(tamToTabela);
            tabelaPrincipal.setMaxHeight(tamToTabela);
        } else {
            tabelaPrincipal.setPrefHeight(464);
            tabelaPrincipal.setMinHeight(464);
            tabelaPrincipal.setMaxHeight(464);
        }

        tabelaAndar.setCellValueFactory(new PropertyValueFactory<Quarto, String>("andar"));
        tabelaNumero.setCellValueFactory(new PropertyValueFactory<Quarto, String>("numero"));
        tabelaValor.setCellValueFactory(new PropertyValueFactory<Quarto, Float>("preco"));
        tabelaPrincipal.setItems(quartos);
        tabelaPrincipal.setFixedCellSize(48);
    }

    public void tableToEdit() {
        if(tabelaPrincipal.getSelectionModel().getSelectedItem() != null) {
            buttonEditarQuarto.setVisible(true);
        }
    }

    public void deleteItem() {
        if(tabelaPrincipal.getSelectionModel().getSelectedItem() != null) {
            tabelaPrincipal.setOnKeyPressed(d -> {
                final KeyCombination DELETE = new KeyCodeCombination(KeyCode.DELETE);
                if (DELETE.match(d)) {
                    Quarto quarto = (Quarto) tabelaPrincipal.getSelectionModel().getSelectedItem();
                    QuartoDataBaseControl deletarQuarto = new QuartoDataBaseControl();
                    try {
                        if(deletetarConfirmacao()) {
                            deletarQuarto.deletar(quarto.getId_quarto());
                            filtrarQuartos();
                            aletaDeConfirmacao();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();

                    }
                }
            });
        }
    }

    public boolean deletetarConfirmacao() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ATENÇÃO");
        alert.setHeaderText("Deseja realmente deletar?");
        alert.showAndWait();
        ButtonType alertaResultado = alert.getResult();
        if (alertaResultado == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void aletaDeConfirmacao() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ATENÇÃO");
        alert.setHeaderText("Quarto deletado com sucesso!");
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.setItems(FXCollections.observableArrayList("Andar", "Numero", "Preco"));
        buttonEditarQuarto.setVisible(false);
    }


}
