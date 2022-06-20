package br.com.rodaelli.Loja.DAO;

import br.com.rodaelli.Loja.modelo.Cliente;
import br.com.rodaelli.Loja.modelo.Pedido;
import br.com.rodaelli.Loja.modelo.Produto;

import javax.persistence.EntityManager;

public class ClienteDAO {
    //CLASSE DAO (PADRAO DATA ACCESS OBJECT) - SERVE PARA ISOLAR O CODIGO DE ACESSO AO BANCO DE DADOS
    private EntityManager em;

    //construtor (injeção de dependencias) => nao deixar a classe DAO ser responsavel por criar e gerenciar o entity manager
    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    //metodo cadastrar produto no bd usando a JPA
    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public Cliente buscarPorId(int id) {
        return em.find(Cliente.class, id);
    }

}
