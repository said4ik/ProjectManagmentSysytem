package service;

import model.Task;

public class TaskRepository extends BaseRepository<Task>{
    private static final TaskRepository taskRepository = new TaskRepository();
    public TaskRepository getInstance(){
        return taskRepository;
    }
    public TaskRepository(){

    }
}
