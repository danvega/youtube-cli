package dev.danvega.youtubecli.command;

import dev.danvega.youtubecli.model.TeamTabRow;
import dev.danvega.youtubecli.model.Video;
import dev.danvega.youtubecli.service.CommandService;
import dev.danvega.youtubecli.service.ReportService;
import dev.danvega.youtubecli.service.VideoService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.TableBuilder;

import java.time.LocalDateTime;
import java.util.List;

@ShellComponent
public class YouTubeStatsCommands {

    private final VideoService videoService;
    private final CommandService commandService;
    private final ReportService reportService;

    public YouTubeStatsCommands(VideoService videoService, CommandService commandService, ReportService reportService) {
        this.videoService = videoService;
        this.commandService = commandService;
        this.reportService = reportService;
    }

    @ShellMethod(key = "all", value = "List all videos on the channel.")
    public void all() {
        List<Video> videos = videoService.findAll();
        TableBuilder tableBuilder = commandService.listToArrayTableModel(videos);
        System.out.println(tableBuilder.build().render(120));
    }

    @ShellMethod(key = "recent", value = "List recent videos by max count.")
    public void recent(@ShellOption(defaultValue = "5") Integer max) {
        List<Video> videos = videoService.findRecent(max);
        TableBuilder tableBuilder = commandService.listToArrayTableModel(videos);
        System.out.println(tableBuilder.build().render(120));
    }

    @ShellMethod(key = "filter-by-year", value = "Filter videos by year.")
    public void byYear(@ShellOption(defaultValue = "2023") Integer year) {
        List<Video> videos = videoService.findAllByYear(year);
        TableBuilder tableBuilder = commandService.listToArrayTableModel(videos);
        System.out.println(tableBuilder.build().render(120));
    }

    @ShellMethod(key = "report", value = "Run a report based on all the videos for the current year.")
    public void report() {
        List<Video> videos = videoService.findAllByYear(LocalDateTime.now().getYear());
        List<TeamTabRow> rows = reportService.videosToTeamTabRows(videos);
        rows.forEach(TeamTabRow::print);
    }


}
