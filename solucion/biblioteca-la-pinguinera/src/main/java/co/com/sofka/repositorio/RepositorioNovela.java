package co.com.sofka.repositorio;

import co.com.sofka.modelo.Novela;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
public class RepositorioNovela {
    private final SessionFactory sessionFactory;

    public RepositorioNovela(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void guardar(Novela novela) {
        try (Session session = sessionFactory.openSession()) {
            session.save(novela);
        }
    }

    public List<Novela> listarNovelas() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Novela> criteria = builder.createQuery(Novela.class);
            Root<Novela> root = criteria.from(Novela.class);
            criteria.select(root);

            return session.createQuery(criteria).getResultList();
        }
    }

    public Novela obtenerNovela(Long id){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Novela> criteria = builder.createQuery(Novela.class);
            Root<Novela> root = criteria.from(Novela.class);
            criteria.select(root);

            criteria.where(builder.equal(root.get("id"), id));

            return session.createQuery(criteria).uniqueResult();
        }
    }

    public void modificar(Novela novela){
        try(Session session = sessionFactory.openSession()){
            session.update(novela);
        }
    }
}
