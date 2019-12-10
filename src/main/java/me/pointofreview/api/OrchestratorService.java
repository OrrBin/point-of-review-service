package me.pointofreview.api;

import me.pointofreview.core.objects.Score;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrchestratorService {

    @RequestMapping("/score")
    public Score score(@RequestParam(value="name", defaultValue="World") String name) {
        return new Score(0.5f, 5, 6, 1);
    }
}

