package controller;

import enam.Status;
import model.Project;
import model.Task;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static controller.Main.*;
import static controller.ManagerController.taskMenu;


public class TaskController {


    public static void create() {
        ArrayList<Project> projects = projectService.getProjectByManagerId(curretnUser.getId());
        int i = 1;
        for (Project project : projects) {
            System.out.println(i++ + ". " + project.getTitle());
        }

        try {
            System.out.print("0.Exit\nChoose one: ");
            int choose = scanInt.nextInt() -1;
            if (choose == -1) {
               taskMenu();
            }
            String title = inputStr("Enter title : ");

            taskService.add(new Task(title, projects.get(choose).getId()));

            System.out.println("Successfully ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> read() {

        ArrayList<Project> projects = projectService.getProjectByManagerId(curretnUser.getId());
        int i = 1;
        for (Project project : projects) {
            System.out.println(i++ + ". " + project.getTitle());
        }
        System.out.print("Enter choose one: ");
        ArrayList<Task> tasks = null;
        try {
            int choose = scanInt.nextInt() - 1;
            if (choose == -1) {
                taskMenu();
            }

            tasks = taskService.getTasksByProjectId(projects.get(choose).getId());
        }catch (InputMismatchException | IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        int j = 1;
        for (Task task : tasks) {
            System.out.println(j++ + ":" + task.getTitle());
        }
        return tasks;
    }

    public static void update() {
        ArrayList<Task> tasks = read();

        try {
            int choose = scanInt.nextInt() -1;
            if (choose == -1) {
                taskMenu();
            }

            String newTitle = inputStr("Enter new title :");
            taskService.update(tasks.get(choose).getId(), new Task(newTitle, tasks.get(choose).getProjectId()));
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void delete() {
        ArrayList<Task> tasks = read();

        try {
            int choose = scanInt.nextInt() -1;
            if (choose == -1) {
                taskMenu();
            }
            if (tasks.get(choose).getStatus() == Status.ACCEPTED) {
                System.out.println("This is Do not Delete !");
                delete();
            }
            taskService.delete(tasks.get(choose).getId());
            System.out.println("Delete Successfully ✅");
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }
}
