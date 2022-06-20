package br.com.rodaelli.Loja.DAO;

import br.com.rodaelli.Loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    //metodo com jpql
    //public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
    //    String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
//
    //    if (nome != null && !nome.trim().isEmpty()) {
    //        jpql = " AND p.nome = :nome ";
    //    }
    //    if (preco != null) {
    //        jpql = " AND p.preco = :preco ";
    //    }
    //    if (dataCadastro != null) {
    //        jpql = " AND p.dataCadastro = :dataCadastro ";
    //    }
    //    TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
    //    if (nome != null && !nome.trim().isEmpty()) {
    //        query.setParameter("nome", nome);
    //    }
    //    if (preco != null) {
    //        query.setParameter("preco", preco);
    //    }
    //    if (dataCadastro != null) {
    //        query.setParameter("dataCadastro", dataCadastro);
    //    }
//
    //    return query.getResultList();
    //}


    //usando criteria API
    public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);
        //select automatico igual ao from

        Predicate filtros = builder.and();
        if (nome != null && !nome.trim().isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }
        if (preco != null) {
            filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
        }
        if (dataCadastro != null) {
            filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);

        return em.createQuery(query).getResultList();
    }
}
