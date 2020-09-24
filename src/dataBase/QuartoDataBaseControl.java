package dataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import object.Quarto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuartoDataBaseControl {

        public void criar(Quarto quarto) throws SQLException {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement("INSERT INTO quarto VALUES " +
                        "(default, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                preparedStatement.setString(1, quarto.getAndar());
                preparedStatement.setString(2, quarto.getNumero());
                preparedStatement.setString(3, quarto.getTipo());
                preparedStatement.setInt(4, quarto.getCapacidade());
                preparedStatement.setInt(5, quarto.getQtdBanheiros());
                preparedStatement.setInt(6, quarto.getQtdQuartos());
                preparedStatement.setInt(7, quarto.getQtdCamasCasal());
                preparedStatement.setInt(8, quarto.getQtdCamasSolteiro());
                preparedStatement.setFloat(9, quarto.getPreco());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                ConnectionFactory.closeConnection(connection, preparedStatement);
            }
        }

        public void deletar(int id) throws SQLException {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement("DELETE FROM quarto WHERE idquarto =  ?");

                preparedStatement.setInt(1, id);


                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                ConnectionFactory.closeConnection(connection, preparedStatement);
            }
        }

        public void atualizar(Quarto quarto) throws SQLException {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement("UPDATE quarto SET preco = ? , capacidade = ?, " +
                        "qtdCamasSolteiro = ?, qtdCamasCasal = ?, qtdQuartos = ?, qtdBanheiros = ?, tipo = ?" +
                                "WHERE idquarto = ?");

                preparedStatement.setFloat(1, quarto.getPreco());
                preparedStatement.setInt(2, quarto.getCapacidade());
                preparedStatement.setInt(3, quarto.getQtdCamasSolteiro());
                preparedStatement.setInt(4, quarto.getQtdCamasCasal());
                preparedStatement.setInt(5, quarto.getQtdQuartos());
                preparedStatement.setInt(6, quarto.getQtdBanheiros());
                preparedStatement.setString(7, quarto.getTipo());
                preparedStatement.setInt(8, quarto.getId_quarto());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                ConnectionFactory.closeConnection(connection, preparedStatement);
            }
        }

        public ObservableList<Quarto> filtrarTemporario(int capacidade) throws SQLException {

            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            final ObservableList<Quarto> quartos = FXCollections.observableArrayList( );

            try {
                preparedStatement = connection.prepareStatement("SELECT idquarto, andar, numero, preco " +
                        "FROM TooNearSolutions.quarto WHERE capacidade > ?");

                preparedStatement.setInt(1, capacidade);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Quarto quarto = new Quarto(0 , "", "", "", 0, 0,
                            0, 0, 0, 0);

                    quarto.setId_quarto(resultSet.getInt("idquarto"));
                    quarto.setAndar(resultSet.getString("andar"));
                    quarto.setNumero(resultSet.getString("numero"));
                    quarto.setPreco(resultSet.getFloat("preco"));
                    quartos.add(quarto);
                }

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
            }

            return quartos;
        }

        public ObservableList<Quarto> filtrar(String tipo, String conteudo) throws SQLException {

            tipo = tipo.toLowerCase();
            conteudo = conteudo.toUpperCase();

            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            final ObservableList<Quarto> quartos = FXCollections.observableArrayList( );

            try {
                preparedStatement = connection.prepareStatement("SELECT idquarto, andar, numero, preco " +
                        "FROM TooNearSolutions.quarto WHERE " + tipo + " = ? ORDER BY " + tipo);

                preparedStatement.setString(1, conteudo);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Quarto quarto = new Quarto(0 , "", "", "", 0, 0,
                            0, 0, 0, 0);

                    quarto.setId_quarto(resultSet.getInt("idquarto"));
                    quarto.setAndar(resultSet.getString("andar"));
                    quarto.setNumero(resultSet.getString("numero"));
                    quarto.setPreco(resultSet.getFloat("preco"));
                    quartos.add(quarto);
                }

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
            }

            return quartos;
        }

    public Quarto filtrar(int id) throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Quarto quarto = new Quarto(0 , "", "", "", 0, 0,
                0, 0, 0, 0);

        try {
            preparedStatement = connection.prepareStatement("SELECT idquarto, andar, numero, preco, capacidade," +
                    "qtdCamasSolteiro, qtdCamasCasal, qtdQuartos, qtdBanheiros, tipo FROM TooNearSolutions.quarto " +
                    "WHERE idquarto = " + id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            quarto.setId_quarto(resultSet.getInt("idquarto"));
            quarto.setAndar(resultSet.getString("andar"));
            quarto.setNumero(resultSet.getString("numero"));
            quarto.setPreco(resultSet.getFloat("preco"));
            quarto.setCapacidade(resultSet.getInt("capacidade"));
            quarto.setQtdCamasSolteiro(resultSet.getInt("qtdCamasSolteiro"));
            quarto.setQtdCamasCasal(resultSet.getInt("qtdCamasCasal"));
            quarto.setQtdQuartos(resultSet.getInt("qtdQuartos"));
            quarto.setQtdBanheiros(resultSet.getInt("qtdBanheiros"));
            quarto.setTipo(resultSet.getString("tipo"));

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
        }

        return quarto;
    }

    public boolean checarQuarto(Quarto quarto) throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idCheck = 0;

        try {
            preparedStatement = connection.prepareStatement("SELECT idquarto FROM TooNearSolutions.quarto " +
                    "WHERE andar = ? AND numero = ?");

            preparedStatement.setString(1, quarto.getAndar());
            preparedStatement.setString(2, quarto.getNumero());

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                idCheck = -1;
            }



        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
        }

        if(idCheck == -1) {
            return true;
        } else {
            return false;
        }

    }


    public ObservableList<Quarto> listarTudo() throws SQLException {

            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            final ObservableList<Quarto> quartos = FXCollections.observableArrayList( );

            try {
                preparedStatement = connection.prepareStatement("SELECT idquarto, andar, numero, preco " +
                        "FROM TooNearSolutions.quarto ORDER BY andar");

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Quarto quarto = new Quarto(0 , "", "", "", 0, 0,
                            0, 0, 0, 0);

                    quarto.setId_quarto(resultSet.getInt("idquarto"));
                    quarto.setAndar(resultSet.getString("andar"));
                    quarto.setNumero(resultSet.getString("numero"));
                    quarto.setPreco(resultSet.getFloat("preco"));
                    quartos.add(quarto);
                }

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
            }

            return quartos;
        }

}
