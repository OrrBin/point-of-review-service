package me.pointofreview.persistence;

import me.pointofreview.core.data.filter.CodeSnippetsFilter;
import me.pointofreview.core.objects.CodeReview;
import me.pointofreview.core.objects.CodeSnippet;
import me.pointofreview.core.objects.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoModelDataStore implements ModelDataStore {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<CodeSnippet> getCodeSnippets(CodeSnippetsFilter filter) {
        return mongoTemplate.findAll(CodeSnippet.class); // TODO: use filter
    }

    @Override
    public CodeSnippet getCodeSnippet(String snippetId) {
        return mongoTemplate.findOne(Query.query(Criteria.where("snippetId").is(snippetId)), CodeSnippet.class);
    }

    @Override
    public List<CodeSnippet> getCodeSnippetsByUserId(String userId) {
        return mongoTemplate.find(Query.query(Criteria.where("userId").is(userId)), CodeSnippet.class);
    }

    @Override
    public boolean createCodeSnippet(CodeSnippet snippet) {
        mongoTemplate.insert(snippet);
        return true;
    }

    @Override
    public boolean createComment(Comment comment) {
        var snippet =  mongoTemplate.findOne(Query.query(Criteria.where("snippetId").is(comment.getCodeSnippetId())), CodeSnippet.class);
        if (snippet == null)
            return false;

        var review = snippet.getReview(comment.getCodeReviewId());
        review.addComment(comment);

        mongoTemplate.save(snippet);

        return true;
    }

    @Override
    public boolean addCodeReview(CodeReview review) {
        var snippet =  mongoTemplate.findOne(Query.query(Criteria.where("snippetId").is(review.getCodeSnippetId())), CodeSnippet.class);
        if (snippet == null)
            return false;
        snippet.addReview(review);

        mongoTemplate.save(snippet);

        return true;
    }

    @Override
    public List<CodeSnippet> getAllCodeSnippets() {
        return mongoTemplate.findAll(CodeSnippet.class);
    }

}