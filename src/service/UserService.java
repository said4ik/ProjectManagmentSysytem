package service;

import enam.Role;
import exception.DataNotFoundException;
import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public class UserService extends BaseService<User, UserRepository> {

    private static final UserService userService = new UserService(new UserRepository());

    public static UserService getInstance() {
        return userService;
    }

    public UserService(UserRepository repository) {
        super(repository);
    }

    public User signIn(String username) throws DataNotFoundException {
        return repository.findByUserName(username).orElseThrow(new Supplier<DataNotFoundException>() {
            @Override
            public DataNotFoundException get() {
                return new DataNotFoundException("user not found");
            }
        });
    }

    public ArrayList<User> showRole(Role role) {
        return repository.showRole(role);
    }

    public void stopManager(UUID id, boolean ans) {
        repository.stopManager(id, ans);
    }

    public ArrayList<User> getEmployerProjects(UUID id) {
        return repository.getEmployerWithProject(id);
    }

    @Override
    public boolean check(User user) {
        for (User active : getActives()) {
            if (Objects.equals(active.getUsername(), user.getUsername())) {
               return true;
            }
        }
       return false;
    }
}
