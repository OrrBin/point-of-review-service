package me.pointofreview.api;

import me.pointofreview.core.objects.CodeSnippet;
import me.pointofreview.core.objects.Statistics;
import me.pointofreview.core.objects.Tag;
import me.pointofreview.persistence.ModelDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StatisticsCollector {
    private final ModelDataStore modelDataStore;

    @Autowired
    public StatisticsCollector(@Qualifier("mongoModelDataStore") ModelDataStore modelDataStore) {
        this.modelDataStore = modelDataStore;
    }

    /**
     * Get the distribution of a certain kind of tags (for example: languages).
     *
     * @param statType the type of tags which the statistic collected on
     * @param limit the maximum number of objects in the distribution - optional parameter, if not provided, there is no upper bound
     * @example statType = "language", limit = 3 => returns the 3 most popular languages
     * @return a {@link Statistics} object that holds the distribution
     * @HttpStatus BAD_REQUEST - no tags are matching the query (empty distribution)
     */
    @GetMapping("/statistics")
    public ResponseEntity<List<Statistics.Stat>> login(@RequestParam String statType, @RequestParam(required = false) Integer limit) {
        List<CodeSnippet> snippets = modelDataStore.getAllCodeSnippets();
        Map<String, Integer> tagCounts = new HashMap<>();
        int total = 0;

        for (CodeSnippet snippet : snippets) {
            for (Tag tag : snippet.getTags()) {
                if (!(tag.getType().equals(statType))) { // We calculate counts of relevant tags only
                    continue;
                }
                int tagCount = tagCounts.getOrDefault(tag.getName(), 0);
                tagCounts.put(tag.getName(), tagCount + 1);
                total++;
            }
        }

        if (total == 0) // no tags match the statType
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Statistics statistics = new Statistics(statType, total);

        for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
            statistics.add(entry.getKey(), entry.getValue());
        }

        if (limit != null && limit < statistics.getStatList().size()) {
            reduceStatList(statistics, limit);
        }

        return new ResponseEntity<>(statistics.getStatList(), HttpStatus.OK);
    }

    private void reduceStatList(Statistics statistics, int limit) {
        Collections.sort(statistics.getStatList()); // sorts in descending order
        int otherCount = 0;
        for (int i = limit; i < statistics.getStatList().size(); i++)
            otherCount += statistics.getStatList().get(i).getAmount();

        statistics.setStatList(statistics.getStatList().subList(0, limit));
        statistics.add("other", otherCount);
    }

}