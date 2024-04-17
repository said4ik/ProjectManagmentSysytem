package controller;

import model.Project;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static controller.Main.*;
import static controller.ManagerController.projectMenu;

public class ProjectController {
    public static void create() {
        System.out.print("Enter project title: ");
        String title = scanStr.nextLine();

        if (projectService.add(new Project(title, curretnUser.getId()))) {
            System.out.println("SUCCESSFULLY!!!");
            projectMenu();
        } else {
            System.out.println("This is title already in used!!!");
            projectMenu();
        }
    }

    public static ArrayList<Project> read() {
        ArrayList<Project> list = projectService.getProjectByManagerId(curretnUser.getId());
        int i = 1;
        for (Project project : list) {
            System.out.println(i++ + "." + project.getTitle());
        }
        return list;
    }

    public static void update() {
        ArrayList<Project> read = read();
        if (read.isEmpty()) {
            System.out.println("Empty!!!");
            projectMenu();
        }
        System.out.print("0.Exit\tChoose one: ");
        try {
            int choice = scanInt.nextInt() - 1;
            if (choice == -1) {
                projectMenu();
            }
            System.out.print("Enter new project title: ");
            String newTitle = scanStr.nextLine();
            if (projectService.check(new Project(newTitle, curretnUser.getId()))) {
                System.out.println("This is title already in used!!!");
                projectMenu();
            } else {
                projectService.update(read.get(choice).getId(), new Project(newTitle, curretnUser.getId()));
                System.out.println("Update successfully!!!");
                projectMenu();
            }
        } catch (IndexOutOfBoundsException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete() {
        ArrayList<Project> project = read();
        if (project.isEmpty()) {
            System.out.println("Empty!!!");
            projectMenu();
        }
        System.out.print("0.Exit\tChoose one: ");
        try {
            int choice = scanInt.nextInt() - 1;
            if (choice == -1) {
                projectMenu();
            }
            projectService.delete(project.get(choice).getId());
            System.out.println("Delete successfully");
            projectMenu();
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
