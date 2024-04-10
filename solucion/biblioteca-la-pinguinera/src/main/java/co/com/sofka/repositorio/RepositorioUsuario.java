package co.com.sofka.repositorio;

import co.com.sofka.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
}
