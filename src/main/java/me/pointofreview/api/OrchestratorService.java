package me.pointofreview.api;

import me.pointofreview.core.data.filter.CodeSnippetsFilter;
import me.pointofreview.core.objects.CodeSnippet;
import me.pointofreview.persistence.ModelDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrchestratorService {

    @Autowired
    ModelDataStore dataStore;

    @RequestMapping("/snippets/recent")
    public List<CodeSnippet> recentSnippets(@RequestParam(value="maximumNumber", defaultValue="10") int maximumNumber) {
        return dataStore.getCodeSnippets(new CodeSnippetsFilter(new ArrayList<>(), CodeSnippetsFilter.SortBy.RECENT, maximumNumber));
    }
}
