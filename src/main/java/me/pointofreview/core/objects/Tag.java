package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * A Tag object.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tag {
    String name;
    String type;

    public Tag(String name){
        this.name = name;
    }


}
