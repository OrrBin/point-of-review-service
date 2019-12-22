package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/*
A review of someone on an uploaded code, a collection of <CodeReviewSection>.
*/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class CodeReview {
    String id;
    long timestamp;
    String userId;
    String codeSnippetId;
    float score;
    String description;
    List<CodeReviewSection> sections;
    List<Tag> tags;
    List<Comment> comments;

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
