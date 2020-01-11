package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Score implements Comparable<Score> {
    private static final int LIMIT = 5;
    private static final float RATIO = (float) 0.4;
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
        else if (currentImperssion == impression){
            voterToImpression.remove(userId);
            impressions.put(currentImperssion, impressions.get(currentImperssion)- 1);
        }

    }

    public int calculate(){
        float likes, dislikes, hasEnoughImpressions,likesRatio;
        if (getImpressions().get(Impression.LIKE) == null) {
            likes = 0;
        } else {
            likes = getImpressions().get(Impression.LIKE);
        }
        if (impressions.get(Impression.DISLIKE)== null) {
            dislikes = 0;
        } else {
            dislikes = impressions.get(Impression.DISLIKE);
        }
        if (dislikes+likes == 0) return 0;
        hasEnoughImpressions = (likes + dislikes) > LIMIT ? 1 : 0 ;
        likesRatio = likes / (likes + dislikes);
        return (int)((dislikes == 0 && likes == 0) ? 0 : 100 * (RATIO * hasEnoughImpressions + (1-RATIO) * likesRatio));
    }

}
