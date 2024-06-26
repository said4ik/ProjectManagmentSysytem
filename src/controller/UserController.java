package controller;

import enam.Role;
import exception.DataNotFoundException;

import java.util.Objects;

import static controller.Main.*;
import static controller.Main.curretnUser;

public class UserController {

    public static void signIn() {
        String username = inputStr("Enter username : ");
        String password = inputStr("Enter password : ");

        try {
            curretnUser = userService.signIn(username);
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
            signIn();
        }
        if (Objects.equals(curretnUser.getPassword(), password)) {
            if (curretnUser.isMissionM()) {
                System.out.println("Welcome " + curretnUser.getUsername() + " ! \n");
                roleMenu(curretnUser.getRole());

            } else {
                System.out.println("Please do the job !!!");
                signIn();
            }
        }else {
            System.out.println("Wrong password or username");
            signIn();
        }



    }

    public static void roleMenu(Role role) {

        switch (role) {
            case SUPER_ADMIN -> SuperAdmin.superAdmin();
            case ADMIN -> AdminController.adminMenu();
            case MANAGER -> ManagerController.managerController();
            case TEAM_LEAD_BE,TEAM_LEAD_F -> TeamLeaderController.teamLeaderMenu();
            case TESTER, DEVELOPER_BE, DEVELOPER_FE -> EmployeeController.employeeMenu();

        }
    }
}
