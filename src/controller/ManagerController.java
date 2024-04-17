package controller;

import enam.Role;
import model.Project;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static controller.Main.*;

public class ManagerController {
    public static void managerController() {
        while (true) {
            System.out.println("1 CRUD Project \t 2 Add Employer \t 3 Show Employer \t 4 Delete Employer \t5 CRUD Task \t 6 Assign Task \t 0 Exit");
            String command = inputStr("Choose ->");
            switch (command) {
                case "1" -> projectMenu();

                case "2" -> addEmployer();
                case "3" -> showEmployer();
                case "4" -> deleteEmployer();
                case "0" -> UserController.signIn();
            }
        }
    }

    private static void deleteEmployer() {
        ArrayList<User> managers = showEmployer();

        try {
            int choose = inputInt("Choose for Delete ->") - 1;
            taskService.deleteTaskForEmployer(managers.get(choose).getId());
            userService.delete(managers.get(choose).getId());
            System.out.println("Delete Successfully ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }

    private static ArrayList<User> showEmployer() {
        ArrayList<Project> projects = ProjectController.read();


        int choose = inputInt("Choose ->") - 1;

        ArrayList<User> users = userService.getEmployerProjects(projects.get(choose).getId());

        int i = 1;
        for (User user : users) {
            System.out.println(i++ + "." + user);
        }

        return users;
    }


    private static void addEmployer() {
        ArrayList<Project> projects = ProjectController.read();

        try {
            int choose = inputInt("Choose -> ") - 1;

            String username = inputStr("Enter username :");
            String password = inputStr("Enter password :");

            if (userService.add(new User(username, password, projects.get(choose).getId(), getRole()))) {
                System.out.println("Employer Successfully Added ✅");
            } else {
                System.out.println("Please try Again ♻️");

            }


        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void projectMenu() {
        while (true) {
            System.out.println("1.Create\t2.Read\t3.Update\t4.Delete\t0.Exit");
            String choice = scanStr.nextLine();
            switch (choice) {
                case "1" -> ProjectController.create();
                case "2" -> ProjectController.read();
                case "3" -> ProjectController.update();
                case "4" -> ProjectController.delete();
                default -> managerController();
            }
        }
    }


    public static Role getRole() {
        Role role = null;
        System.out.println("1 TEAM_LEAD_BE\t2 TEAM_LEAD_FE\t3 TESTER\t 4 DEVELOPER_BE\t5 DEVELOPER_FE");
        String command = inputStr("Choose employer-> ");
        switch (command) {
            case "1" -> role = Role.TEAM_LEAD_BE;
            case "2" -> role = Role.TEAM_LEAD_F;
            case "3" -> role = Role.TESTER;
            case "4" -> role = Role.DEVELOPER_BE;
            case "5" -> role = Role.DEVELOPER_FE;
        }

        return role;

    }
}
