package br.com.rodaelli.Loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    //2 pk (chave composta)
    @EmbeddedId
    private CategoriaId id;

    public Categoria() {

    }

    public Categoria(String nome) {
        this.id = new CategoriaId(nome, "xpto");
    }

    public String getNome() {
        return this.id.getNome();
    }

}
