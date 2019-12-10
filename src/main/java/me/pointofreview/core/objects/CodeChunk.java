package me.pointofreview.core.objects;

import lombok.Value;

import java.util.List;

@Value
public class CodeChunk {
    String codeSnippetId;
    List<CodeRange> range;
    Code code;

}
