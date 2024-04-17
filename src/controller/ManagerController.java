package controller;
import static controller.Main.*;
public class ManagerController {
    public static void managerController(){
        while (true){
            System.out.println("1 CRUD Task \t 2 Add Employer \t 3 Show Employer \t 4 Delete Employer \t5 CRUD Task \t 6 Assign Task \t 0 Exit");
            String command=inputStr("Choose ->");
            switch (command){
                case "0"->UserController.signIn();
            }
        }
    }
}
