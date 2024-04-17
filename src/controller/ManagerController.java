package controller;
import static controller.Main.*;
public class ManagerController {
    public static void managerController(){
        while (true){
            System.out.println("1 CRUD Project \t 2 Add Employer \t 3 Show Employer \t 4 Delete Employer \t5 CRUD Task \t 6 Assign Task \t 0 Exit");
            String command=inputStr("Choose ->");
            switch (command){
                case "1" -> projectMenu();
                case "0"->UserController.signIn();
            }
        }
    }
    public static void projectMenu(){
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
}
