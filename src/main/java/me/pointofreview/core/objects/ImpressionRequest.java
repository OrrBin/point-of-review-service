
package me.pointofreview.core.objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImpressionRequest {
    public String snippetId;
    public String codeReviewId;
    public String codeReviewSectionId;
    public String voterId;
    public Impression impression;
}