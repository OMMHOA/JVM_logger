package dal;

import Entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MessageFacade extends AbstractFacade<Message>{

    private EntityManager em;

    public MessageFacade() {
        super(Message.class);
    }

    protected EntityManager em() {
        if (em == null) {
            EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("RabbitPersistenceUnit");
            em = emFactory.createEntityManager();
            emFactory.close();
        }
        return em;
    }
}
