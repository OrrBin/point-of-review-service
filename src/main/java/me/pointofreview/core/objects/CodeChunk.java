package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Unknown - Orr complete it PLEASE
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeChunk {
    String codeSnippetId;
    List<CodeRange> range;
    Code code;
}
