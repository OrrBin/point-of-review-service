package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Score implements Comparable<Score> {

    float score;
    Map<Impression,Integer> impressions = new HashMap<>();

    // track users that gave impressions to the section
    // in order to check user can't vote more than once
    Map<String,Impression> voterToImpression = new HashMap<>();

    @Override
    public int compareTo(Score o) {
        return Float.compare(this.score, o.score);
    }

    public int impressionCounter(Impression impression){
        return impressions.get(impression);
    }

    public void updateImpressions(String userId,Impression impression){

        Impression currentImperssion = voterToImpression.get(userId);
        if (currentImperssion != impression) {
            voterToImpression.put(userId, impression);
            if (currentImperssion!=null){
                // user changed his mind
                impressions.put(currentImperssion, impressions.get(currentImperssion)- 1);
            }

            impressions.put(impression,
                    impressions.get(impression) != null ? impressions.get(impression) + 1 :  1);
        }
    }

}
