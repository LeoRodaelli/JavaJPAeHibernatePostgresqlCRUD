package br.com.rodaelli.Loja.DAO;

import br.com.rodaelli.Loja.modelo.Categoria;
import br.com.rodaelli.Loja.modelo.Produto;

import javax.persistence.EntityManager;

public class CategoriaDAO {

    private EntityManager em;

    //construtor (injeção de dependencias) => nao deixar a classe DAO ser responsavel por criar e gerenciar o entity manager
    public CategoriaDAO(EntityManager em) {
        this.em = em;
    }

    //metodo cadastrar produto no bd usando a JPA
    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.em.merge(categoria);
    }

    public void remover(Categoria categoria) {
        //remover na manager, e nao detached
        categoria = em.merge(categoria);
        this.em.remove(categoria);
    }
}
