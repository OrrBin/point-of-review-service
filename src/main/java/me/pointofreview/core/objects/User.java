package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    String username;
    String password;
    String id;
    Reputation reputation;
    ReportStatus report;

    public void updateImpressions(String voterId,String sourceId,Impression impression) {
        reputation.updateImpressions(voterId,sourceId,impression);
    }

}
