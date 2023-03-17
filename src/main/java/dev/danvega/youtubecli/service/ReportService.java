package dev.danvega.youtubecli.service;

import dev.danvega.youtubecli.model.TeamTabRow;
import dev.danvega.youtubecli.model.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    public List<TeamTabRow> videosToTeamTabRows(List<Video> videos) {
        return videos.stream()
                .map(video -> new TeamTabRow(video.snippet().title(),
                        "YouTube",
                        video.snippet().publishedAt().toLocalDate(),
                        video.snippet().publishedAt().toLocalDate(),
                        "Virtual",
                        "Y",
                        0,
                        video.statistics().viewCount(),
                        "https://www.youtube.com/watch?v=" + video.id(),
                        "",
                        ""))
                .toList();
    }
}
