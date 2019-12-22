package me.pointofreview.core.data.filter;

import lombok.Value;
import me.pointofreview.core.objects.CodeSnippet;
import me.pointofreview.core.objects.Tag;

import java.util.List;

@Value
public class CodeSnippetsFilter {
    List<Tag> tags;
    SortBy sortBy;
    int maximumNumber;

    public boolean accept(CodeSnippet snippet) {
        if(tags.isEmpty())
            return true;

        var result = false;
        for(var tag : tags)
            if(snippet.getTags().contains(tag)) {
                result = true;
                break;
            }

        return result;
    }

    public enum SortBy {
        POPULARITY,
        RECENT
    }
}
