package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a code, to be reviewed.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class CodeSnippet {
    @Id
    String id;
    long timestamp;
    String userId;
    String title;
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

    public void updateImpressions(String userId,Impression impression) {
        score.updateImpressions(userId,impression);
    }

    public int impressionCounter(Impression impression){
        return score.impressionCounter(impression);
    }

    public CodeReviewSection getCodeReviewSection(String codeReviewId, String sectionId) {
        var codeReview = getReview(codeReviewId);
        return codeReview != null ? codeReview.getCodeReviewSection(sectionId) : null;
    }

    public static void sortByTimestamps(List<CodeSnippet> snippets) {
        Collections.sort(snippets, Collections.reverseOrder(Comparator.comparing(snippet -> ((Long) snippet.getTimestamp()))));

    }
}