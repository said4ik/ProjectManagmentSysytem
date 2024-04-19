package repository;

import enam.Status;
import model.Task;

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

    public ArrayList<Task> getTasksByProjectId(UUID id) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : getActives()) {
            if (Objects.equals(task.getProjectId(), id) && task.getEmployerId() == null) {
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
    public boolean checkWorking (UUID employeeId){
        for (Task task : getActives()) {
            if(task.isActive() && Objects.equals(task.getEmployerId(),employeeId)){
                return false;
            }
        }
        return true;
    }

}
