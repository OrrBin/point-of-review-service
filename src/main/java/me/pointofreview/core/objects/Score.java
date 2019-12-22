package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
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
