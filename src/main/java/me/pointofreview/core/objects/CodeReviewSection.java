package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Represent a part of a code review (on a specific <CodeRange>)
*/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeReviewSection {

    String id;
    String userId;
    String codeSnippetId;
    String codeReviewId;
    Code code;
    String content;
    List<Tag> tags;
    List<Comment> comments;
    Score score;

    public int impressionCounter(Impression impression){
        return score.impressionCounter(impression);
    }

    public void updateImpressions(String userId,Impression impression) {
        score.updateImpressions(userId,impression);
    }


}
