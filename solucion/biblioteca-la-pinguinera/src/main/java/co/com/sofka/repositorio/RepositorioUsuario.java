package co.com.sofka.repositorio;

import co.com.sofka.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import java.util.List;

public class RepositorioUsuario {

    private final SessionFactory sessionFactory;

    public RepositorioUsuario(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void guardar(Usuario usuario){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            session.save(usuario);
            tx.commit();
        }
    }

    public Usuario obtenerPorCorreoYContrasena(String correo, String contrasena){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.select(root);

            Predicate correoPredicate = builder.equal(root.get("correo"), correo);
            Predicate contrasenaPredicate = builder.equal(root.get("contrasena"), contrasena);
            Predicate combinedPredicate = builder.and(correoPredicate, contrasenaPredicate);

            criteria.where(combinedPredicate);

            return session.createQuery(criteria).uniqueResult();
        }
    }

    public List<Usuario> listarUsuarios(){
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.select(root);

            return session.createQuery(criteria).getResultList();
        }
    }
}
