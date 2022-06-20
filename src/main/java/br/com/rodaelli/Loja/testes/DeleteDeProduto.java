package br.com.rodaelli.Loja.testes;

import br.com.rodaelli.Loja.DAO.CategoriaDAO;
import br.com.rodaelli.Loja.DAO.ProdutoDAO;
import br.com.rodaelli.Loja.modelo.Categoria;
import br.com.rodaelli.Loja.modelo.Produto;
import br.com.rodaelli.Loja.util.JPAutil;
import br.com.rodaelli.Loja.testes.SelectDeProduto;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

import static br.com.rodaelli.Loja.testes.CadastroDeProduto.cadastrarProduto;

public class DeleteDeProduto {
    public static void main(String[] args) {

        EntityManager em = JPAutil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);


        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, 1L);
        em.flush();
        em.remove(produto);
        em.flush();


    }
}

