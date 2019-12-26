package me.pointofreview.api;

import me.pointofreview.core.objects.AuthenticationRequest;
import me.pointofreview.core.objects.Reputation;
import me.pointofreview.core.objects.User;
import me.pointofreview.persistence.UserDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserAuthentication {
    private final UserDataStore userDataStore;

    @Autowired
    public UserAuthentication(@Qualifier("mongoUserDataStore") UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
    }

    /**
     * Attempt to log in.
     * @param request contains username and password
     * @return {@link User} if username and password match, null otherwise
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthenticationRequest request) {
        if (!userDataStore.checkUsernameAndPassword(request.username, request.password))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        var user = userDataStore.getUserByUsername(request.username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Register a user.
     * Username and password length should be between 3 to 12, and contain only numbers and letters.
     * Username should start with a letter.
     * @param request contains username and password to register
     * @return {@link User} of the created user if succeed, null otherwise
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody AuthenticationRequest request) {

        if (userDataStore.existsUsername(request.username))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        if (!isLegalFormat(request.username, request.password))
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);

        // Create user
        var user = new User(request.username, request.password, UUID.randomUUID().toString(), new Reputation());
        var created = userDataStore.createUser(user);

        if (!created)
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED); // user id already exists

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private boolean isLegalFormat(String username, String password){
        char c = username.toLowerCase().charAt(0);

        // Check user and password are legal
        if (!(username.length() >= 3 && username.length() <= 12))
            return false;

        if (!(password.length() >= 3 && password.length() <= 12))
            return false;

        if (!(c >= 'a' && c <= 'z')) // username doesn't start with a letter
            return false;

        for (int i = 1; i < username.length(); i++) {
            c = username.toLowerCase().charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= '0' || c <= '9')))
                return false;
        }

        for (int i = 0; i < password.length(); i++) {
            c = password.toLowerCase().charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= '0' || c <= '9')))
                return false;
        }

        return true;
    }
}