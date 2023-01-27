package dev.danvega.youtubecli.command;

import dev.danvega.youtubecli.model.TeamTabRow;
import dev.danvega.youtubecli.model.Video;
import dev.danvega.youtubecli.service.CommandService;
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

    public YouTubeStatsCommands(VideoService videoService, CommandService commandService) {
        this.videoService = videoService;
        this.commandService = commandService;
    }

    @ShellMethod(value = "All Videos")
    public void all() {
        List<Video> videos = videoService.findAll();
        TableBuilder tableBuilder = commandService.listToArrayTableModel(videos);
        System.out.println(tableBuilder.build().render(120));
    }

    @ShellMethod(value = "Recent Videos")
    public void recent(@ShellOption(defaultValue = "5") Integer max) {
        List<Video> videos = videoService.findRecent(max);
        TableBuilder tableBuilder = commandService.listToArrayTableModel(videos);
        System.out.println(tableBuilder.build().render(120));
    }

    @ShellMethod(value = "Report")
    public void report() {
        List<Video> videos = videoService.findAllByYear(LocalDateTime.now().getYear());
        List<TeamTabRow> rows = commandService.videosToTeamTabRows(videos);
        rows.forEach(TeamTabRow::print);
    }


}
