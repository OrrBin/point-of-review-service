package me.pointofreview.persistence;

import me.pointofreview.core.objects.Impression;
import me.pointofreview.core.objects.Notification;
import me.pointofreview.core.objects.User;

import java.util.List;

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
     * Increase counter
     *
     * @param user the reported user
     * @param reportType type of report - must be spam, badLanguage or misleading
     * @return true if successful
     */
    boolean addReport(User user, String reportType);

    /**
     * Updates existing user, only if user with the same id as the given
     * user already exists
     * @param user user to update
     * @return true if the update was successful, and false otherwise
     */
    boolean updateUser(User user);

    /**
     * Removes all user entries from the database.
     */
    void resetDatabase();

    /**
     * Change password to a user
     * @param user user to update
     * @param newPassword user's new password
     * @return true if the update was successful, and false otherwise
     */
    // boolean changePassword(User user, String newPassword);

    /**
     * Updates existing user reputation
     * @return true if the update was successful, and false otherwise
     */
    boolean updateUserReputation(User user, String voterId, String sourceId, Impression impression);

    /**
     * Get list of notifications of a user.
     * @param username - the user
     * @return list of notifications if user exists, null otherwise
     */
    List<Notification> getNotificationsByUsername(String username);

    /**
     * Add a notification to a user.
     * @param username - the user
     * @param notification - the notification
     * @return return true if successful, false otherwise
     */
    boolean addNotification(String username, Notification notification);
}