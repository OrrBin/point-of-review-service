package me.pointofreview.core.objects;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/*
Represents a part of a code.
For example, a part of the code to be reviewed.
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeRange {

    List<RowRange> rows;

    @Value
    public static class ColumnRange {
        int start;
        int end;
    }

    @Value
    public static class RowRange {
        int rowNum;
        List<ColumnRange> columns;
    }
}
