package me.pointofreview.core.objects;

import lombok.Value;

import java.util.List;

/*
A review of someone on an uploaded code, a collection of <CodeReviewSection>.
*/
@Value
public class CodeReview {
    String id;
    String userId;
    String codeSnippetId;
    float score;
    String description;
    List<CodeReviewSection> sections;
    List<Tag> tags;
    List<Comment> comments;
}
