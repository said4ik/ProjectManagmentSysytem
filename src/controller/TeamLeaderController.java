package controller;

import enam.Role;
import enam.Status;
import model.Project;
import model.Task;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.UUID;

import static controller.Main.*;
import static sun.net.ftp.impl.FtpClient.create;

public class TeamLeaderController {

    public static void teamLeaderMenu() {
        while (true) {
            System.out.println("1 See task \t 2 Change Status \t 3  CRUD Task \t 4 Assign Task \t 0 Exit ");
            String command = inputStr("Choose ->");
            switch (command) {
                case "1" -> seeTask(curretnUser.getId());
                case "2" -> changeStatus();
                case "3"->crudMenu();
            }


        }
    }

    private static void crudMenu() {

            while (true) {
                System.out.println("1.Create\t2.Read\t3.Update\t4.Delete\t0.Exit");
                String choice = scanStr.nextLine();
                switch (choice) {
                    case "1" -> create();
                    case "2" ->
                    case "3" ->
                    case "4" ->
                    default -> teamLeaderMenu();
                }
            }

    }

    private static void create() {
        ArrayList<Project>
    }

    private static void seeTask(UUID userId) {
        for (Task task : taskService.getActives()) {
            if (Objects.equals(task.getEmployerId(), userId)) {
                System.out.println(task.getTitle());
            }
        }
    }

    private static void changeStatus() {
        ArrayList<Task> tasks = taskService.statusCreated();
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i++ + "." + task.getTitle());
        }

        int choose = inputInt("Choose  ->") - 1;


        Task task = tasks.get(choose);

        task.setStatus(Status.ACCEPTED);

        taskService.update(tasks.get(choose).getId(),task);
        System.out.println("✅");
    }


}
