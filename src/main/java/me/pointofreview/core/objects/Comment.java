package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * A comment to ??
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Comment {
    String id;
    long timestamp;
    String userId;
    String codeSnippetId;
    String codeReviewId;
    String codeReviewSectionId;
    String content;
    List<Tag> tags;
    Score score;
}
