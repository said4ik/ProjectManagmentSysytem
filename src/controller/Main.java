package controller;

import enam.Role;
import model.User;
import service.UserService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Scanner scanStr = new Scanner(System.in);
    public static Scanner scanInt = new Scanner(System.in);

    public static UserService userService = UserService.getInstance();
    public static User curretnUser = null;


    public static void main(String[] args) {
        UserController.signIn();
    }

    static {
        userService.add(new User("1", "111", null, Role.SUPER_ADMIN));
        userService.add(new User("2", "222", null, Role.ADMIN));
        userService.add(new User("3", "333", null, Role.MANAGER));
    }

    public static String inputStr(String hint) {
        System.out.print(hint);
        return scanStr.nextLine();
    }

    public static int inputInt(String hint) {
        System.out.print(hint);
        return scanInt.nextInt();
    }
}