package camt.se234.project.dao;

import camt.se234.project.entity.User;

public interface UserDao {
    User getUser(String username, String password);
    User getRole();
}
