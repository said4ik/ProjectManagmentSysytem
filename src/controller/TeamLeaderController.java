package controller;

import enam.Role;
import enam.Status;
import exception.DataNotFoundException;
import model.Project;
import model.Task;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.UUID;

import static controller.Main.*;
import static controller.UserController.signIn;


public class TeamLeaderController {

    public static void teamLeaderMenu() {
        while (true) {
            System.out.println("1 See task \t 2 Change Status \t 3  CRUD Task \t 4 Assign Task \t 0 Exit ");
            String command = inputStr("Choose ->");
            switch (command) {
                case "1" -> seeTask(curretnUser.getId());
                case "2" -> changeStatus();
                case "3"->crudMenu();
                case "4" -> assignTaskByEmp();
                default -> signIn();
            }
        }
    }

    private static void assignTaskByEmp() {
        ArrayList<Task> tasks = read();
        if(Objects.isNull(tasks)){
            System.out.println("IsEmpty!!!");
            crudMenu();
        }
        try{
            System.out.print("0.Exit\tEnter choose: ");
            int choice = scanInt.nextInt() - 1;
            if (choice == -1) {
                teamLeaderMenu();
            }
            ArrayList<User> users = userService.getNoWorkingEmployerProjects(tasks.get(choice).getProjectId());
            int i = 1;
            for (User user : users) {
                if(user.getRole() == Role.TESTER || user.getRole() == Role.DEVELOPER_BE
                        || user.getRole() == Role.DEVELOPER_FE){
                System.out.println(i++ + ". " + user.getUsername() +"  " + user.getRole());
                }
            }
            int ans = inputInt("Choose -> ") - 1;
            if (choice == -1) {
                teamLeaderMenu();
            }
            User employee = users.get(ans);
            Task task = tasks.get(choice);
            task.setEmployerId(employee.getId());
            taskService.update(task.getId(),task);
            System.out.println("Successfully ✅");

        }catch (NullPointerException | InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }

    private static void crudMenu() {

            while (true) {
                System.out.println("1.Create\t2.Read\t3.Update\t4.Delete\t0.Exit");
                String choice = scanStr.nextLine();
                switch (choice) {
                    case "1" -> create();
                    case "2" -> read();
                    case "3" -> update();
                    case "4" -> delete();
                    case "0" -> crudMenu();
                    default -> teamLeaderMenu();
                }
            }

    }

    private static void create(){
        Project project;
        try {
            project = projectService.getProject(curretnUser.getProjectId());
        }catch (DataNotFoundException e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Enter title task: ");
        String title = scanStr.nextLine();
        taskService.add(new Task(title,project.getId()));
        System.out.println("Successfully!!!");
    }
    private static ArrayList<Task> read(){
        Project project;
        try {
            project = projectService.getProject(curretnUser.getProjectId());
            ArrayList<Task> tasks = taskService.getTasksByProjectId(project.getId());
            int i = 1;
            for (Task task : tasks) {
                System.out.println(i++ + ". " + task.getTitle());
            }
            return tasks;
        }catch (DataNotFoundException e){
            System.out.println(e.getMessage());
           return null;
        }
    }
    private static void update(){
        ArrayList<Task> tasks = read();
        if(Objects.isNull(tasks)){
            System.out.println("IsEmpty!!!");
            crudMenu();
        }


        try {
            System.out.print("0.Exit\tEnter choose: ");
            int choice = scanInt.nextInt() - 1;
            if (choice == -1) {
                crudMenu();
            }
            System.out.print("Enter new task title: ");
            String newTitle = scanStr.nextLine();
            taskService.update(tasks.get(choice).getId(),new Task(newTitle, curretnUser.getProjectId()));
            System.out.println("Successfully!!!");
        } catch (IndexOutOfBoundsException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void delete(){
        ArrayList<Task> tasks = read();
        if(Objects.isNull(tasks)){
            System.out.println("IsEmpty!!!");
            crudMenu();
        }
        try {
            int i = 1;
            for (Task task : tasks) {
                System.out.println(i++ + ". " + task.getTitle());
            }
            System.out.print("0.Exit\tEnter choose: ");
            int choice = scanInt.nextInt() - 1;
            if (choice == -1) {
                crudMenu();
            }
            taskService.delete(tasks.get(choice).getId());
            System.out.println("Successfully!!!");
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }


    private static void seeTask(UUID userId) {
        int i = 1;
        for (Task task : taskService.getActives()) {
            if (Objects.equals(task.getEmployerId(), userId)) {
                System.out.println(i++ +". " + task.getTitle());
            }
        }
    }

    private static void changeStatus() {
        ArrayList<Task> tasks = taskService.statusCreated();
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i++ + "." + task.getTitle());
        }
        try {
            int choose = inputInt("Choose  -> ") - 1;
            if (choose == -1) {
                teamLeaderMenu();
            }


            Task task = tasks.get(choose);

            task.setStatus(Status.ACCEPTED);

            taskService.update(tasks.get(choose).getId(),task);
            System.out.println("✅");
        }catch (InputMismatchException | IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

    }


}
