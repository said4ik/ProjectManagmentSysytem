package repository;

import model.User;

import java.util.Objects;
import java.util.Optional;

public class UserRepository extends BaseRepository<User> {
    private static final UserRepository userRepository = new UserRepository();

    public UserRepository getInstance() {
        return userRepository;
    }

    public Optional<User> findByUserName(String username) {
        for (User user : data) {
            if (Objects.equals(user.getUsername(), username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
