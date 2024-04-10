package co.com.sofka;

import co.com.sofka.repositorio.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        System.out.println("Hello world!");
    }
}