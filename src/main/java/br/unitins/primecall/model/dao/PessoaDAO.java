package br.unitins.primecall.model.dao;

import br.unitins.primecall.model.entities.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filipe
 */
public class PessoaDAO {

    private Connection conexao;
    private PreparedStatement comando;
    private int idPessoa = 0;
    Calendar c = Calendar.getInstance();
    Date data = c.getTime();
    DateFormat formataData = DateFormat.getDateInstance();

    public int inserir(Pessoa pessoa) throws SQLException {

        String dataAtual = formataData.format(data);
        conectar();
        String sql = "INSERT INTO pessoa (nome, sobrenome, cpf, telefone, dataCadastro) VALUES  (?, ?, ?, ?, ?)";
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        comando.setString(1, pessoa.getNome());
        comando.setString(2, pessoa.getSobrenome());
        comando.setString(3, pessoa.getCpf());
        comando.setString(4, pessoa.getTelefone());
        comando.setString(5, dataAtual);

        try {
            comando.executeUpdate();
            ResultSet rs = comando.getGeneratedKeys();

            rs.next();
            idPessoa = rs.getInt(1);

        } catch (SQLException ex) {

        } finally {
            fechar();
            return idPessoa;
        }
    }

    private void conectar() {
        try {
            conexao = Conexao.getConexao();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechar() {
        try {
            conexao.close();
            comando.close();
        } catch (SQLException ex) {
        }
    }
}
