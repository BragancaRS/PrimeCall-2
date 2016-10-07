package br.unitins.primecall.model.dao;

import br.unitins.primecall.model.entities.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.model.SelectItem;

/**
 *
 * @author filipe
 */
public class FuncionarioDAO {

    private Connection conexao;
    private PreparedStatement comando;
    private Statement comandoNormal;
    private ResultSet resultado;
    private ArrayList<SelectItem> listaFuncionarios = new ArrayList();
    private Funcionario funcionario = new Funcionario();

    public Boolean inserir(Funcionario novoFuncionario) throws SQLException {
        PessoaDAO pessoa = new PessoaDAO();
        int idPessoa = pessoa.inserir(novoFuncionario);

        conectar();
        String sql = "INSERT INTO funcionario (login, senha, perfil, habilitado, pessoa_id) VALUES  (?, ?, ?, ?, ?)";
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        comando.setString(1, novoFuncionario.getLogin());
        comando.setString(2, novoFuncionario.getSenha());
        comando.setInt(3, 1);
        comando.setBoolean(4, true);
        comando.setInt(5, idPessoa);

        try {
            comando.executeUpdate();
            fechar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<SelectItem> buscarTodos() {

        String sql = "SELECT funcionario.id, pessoa.nome, pessoa.sobrenome FROM pessoa, funcionario WHERE funcionario.pessoa_id = pessoa.id";

        try {
            conectar();
            resultado = comandoNormal.executeQuery(sql);
            listaFuncionarios.add(new SelectItem(null, "Funcionário Abertura"));
            String nomeCompleto = "";

            while (resultado.next()) {
                funcionario.setId(resultado.getInt("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setSobrenome(resultado.getString("sobrenome"));
                nomeCompleto = String.format("%s %s", funcionario.getNome(), funcionario.getSobrenome());
                listaFuncionarios.add(new SelectItem(funcionario.getId(), nomeCompleto));
            }
            return listaFuncionarios;
        } catch (SQLException ex) {
            return null;
        }
    }

    public Boolean login(Funcionario usuario) throws SQLException {
        conectar();
        String sql = "SELECT * FROM funcionario WHERE login = '" + usuario.getLogin() + "' and senha = '" + usuario.getSenha() + "'";
        resultado = comandoNormal.executeQuery(sql);

        if (resultado.next()) {
            usuario.setLogin(resultado.getString("login"));
            return true;
        } else {
            return false;
        }
    }

    private void conectar() throws SQLException {
        try {
            conexao = Conexao.getConexao();
            comandoNormal = conexao.createStatement();
        } catch (SQLException e) {
        }
    }

    private void fechar() {
        try {
            comando.close();
            comandoNormal.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }
}
