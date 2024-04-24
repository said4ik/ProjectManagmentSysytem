package controller;

import enam.Status;
import model.Task;

import java.util.Objects;

import static controller.Main.*;
import static controller.UserController.signIn;

public class EmployeeController {
    public static void employeeMenu(){
        int i = 1;
        for (Task task : taskService.getActives()) {
            if(Objects.equals(task.getEmployerId(),curretnUser.getId())){
                System.out.println(i++ + ". " + task.getTitle());
                System.out.println("0.Exit\t1.Completed");
                String choice = scanStr.nextLine();
                switch (choice){
                    case "1" -> {
                        task.setStatus(Status.COMPLETED);
                        taskService.delete(task.getId());
                        System.out.println("Project on completed");
                        signIn();
                    }
                    case "0" -> signIn();
                    default -> employeeMenu();
                }
            }
        }
    }
}
