package me.pointofreview.persistence;

import me.pointofreview.core.data.filter.CodeSnippetsFilter;
import me.pointofreview.core.objects.*;
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
        var snippet = mongoTemplate.findOne(Query.query(Criteria.where("snippetId").is(comment.getCodeSnippetId())), CodeSnippet.class);
        if (snippet == null)
            return false;

        var review = snippet.getReview(comment.getCodeReviewId());
        review.addComment(comment);

        mongoTemplate.save(snippet);

        return true;
    }

    @Override
    public boolean addCodeReview(CodeReview review) {
        var snippet = mongoTemplate.findOne(Query.query(Criteria.where("snippetId").is(review.getCodeSnippetId())), CodeSnippet.class);
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

    @Override
    public CodeReview getCodeReview(String snippetId, String codeReviewId) {
        var snippet = mongoTemplate.findOne(Query.query(Criteria.where("snippetId").is(snippetId)), CodeSnippet.class);
        return snippet != null ? snippet.getReview(codeReviewId) : null;
    }

    @Override
    public CodeReviewSection getCodeReviewSection(String snippetId, String codeReviewId, String sectionId) {
        var codeReview = getCodeReview(snippetId, codeReviewId);
        return codeReview != null ? codeReview.getCodeReviewSection(sectionId) : null;
    }

//    @Override
//    public boolean updateCodeReviewSectionImpressions(CodeReviewSection codeReviewSection, String userId, Impression impression) {
//
//        if (codeReviewSection==null)
//            return false;
//
//        codeReviewSection.updateImpressions(userId,impression);
//
//        mongoTemplate.save(codeReviewSection);
//
//        return true;
//    }

    @Override
    public boolean updateCodeSnippetImpressions(CodeSnippet codeSnippet, String userId, Impression impression) {

        if (codeSnippet == null)
            return false;

        codeSnippet.updateImpressions(userId, impression);

        mongoTemplate.save(codeSnippet);

        return true;
    }

}