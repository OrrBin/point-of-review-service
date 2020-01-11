package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.*;

/**
 * Represents a code, to be reviewed.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeSnippet {
    @Id
    String id;
    long timestamp;
    String username;
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
        for (var review : reviews) {
            if (review.getId().equals(codeReviewId))
                return review;
        }

        return null;
    }

    public void updateImpressions(String userId, Impression impression) {
        score.updateImpressions(userId, impression);
    }

    public int impressionCounter(Impression impression) {
        return score.impressionCounter(impression);
    }

    public CodeReviewSection getCodeReviewSection(String codeReviewId, String sectionId) {
        var codeReview = getReview(codeReviewId);
        return codeReview != null ? codeReview.getCodeReviewSection(sectionId) : null;
    }

    public static void sortByTimestamps(List<CodeSnippet> snippets) {
        Collections.sort(snippets, Collections.reverseOrder(Comparator.comparing(snippet -> ((Long) snippet.getTimestamp()))));
    }

    public static void sortByAlphabetical(List<CodeSnippet> snippets) {
        Collections.sort(snippets, Comparator.comparing(CodeSnippet::getTitle));
    }

    public static void sortByPopularity(List<CodeSnippet> snippets) {
        snippets.sort(Collections.reverseOrder(Comparator.comparing(snippet -> (snippet.getScore().getScore()))));
    }

    //custom made by tags
    public static Map<String, Integer> getTagCountMap(List<CodeSnippet> snippets){
        Map<String, Integer> tagCount = new HashMap<>();
        for (CodeSnippet codeSnip: snippets) {
            for (Tag tag: codeSnip.getTags()) {
                tagCount.merge(tag.getName(), 1, Integer::sum);
            }
        }
        return tagCount;
    }

    public static int getTagCount(Map<String, Integer> tagCount, String tagName) {
        Integer val = tagCount.get(tagName);
        if (val != null && val != 0) {
            return val;
        }
        return 0;
    }

    public static int getSnippetCorrelationScore(List<CodeSnippet> userPersonalSnippets, List<Tag> tags) {
        int maxCorScore = 0;
        Map<String, Integer> tagCount = getTagCountMap(userPersonalSnippets);
        for (Tag tag : tags) {
            maxCorScore = Math.max(maxCorScore , getTagCount(tagCount, tag.getName()));
        }

        return maxCorScore;
    }

    public static void sortByCustomUser(List<CodeSnippet> snippets, List<CodeSnippet> userPersonalSnippets) {
        snippets.sort(Collections.reverseOrder(Comparator.comparing(snippet -> getSnippetCorrelationScore(userPersonalSnippets, snippet.getTags()))));
    }


}
