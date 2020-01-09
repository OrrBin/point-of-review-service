package me.pointofreview.persistence;

import jdk.jshell.Snippet;
import me.pointofreview.core.data.filter.CodeSnippetsFilter;
import me.pointofreview.core.objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        return mongoTemplate.findOne(Query.query(Criteria.where("id").is(snippetId)), CodeSnippet.class);
    }

    @Override
    public List<CodeSnippet> getCodeSnippetsByUserId(String userId) {
        return mongoTemplate.find(Query.query(Criteria.where("userId").is(userId)), CodeSnippet.class);
    }

    @Override
    public List<CodeSnippet> getCodeSnippetByTag(String tagName) {
        List<CodeSnippet> snippets = getAllCodeSnippets();
        List<CodeSnippet> out = new ArrayList<>();

        for (CodeSnippet snippet : snippets) {
            for (Tag tag : snippet.getTags()) {
                if (tag.getName().equals(tagName)) {
                    out.add(snippet);
                    break;
                }
            }
        }

        if (out.isEmpty())
            return null;

        return out;
    }

    @Override
    public List<CodeSnippet> getCodeSnippetByTags(List<String> tagNames) {
        List<CodeSnippet> snippets = getAllCodeSnippets();
        List<CodeSnippet> out = new ArrayList<>();

        for (CodeSnippet snippet : snippets) {
            for (Tag tag : snippet.getTags()) {
                if (tagNames.contains(tag.getName())) {
                    out.add(snippet);
                    break;
                }
            }
        }

        return out;
    }

    @Override
    public boolean createCodeSnippet(CodeSnippet snippet) {
        mongoTemplate.insert(snippet);
        return true;
    }

    @Override
    public boolean createComment(Comment comment) {
        var snippet =  mongoTemplate.findOne(Query.query(Criteria.where("id").is(comment.getCodeSnippetId())), CodeSnippet.class);
        if (snippet == null)
            return false;

        var review = snippet.getReview(comment.getCodeReviewId());
        review.addComment(comment);

        mongoTemplate.save(snippet);

        return true;
    }

    @Override
    public boolean addCodeReview(CodeReview review) {
        int i = 1;
        var snippet =  mongoTemplate.findOne(Query.query(Criteria.where("id").is(review.getCodeSnippetId())), CodeSnippet.class);
        if (snippet == null)
            return false;
        for ( CodeReviewSection section : review.getSections()){
            section.setCodeReviewId(review.getId());
            section.setId(Integer.toString(i++));
        }
        snippet.addReview(review);

        mongoTemplate.save(snippet);

        return true;
    }

    @Override
    public List<CodeSnippet> getAllCodeSnippets() {
        return mongoTemplate.findAll(CodeSnippet.class);
    }

    @Override
    public Score updateCodeReviewSectionImpressions(String snippetId,String codeReviewId, String codeReviewSectionId, String userId, Impression impression){
        CodeSnippet snippet = getCodeSnippet(snippetId);
        if (snippet == null)
            return null;
        CodeReviewSection section = snippet.getCodeReviewSection(codeReviewId,codeReviewSectionId);

        section.updateImpressions(userId,impression);
        mongoTemplate.save(snippet);

        return section.getScore();
    }

    @Override
    public boolean updateCodeSnippetImpressions(CodeSnippet codeSnippet, String userId, Impression impression) {
        if (codeSnippet == null)
            return false;

        codeSnippet.updateImpressions(userId, impression);

        mongoTemplate.save(codeSnippet);

        return true;
    }

}
