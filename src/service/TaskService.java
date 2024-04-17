package service;

import model.Task;
import repository.TaskRepository;


public class TaskService extends BaseService<Task, TaskRepository> {

    private static final TaskService taskService=new TaskService(new TaskRepository());

    public static TaskService getInstance() {
        return taskService;
    }

    public TaskService(TaskRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Task task) {
        return false;
    }
}
