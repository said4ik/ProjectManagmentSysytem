package controller;
import model.User;

import static controller.Main.*;
public class SuperAdmin {
    public static  void superAdmin(){
        while (true){
            System.out.println("1 Add Admin \t 2 Show Amin \t 3 Remove Admin \t 4 All projects \t 0 Exit");
            String command=inputStr("Choose ->");
            switch (command){
                case "1"->addAdmin();
                case "0"->{
                    UserController.signIn();
                    return;
                }

            }
        }
    }

    private static void addAdmin() {
        String username=inputStr("Enter username :");
        String password =inputStr("Enter password :");

        if (userService.add(new User(username,password))){
            System.out.println("Successfully ✅");
        }else {
            System.out.println("Please try again ♻️");
        }
    }
}
