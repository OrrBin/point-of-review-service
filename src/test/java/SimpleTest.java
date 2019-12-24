import lombok.Getter;
import me.pointofreview.Application;
import me.pointofreview.persistence.ModelDataStore;
import me.pointofreview.persistence.MongoModelDataStore;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;


@SpringBootTest(classes = Application.class)
public class SimpleTest {

    @Getter
    private ApplicationContext context;

    private ModelDataStore dataStore;

    @Before
    public void initializeTestEnvironment() throws Exception {
        final var testManager = new TestContextManager(getClass());
        testManager.prepareTestInstance(this);
        context = testManager.getTestContext().getApplicationContext();

        dataStore = context.getBean(MongoModelDataStore.class);

    }

    @Test
    public void test() {
        assert dataStore.getCodeSnippetsByUserId("NO_SUCH_USER_EXISTS").isEmpty();
    }
}
