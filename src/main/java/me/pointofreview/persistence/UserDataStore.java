package me.pointofreview.persistence;

import me.pointofreview.core.objects.User;

public interface UserDataStore {

    /**
     * Check is user already exists.
     * @param username user's username
     * @return true if the user already exists, false otherwise
     */
    boolean existsUsername(String username);

    /**
     * Get user by user id as {@link User} object
     * @param userId requested user id
     * @return if user with the requested user id exists then it is returned.
     * otherwise returns null
     */
    User getUserById(String userId);

    /**
     * Get user by username as {@link User} object
     * @param username requested username
     * @return if user with the requested username exists then it is returned.
     * otherwise returns null
     */
    User getUserByUsername(String username);

    /**
     * Checks if username and password match
     * @param username requested username
     * @param password matching password
     * @return true if the username exists and the password is correct, false otherwise
     */
    boolean checkUsernameAndPassword(String username, String password);

    /**
     * Creates new user, only if user with the same id or username as the given
     * user does not already exist
     * @param user user to create
     * @return true if the creation was successful, and false otherwise
     */
    boolean createUser(User user);

    /**
     * Updates existing user, only if user with the same id as the given
     * user already exists
     * @param user user to update
     * @return true if the update was successful, and false otherwise
     */
    boolean updateUser(User user);

    /**
     * Change password to a user
     * @param user user to update
     * @param newPassword user's new password
     * @return true if the update was successful, and false otherwise
     */
    // boolean changePassword(User user, String newPassword);
}