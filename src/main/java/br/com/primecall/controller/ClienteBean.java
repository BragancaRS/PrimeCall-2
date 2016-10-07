package br.com.primecall.controller;

import br.unitins.primecall.model.dao.ClienteDAO;
import br.unitins.primecall.model.entities.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author filipe
 */
@ManagedBean
public class ClienteBean {

    private Cliente novoCliente = new Cliente();
    private ClienteDAO banco = new ClienteDAO();

    public String inserir() throws SQLException {
        if (banco.inserir(novoCliente)) {
            return "clienteTrue";
        }
        return "erro";
    }

    public ArrayList<SelectItem> getBuscarTodos() {
        ArrayList<SelectItem> listaClientes = new ArrayList();
        listaClientes = banco.buscarTodos();
        return listaClientes;
    }

    public ArrayList<Cliente> getBuscarTodosTable() {
        ArrayList<Cliente> listaClientes = new ArrayList();
        listaClientes = banco.buscarTodosTable();
        return listaClientes;
    }

    public Cliente getNovoCliente() {
        return novoCliente;
    }
}
