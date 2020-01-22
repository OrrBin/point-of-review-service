package me.pointofreview.core.data.generator;

import me.pointofreview.core.objects.CodeReview;
import me.pointofreview.core.objects.CodeSnippet;
import me.pointofreview.persistence.ModelDataStore;
import me.pointofreview.persistence.UserDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Util {
    private final ModelDataStore modelDataStore;
    private final UserDataStore userDataStore;

    @Autowired
    public Util(@Qualifier("mongoModelDataStore") ModelDataStore modelDataStore, @Qualifier("mongoUserDataStore") UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
        this.modelDataStore = modelDataStore;
    }

    /**
     * @return the current timestamp
     */
    public static long getTimestamp() {
        Date date = new Date();
        return date.getTime();
    }

    public static void sortReviewsByReputation(List<CodeSnippet> snippets, UserDataStore userDataStore) {
        for (CodeSnippet snippet : snippets)
            sortReviewByReputation(snippet.getReviews(), userDataStore);
    }
    public static void sortReviewByReputation(List<CodeReview> reviews, UserDataStore userDataStore) {
        Collections.sort(reviews, (Collections.reverseOrder(Comparator.comparing(review -> userDataStore.getUserByUsername(review.getUserId()).getReputation().calculate()))));
    }
}
