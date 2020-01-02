package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of tags from a similar family (for example, languages), each with its number of appearances
 * and distribution among its family.
 * Each relevant tag is represented with a Stat object.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Statistics {
    public Statistics(String statType, int total) {
        this.statType = statType;
        this.total = total;
    }

    String statType;
    int total;
    List<Stat> statList = new ArrayList<>();

    public void add(String tagName, int amount) {
        statList.add(new Stat(tagName, amount, (double)amount / total));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Stat implements Comparable<Stat> {
        String tagName;
        Integer amount; // total number of appearances
        Double distribution; // % in relation to its family

        @Override
        public int compareTo(Stat o) {
            return o.amount.compareTo(this.amount);
        }
    }
}
