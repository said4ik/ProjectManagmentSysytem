package service;

import exception.DataNotFoundException;
import model.User;
import repository.UserRepository;

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

    @Override
    public boolean check(User user) {
        return false;
    }
}
