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

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthenticationRequest request) {
        if (!userDataStore.checkUsernameAndPassword(request.username, request.password))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        var user = userDataStore.getUserByUsername(request.username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody AuthenticationRequest request) {

        if (userDataStore.existsUsername(request.username))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        var user = new User(request.username, request.password, UUID.randomUUID().toString(), new Reputation());
        var created = userDataStore.createUser(user);

        if (!created)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // user id already exists

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}