import lombok.Getter;
import me.pointofreview.Application;
import me.pointofreview.core.data.generator.CodeSnippetGenerator;
import me.pointofreview.persistence.ModelDataStore;
import me.pointofreview.persistence.MongoModelDataStore;
import me.pointofreview.persistence.MongoUserDataStore;
import me.pointofreview.persistence.UserDataStore;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestContextManager;


@SpringBootTest(classes = Application.class)
public class SimpleTest {

    @Getter
    private ApplicationContext context;

    private ModelDataStore dataStore;
    private UserDataStore userDataStore;
    private MongoTemplate mongoTemplate;

    @Before
    public void initializeTestEnvironment() throws Exception {
        final var testManager = new TestContextManager(getClass());
        testManager.prepareTestInstance(this);
        context = testManager.getTestContext().getApplicationContext();

        dataStore = context.getBean(MongoModelDataStore.class);
        userDataStore = context.getBean(MongoUserDataStore.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
    }

    @Test
    public void generalTest() {
        CodeSnippetGenerator.generateToDB(10, mongoTemplate);
    }
}