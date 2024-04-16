package service;

import model.Project;

public class ProjectRepository extends BaseRepository<Project> {
    private static final ProjectRepository projectRepository = new ProjectRepository();
    public ProjectRepository getInstance(){
        return projectRepository;
    }
    public ProjectRepository(){

    }

}
