package me.pointofreview.persistence;

import me.pointofreview.core.objects.User;

public interface UserDataStore {

    /**
     * Get user by user id as {@link User} object
     * @param userId requested user id
     * @return if user with the requested user id exists then it is returned.
     * otherwise returns null
     */
    User getUser(String userId);

    /**
     * Creates new user, only if user with the same id as the given
     * user does not already exist
     * @param user user to create
     * @return true if the creation was successful, and false otherwise
     */
    boolean createUser(User user);

    /**
     * updates existing user, only if user with the same id as the given
     * user already exists
     * @param user user to update
     * @return true if the update was successful, and false otherwise
     */
    boolean updateUser(User user);
}
