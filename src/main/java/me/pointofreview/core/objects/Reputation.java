package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents User reputation.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reputation extends Score{
    int reputation;
    int rank;

    Map<String, Map<String,Impression>> voterToSourceToImpression = new HashMap<>();

    public void updateImpressions(String userId,String sourceId, Impression impression){

        Map<Impression, Integer> impressions = getImpressions();

        if (voterToSourceToImpression.get(userId) == null) {
            Map<String,Impression> map = new HashMap<>();
            map.put(sourceId,impression);
            voterToSourceToImpression.put(userId, map);
            impressions.put(impression,
                    impressions.get(impression) != null ? impressions.get(impression) + 1 :  1);

        }
        else{
            Map<String,Impression> map = voterToSourceToImpression.get(userId);
            Impression currentImperssion = map.get(sourceId);
            if (currentImperssion != impression) {
                map.put(sourceId, impression);
                if (currentImperssion!=null){
                    // user changed his mind
                    impressions.put(currentImperssion, impressions.get(currentImperssion)- 1);
                }
                impressions.put(impression,
                        impressions.get(impression) != null ? impressions.get(impression) + 1 :  1);
            }
            else if (currentImperssion == impression){
                map.remove(sourceId);
                impressions.put(currentImperssion, impressions.get(currentImperssion)- 1);
            }

        }
    }


    void decreaseReputation(){
        reputation--;
    }

    void decreaseReputation(int delta){
        reputation-=delta;
    }

    void increaseReputation(){
        reputation++;
    }

    void increaseReputation(int delta){
        reputation+=delta;
    }
}

