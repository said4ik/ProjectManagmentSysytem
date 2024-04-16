package controller;

import model.User;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Scanner scanStr = new Scanner(System.in);
    public static Scanner scanInt = new Scanner(System.in);

    public static User curretnUser = null;



    public static void main(String[] args) {
        
    }
    public static String inputStr(String hint) {
        System.out.print(hint);
        return scanStr.nextLine();
    }

    public static int input(String hint) {
        System.out.print(hint);
        return scanInt.nextInt();
    }
}