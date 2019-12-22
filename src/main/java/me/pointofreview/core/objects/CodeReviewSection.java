package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/*
Represent a code review on a <CodeRange>.
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
    CodeChunk sourceCode;
    String content;
    List<Tag> tags;
    List<Comment> comments;
    Score score;


}
