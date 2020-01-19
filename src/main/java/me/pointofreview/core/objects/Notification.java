package me.pointofreview.core.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    long timestamp;
    String sender; // the user who triggered the notification
    String receiver; // the user who received the notification
    String action; // LIKE or REVIEW
    CodeSnippet snippet;
    CodeReview review;
}
