package dev.danvega.youtubecli.command;

import dev.danvega.youtubecli.model.Video;
import dev.danvega.youtubecli.service.VideoService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class YouTubeStatsCommands {

    private final VideoService videoService;

    public YouTubeStatsCommands(VideoService videoService) {
        this.videoService = videoService;
    }

    @ShellMethod(value = "All Videos")
    public void allVideos() {
        List<Video> videos = videoService.findAll();
        for (int i=0; i< videos.size(); ++i) {
            Video v = videos.get(i);
            System.out.println(i+1 + ": " + v.snippet().title() + " | " + v.id());
        }
    }

    @ShellMethod(value = "Recent Videos")
    public List<Video> recentVideos(@ShellOption(defaultValue = "5") Integer max) {
        return videoService.findRecent(max);
    }

    @ShellMethod(value = "Tasha Report")
    public Video tashaReport(@ShellOption() String videoId) {
        return null;
    }

    // get video details by id

    // find all videos by year | current y

    // find all videos by date range

}
