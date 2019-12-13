package me.pointofreview.core.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
Represent a code review on a <CodeRange>.
*/

@Getter
@Setter
@AllArgsConstructor
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
