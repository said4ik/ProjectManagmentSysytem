package service;

import model.Project;
import repository.ProjectRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

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
        for (Project active : getActives()) {
            if(Objects.equals(active.getTitle(),project.getTitle())){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Project> getProjectByManagerId(UUID id){
       return repository.getProjectsByManagerId(id);
    }

    public ArrayList<Project>getAllProjects(boolean active){
        return repository.allProjects(active);
    }
}
