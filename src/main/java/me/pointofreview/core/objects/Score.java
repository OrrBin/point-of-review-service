package me.pointofreview.core.objects;

import lombok.Value;

@Value
public class Score implements Comparable<Score> {
    float score;
    int approves;
    int disapproves;
    int reports;

    @Override
    public int compareTo(Score o) {
        return Float.compare(this.score, o.score);
    }
}
