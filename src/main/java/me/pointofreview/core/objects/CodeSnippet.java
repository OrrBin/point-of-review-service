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

    //popularity calculation

    public static int calculatePopularity(CodeSnippet snippet) {
        //Calculated_Popularity = (1 + num_of_reviews)*(score)
        return snippet.getScore().calculate();
    }

    public static void sortByPopularity(List<CodeSnippet> snippets) {
        snippets.sort(Collections.reverseOrder(Comparator.comparing(CodeSnippet::calculatePopularity)));
    }

    //custom made by tags
    public static Map<String, Integer> getTagCountMap(List<CodeSnippet> userSnippets, List<CodeSnippet> allSnippets, String username) {
        Map<String, Integer> tagCount = new HashMap<>();

        for (CodeSnippet codeSnip : userSnippets) { // get tags from snippets
            for (Tag tag : codeSnip.getTags()) {
                tagCount.merge(tag.getName(), 1, Integer::sum);
            }
        }

        for (CodeSnippet snippet : allSnippets) { // get tags from review
            for (CodeReview review : snippet.getReviews()) {
                if (review.getUserId().equals(username)) {
                    for (CodeReviewSection section : review.getSections()) {
                        for (Tag tag : section.getTags()) {
                            tagCount.merge(tag.getName(), 1, Integer::sum);
                        }
                    }
                }
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

    public static int getSnippetCorrelationScore(List<CodeSnippet> userPersonalSnippets, List<CodeSnippet> allSnippets, List<Tag> tags, String username) {
        int maxCorScore = 0;
        Map<String, Integer> tagCount = getTagCountMap(userPersonalSnippets, allSnippets, username);
        for (Tag tag : tags) {
            maxCorScore = Math.max(maxCorScore, getTagCount(tagCount, tag.getName()));
        }

        return maxCorScore;
    }

    public static void sortByCustomUser(List<CodeSnippet> snippets, List<CodeSnippet> userPersonalSnippets, List<CodeSnippet> allSnippets, String username) {
        snippets.sort(Collections.reverseOrder(Comparator.comparing(snippet -> getSnippetCorrelationScore(userPersonalSnippets, allSnippets, snippet.getTags(), username))));
    }

    public static List<Tag> getTopTags(List<CodeSnippet> userSnippets, List<CodeSnippet> allSnippets, String username) {
        Map<String, Integer> tagCount = getTagCountMap(userSnippets, allSnippets, username);
        List<String> list = new ArrayList<String>(tagCount.keySet());
        list.sort(Comparator.comparing(tag -> getTagCount(tagCount, tag)));

        if (list.size() > 3) {
            list = list.subList(list.size() - 3, list.size());
        }

        List<Tag> tags = new ArrayList<>();
        for (String tagName : list) {
            tags.add(new Tag(tagName, ""));
        }

        return tags;
    }

}
