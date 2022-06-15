package br.com.rodaelli.Loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAutil {
    //quando carregar o JPAutil ja carrega a EntityManagerFactory

    //qualquer operação no banco de dados é
    // a interface EntityManager(gestao de entidades) save, update, delete, select, etc

    //FINAL = CONSTANTE
    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("Loja");

    //metodo que vai criar o EntityManager, só chamar quando precisa
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }


}
