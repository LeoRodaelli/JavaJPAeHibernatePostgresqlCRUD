package br.com.rodaelli.Loja.DAO;

import br.com.rodaelli.Loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {
    //CLASSE DAO (PADRAO DATA ACCESS OBJECT) - SERVE PARA ISOLAR O CODIGO DE ACESSO AO BANCO DE DADOS
    private EntityManager em;

    //construtor (injeção de dependencias) => nao deixar a classe DAO ser responsavel por criar e gerenciar o entity manager
    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    //metodo cadastrar produto no bd usando a JPA
    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualizar(Produto produto) {
        this.em.merge(produto);
    }

    public void remover(Produto produto) {
        //remover na manager, e nao detached
        produto = em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(int id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }
}
