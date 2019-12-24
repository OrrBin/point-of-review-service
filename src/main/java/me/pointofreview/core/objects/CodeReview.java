package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * A single review on an uploaded code.
 * Contains a collection of {@link CodeReviewSection}.
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
