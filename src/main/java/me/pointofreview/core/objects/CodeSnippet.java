package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class CodeSnippet {
    String id;
    long timestamp;
    String userId;
    String description;
    Code code;
    List<CodeReview> reviews;
    List<Tag> tags;
    Score score;

    public void addReview(CodeReview review) {
        reviews.add(review);
    }

    public CodeReview getReview(String codeReviewId) {
        for(var review : reviews) {
            if(review.getId().equals(codeReviewId))
                return review;
        }

        return null;
    }
}
