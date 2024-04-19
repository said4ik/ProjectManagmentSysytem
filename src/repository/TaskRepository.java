package repository;

import enam.Role;
import enam.Status;
import model.Task;
import model.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class TaskRepository extends BaseRepository<Task> {
    private static final TaskRepository taskRepository = new TaskRepository();

    public static TaskRepository getInstance() {
        return taskRepository;
    }

    private TaskRepository() {

    }

    public ArrayList<Task> getEmployeeTasks(UUID id) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : getActives()) {
            if (Objects.equals(task.getEmployerId(), id)) {
                list.add(task);
            }
        }
        return list;
    }

    public ArrayList<Task> getEmployeeByProjectId(UUID id) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : getActives()) {
            if (Objects.equals(task.getProjectId(), id)) {
                list.add(task);
            }
        }
        return list;
    }

    public void deleteTaskForEmployer(UUID id) {
        for (Task task : data) {
            if (Objects.equals(task.getEmployerId(), id) && task.getStatus() != Status.ACCEPTED) {
                task.setEmployerId(null);
                task.setStatus(Status.CREATED);
            }
        }
    }



}
