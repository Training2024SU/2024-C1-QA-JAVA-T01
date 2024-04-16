package co.com.sofka.repositorio;

import co.com.sofka.modelo.Prestamo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioPrestamo {

    private final SessionFactory sessionFactory;
    public RepositorioPrestamo(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void guardar(Prestamo prestamo){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.save(prestamo);
            tx.commit();
        }
    }

    public void modificar(Prestamo prestamo){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.update(prestamo);
            tx.commit();
        }
    }

    public List<Prestamo> listarPrestamos() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Prestamo> criteria = builder.createQuery(Prestamo.class);
            Root<Prestamo> root = criteria.from(Prestamo.class);
            criteria.select(root);

            return session.createQuery(criteria).getResultList();
        }
    }

    public Prestamo obtenerPrestamo(Long id){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Prestamo> criteria = builder.createQuery(Prestamo.class);
            Root<Prestamo> root = criteria.from(Prestamo.class);
            criteria.select(root);

            criteria.where(builder.equal(root.get("id"), id));

            return session.createQuery(criteria).uniqueResult();
        }
    }
}
