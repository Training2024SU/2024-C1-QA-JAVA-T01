package co.com.sofka.repositorio;

import co.com.sofka.modelo.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioLibro {
    private final SessionFactory sessionFactory;

    public RepositorioLibro(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void guardar(Libro libro) {
        try (Session session = sessionFactory.openSession()) {
            session.save(libro);
        }
    }

    public List<Libro> listarLibros() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Libro> criteria = builder.createQuery(Libro.class);
            Root<Libro> root = criteria.from(Libro.class);
            criteria.select(root);

            return session.createQuery(criteria).getResultList();
        }
    }

    public void modificar(Libro libro){
        try(Session session = sessionFactory.openSession()){
            session.update(libro);
        }
    }

    public Libro obtenerLibro(Long id){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Libro> criteria = builder.createQuery(Libro.class);
            Root<Libro> root = criteria.from(Libro.class);
            criteria.select(root);

            criteria.where(builder.equal(root.get("id"), id));

            return session.createQuery(criteria).uniqueResult();
        }
    }
}
