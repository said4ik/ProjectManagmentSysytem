package controller;

import enam.Role;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static controller.Main.*;

public class SuperAdmin {
    public static void superAdmin() {
        while (true) {
            System.out.println("1 Add Admin \t 2 Show Amin \t 3 Remove Admin \t 4 All projects \t 0 Exit");
            String command = inputStr("Choose ->");
            switch (command) {
                case "1" -> addAdmin();
                case "2" -> showAdmins();
                case "3" -> removeAdmin();
                case "0" -> {
                    UserController.signIn();

                }

            }
        }
    }

    private static void removeAdmin() {
        ArrayList<User> admin = showAdmins();

        try {
            int choose = inputInt("Choose ->") - 1;
            userService.delete(admin.get(choose).getId());
            System.out.println("Delete Successfully ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<User> showAdmins() {
        ArrayList<User> admin = userService.showRole(Role.ADMIN);
        int i = 1;
        for (User user : admin) {
            System.out.println(i++ + "." + user);
        }
        return admin;
    }

    private static void addAdmin() {
        String username = inputStr("Enter username :");
        String password = inputStr("Enter password :");

        if (userService.add(new User(username, password,null,Role.ADMIN))) {
            System.out.println("Successfully ✅");
            superAdmin();
        } else {
            System.out.println("Please try again ♻️");
            addAdmin();
        }
    }
}
