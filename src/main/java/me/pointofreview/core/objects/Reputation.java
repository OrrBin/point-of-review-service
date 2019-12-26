package me.pointofreview.core.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents User reputation.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reputation {
    int reputation;
    int rank;

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
