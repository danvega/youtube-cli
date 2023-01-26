package dev.danvega.youtubecli.command;

import dev.danvega.youtubecli.model.TeamTabRow;
import dev.danvega.youtubecli.model.Video;
import dev.danvega.youtubecli.service.VideoService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;

import java.time.LocalDateTime;
import java.util.List;

@ShellComponent
public class YouTubeStatsCommands {

    private final VideoService videoService;

    public YouTubeStatsCommands(VideoService videoService) {
        this.videoService = videoService;
    }

    @ShellMethod(value = "All Videos")
    public void all() {
        List<Video> videos = videoService.findAll();
        TableBuilder tableBuilder = listToArrayTableModel(videos);
        System.out.println(tableBuilder.build().render(120));
    }

    @ShellMethod(value = "Recent Videos")
    public void recent(@ShellOption(defaultValue = "5") Integer max) {
        List<Video> videos = videoService.findRecent(max);
        TableBuilder tableBuilder = listToArrayTableModel(videos);
        System.out.println(tableBuilder.build().render(120));
    }

    @ShellMethod(value = "Report")
    public void report() {
        List<Video> videos = videoService.findAllByYear(LocalDateTime.now().getYear());
        List<TeamTabRow> rows = videos.stream()
                .map(video -> new TeamTabRow(video.snippet().title(),
                        "YouTube",
                        video.snippet().publishedAt().toLocalDate(),
                        video.snippet().publishedAt().toLocalDate(),
                        "Virtual",
                        "Y",
                        video.statistics().viewCount(),
                        video.statistics().viewCount(),
                        video.snippet().description(),
                        "n/a",
                        "n/a"))
                .toList();

        rows.forEach(TeamTabRow::print);
    }



    private TableBuilder listToArrayTableModel(List<Video> videos) {
        ArrayTableModel model = new ArrayTableModel(videos.stream()
                .map(v -> new String[]{v.id(), v.snippet().title(), v.snippet().publishedAt().toString()})
                .toArray(String[][]::new));
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light_double_dash);
        return tableBuilder;
    }

}
