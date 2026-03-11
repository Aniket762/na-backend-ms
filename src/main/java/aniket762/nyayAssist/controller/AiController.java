package aniket762.nyayAssist.controller;

import aniket762.nyayAssist.service.AiLawyerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiLawyerService aiService;

    public AiController(AiLawyerService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String q) {
        return aiService.ask(q);
    }
}