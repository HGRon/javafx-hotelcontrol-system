package controller;

import dataBase.QuartoDataBaseControl;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import object.Quarto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroQuarto implements Initializable {

    private CadastroQuarto cadastroQuarto;

    @FXML
    private TextField fd_banheiro;

    @FXML
    private TextField fd_quarto;

    @FXML
    private TextField fd_tipo ;

    @FXML
    private TextField fd_casal;

    @FXML
    private TextField fd_numero;

    @FXML
    private TextField fd_andar;

    @FXML
    private Button buttonCadastrarQuarto;

    @FXML
    private Button buttonCancelar;

    @FXML
    private TextField fd_solteiro;

    @FXML
    private TextField fd_preco;

    @FXML
    private Pane mensagemPane;

    @FXML
    private Button mensagemButton;

    @FXML
    private Label mensagemLabel;

    @FXML
    private Text tituloTela;

    private Stage stage;

    private Quarto operarQuarto = new Quarto(0, "", "", "", 0, 0, 0
    ,0, 0, 0);

    public void abrirTelaDeCadastro(boolean isToEdit, int id) throws SQLException {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/TelaRegistroDoQuarto.fxml"));
            Parent rootCadastroQuarto = (Parent) fxmlLoader.load();

            if (isToEdit) {
                cadastroQuarto = fxmlLoader.getController();
                cadastroQuarto.editarQuarto(id);
            }

            stage = new Stage();
            stage.setScene(new Scene(rootCadastroQuarto));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setMaxWidth(500);
            stage.setMaxHeight(430);
            stage.setMinWidth(500);
            stage.setMaxHeight(430);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void alertaDeConfirmacao() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ATENÇÃO");
        alert.setHeaderText("Quarto registrado com sucesso!");
        alert.showAndWait();
    }

    public void alertaDeEdicao() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ATENÇÃO");
        alert.setHeaderText("Quarto editado com sucesso!");
        alert.showAndWait();
    }

    @FXML
    public void editarQuarto(int id) throws SQLException {

        tituloTela.setText("EDITAR");

        Quarto quarto = null;
        QuartoDataBaseControl novoQuarto = new QuartoDataBaseControl();
        quarto = novoQuarto.filtrar(id);

        fd_andar.setText(quarto.getAndar());
        fd_numero.setText(quarto.getNumero());
        fd_tipo.setText(quarto.getTipo());

        fd_andar.setDisable(true);
        fd_numero.setDisable(true);

        //cast
        String sPreco = Float.toString(quarto.getPreco());
        String sQuartos = Integer.toString(quarto.getQtdQuartos());
        String sBanheiros = Integer.toString(quarto.getQtdBanheiros());
        String sCamaCasal = Integer.toString(quarto.getQtdCamasCasal());
        String sCamaSolteiro = Integer.toString(quarto.getQtdCamasSolteiro());

        fd_preco.setText(sPreco);
        fd_quarto.setText(sQuartos);
        fd_banheiro.setText(sBanheiros);
        fd_casal.setText(sCamaCasal);
        fd_solteiro.setText(sCamaSolteiro);

        operarQuarto.setId_quarto(quarto.getId_quarto());

    }

    public void registarQuarto() throws SQLException {

        String andar = fd_andar.getText().toUpperCase();
        String numero = fd_numero.getText().toUpperCase();
        String tipo = fd_tipo.getText().toUpperCase();
        String preco = fd_preco.getText();
        String qtdBanheiros = fd_banheiro.getText();
        String qtdQuartos = fd_quarto.getText();
        String qtdCamasC = fd_casal.getText();
        String qtdCamasS = fd_solteiro.getText();

        if(fd_andar.getText().isEmpty() ||fd_numero.getText().isEmpty() || fd_tipo.getText().isEmpty()
                || fd_preco.getText().isEmpty() || fd_banheiro.getText().isEmpty() || fd_quarto.getText().isEmpty()
                || fd_casal.getText().isEmpty() || fd_solteiro.getText().isEmpty()) {
            mensagemPane.setVisible(true);
        } else {
            //cast
            float fPreco = Float.parseFloat(preco);
            int iQtdBanheiros = Integer.parseInt(qtdBanheiros);
            int iQtdQuartos = Integer.parseInt(qtdQuartos);
            int iQtdCamasC = Integer.parseInt(qtdCamasC);
            int iQtdCamasS = Integer.parseInt(qtdCamasS);

            int capacidade = iQtdCamasC * 2 + iQtdCamasS;

            QuartoDataBaseControl novoQuarto = new QuartoDataBaseControl();

            int id = operarQuarto.getId_quarto();

            Quarto quarto = new Quarto(0 , andar, numero, tipo, capacidade, iQtdBanheiros, iQtdQuartos
                    ,iQtdCamasC, iQtdCamasS, fPreco);

            quarto.setId_quarto(id);

            if (tituloTela.getText() == "EDITAR") {
                novoQuarto.atualizar(quarto);
                fecharAplicacao();
                alertaDeEdicao();
            } else {

                if(novoQuarto.checarQuarto(quarto)){
                    mensagemPane.setVisible(true);
                    mensagemLabel.setText("QUARTO JÁ CADASTRADO");
                } else {
                    novoQuarto.criar(quarto);
                    fecharAplicacao();
                    alertaDeConfirmacao();
                }
            }
        }
    }

    @FXML
    public void fecharAplicacao() {
        Stage stage = (Stage) buttonCancelar.getScene().getWindow();
        stage.close();
    }

    public void fecharMenssagem() {
        mensagemPane.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
