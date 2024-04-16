package service;

import model.User;

public class UserRepository extends BaseRepository<User>{
    private static final UserRepository userRepository = new UserRepository();
    public UserRepository getInstance(){
        return userRepository;
    }
    public UserRepository(){

    }
}
