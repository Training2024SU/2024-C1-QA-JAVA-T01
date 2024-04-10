package co.com.sofka.menu;

import co.com.sofka.repositorio.HibernateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.env.BasicIniEnvironment;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(System.in);
    static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    static boolean seguirEjecucion = true;
    private static final transient Logger log = LoggerFactory.getLogger(Menu.class);
    public static void iniciar(){

        SecurityManager securityManager = new BasicIniEnvironment("classpath:shiro.ini").getSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                System.out.println("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                System.out.println("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }

            System.out.println("User [" + currentUser.getPrincipal() + "] logged in successfully.");

            //test a role:
            if (currentUser.hasRole("schwartz")) {
                System.out.println("May the Schwartz be with you!");
            } else {
                System.out.println("Hello, mere mortal.");
            }
        }

        currentUser.logout();

        while(seguirEjecucion){
            imprimirMenuInicial();

            int eleccion = scanner.nextInt();
            scanner.nextLine();

            switch (eleccion){
                case 1:
                    System.out.println("Se hara ingreso pidiendo correo y contraseña");
                    break;
                case 2:
                    System.out.println("Se hara registro pidiendo nombre, correo y contraseña");
                    break;
                case 0:
                    seguirEjecucion = false;
                    System.out.println(ConstantesMenu.MENSAJE_DESPEDIDA);
                    break;
                default:
                    System.out.println(ConstantesMenu.OPCION_INVALIDA);
            }
        }

    }

    private static void imprimirMenuInicial(){
        System.out.println(ConstantesMenu.MENSAJE_BIENVENIDA);
    }
}
