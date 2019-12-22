package me.pointofreview.persistence;

import me.pointofreview.core.objects.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryUserDataStore implements UserDataStore {

    private Map<String, User> users;

    public InMemoryUserDataStore() {
        this.users = new HashMap<>();
    }

    @Override
    public User getUser(String userId) {
        return users.get(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return null;
    }


    @Override
    public boolean createUser(User user) {
        if (users.containsKey(user.getId()))
            return false;

        users.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        if (!users.containsKey(user.getId()))
            return false;

        users.put(user.getId(), user);
        return true;
    }
}
