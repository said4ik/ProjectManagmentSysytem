package repository;

import model.Project;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ProjectRepository extends BaseRepository<Project> {
    private static final ProjectRepository projectRepository = new ProjectRepository();
    public ProjectRepository getInstance(){
        return projectRepository;
    }
    private ProjectRepository(){
    }
    public ArrayList<Project> getProjectsByManagerId(UUID managerId){
        ArrayList<Project> list  = new ArrayList<>();
        for (Project project : getActives()) {
            if(Objects.equals(project.getManagerId(),managerId)){
                list.add(project);
            }
        }
        return list;
    }
    public void stopProjectByManagerId(UUID managerId){
        for (Project project : getActives()) {
            if(Objects.equals(project.getManagerId(),managerId)){
                project.setActive(false);
                return;
            }
        }
    }
}
