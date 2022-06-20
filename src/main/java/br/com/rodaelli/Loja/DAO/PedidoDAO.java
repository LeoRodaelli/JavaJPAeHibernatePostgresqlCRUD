package br.com.rodaelli.Loja.DAO;

import br.com.rodaelli.Loja.Vo.RelatorioDeVendasVo;
import br.com.rodaelli.Loja.modelo.Pedido;
import br.com.rodaelli.Loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {
    //CLASSE DAO (PADRAO DATA ACCESS OBJECT) - SERVE PARA ISOLAR O CODIGO DE ACESSO AO BANCO DE DADOS
    private EntityManager em;

    //construtor (injeção de dependencias) => nao deixar a classe DAO ser responsavel por criar e gerenciar o entity manager
    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    //metodo cadastrar produto no bd usando a JPA
    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    //valor total
    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<RelatorioDeVendasVo> relatorioDeVendas() {
        String jpql = "SELECT new br.com.rodaelli.Loja.Vo.RelatorioDeVendasVo(produto.nome, SUM(item.quantidade), MAX(pedido.data)) FROM Pedido pedido INNER JOIN pedido.itens item INNER JOIN item.produto produto GROUP BY produto.nome ORDER BY item.quantidade";
        return em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
    }

    //tratar exception lazyInicializationException -- JOIN FETCH
    public Pedido buscarPedidoComCliente(int id) {
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
