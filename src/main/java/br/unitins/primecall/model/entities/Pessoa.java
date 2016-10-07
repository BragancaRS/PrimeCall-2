package br.unitins.primecall.model.entities;

/**
 *
 * @author filipe
 */
public class Pessoa {

    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String dataCadastro;

    public Pessoa() {
        this.id = 0;
        this.nome = "";
        this.sobrenome = "";
        this.cpf = "";
        this.telefone = "";
        this.dataCadastro = "";
    }

    public Pessoa(int id, String nome, String sobrenome, String cpf, String telefone, String dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
