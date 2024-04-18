package service;

import model.Task;
import repository.TaskRepository;


import java.util.ArrayList;
import java.util.Objects;


import java.util.UUID;


public class TaskService extends BaseService<Task, TaskRepository> {

    private static final TaskService taskService = new TaskService(new TaskRepository());

    public static TaskService getInstance() {
        return taskService;
    }

    private TaskService(TaskRepository repository) {
        super(repository);
    }

    public void deleteTaskForEmployer(UUID id) {
        repository.deleteTaskForEmployer(id);
    }

    @Override
    public boolean check(Task task) {
        return false;
    }

    public ArrayList<Task> getEmployeeByProjectId(UUID id){
        return repository.getEmployeeByProjectId(id);
    }
}
