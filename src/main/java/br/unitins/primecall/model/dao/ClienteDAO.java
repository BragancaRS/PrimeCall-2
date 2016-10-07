package br.unitins.primecall.model.dao;


import br.unitins.primecall.model.entities.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;


/**
 *
 * @author filipe
 */
public class ClienteDAO {

    private Connection conexao;
    private Statement comando;

    private Cliente cliente = new Cliente();
    private ArrayList<SelectItem> listaClientes = new ArrayList();
    private ArrayList<Cliente> listaClientesTable = new ArrayList();

    public Boolean inserir(Cliente novoCliente) throws SQLException {

        PessoaDAO pessoa = new PessoaDAO();
        int idPessoa = pessoa.inserir(novoCliente);

        String sql = "INSERT INTO cliente VALUES (" + idPessoa + ", null)";

        try {
            conectar();
            comando.executeUpdate(sql);
            fechar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<SelectItem> buscarTodos() {

        String sql = "SELECT cliente.id, pessoa.nome, pessoa.sobrenome, pessoa.cpf, pessoa.telefone, pessoa.dataCadastro "
                + "FROM pessoa, cliente WHERE cliente.pessoa_id = pessoa.id";

        try {
            conectar();
            ResultSet resultado = comando.executeQuery(sql);
            listaClientes.add(new SelectItem(null, "Selecione um Cliente"));
            String nomeCompleto = "";

            while (resultado.next()) {
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setSobrenome(resultado.getString("sobrenome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setDataCadastro(resultado.getString("dataCadastro"));
                nomeCompleto = String.format("%s %s", cliente.getNome(), cliente.getSobrenome());
                listaClientes.add(new SelectItem(cliente.getId(), nomeCompleto));
            }

            fechar();
            return listaClientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Cliente> buscarTodosTable() {

        String sql = "SELECT cliente.id, pessoa.nome, pessoa.sobrenome, pessoa.cpf, pessoa.telefone, pessoa.dataCadastro "
                + "FROM pessoa, cliente WHERE cliente.pessoa_id = pessoa.id";

        try {
            conectar();
            ResultSet resultado = comando.executeQuery(sql);

            while (resultado.next()) {
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setSobrenome(resultado.getString("sobrenome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setDataCadastro(resultado.getString("dataCadastro"));
                listaClientesTable.add(cliente);
            }

            fechar();
            return listaClientesTable;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void conectar() {
        try {
            conexao = Conexao.getConexao();
            comando = conexao.createStatement();
        } catch (SQLException ex) {
        }
    }

    private void fechar() {
        try {
            conexao.close();
            comando.close();
        } catch (SQLException ex) {
        }
    }

    public ArrayList<SelectItem> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<SelectItem> listaClientes) {
        this.listaClientes = listaClientes;
    }
}
