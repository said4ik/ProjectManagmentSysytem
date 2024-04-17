package service;

import model.Project;
import repository.ProjectRepository;

public class ProjectService extends BaseService<Project, ProjectRepository> {
    private static final ProjectService projectService = new ProjectService();
    public static ProjectService getInstance(){
        return projectService;
    }
    public ProjectService(){
        super(ProjectRepository.getInstance());
    }

    @Override
    public boolean check(Project project) {
        return false;
    }
}
