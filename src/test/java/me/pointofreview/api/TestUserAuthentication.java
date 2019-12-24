package me.pointofreview.api;

import me.pointofreview.Application;
import me.pointofreview.core.objects.AuthenticationRequest;
import me.pointofreview.core.objects.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestContextManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = Application.class)
public class TestUserAuthentication {

    private ApplicationContext context;

    private UserAuthentication userAuth;

    @Before
    public void initializeTestEnvironment() throws Exception {
        final var testManager = new TestContextManager(getClass());
        testManager.prepareTestInstance(this);
        context = testManager.getTestContext().getApplicationContext();
        userAuth = context.getBean(UserAuthentication.class);
        // Add User ScrumMaster:123456
        userAuth.registerUser(new AuthenticationRequest("ScrumMaster", "123456"));
    }

    @Test
    public void loginAttemptShouldFail() {
        String username = "_IllegalUser", password = "pass";
        AuthenticationRequest request = new AuthenticationRequest(username, password);
        ResponseEntity<User> user = userAuth.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, user.getStatusCode());
        assertNull(user.getBody());
    }

    @Test
    public void loginAttemptShouldSucceed() {
        String username = "ScrumMaster", password = "123456";

        AuthenticationRequest request = new AuthenticationRequest(username, password);
        ResponseEntity<User> user = userAuth.login(request);

        assertEquals(HttpStatus.OK, user.getStatusCode());
        assertEquals(username, user.getBody().getUsername());
        assertEquals(password, user.getBody().getPassword());
    }

    @Test
    public void registerShouldFailWithConflict() {
        String username = "ScrumMaster", password = "Or_Binyamini";

        AuthenticationRequest request = new AuthenticationRequest(username, password);
        ResponseEntity<User> user = userAuth.registerUser(request);

        assertEquals(HttpStatus.CONFLICT, user.getStatusCode());
    }

    @Test
    public void registerShouldFailWithPreconditionFailed() {
        String username = "0Yosi", password = "12345789";

        AuthenticationRequest request = new AuthenticationRequest(username, password);
        ResponseEntity<User> user = userAuth.registerUser(request);

        assertEquals(HttpStatus.PRECONDITION_FAILED, user.getStatusCode());
    }

    @Test
    public void registerShouldSucceed() {
        String username = "ScrumKing", password = "123456789";
        AuthenticationRequest request = new AuthenticationRequest(username, password);
        ResponseEntity<User> user = userAuth.registerUser(request);

        assertEquals(HttpStatus.OK, user.getStatusCode());
        assertEquals(username, user.getBody().getUsername());
        assertEquals(password, user.getBody().getPassword());
    }
}
