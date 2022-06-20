package br.com.rodaelli.Loja.testes;

import br.com.rodaelli.Loja.DAO.CategoriaDAO;
import br.com.rodaelli.Loja.DAO.ProdutoDAO;
import br.com.rodaelli.Loja.modelo.Categoria;

import br.com.rodaelli.Loja.modelo.Produto;
import br.com.rodaelli.Loja.util.JPAutil;

import javax.persistence.EntityManager;


public class UpdateDeProduto {
    public static void main(String[] args) {

        Categoria celulares = new Categoria("CELULARES");

        EntityManager em = JPAutil.getEntityManager();

        em.getTransaction().begin();

        em.persist(celulares);
        celulares.setNome("AlphaFone");

        em.flush();
        em.clear();

        celulares = em.merge(celulares);
        celulares.setNome("Xiaomi");
        em.flush();
    }
}

