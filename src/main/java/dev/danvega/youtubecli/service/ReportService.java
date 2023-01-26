package dev.danvega.youtubecli.service;

import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final VideoService videoService;

    public ReportService(VideoService videoService) {
        this.videoService = videoService;
    }

}
