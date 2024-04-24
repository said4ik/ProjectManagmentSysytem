package service;

import exception.DataNotFoundException;
import model.Project;
import repository.ProjectRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public class ProjectService extends BaseService<Project, ProjectRepository> {

    private static final ProjectService projectService=new ProjectService();

    public static ProjectService getInstance() {
        return projectService;
    }

    private ProjectService() {
        super(ProjectRepository.getInstance());
    }

    @Override
    public boolean check(Project project) {
        for (Project active : getActives()) {
            if (Objects.equals(active.getTitle(), project.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Project> getProjectByManagerId(UUID id) {
        return repository.getProjectsByManagerId(id);
    }

    public ArrayList<Project> getAllProjects(boolean active) {
        return repository.allProjects(active);
    }
    public Project getProject(UUID projectId) throws DataNotFoundException{
        return repository.getProject(projectId).orElseThrow(new Supplier<DataNotFoundException>(){
            @Override
            public DataNotFoundException get() {
                return new DataNotFoundException("user not found");
            }
        });
    }
}
