package br.com.primecall.controller;

import br.unitins.primecall.model.dao.ProblemaDAO;
import br.unitins.primecall.model.entities.Problema;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author filipe
 */
@ManagedBean
public class ProblemaBean {

    private Problema novoProblema = new Problema();
    private ProblemaDAO banco = new ProblemaDAO();

    public void inserir() {

        try {
            banco.inserir(novoProblema);
            FacesMessage mensagem = new FacesMessage("Cadastrado com sucesso!");
            FacesContext.getCurrentInstance().addMessage("inserido", mensagem);
            limpar();
        } catch (Exception e) {
            System.out.println("Estourou a exceção ao inserir o problema.");
        }
    }

    public ArrayList<SelectItem> getBuscarTodos() {
        ArrayList<SelectItem> listaProblemas = new ArrayList();
        listaProblemas = banco.buscarTodos();
        return listaProblemas;
    }

    private void limpar() {
        novoProblema = new Problema();
    }

    public Problema getNovoProblema() {
        return novoProblema;
    }

    public void setNovoProblema(Problema novoProblema) {
        this.novoProblema = novoProblema;
    }
}
