package controller;

import model.Task;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static controller.Main.*;

public class TeamLeaderController {

    public static void teamLeaderMenu() {
        while (true) {
            System.out.println("1 See task \t 2 Change Status \t 3  CRUD Task \t 4 Assign Task \t 0 Exit ");
            String command = inputStr("Choose ->");
            switch (command) {
                case "1" -> seeTask();
            }

        }
    }

    private static void seeTask() {
        ArrayList<Task> tasks = TaskController.read();

        try {
            int choose = inputInt("Choose ->") - 1;


        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
