package me.pointofreview.core.objects;

import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@NonNull
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
