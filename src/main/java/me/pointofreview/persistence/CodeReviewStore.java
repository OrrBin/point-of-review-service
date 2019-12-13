package me.pointofreview.persistence;

import me.pointofreview.core.objects.CodeReview;
import me.pointofreview.core.objects.Post;

import java.util.List;

public interface CodeReviewStore {
    public List<Post> getPosts();
}
