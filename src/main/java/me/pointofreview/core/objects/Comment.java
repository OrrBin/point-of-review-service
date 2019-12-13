package me.pointofreview.core.objects;

import lombok.Value;

import java.util.List;

@Value
public class Comment {
    String id;
    String userId;
    String codeReviewSectionId;
    String content;
    List<Tag> tags;
    Score score;
}
