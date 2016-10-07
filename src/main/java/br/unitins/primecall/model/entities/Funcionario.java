package br.unitins.primecall.model.entities;

/**
 *
 * @author filipe
 */
public class Funcionario extends Pessoa {

    private int id;
    private String login;
    private String senha;
    private int perfil; // 1 - Atendente # 2 - Técnico # 3 - Gerente
    private boolean habilitado;

    public Funcionario() {
        super();
    }

    public Funcionario(int id, String login, String senha, int perfil, boolean habilitado) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.habilitado = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

}
