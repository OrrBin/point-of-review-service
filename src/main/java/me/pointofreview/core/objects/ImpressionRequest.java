
package me.pointofreview.core.objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImpressionRequest {
    public CodeSnippet snippet;
    public String codeReviewId;
    public String codeReviewSectionId;
    public String voterId;
    public Impression impression;
}