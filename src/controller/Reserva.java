package controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import dataBase.QuartoDataBaseControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import object.Quarto;

import java.sql.SQLException;

public class Reserva {

    @FXML
    private Button buttonMaisCrianca;

    @FXML
    private Button buttonMaisAdulto;

    @FXML
    private Button buttonMenosAdulto;

    @FXML
    private Button buttonMenosCrianca;

    @FXML
    private Label labelQtdAdulto;

    @FXML
    private Label labelQtdCrianca;

    @FXML
    private Label valorQuarto;

    @FXML
    private TableColumn tabelaValor;

    @FXML
    private TableView tabelaPrincipal;

    @FXML
    private TableColumn tabelaNumero;

    @FXML
    private TableColumn tabelaAndar;

    @FXML
    public void maisCrianca() throws SQLException {

        int qtdCrianca = Integer.parseInt(labelQtdCrianca.getText());
        qtdCrianca++;
        String sQtdCrianca = Integer.toString(qtdCrianca);
        labelQtdCrianca.setText(sQtdCrianca);
        filtrarQuartos();
    }

    @FXML
    public void maisAdulto() throws SQLException {

        int qtdAdulto = Integer.parseInt(labelQtdAdulto.getText());
        qtdAdulto++;
        String sQtdAdulto = Integer.toString(qtdAdulto);
        labelQtdAdulto.setText(sQtdAdulto);
        filtrarQuartos();
    }

    @FXML
    public void menosCrianca() throws SQLException {

        int qtdCrianca = Integer.parseInt(labelQtdCrianca.getText());
        if(qtdCrianca != 0) {
            qtdCrianca--;
        }
        String sQtdCrianca = Integer.toString(qtdCrianca);
        labelQtdCrianca.setText(sQtdCrianca);
        filtrarQuartos();
    }

    @FXML
    public void menosAdulto() throws SQLException {

        int qtdAdulto = Integer.parseInt(labelQtdAdulto.getText());
        if(qtdAdulto != 0) {
            qtdAdulto--;
        }
        String sQtdAdulto = Integer.toString(qtdAdulto);
        labelQtdAdulto.setText(sQtdAdulto);
        filtrarQuartos();
    }

    public void filtrarQuartos() throws SQLException {

        int qtdCrianca = Integer.parseInt(labelQtdCrianca.getText());
        int qtdAdulto = Integer.parseInt(labelQtdAdulto.getText());

        int capacidade, tamLista = 0;

        capacidade = qtdAdulto + qtdCrianca;

        final ObservableList<Quarto> quartos = FXCollections.observableArrayList( );

        QuartoDataBaseControl filtraQuartos = new QuartoDataBaseControl();
        filtraQuartos.filtrarTemporario(capacidade);

        for (Quarto quarto : (filtraQuartos.filtrarTemporario(capacidade))) {
            tamLista++;
            quartos.add(quarto);
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

    @FXML
    public void pegarInformacoes() {
        if(tabelaPrincipal.getSelectionModel().getSelectedItem() != null) {
            Quarto quarto = (Quarto) tabelaPrincipal.getSelectionModel().getSelectedItem();
            String sValorQuarto = "R$" + Float.toString(quarto.getPreco());
            valorQuarto.setText(sValorQuarto);
        }
    }


}
