package br.com.rodaelli.Loja.testes;

import br.com.rodaelli.Loja.DAO.CategoriaDAO;
import br.com.rodaelli.Loja.DAO.ProdutoDAO;
import br.com.rodaelli.Loja.modelo.Categoria;
import br.com.rodaelli.Loja.modelo.Produto;
import br.com.rodaelli.Loja.util.JPAutil;


import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class testeCriteria {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        produtoDAO.buscarPorParametrosComCriteria("PS5", null, LocalDate.now());
    }

    static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi Redmi", "Nice",
                new BigDecimal("800"), celulares);

        Produto videogame = new Produto("PS5", "Playstation 5",
                new BigDecimal("2500"), videogames);

        Produto macbook = new Produto("Macbook", "Macbook pro excelente para trabalhar",
                new BigDecimal("5000"), informatica);

        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(videogames);
        categoriaDAO.cadastrar(informatica);

        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(videogame);
        produtoDAO.cadastrar(macbook);

        em.getTransaction().commit();
        em.close();
    }
}
