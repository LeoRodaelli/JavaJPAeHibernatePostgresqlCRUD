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

        Produto celular = new Produto();
        Categoria celulares = new Categoria();

        //SIMULAÇÃO USUARIO EM UMA APLICAÇÃO


        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        //inicia transação
        em.getTransaction().begin();

        //operações: (delete)
        produtoDAO.remover(celular);
        categoriaDAO.remover(celulares);

        em.getTransaction().commit();
        em.close();

    }
}

