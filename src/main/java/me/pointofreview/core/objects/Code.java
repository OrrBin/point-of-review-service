package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

/*
The code of a code review request.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Code {
    String text;
}
