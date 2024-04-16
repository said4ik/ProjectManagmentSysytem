package controller;

import enam.Role;
import exception.DataNotFoundException;

import java.util.Objects;

import static controller.Main.*;
import static controller.Main.curretnUser;

public class UserController {

    public static void signIn() {
        String username=inputStr("Enter username :");
        String password=inputStr("Enter password :");

        try {
            curretnUser=userService.signIn(username);
        }catch (DataNotFoundException e){
            System.out.println(e.getMessage());
        }
        if (Objects.equals(curretnUser.getPassword(),password)){
            if (curretnUser.isMissionM()) {
                roleMenu(curretnUser.getRole());
            }else {
                System.out.println("Please do the job !!!");
            }
        }
        System.out.println("Welcome "+curretnUser.getUsername()+" ! \n");

    }

    public static void roleMenu(Role role){
        switch (role){
            case SUPER_ADMIN -> SuperAdmin.superAdmin();
        }
    }
}
