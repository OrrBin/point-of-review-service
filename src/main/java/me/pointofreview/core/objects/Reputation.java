package me.pointofreview.core.objects;

/**
 * Represents User reputation.
 */

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
