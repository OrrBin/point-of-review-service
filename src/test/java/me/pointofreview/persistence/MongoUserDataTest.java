package me.pointofreview.persistence;

import me.pointofreview.Application;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContextManager;

//@DataMongoTest
@SpringBootTest(classes = Application.class)
public class MongoUserDataTest {

    private ApplicationContext context;
    private ModelDataStore dataStore;
//    private MongoTemplate mongoTemplate;

    @Before
    public void initializeTestEnvironment() throws Exception {
        final var testManager = new TestContextManager(getClass());
        testManager.prepareTestInstance(this);
        context = testManager.getTestContext().getApplicationContext();

        dataStore = context.getBean(MongoModelDataStore.class);
    }

    @Test
    public void test() {}
}
