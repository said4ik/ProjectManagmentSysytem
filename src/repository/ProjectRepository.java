package repository;

import model.Project;
import model.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class ProjectRepository extends BaseRepository<Project> {
   private static final ProjectRepository projectRepository=new ProjectRepository();

    public static ProjectRepository getInstance() {
        return projectRepository;
    }

    private ProjectRepository() {
    }

    public ArrayList<Project> getProjectsByManagerId(UUID managerId) {
        ArrayList<Project> list = new ArrayList<>();
        for (Project project : getActives()) {
            if (Objects.equals(project.getManagerId(), managerId)) {
                list.add(project);
            }
        }
        return list;
    }


    public ArrayList<Project> allProjects(boolean active) {
        ArrayList<Project> projects = new ArrayList<>();
        for (Project project : data) {
            if (Objects.equals(project.isActive(), active)) {
                projects.add(project);
            }
        }
        return projects;

    }
    public Optional<Project> getProject(UUID projectId){
        for (Project project : getActives()) {
            if(Objects.equals(project.getId(),projectId)){
                return Optional.of(project);
            }
        }
        return Optional.empty();
    }
}
