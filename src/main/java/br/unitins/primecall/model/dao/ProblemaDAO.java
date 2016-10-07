package br.unitins.primecall.model.dao;

import br.unitins.primecall.model.entities.Problema;
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
public class ProblemaDAO {

    private Statement comando;
    private Connection conexao;

    public Boolean inserir(Problema problema) {
        String sql = "INSERT INTO problema VALUES (null, '" + problema.getDescricao() + "')";

        conectar();
        try {
            comando.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProblemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<SelectItem> buscarTodos() {

        String sql = "SELECT * FROM problema";
        ArrayList<SelectItem> listaProblemas = new ArrayList();

        try {
            conectar();
            ResultSet resultado = comando.executeQuery(sql);
            listaProblemas.add(new SelectItem(null, "Selecione um Problema"));
            Problema problema = new Problema();

            while (resultado.next()) {
                problema.setId(resultado.getInt("id"));
                problema.setDescricao(resultado.getString("descricao"));
                listaProblemas.add(new SelectItem(problema.getId(), problema.getDescricao()));
            }

            fechar();
            return listaProblemas;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void conectar() {
        try {
            conexao = Conexao.getConexao();
            comando = conexao.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProblemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fechar() {
        try {
            conexao.close();
            comando.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProblemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
