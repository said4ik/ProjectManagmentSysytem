package repository;

import model.User;

import java.util.Optional;

public class UserRepository extends BaseRepository<User>{
    private static final UserRepository userRepository = new UserRepository();
    public UserRepository getInstance(){
        return userRepository;
    }


}
