package me.pointofreview.core.objects;

import lombok.Value;

@Value
public class Score {
    float score;
    int approves;
    int disapproves;
    int reports;
}
