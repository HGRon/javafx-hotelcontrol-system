package object;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Quarto {

    private SimpleIntegerProperty id_quarto;
    private SimpleStringProperty andar;
    private SimpleStringProperty numero;
    private SimpleStringProperty tipo;
    private SimpleIntegerProperty capacidade;
    private SimpleIntegerProperty qtdBanheiros;
    private SimpleIntegerProperty qtdQuartos;
    private SimpleIntegerProperty qtdCamasCasal;
    private SimpleIntegerProperty qtdCamasSolteiro;
    private SimpleFloatProperty preco;

    public Quarto(int id_quarto, String andar, String numero, String tipo, int capacidade, int qtdBanheiros,
                  int qtdQuartos, int qtdCamasCasal, int qtdCamasSolteiro, float preco) {
        this.id_quarto = new SimpleIntegerProperty(id_quarto);
        this.andar = new SimpleStringProperty(andar);
        this.numero  = new SimpleStringProperty(numero);
        this.tipo  = new SimpleStringProperty(tipo);
        this.capacidade = new SimpleIntegerProperty(capacidade);
        this.qtdBanheiros = new SimpleIntegerProperty(qtdBanheiros);
        this.qtdQuartos = new SimpleIntegerProperty(qtdQuartos);
        this.qtdCamasCasal = new SimpleIntegerProperty(qtdCamasCasal);
        this.qtdCamasSolteiro = new SimpleIntegerProperty(qtdCamasSolteiro);
        this.preco = new SimpleFloatProperty(preco);
    }

    @Override
    public String toString() {
        return "Quarto{" +
                "id_quarto=" + id_quarto +
                ", andar=" + andar +
                ", numero=" + numero +
                ", tipo=" + tipo +
                ", capacidade=" + capacidade +
                ", qtdBanheiros=" + qtdBanheiros +
                ", qtdQuartos=" + qtdQuartos +
                ", qtdCamasCasal=" + qtdCamasCasal +
                ", qtdCamasSolteiro=" + qtdCamasSolteiro +
                ", preco=" + preco +
                '}';
    }

    public int getId_quarto() {
        return id_quarto.get();
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto.set(id_quarto);
    }


    public String getAndar() {
        return andar.get();
    }

    public void setAndar(String andar) {
        this.andar.set(andar);
    }

    public String getNumero() {
        return numero.get();
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public int getCapacidade() {
        return capacidade.get();
    }

    public void setCapacidade(int capacidade) {
        this.capacidade.set(capacidade);
    }

    public int getQtdBanheiros() {
        return qtdBanheiros.get();
    }

    public void setQtdBanheiros(int qtdBanheiros) {
        this.qtdBanheiros.set(qtdBanheiros);
    }

    public int getQtdQuartos() {
        return qtdQuartos.get();
    }

    public void setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos.set(qtdQuartos);
    }

    public int getQtdCamasCasal() {
        return qtdCamasCasal.get();
    }

    public void setQtdCamasCasal(int qtdCamasCasal) {
        this.qtdCamasCasal.set(qtdCamasCasal);
    }

    public int getQtdCamasSolteiro() {
        return qtdCamasSolteiro.get();
    }

    public void setQtdCamasSolteiro(int qtdCamasSolteiro) {
        this.qtdCamasSolteiro.set(qtdCamasSolteiro);
    }

    public float getPreco() {
        return preco.get();
    }

    public void setPreco(float preco) {
        this.preco.set(preco);
    }
}
