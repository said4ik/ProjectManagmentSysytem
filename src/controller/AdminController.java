package controller;

import enam.Role;
import model.Project;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static controller.Main.*;
import static controller.Main.inputStr;
import static controller.Main.userService;

public class AdminController {

    public static void adminMenu() {
        while (true) {
            System.out.println("1 Add Manager \t 2 Show Manager \t 3 Remove Manager \t 4 Stop Manager \t 5 Restart Manager \t 0 Exit");
            String command = inputStr("Choose -> ");
            switch (command) {
                case "1" -> addManager();
                case "2" -> showManager();
                case "3" -> removeManager();
                case "4" -> stopManager();
                case "5" -> restartManager();
                case "0" -> UserController.signIn();
                default -> adminMenu();
            }
        }
    }
    private static ArrayList<User> showManagerStop(boolean ans){
        ArrayList<User> users = new ArrayList<>();
        for (User user : userService.getActives()) {
            if(user.getRole().equals(Role.MANAGER) && (user.isMissionM() == ans)){
                users.add(user);
            }
        }
        return users;
    }

    private static void restartManager() {
        ArrayList<User> manager = showManagerStop(false);
        int i =1;
        for (User user : manager) {
            System.out.println(i++ + ". " + user);
        }
        if(manager.isEmpty()){
            adminMenu();
        }
        try {
            System.out.print("0.Exit\tChoose one: ");
            int choose = scanInt.nextInt() -1;
            if (choose == -1) {
                adminMenu();
            }
            for (Project project : projectService.getProjectByManagerId(manager.get(choose).getId())) {
                userService.stopProject(project.getId(),true);
            }
            userService.stopManager(manager.get(choose).getId(), true);
            System.out.println("Restart Successfully ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void stopManager() {
        ArrayList<User> manager = showManagerStop(true);
        int i = 1;
        for (User user : manager) {
            System.out.println(i++ + ". " + user);
        }
        if(manager.isEmpty()){
            adminMenu();
        }
        try {
            System.out.print("0.Exit\tChoose one: ");
            int choose = scanInt.nextInt() -1;
            if (choose == -1) {
                adminMenu();
            }
            for (Project project : projectService.getProjectByManagerId(manager.get(choose).getId())) {
                userService.stopProject(project.getId(),false);
            }
            userService.stopManager(manager.get(choose).getId(), false);
            System.out.println("Stop Successfully ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeManager() {
        ArrayList<User> manager = showManager();
        if(manager.isEmpty()){
            adminMenu();
        }
        try {
            System.out.print("0.Exit\tChoose one: ");
            int choose = scanInt.nextInt() -1;
            if (choose == -1) {
                adminMenu();
            }
            userService.delete(manager.get(choose).getId());
            System.out.println("Delete Successfully ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }


    private static ArrayList<User> showManager() {
        ArrayList<User> manager = userService.showRole(Role.MANAGER);
        if(manager.isEmpty()){
            adminMenu();
        }
        int i = 1;
        for (User user : manager) {
            System.out.println(i++ + "." + user);
        }
        return manager;
    }

    private static void addManager() {
        String username = inputStr("Enter username : ");
        String password = inputStr("Enter password: ");

        if (userService.add(new User(username, password, null, Role.MANAGER))) {
            System.out.println("Successfully Added ✅");
        } else {
            System.out.println("Please try again ♻️");
        }
    }
}
