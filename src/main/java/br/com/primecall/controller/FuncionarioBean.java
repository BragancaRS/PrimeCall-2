package br.com.primecall.controller;

import br.unitins.primecall.model.dao.FuncionarioDAO;
import br.unitins.primecall.model.entities.Funcionario;;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author filipe
 */
@ManagedBean
public class FuncionarioBean {

    private Funcionario usuario = new Funcionario();
    private FuncionarioDAO banco = new FuncionarioDAO();
    private Boolean result;

    public String inserir() throws SQLException {
        if (banco.inserir(usuario)) {
            return "funcionarioTrue";
        }
        return "erro";
    }

    public ArrayList<SelectItem> getBuscarTodos() {
        ArrayList<SelectItem> listaFuncionarios = new ArrayList();
        listaFuncionarios = banco.buscarTodos();
        return listaFuncionarios;
    }

    public String autenticar() {
        try {
            result = banco.login(usuario);
            System.out.println(result);
            if (result) {
                return "sucesso";
            } else {
                limpar();
            }
        } catch (SQLException ex) {
        }
        return "sucesso";
    }

    public String limpar() {
        usuario.setLogin(new String());
        usuario.setSenha(new String());
        return "login";
    }

    public Funcionario getUsuario() {
        return usuario;
    }

    public void setUsuario(Funcionario usuario) {
        this.usuario = usuario;
    }
}
