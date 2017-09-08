package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Facade implements IPersonFacade {

    private EntityManagerFactory emf;
    
    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Person addPerson(Person p) {
        EntityManagerFactory emfn = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emfn.createEntityManager(); 

        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
            
        } finally {
            em.close();
        }
    }

    @Override
    public Person deletePerson(int id) {
        EntityManagerFactory emfn = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emfn.createEntityManager();

        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person getPerson(int id) {
        EntityManagerFactory emfn = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emfn.createEntityManager();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersons() {
        EntityManagerFactory emfn = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emfn.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Person e");

        return (List<Person>) query.getResultList();
    }

    @Override
    public Person editPerson(Person p) {
        EntityManagerFactory emfn = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emfn.createEntityManager();

        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, p.getId());
            if (person != null) {
                em.merge(p);
            }
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

}
