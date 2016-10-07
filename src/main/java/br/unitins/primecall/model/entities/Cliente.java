package br.unitins.primecall.model.entities;

/**
 *
 * @author filipe
 */
public class Cliente extends Pessoa {

    private int id;

    public Cliente() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
