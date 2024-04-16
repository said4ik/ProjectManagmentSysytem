package controller;

import enam.Role;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static controller.Main.*;
import static controller.Main.inputStr;
import static controller.Main.userService;

public class AdminController {

    public static void adminMenu() {
        while (true) {
            System.out.println("Add Manager \t 2 Show Manager \t 3 Remove Manager \t 4 Stop Manager \t 5 Restart Manager \t 0 Exit");
            String command = inputStr("Choose ->");
            switch (command) {
                case "1" -> addManager();
                case "2" -> showManager();
                case "3" -> removeManager();
                case "4" -> stopManager();
                case "5"->restartManager();
                case "0" -> UserController.signIn();
            }
        }
    }

    private static void restartManager() {
        ArrayList<User> manager = showManager();

        try {
            int choose = inputInt("Choose for Stopped ->") - 1;
            userService.stopManager(manager.get(choose).getId(), true);
            System.out.println("Manager Successfully Restarted ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void stopManager() {
        ArrayList<User> manager = showManager();

        try {
            int choose = inputInt("Choose for Stopped ->") - 1;
            userService.stopManager(manager.get(choose).getId(), false);
            System.out.println("Manager Successfully Stopped ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeManager() {
        ArrayList<User> manager = showManager();

        try {
            int choose = inputInt("Choose for Delete ->") - 1;
            userService.delete(manager.get(choose).getId());
            System.out.println("Delete Successfully ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }


    private static ArrayList<User> showManager() {
        ArrayList<User> manager = userService.showRole(Role.MANAGER);
        int i = 1;
        for (User user : manager) {
            System.out.println(i++ + "." + user);
        }
        return manager;
    }

    private static void addManager() {
        String username = inputStr("Enter username :");
        String password = inputStr("Enter password:");

        if (userService.add(new User(username, password, null, Role.MANAGER))) {
            System.out.println("Successfully Added ✅");
        } else {
            System.out.println("Please try again ♻️");
        }
    }
}
