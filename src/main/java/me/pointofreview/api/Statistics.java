package me.pointofreview.api;

import me.pointofreview.core.objects.CodeSnippet;
import me.pointofreview.core.objects.User;
import me.pointofreview.persistence.ModelDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Statistics {
    private final ModelDataStore modelDataStore;

    @Autowired
    public Statistics(@Qualifier("mongoModelDataStore") ModelDataStore modelDataStore) {
        this.modelDataStore = modelDataStore;
    }

    /**
     * Attempt to log in.
     * @param request contains username and password
     * @return {@link User} if username and password match, null otherwise
     * @HttpStatus UNAUTHORIZED - username and password don't match
     */
    @GetMapping("/statistics")
    public ResponseEntity<User> login(@RequestParam String request) {
        List<CodeSnippet> snippets = modelDataStore.getAllCodeSnippets();
        return null;
    }
}