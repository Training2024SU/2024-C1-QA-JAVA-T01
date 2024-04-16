package co.com.sofka.utils;

import java.util.Scanner;

public class Utils {
    public static int getIntOption(){
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        try {
            option = scanner.nextInt();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return option;
    }
    public static String getStringOption(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
