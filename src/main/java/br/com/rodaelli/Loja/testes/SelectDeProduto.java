package br.com.rodaelli.Loja.testes;

import br.com.rodaelli.Loja.DAO.CategoriaDAO;
import br.com.rodaelli.Loja.DAO.ProdutoDAO;
import br.com.rodaelli.Loja.modelo.Produto;
import br.com.rodaelli.Loja.util.JPAutil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

import static br.com.rodaelli.Loja.testes.CadastroDeProduto.cadastrarProduto;

public class SelectDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p = produtoDAO.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDAO.buscarTodos();
        todos.forEach(p2 -> System.out.println(p2.getNome()));

        List<Produto> porNome = produtoDAO.buscarPorNome("Iphone 7");
        porNome.forEach(p3 -> System.out.println(p3.getNome()));

        List<Produto> porCategoria = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        porCategoria.forEach(p4 -> System.out.println(p4.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Iphone 7");
        System.out.println("Preco do produto é: " + precoDoProduto);
    }
}
