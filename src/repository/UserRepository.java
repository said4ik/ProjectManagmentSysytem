package repository;

import enam.Role;
import model.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserRepository extends BaseRepository<User> {

    private static final UserRepository userRepository = new UserRepository();

    public static UserRepository getInstance() {
        return userRepository;
    }

    private UserRepository() {

    }

    public Optional<User> findByUserName(String username) {
        for (User user : getActives()) {
            if (Objects.equals(user.getUsername(), username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public ArrayList<User> showRole(Role role) {
        ArrayList<User> admin = new ArrayList<>();
        for (User user : getActives()) {
            if (Objects.equals(user.getRole(), role)) {
                admin.add(user);
            }
        }
        return admin;
    }

    public void stopManager(UUID id, boolean ans) {
        for (User user : data) {
            if (Objects.equals(user.getId(), id)) {
                user.setMissionM(ans);
                return;
            }
        }
    }

    public void stopProject(UUID id, boolean ans) {
        for (User user : data) {
            if (Objects.equals(user.getProjectId(), id)) {
                user.setMissionM(ans);
            }
        }
    }


    public ArrayList<User> getEmployerWithProject(UUID id) {
        ArrayList<User> users = new ArrayList<>();
        for (User user : getActives()) {
            if (Objects.equals(user.getProjectId(), id)) {
                users.add(user);
            }
        }
        return users;
    }


    public ArrayList<User> getTeamLeaderTask(UUID id, Role role) {
        ArrayList<User> users = new ArrayList<>();
        for (User user : getActives()) {
            if (Objects.equals(user.getId(), id) && user.getRole().equals(role)) {
                users.add(user);
            }
        }
        return users;
    }
}


