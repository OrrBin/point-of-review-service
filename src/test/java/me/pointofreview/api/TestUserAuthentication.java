package me.pointofreview.api;

import me.pointofreview.Application;
import me.pointofreview.core.data.generator.UserGenerator;
import me.pointofreview.core.objects.AuthenticationRequest;
import me.pointofreview.core.objects.Reputation;
import me.pointofreview.core.objects.User;
import org.junit.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestContextManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = Application.class)
public class TestUserAuthentication {

    private ApplicationContext context;
    private UserAuthentication userAuth;
    private MongoTemplate mongoTemplate;

    @Before
    public void initializeTestEnvironment() throws Exception {
        final var testManager = new TestContextManager(getClass());
        testManager.prepareTestInstance(this);
        context = testManager.getTestContext().getApplicationContext();

        userAuth = context.getBean(UserAuthentication.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        UserGenerator.generateUsersToDB(100, mongoTemplate);
    }

    @After
    public void cleanDatabase() throws Exception {
        mongoTemplate.dropCollection(User.class);
    }

    @Test
    public void loginAttemptShouldFail() {
        String username = "IllegalUser", password = "pass";
        AuthenticationRequest request = new AuthenticationRequest(username, password);
        ResponseEntity<User> user = userAuth.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, user.getStatusCode());
        assertNull(user.getBody());
    }

    @Test
    public void loginAttemptShouldSucceed() {
        mongoTemplate.insert(new User("ScrumMaster", "123456", "key-123", new Reputation()));

        String username = "ScrumMaster", password = "123456";

        AuthenticationRequest request = new AuthenticationRequest(username, password);
        ResponseEntity<User> user = userAuth.login(request);

        assertEquals(HttpStatus.OK, user.getStatusCode());
        assertEquals(username, user.getBody().getUsername());
        assertEquals(password, user.getBody().getPassword());
    }

    @Test
    public void registerShouldFailWithConflict() {
        mongoTemplate.insert(new User("ScrumMaster", "123456", "key-123", new Reputation()));

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

        User inserted = mongoTemplate.findOne(Query.query(Criteria.where("username").is("ScrumKing")), User.class);

        assertEquals(HttpStatus.OK, user.getStatusCode());
        assertEquals(username, user.getBody().getUsername());
        assertEquals(password, user.getBody().getPassword());
        assertEquals(username, inserted.getUsername());
        assertEquals(password, inserted.getPassword());
    }
}
