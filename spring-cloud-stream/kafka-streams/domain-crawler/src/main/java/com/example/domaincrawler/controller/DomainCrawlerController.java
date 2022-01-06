package com.example.domaincrawler.controller;

import com.example.domaincrawler.service.DomainCrawlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DomainCrawlerController {

    private final DomainCrawlerService domainCrawlerService;

    public DomainCrawlerController(DomainCrawlerService domainCrawlerService) {
        this.domainCrawlerService = domainCrawlerService;
    }

    @GetMapping("/asteroids")
    public String lookup() {
        domainCrawlerService.crawl();
        return "Domain Crawler has scrapped your data";
    }
}
