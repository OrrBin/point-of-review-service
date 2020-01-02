package me.pointofreview.persistence;

import me.pointofreview.Application;
import me.pointofreview.core.objects.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestContextManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataMongoTest
@SpringBootTest(classes = Application.class)
public class TestMongoUserData {

    private ApplicationContext context;
    private ModelDataStore dataStore;
    private MongoTemplate mongoTemplate;

    @Before
    public void initializeTestEnvironment() throws Exception {
        final var testManager = new TestContextManager(getClass());
        testManager.prepareTestInstance(this);
        context = testManager.getTestContext().getApplicationContext();

        dataStore = context.getBean(MongoModelDataStore.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
    }

    @Test
    public void test() {
        mongoTemplate.findAll(CodeSnippet.class);
    }

    @Test
    public void generateSnippet() {
        List<CodeReviewSection> sections = new ArrayList<>();
        CodeReviewSection section1 = new CodeReviewSection(UUID.randomUUID().toString(),"1","1","1","content1",null,null,new Score());
        CodeReviewSection section2 = new CodeReviewSection(UUID.randomUUID().toString(),"1","1","1","content2", null, null,new Score());
        sections.add(section1);
        sections.add(section2);
        CodeReview codeReview = new CodeReview(UUID.randomUUID().toString(),1,"userId","snippetId",1,"desc",sections,null,null);
        List<CodeReview> codeReviews = new ArrayList<>();
        codeReviews.add(codeReview);
        CodeSnippet snippet = new CodeSnippet("oz",1,"a","Question",
                "hello, what do you think about my code",new Code("code","java"),codeReviews,null,new Score());

        mongoTemplate.insert(snippet);
    }

    @Test
    public void like() {
        CodeSnippet snippet = dataStore.getCodeSnippet("oz");
        dataStore.updateCodeSnippetImpressions(snippet,"2",Impression.LIKE);
        dataStore.updateCodeSnippetImpressions(snippet,"3",Impression.DISLIKE);
        dataStore.updateCodeSnippetImpressions(snippet,"3",Impression.LIKE);

        assertEquals(2, snippet.impressionCounter(Impression.LIKE));
        assertEquals(0, snippet.impressionCounter(Impression.DISLIKE));
        assertEquals(2, snippet.getScore().getVoterToImpression().size());
    }

}
