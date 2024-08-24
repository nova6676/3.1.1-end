package ba.valera.springBoot.dao;


import ba.valera.springBoot.model.User;

import java.util.List;

public interface UserDao {
    void removeUserById(long id);

    List<User> getAllUsers();

    User getUserById(long id);

    void save(User user);

    void update(User user);
}
