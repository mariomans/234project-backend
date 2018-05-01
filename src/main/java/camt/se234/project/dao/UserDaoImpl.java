package camt.se234.project.dao;

import camt.se234.project.entity.User;
import camt.se234.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    List<User> users;
    @Autowired
    UserRepository userRepository;

    public UserDaoImpl(){
        users = new ArrayList<>();
        users.add(new User("001","mario123"));
    }
    @Override
    public User getUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
