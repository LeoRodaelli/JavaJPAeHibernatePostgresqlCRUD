package br.com.rodaelli.Loja.testes;

import br.com.rodaelli.Loja.DAO.CategoriaDAO;
import br.com.rodaelli.Loja.DAO.ClienteDAO;
import br.com.rodaelli.Loja.DAO.PedidoDAO;
import br.com.rodaelli.Loja.DAO.ProdutoDAO;
import br.com.rodaelli.Loja.Vo.RelatorioDeVendasVo;
import br.com.rodaelli.Loja.modelo.*;

import br.com.rodaelli.Loja.util.JPAutil;
import com.sun.xml.bind.v2.TODO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        //id (28) pois estou testando com o meu bd postgresql
        Produto produto = produtoDAO.buscarPorId(28);
        Produto produto2 = produtoDAO.buscarPorId(66);
        Produto produto3 = produtoDAO.buscarPorId(67);

        Cliente cliente = clienteDAO.buscarPorId(1);

        em.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        pedido.adicionarItem(new ItemPedido(40, pedido, produto2));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(new ItemPedido(2, pedido, produto3));


        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.cadastrar(pedido);
        pedidoDAO.cadastrar(pedido2);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);


        //List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatorioDeVendas();
        //relatorio.forEach(System.out::println);
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


        Cliente cliente = new Cliente("Rodrigo", "1234567");

        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(videogames);
        categoriaDAO.cadastrar(informatica);

        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(videogame);
        produtoDAO.cadastrar(macbook);

        clienteDAO.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }

}
