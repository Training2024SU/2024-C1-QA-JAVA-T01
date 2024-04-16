package co.com.sofka;

import co.com.sofka.menu.Menu;
import co.com.sofka.repositorio.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        Menu.iniciar();
    }
}